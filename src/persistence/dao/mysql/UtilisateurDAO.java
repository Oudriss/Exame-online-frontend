package persistence.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import beans.Utilisateur;
import persistence.dao.AbstractDAO;
import persistence.dao.xml.CandidatureDAO;
import utils.MD5Password;

public class UtilisateurDAO extends AbstractDAO {

	public CandidatureDAO candidatureDAO;
	
	public UtilisateurDAO() throws JAXBException {
		// TODO Auto-generated constructor stub
		candidatureDAO = new CandidatureDAO();
	}
	
	@Override
	public boolean addObject(Object o) {
		Utilisateur utilisateur = (Utilisateur) o;
			String cmd  = "insert into UTILISATEURS(CIN,NOM,PRENOM,EMAIL,GENRE,ADDRESSE,LOGIN,PASSWORD) "
					+ "VALUES ('"+utilisateur.getCin()+"','"+utilisateur.getNom()+
					"','"+utilisateur.getPrenom()+"','"+utilisateur.getEmail()+"','"+utilisateur.getGenre()+"','"+utilisateur.getAdresse()+
					"','"+utilisateur.getLogin()+"','"+utilisateur.getPassword()+"')";
			if( Connexion.update(cmd) > 0) return true;
			else return false;
	}

	@Override
	public boolean modifyObject(Object o_new) {
		boolean res = false;
		
			Utilisateur utilisateur2 = (Utilisateur) o_new;
			System.out.println("DAAAAAAAAAAAOOOO+ "+utilisateur2.toString());
			Connection conn = Connexion.getConnection();
			try {
				PreparedStatement ps = conn.prepareStatement("update UTILISATEURS SET CIN = ?,NOM = ?,PRENOM = ?,EMAIL = ?,GENRE = ?,ADDRESSE = ?,LOGIN = ?,PASSWORD = ? where ID = ? "
						+ " and ROLE like 'USER'");
				ps.setString(1, utilisateur2.getCin());
				ps.setString(2, utilisateur2.getNom());
				ps.setString(3, utilisateur2.getPrenom());
				ps.setString(4, utilisateur2.getEmail());
				ps.setString(5, utilisateur2.getGenre()+"");
				ps.setString(6, utilisateur2.getAdresse());
				ps.setString(7, utilisateur2.getLogin());
				System.out.println("login *** "+utilisateur2.getLogin());
				ps.setString(8, utilisateur2.getPassword());
				System.out.println("password *** "+utilisateur2.getPassword());
				ps.setInt(9, utilisateur2.getId());
				System.out.println("IDDDDDDDDDDDD ********* "+utilisateur2.getId());
				ps.executeUpdate();
				ps.close();
				res = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return res;
	}

	@Override
	public boolean removeObject(Object o) {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Utilisateur> getAllObject()
	{
		ArrayList<Utilisateur> utils = new ArrayList<>();
		Connection conn = Connexion.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM UTILISATEURS where ROLE like 'USER'");
			ResultSet rs= ps.executeQuery();
			
			while(rs.next())
			{
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setId(rs.getInt("ID"));
				utilisateur.setCin(rs.getString("CIN"));
				utilisateur.setNom(rs.getString("NOM"));
				utilisateur.setPrenom(rs.getString("PRENOM"));
				utilisateur.setEmail(rs.getString("EMAIL"));
				utilisateur.setGenre(rs.getString("GENRE").charAt(0));
				utilisateur.setAdresse(rs.getString("ADDRESSE"));
				utilisateur.setLogin(rs.getString("LOGIN"));
				utilisateur.setPassword(rs.getString("PASSWORD"));
				utilisateur.candidats = candidatureDAO.getAllObjectByCIN(utilisateur.getCin());
				utils.add(utilisateur);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return utils;
	}
	
	public Utilisateur isExisted(Utilisateur user){
				String cmd = "select * from UTILISATEURS where LOGIN like '"+user.getLogin()+"' AND PASSWORD like '"+MD5Password.getEncodedPassword(user.getPassword())+"'"
						+ "AND ROLE like 'USER'";
				ResultSet rs;
				try {
					rs = Connexion.select(cmd);
					if(rs.next()) {
						user.setAdresse(rs.getString("ADDRESSE"));
						user.setCin(rs.getString("CIN"));
						user.setEmail(rs.getString("email"));
						user.setGenre(rs.getString("GENRE").charAt(0));
						user.setNom(rs.getString("NOM"));
						user.setPrenom(rs.getString("PRENOM"));
						return user;
					}
					else return null;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
				
	}
}
