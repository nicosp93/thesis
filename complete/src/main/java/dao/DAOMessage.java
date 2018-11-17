package dao;



import beans.Message;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public interface DAOMessage  {
	
	public void register(Message message) throws Exception;
	
	
}