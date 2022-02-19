package Traitement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.TreeMap;


public class Agence implements Serializable {

	/**
	 * 
	 */
	/**
	 * 
	 */
	private List<Voiture> Liste_Voiture;
	private List<Client> Liste_Client;
	private HashMap<Client, Voiture> Gestion_Location;
	//private TreeMap<Client, Voiture> Gestion_Location;
	public Voiture m_Voiture;
	
	public Agence()
	{
		Liste_Voiture=new LinkedList<>();
		Liste_Client=new LinkedList<>();
		Gestion_Location=new HashMap<Client,Voiture>();
		//Gestion_Location=new TreeMap<Client,Voiture>();
	}
	
	public Agence(List<Voiture> My_List){
		Liste_Voiture=My_List;
		Gestion_Location=new HashMap<Client,Voiture>();
		//Gestion_Location=new TreeMap<Client,Voiture>();
	}
	
	public void AddVoiture(Voiture A)
	{
		Liste_Voiture.add(A);
	}
	
	
	public void AddClient(Client A) throws Exception
	{
		if (Liste_Client.contains(A)) throw new Exception();
		Liste_Client.add(A);
	}
	
	
	public void SupprimerVoiture(Voiture A)
	{
		Liste_Voiture.remove(A);
	}
	public void SupprimerClient(Client A)
	{
		Liste_Client.remove(A);
	}
	
	public List<Object[]> Imprimer_Voiture()
	{
		List<Object[]> my_list=new LinkedList<Object[]>();
		for (Iterator<Voiture> i=this.Liste_Voiture.iterator() ; i.hasNext();)
		{
			Voiture voiture=i.next();
			System.out.println(voiture);
			my_list.add(voiture.AfficherSurObjet());
		}
		return my_list;
	}
	
	public List<Object[]> Imprimer_Voiture_Dispo()
	{
		List<Object[]> my_list=new LinkedList<Object[]>();
		for (Iterator<Voiture> i=this.Liste_Voiture.iterator() ; i.hasNext();)
		{
			Voiture voiture=i.next();
			System.out.println(voiture);
			if (this.estLoue(voiture)==false)
			my_list.add(voiture.AfficherSurObjet());
		}
		return my_list;
	}
	
	public List<Object[]> List_Of_Client()
	{
		List<Object[]> my_list=new LinkedList<Object[]>();
		for (Iterator<Client> i=this.Liste_Client.iterator(); i.hasNext();)
		{
			Client client=i.next();
			System.out.println(client);
			my_list.add(client.AfficherSurObjet());
		}
		return my_list;
	}
	
	public List<Object[]> List_Of_Client_Voiture()
	{
		List<Object[]> my_list=new LinkedList<Object[]>();
		 for (Iterator<Client> i = this.Gestion_Location.keySet().iterator(); i.hasNext(); ) {
		       Client temp=i.next(); Object[] row1=temp.AfficherSurObjet();
		       Voiture vv=Gestion_Location.get(temp); Object[] row2=vv.AfficherSurObjet();
		       Object[] row =new Object[4];
		       row[0]=row2[0];
		       row[1]=row2[1]+ " ( "+row2[2]+" ) "+row2[3]+"/"+row2[4]+"DH";
		       row[2]=row1[0];
		       row[3]=row1[3]+" "+row1[1]+ " "+row1[2];
		       my_list.add(row);
		   }
		 return my_list;
	}
	
	public void loueVoiture ( Client client , Voiture v)
	{
		
		if (Liste_Voiture.contains(v)==false) System.out.println("^\\^ Voiture N'Existe Pas ^\\^");
		else if (estLoue(v)==true) System.out.println("^\\^ Voiture Louer Mnt ^\\^");
		//else if (estLoueur(client)==true) System.out.println("^\\^ 1 location possible ^\\^");
		else Gestion_Location.put(client, v);
	}
	
	public boolean estLoueur (Client client)
	{
		return Gestion_Location.containsKey(client);
	}
	
	
	
	public void rendVoiture (Client client)
	{
		Gestion_Location.remove(client);
	}
	
	public void rendVoitureClient (Voiture voiture,Client client)
	{
		
		System.out.println(Gestion_Location.remove(client, voiture));
	}
	
	public boolean estLoue (Voiture v)
	{
		return Gestion_Location.containsValue(v);
	}
	
	public Iterator<Voiture> lesVoituresLouees()
	{	
		return Gestion_Location.values().iterator();
	}
	
	
	public Client ClientofVoiture(Voiture v)
	{
		for (Entry<Client, Voiture> entry : this.Gestion_Location.entrySet()) {
	        if (Objects.equals(v, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	public void Imprimer_TableHashage()
	{
		 for (Iterator<Client> i = this.Gestion_Location.keySet().iterator(); i.hasNext(); ) {
		       Client temp=i.next();
		       System.out.println(temp + " = " + Gestion_Location.get(temp));
		   }
	}
	
	
	public int Nmb_Voiture ()
	{
		return Liste_Voiture.size();
		
	}

	public Voiture find_voiture_byId(int id)
	{
		
		for (Iterator<Voiture> i=this.Liste_Voiture.iterator() ; i.hasNext();)
		{
			Voiture v=i.next();
			if (v.getId()==id) return v;
		}
		return null;
	}
	
	public Client find_Client_byCIN(String CIN)
	{
		
		for (Iterator<Client> i=this.Liste_Client.iterator() ; i.hasNext();)
		{
			Client v=i.next();
			if (v.getCIN().equals(CIN)==true) return v;
		}
		;
		return null;
	}
	
	
	public int max_id_voiture()
	{
		int idmax=0;
		for (Iterator<Voiture> i=this.Liste_Voiture.iterator() ; i.hasNext();)
		{
			Voiture v=i.next();
			if (v.getId() > idmax) idmax=v.getId();
		}
		return idmax;
	}
	
	
	public void afficheSelection(Critere C){
		Iterator<Voiture> My_List=(Iterator<Voiture>)selectionner(C);
		while(My_List.hasNext()){
			System.out.println(My_List.next());
		}
		
	}

	public Iterator<Voiture> selectionner(Critere my_Critere){
		LinkedList<Voiture> ListSelect=new LinkedList<Voiture>();
		Iterator<Voiture> i = Liste_Voiture.iterator();
		Voiture temp;
		while(i.hasNext()){
			temp=i.next();
			if (my_Critere.estSatisfaitPar(temp)==true) ListSelect.add(temp);
		}
		return ListSelect.iterator();
	}

	
	public Agence LireSerialize() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		try {
			ObjectInputStream oos = new ObjectInputStream(new FileInputStream("fichierObjet"));
			Agence b=(Agence)oos.readObject();
			oos.close();
			return b;
		}
		catch (FileNotFoundException e)
		{
			return new Agence();
		}
	}
	
	public void EcrireSerialize() throws FileNotFoundException, IOException
	{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("fichierObjet"));
		try {
			oos.writeObject(this);
			oos.flush();
		} finally {
			oos.close();
		}
	}
}
