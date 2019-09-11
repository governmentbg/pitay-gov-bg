package indexbg.pdoi.system;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.DbErrorException;


public class MyHttpSessionListener implements HttpSessionListener {
	
	static final Logger	LOGGER	= LoggerFactory.getLogger(MyHttpSessionListener.class);
	
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		LOGGER.debug(getTime() + " (session 4) Created:");
		LOGGER.debug("ID=" + session.getId() + " MaxInactiveInterval="+ session.getMaxInactiveInterval());

		for (Enumeration<String> e = session.getAttributeNames(); e.hasMoreElements();) {
			String attName = e.nextElement();
			LOGGER.debug("<br/>" + attName);
		}

		// BaseSystemData sd = (BaseSystemData)
		// FacesContext.getCurrentInstance().getExternalContext()
		// .getApplicationMap().get("systemData");

		// BaseSystemData sd = (BaseSystemData)
		// FacesContext.getCurrentInstance().getELContext().getELResolver()
		// .getValue(FacesContext.getCurrentInstance().getELContext(), null,
		// "systemData");
		// if (sd==null)
		// if (LOGGER.isDebugEnabled()) LOGGER.debug("bbbb");
		// else
		// if (LOGGER.isDebugEnabled()) LOGGER.debug(sd.getRdbms());
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		LOGGER.debug("-------------------------------------");
		
		UserData ud = (UserData) session.getAttribute("userData");
		if (ud==null) {
			LOGGER.debug("UserData is null");
		} else if(ud.getUserId()==null) {
			LOGGER.debug("UserData.getUserId() is null");
		} else {
			LOGGER.debug("UserData is NOT null");
			LOGGER.debug("Current user :" + ud.getUserId());	
			
			try {
				
				JPA.getUtil().begin();
				new Locker().unlockObjects(ud.getUserId());
				JPA.getUtil().commit();	

			} catch (DbErrorException e) {
				JPA.getUtil().rollback();
				LOGGER.error(e.getMessage(), e);
			} catch (Exception e) {
				JPA.getUtil().rollback();
				LOGGER.error(e.getMessage(), e);
			} finally {
				JPA.getUtil().closeConnection();
			}
			
		}
		
		SystemData sd = (SystemData) session.getServletContext().getAttribute("systemData");
		if (sd == null) {
			LOGGER.debug("sd22 is null");
		} else {
			LOGGER.debug("sd22 is NOT null");
			// session has been invalidated and all session data
			// (except Id)is no longer available
			LOGGER.debug(getTime() + " (session) Destroyed:ID=" + session.getId());
		

			LOGGER.debug("Users Before-" + sd.getActiveUsers().getCount());

			//sd.removeActiveUser(se.getSession().getId());
			
			LOGGER.debug("Users After-" + sd.getActiveUsers().getCount());
		}

	}

	private String getTime() {
		return new Date(System.currentTimeMillis()).toString();
	}
	
	
	
}
