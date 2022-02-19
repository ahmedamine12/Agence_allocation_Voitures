package Traitement;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class InterCritere implements Serializable,Critere{

	/**
	 * 
	 */
	private List<Critere> List_Critere;
	
	public InterCritere(){
		List_Critere=new LinkedList<>();
	}
	public void addCritere(Critere c)
	{
		List_Critere.add(c);
	}
	public int nmbCritere()
	{
		return List_Critere.size();
	}
	public boolean estSatisfaitPar(Voiture A)
	{
		
		Iterator<Critere> My_iterator=List_Critere.iterator();
		while(My_iterator.hasNext())
		{
			
			if (My_iterator.next().estSatisfaitPar(A)==false) return false;
		}
		return true;
	}
	
}
