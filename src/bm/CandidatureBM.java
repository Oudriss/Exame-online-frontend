package bm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.bind.JAXBException;

import beans.Candidature;
import beans.Examen;
import persistence.dao.xml.CandidatureDAO;
import utils.PDFGenerator;

public class CandidatureBM implements ICandidatureBM{
	/**
	 * DAO
	 */
	private CandidatureDAO candidatureDAO;
	/**
	 * Constructeur
	 * @throws JAXBException
	 */
	public CandidatureBM() throws JAXBException{
		candidatureDAO = new CandidatureDAO();
	}
	/**
	 * Renvoi toutes les candidatures
	 */
	public ArrayList<Candidature> getCandidatures(String cin){
		return candidatureDAO.getAllObjectByCIN(cin);
	}
	/**
	 * Renvoi les candidature qui ont l'examen avec cin ,code , anee est mois
	 */
	@Override
	public Candidature getCandidatureByCode(String cin, int code, String annee, String mois) {
		return candidatureDAO.getObjectByCinCode(cin, code, annee, mois);
	}
	/**
	 * Enregistrer les reponses
	 */
	public boolean saisirReponses(Candidature candidature,HashMap<Integer,String> reponses){
		candidature.setReponses(reponses);
		candidature.setValidation(true);
		return candidatureDAO.addObject(candidature);
	}
	/**
	 * Les candidatures qui ne sont pas encore validé
	 * @param cin
	 * @return
	 */
	public ArrayList<Candidature> getNouvellesCandidatures(String cin){
		ArrayList<Candidature> nouvelles = new ArrayList<Candidature>();
		ArrayList<Candidature> AllCands = this.getCandidatures(cin);
		for( int i = 0 ; i < AllCands.size() ; i++){
			if( !AllCands.get(i).isValidation() ) nouvelles.add(AllCands.get(i));
		}
		return nouvelles;
	}
	/**
	 * Renvoi les candidatures validés
	 */
	public ArrayList<Candidature> getAncienCandidatures(String cin){
		ArrayList<Candidature> historiques = new ArrayList<Candidature>();
		ArrayList<Candidature> AllCands = this.getCandidatures(cin);
		for( int i = 0 ; i < AllCands.size() ; i++){
			if( AllCands.get(i).isValidation() ) historiques.add(AllCands.get(i));
		}
		return historiques;
	}
	
	@Override
	public void exportPdf(String cin, int code, String annee, String mois) throws Exception{
		PDFGenerator.xmlToPdf(candidatureDAO.getObjectByCinCode(cin, code, annee, mois).getExamen(),"PDFS");
	}
	
	public float calculerNote(Candidature candidature){
		float note = 0;
		Examen exam = candidature.getExamen();
		for( Entry<Integer, String> e : candidature.getReponses().entrySet() ){
			if( e.getValue() != null)
				System.out.println(e.getValue()+" ==== "+exam.getQuestions().get(e.getKey()).getVraiReponse());
				if( e.getValue().equals(exam.getQuestions().get(e.getKey()).getVraiReponse()))
					note = note + exam.getQuestions().get(e.getKey()).getNote();
		}
		return note;
	}

}
