package Traitement;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Scanner;

/**
 * @author ERROUISSI
 * @version 1.0
 * @created 25-déc.-2016 21:50:09
 */
public class Voiture implements Serializable {

	/**
	 * 
	 */
	private int AnneeProd;
	private String Marque;
	private String Nom;
	private int PxLocPerDay;
	private int id;

	public Voiture(){
			
	}
	public Voiture(int AnneeProd , String Marque , String Nom , int PxPerDay , int id){
		this.AnneeProd=AnneeProd;
		this.Marque=Marque;
		this.Nom=Nom;
		this.PxLocPerDay=PxPerDay;
		this.id=id;
	}
	
	public void ModifierVoiture(int AnneeProd , String Marque , String Nom , int PxPerDay){
		this.AnneeProd=AnneeProd;
		this.Marque=Marque;
		this.Nom=Nom;
		this.PxLocPerDay=PxPerDay;
	}
	
	static public Voiture SasirVoiture(int Id_Voiture)
	{
		Voiture v=new Voiture();
		Scanner sc=new Scanner(System.in);
		System.out.print("\n\tEntrer la Marque de la voiture : ");
		v.Marque=sc.nextLine();
		while (v.Marque.length()==0)
	    {
			System.out.println("Chaine vide ! Entrer a nouveau : ");
	    	v.Marque=sc.nextLine();
	    }
		System.out.print("\n\tEntrer le Nom de la voiture : ");
		v.Nom=sc.nextLine();
		 while (v.Nom.length()==0)
	      {
	    	  System.out.println("Chaine vide ! Entrer a nouveau : ");
	    	  v.Nom=sc.nextLine();
	      }
		System.out.print("\n\tEntrer l'annee de production : ");
		while (!sc.hasNextInt()) {
            System.out.println("Int Invalide ! Entrer a nouveau : ");
            sc.next(); // this is important!
        }
		v.AnneeProd=sc.nextInt();
		System.out.print("\n\tEntrer le prix de location par jours : ");
		while (!sc.hasNextDouble()) {
            System.out.println("Int Invalide ! Entrer a nouveau : ");
            sc.next(); // this is important!
        }
		v.PxLocPerDay=sc.nextInt();
		v.id=Id_Voiture;
		return v;
	}
	public int getId()
	{
		return id;
	}
	public String getMarque() {
		return Marque;
	}

	public int getPxLocPerDay() {
		return PxLocPerDay;
	}
	
	public int getAnneeProd() {
		return AnneeProd;
	}
	
	public boolean equals(Object A){
		
		return (this.id==((Voiture)A).id);
	}
	
	public String toString(){
		return "id : "+id+" | Marque : "+Marque+" | Nom Modele : "+Nom+" |AnneeProd : "+AnneeProd
				+ "|Prix de location par jour : "+PxLocPerDay;
	}
	
	public Object[] AfficherSurObjet(){
		Object[] row =new Object[5];
		row[0] = id;
        row[1] = Marque;
        row[2] = Nom;
        row[3] = AnneeProd;
        row[4] = PxLocPerDay;
		return row;
	}

	/**
	 * 
	 * @param A
	 */
	public boolean equals(Voiture A){
		return false;
	}

}