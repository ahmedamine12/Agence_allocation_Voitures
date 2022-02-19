package Traitement;
import java.io.Serializable;


public class CritereAnnee implements Critere , Serializable{

	private int Annee;
	public CritereAnnee(int Annee){
		this.Annee=Annee;
	}
	/**
	 * 
	 * @param v
	 */
	public boolean estSatisfaitPar(Voiture v){
		return (Annee == v.getAnneeProd());
	}

}
