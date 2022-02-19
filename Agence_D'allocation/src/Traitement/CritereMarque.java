package Traitement;
import java.io.Serializable;


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
