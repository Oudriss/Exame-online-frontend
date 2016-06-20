package bm;

import beans.Utilisateur;

public interface IAuthentification {

	public Utilisateur authentifier(String login,String password);
	public boolean deconnecter(Utilisateur u);
	
}
