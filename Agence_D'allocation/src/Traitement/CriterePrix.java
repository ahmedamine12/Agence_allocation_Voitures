package Traitement;
import java.io.Serializable;


public class CriterePrix implements Critere , Serializable {
	/**
	 * 
	 */
	private int PxPerDay;
	public CriterePrix(int px){
		PxPerDay=px;
	}
	/**
	 * 
	 * @param v
	 */
	public boolean estSatisfaitPar(Voiture v){
		
		return (PxPerDay > v.getPxLocPerDay());
	}

}
