package indexbg.pdoi.system;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.BaseUserData;


public class UserData extends BaseUserData {
	
	static final Logger LOGGER = LoggerFactory.getLogger(UserData.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 2989532705334584877L;

	
	/** тип потребител : външен /вътрешен*/
    private Long typeUser;
    
    /** електронна поща на потребител */
    private String emailUser;
    
    /** организация на потребителя*/
    private Long codeOrg;
    
    
    private Map<Long,String> subscriptions;
    
	public Long getTypeUser() {
		return typeUser;
	}


	public void setTypeUser(Long typeUser) {
		this.typeUser = typeUser;
	}


	public String getEmailUser() {
		return emailUser;
	}


	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}


	public Long getCodeOrg() {
		return codeOrg;
	}


	public void setCodeOrg(Long codeOrg) {
		this.codeOrg = codeOrg;
	}


	public Map<Long,String> getSubscriptions() {
		return subscriptions;
	}


	public void setSubscriptions(Map<Long,String> subscriptions) {
		this.subscriptions = subscriptions;
	}



   
}
