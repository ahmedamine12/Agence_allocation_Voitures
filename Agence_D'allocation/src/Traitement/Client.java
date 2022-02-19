package Traitement;
import java.io.Serializable;
import java.util.Scanner;


public class Client implements Comparable , Serializable {

	private String Nom;
	private String Prenom;
	private String CIN;
	static private String[] Civilite = {"M.","Mlle","Mme"};
	int Num_Civilite;
	
	public Client(){
	}
	
	public Object[] AfficherSurObjet(){
		Object[] row =new Object[4];
		row[0] = CIN;
        row[1] = Nom;
        row[2] = Prenom;
        row[3] = Civilite[Num_Civilite-1];
 
		return row;
	}
	
	public Client(String Nom , String Prenom , String CIN , int  Civilite)
	{
		this.Nom=Nom;
		this.Prenom=Prenom;
		this.CIN=CIN;
		this.Num_Civilite=Civilite;
	}
	
	public void ModifierClient(String Nom , String Prenom , String CIN , int  Civilite)
	{
		this.Nom=Nom;
		this.Prenom=Prenom;
		this.CIN=CIN;
		this.Num_Civilite=Civilite;
	}
	public boolean equals(Object c)
	{
		
		return (((Client)c).CIN.equals(this.CIN) );
	}
	
	public String getCIN()
	{
		return CIN;
	}
	
	public int compareTo(Object c){
		return (Nom+Prenom).compareTo((((Client)(c)).Nom+((Client)(c)).Prenom));
	}
	
	public String toString(){
		return "CIN : "+CIN+" |Nom :"+Civilite[Num_Civilite-1]+" "+Nom+" |Prenom :"+Prenom ;
	}
	
	
	
	static public Client SaisirClient()
	{
		Client v=new Client();
		Scanner sc=new Scanner(System.in);
		System.out.print("\n\tEntrer le nom du client : ");
		v.Nom=sc.nextLine();
		while (v.Nom.length()==0)
	    {
			System.out.println("Chaine vide ! Entrer a nouveau : ");
	    	v.Nom=sc.nextLine();
	    }
		System.out.print("\n\tEntrer le prenom du client : ");
		v.Prenom=sc.nextLine();
		 while (v.Prenom.length()==0)
	      {
	    	  System.out.println("Chaine vide ! Entrer a nouveau : ");
	    	  v.Prenom=sc.nextLine();
	      }
		 System.out.print("\n\tEntrer CIN du client : ");
		v.CIN=sc.nextLine();
		 while (v.Prenom.length()==0)
	      {
	    	  System.out.println("Chaine vide ! Entrer a nouveau : ");
	    	  v.CIN=sc.nextLine();
	      }
		 System.out.print("\n\tChoisisser l'une de ses  civilite  : \n - 1)M."
		 		+ "\n - 2)Mlle " +"\n - 3)Mme");
		 System.out.print("\n Entrer le numero de votre choix : ");
		 while (!sc.hasNextDouble()) {
	            System.out.println("Int Invalide ! Entrer a nouveau : ");
	            sc.next(); // this is important!
	        }
		v.Num_Civilite=sc.nextInt();
		return v;
	}
	
}
