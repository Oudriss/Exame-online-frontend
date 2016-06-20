package beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Classe Candidat
 * @author soufiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Candidature implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Liste des reponses selectionner par le condidat
	 */
	@XmlElement
   private Map<Integer,String> reponses;
   /**
    * Validation
    */
	private boolean Validation;
	/**
    * Association entre Examen et Candidat
    */
	@XmlElement
   public Examen examen;
   /**
    * Association Utilisateur et Candidat
    */
	@XmlElement
   public Utilisateur utilisateur;
   
   
   /**
    * Constructeur par défaut
    */
   public Candidature() {
		super();
		this.reponses = new HashMap<Integer, String>();
	}
   

   public Map<Integer, String> getReponses() {
	   return reponses;
   }

	public void setReponses(Map<Integer, String> reponses) {
		this.reponses = reponses;
	}


	public Examen getExamen() {
		return examen;
	}


	public void setExamen(Examen examen) {
		this.examen = examen;
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	public boolean isValidation() {
		return Validation;
	}


	public void setValidation(boolean validation) {
		Validation = validation;
	}

	

}