package Traitement;
import java.io.Serializable;

/**
 * @author ERROUISSI
 * @version 1.0
 * @created 25-déc.-2016 22:07:35
 */
public class CritereMarque implements Critere , Serializable {
	/**
	 * 
	 */
	private String MarqueVoiture;
	public CritereMarque(String Marque){
		MarqueVoiture=Marque;
	}
	/**
	 * 
	 * @param v
	 */
	public boolean estSatisfaitPar(Voiture v){
		return (v.getMarque().equals(MarqueVoiture));
	}

}