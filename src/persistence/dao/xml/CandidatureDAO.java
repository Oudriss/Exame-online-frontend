package persistence.dao.xml;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import beans.Candidature;
import utils.Properties;


public class CandidatureDAO extends XmlDAO{

	
	
	
	public  CandidatureDAO() throws JAXBException {
		super();
		context = JAXBContext.newInstance(Candidature.class);
		marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		unmarshaller = context.createUnmarshaller();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean addObject(Object o) {
		// TODO Auto-generated method stub
		Candidature cand = (Candidature) o;
		try {
			String path =  Properties.XML_DESTINATION_2+"/"+(cand.examen.getDate().getYear()+1900)+"/"+(cand.examen.getDate().getMonth()+1)+"/";
			new File(path).mkdirs();
			System.out.println(path+cand.examen.getCode()+"_"+cand.utilisateur.getCin());
			marshaller.marshal(cand,new File(path+cand.examen.getCode()+"_"+cand.utilisateur.getCin()+".xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean modifyObject(Object o_new) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeObject(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Candidature> getAllObject() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public ArrayList<Candidature> getAllObjectByCIN(String cin) {
		// TODO Auto-generated method stub
		ArrayList<Candidature> cands = new ArrayList<Candidature>();
		try {
			ArrayList<File> files = new ArrayList<File>();
			System.out.println(cin+"***************************************");
			this.listf(Properties.XML_DESTINATION_2+"/",files,cin);
			for(File file : files){
				System.out.println(file.getAbsolutePath()+"==="+file.getName());
				cands.add((Candidature) unmarshaller.unmarshal(file));
			}	
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cands;
	}
	

	public Candidature getObjectByCinCode(String cin,int code,String annee,String mois){
		Candidature cands = null;
		try {
			ArrayList<File> files = new ArrayList<File>();
			this.listf(Properties.XML_DESTINATION_2+"/"+annee+"/"+mois,files,cin,code);
			for(File file : files){
				System.out.println(file.getAbsolutePath()+"==="+file.getName());
				cands = (Candidature) unmarshaller.unmarshal(file);
			}	
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cands;
	}

	
	private void listf(String directoryName, ArrayList<File> files,String cin) {
	    File directory = new File(directoryName);
	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    for (File file : fList) {
	        if (file.isFile() && file.getName().contains(cin) ) {
	            files.add(file);
	        } else if (file.isDirectory()) {
	            listf(file.getAbsolutePath(), files,cin);
	        }
	    }
	}
	
	private void listf(String directoryName, ArrayList<File> files,String cin, int code) {
	    File directory = new File(directoryName);
	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    for (File file : fList) {
	        if (file.isFile() && file.getName().contains(code+"_"+cin) ) {
	            files.add(file);
	        } else if (file.isDirectory()) {
	            listf(file.getAbsolutePath(), files,cin);
	        }
	    }
	}


}
