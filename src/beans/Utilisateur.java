package beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * La class utilisateur
 * @author soufiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Utilisateur{
	

	/**
    * CIN
    */
	@XmlElement
   protected String cin;
   /**
    * Nom
    */
	@XmlTransient
	protected java.lang.String nom;
   /**
    * Prenom
    */
	@XmlTransient
	protected String prenom;
   /**
    * Email
    */
	@XmlTransient
	protected String email;
   /**
    * Genre
    */
	@XmlTransient
	protected Character genre;
   /**
    * Adresse
    */
	@XmlTransient
	protected String adresse;
	
	/**
	 * L'identifiant d'utilisateur
	 */
	@XmlTransient
   private int id;
   
   /**
    * L'association entre classe Utilisateur et Candidat
    */
	@XmlTransient
   public List<Candidature> candidats;
   /**
    * Login
    */
	@XmlTransient
   private String login;
   /**
    * Password
    */
	@XmlTransient
   private String password;
   
   /**
    * Constructeur par defaut
    */
	public Utilisateur() {
		super();
		this.candidats = new ArrayList<Candidature>();
	}
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * User1 == User2 si seulement si id et cin des utilisateurs ont egaux
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		if (!cin.equals(other.cin))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public java.lang.String getNom() {
		return nom;
	}
	public void setNom(java.lang.String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Character getGenre() {
		return genre;
	}
	public void setGenre(Character genre) {
		this.genre = genre;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public List<Candidature> getCandidats() {
		return candidats;
	}
	public void setCandidats(List<Candidature> candidats) {
		this.candidats = candidats;
	}
	
	

}