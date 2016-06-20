package persistence.dao;

import java.util.ArrayList;

public abstract class AbstractDAO {

	public AbstractDAO(){
		
	}
	
	public abstract boolean addObject(Object o);
	public abstract boolean modifyObject(Object o_new);
	public abstract boolean removeObject(Object o);
	public abstract <T> ArrayList<T> getAllObject();
}
