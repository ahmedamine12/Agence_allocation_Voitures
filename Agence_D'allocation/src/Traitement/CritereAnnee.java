package Traitement;
import java.io.Serializable;

/**
 * @author ERROUISSI
 * @version 1.0
 * @created 25-déc.-2016 23:16:19
 */
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