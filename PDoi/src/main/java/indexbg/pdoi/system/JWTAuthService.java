package indexbg.pdoi.system;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.oauth2.BaseJWTAuthService;
import com.indexbg.system.utils.HTTPUtils;

import indexbg.pdoi.db.dao.SubscriptionDAO;

@Path("/auth")
public class JWTAuthService extends BaseJWTAuthService {

	static final Logger LOG = Logger.getLogger(JWTAuthService.class);

	@Override
	protected void loadUserData(HttpSession session, JsonObject json) {
		UserData userData = new UserData(); 
		userData.setUserId(json.get("id").getAsLong());
		userData.setLoginName(json.get("login_name").getAsString());
		userData.setLiceNames(json.get("names").getAsString());
		
		if(json.get("model")!=null && json.get("model").isJsonPrimitive()){
			try {
				Long model = json.get("model").getAsLong();
				userData.setUserModel(model);
			} catch (Exception e) {
				userData.setUserModel(null);
			}
		}
		
		JsonArray roles = json.get("roles").getAsJsonArray();
		
		HashMap<String, HashMap<String,Boolean>> rolesMap = new HashMap<>();
		for(JsonElement je: roles){
			HashMap<String,Boolean> tempMap = new HashMap<>();
			String key = je.getAsJsonObject().get("key").getAsString();
			JsonArray tempArray = je.getAsJsonObject().get("values").getAsJsonArray();
			for(JsonElement je2: tempArray){
				tempMap.put(je2.getAsString(), true);
			}
			rolesMap.put(key, tempMap);
		}
		LOG.info("USER ROLES: " + rolesMap.toString());
		userData.setAccessValues(rolesMap);
		
		if(json.get("type")!=null && json.get("type").isJsonPrimitive()){
			try {
				Long typeUser = json.get("type").getAsLong();
				userData.setTypeUser(typeUser);
			} catch (Exception e) {
				userData.setTypeUser(null);
			}
		}
		
		if(json.get("email")!=null){
			userData.setEmailUser(json.get("email").getAsString());
		}
		
		if(json.get("org_code")!=null && json.get("org_code").isJsonPrimitive()){
			try {
				Long orgCode = json.get("org_code").getAsLong();
				userData.setCodeOrg(orgCode);
			} catch (Exception e) {
				userData.setCodeOrg(null);
			}
		}
		
		userData.setSubscriptions(new HashMap<Long,String>()); 
		if(userData.getTypeUser().longValue() == Constants.CODE_ZNACHENIE_TIP_POTR_VANSHEN){ 
			//zarejdame na potrebitelite zaqwleniqta za koito sa se abonirali
			try {
				userData.setSubscriptions(new SubscriptionDAO(userData.getUserId()).findSubscriptionByUserId(userData.getUserId()));
			} catch (DbErrorException e) {
				LOG .error(e.getMessage(),e);
			} catch (Exception e) {
				LOG .error(e.getMessage(), e);
			} finally {
				JPA.getUtil().closeConnection();
			}
			
		} else if(userData.getTypeUser().longValue() == Constants.CODE_ZNACHENIE_TIP_POTR_VATR){
			
			userData.setTypeUser(Constants.CODE_ZNACHENIE_TYPE_USER_MODERATOR);
			
			if(userData.hasAccess(7L, 42L)){
				userData.setTypeUser(Constants.CODE_ZNACHENIE_TIP_POTR_VATR);
			}
			
		}
		
		session.setAttribute("userData", userData);
		
	}


}
