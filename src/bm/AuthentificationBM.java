package bm;

import javax.xml.bind.JAXBException;

import beans.Utilisateur;
import persistence.dao.mysql.UtilisateurDAO;

public class AuthentificationBM implements IAuthentification{
	/**
	 * DAO
	 */
	UtilisateurDAO utilisateurDAO;
	/**
	 * Constructeur
	 * @throws JAXBException
	 */
	public AuthentificationBM() throws JAXBException{
		utilisateurDAO = new UtilisateurDAO();
	}
	
	@Override
	public boolean deconnecter(Utilisateur u) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * La methode revoi L'utilisateur si il existe , sinon null
	 */
	@Override
	public Utilisateur authentifier(String login, String password) {
		// TODO Auto-generated method stub
		Utilisateur user = new Utilisateur();
		user.setPassword(password);
		user.setLogin(login);
		return utilisateurDAO.isExisted(user);
	}
	
	

}
