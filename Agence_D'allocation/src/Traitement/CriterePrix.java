package Traitement;
import java.io.Serializable;

/**
 * @author ERROUISSI
 * @version 1.0
 * @created 25-déc.-2016 22:07:36
 */
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