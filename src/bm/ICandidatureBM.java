package bm;

import java.util.ArrayList;
import java.util.HashMap;

import beans.Candidature;
import beans.Utilisateur;

public interface ICandidatureBM {

	public ArrayList<Candidature> getCandidatures(String cin);
	public Candidature getCandidatureByCode(String cin,int code,String annee,String mois);
	public boolean saisirReponses(Candidature candidature,HashMap<Integer,String> reponses);
	public ArrayList<Candidature> getAncienCandidatures(String cin);
	public float calculerNote(Candidature candidature);
	public void exportPdf(String cin, int code, String annee, String mois) throws Exception;
	
}
