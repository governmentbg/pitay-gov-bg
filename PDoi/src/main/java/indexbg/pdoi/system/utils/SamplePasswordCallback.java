package indexbg.pdoi.system.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

/**
 * Използва се при извикването на сървисите eDelivery
 */
public class SamplePasswordCallback implements CallbackHandler {

	private Map<String, String> keyPasswords = new HashMap<>();

	private Map<String, String> userPasswords = new HashMap<>();

	public SamplePasswordCallback() {
		// some example user passwords
		this.userPasswords.put("user1", "user1pswd");
		this.userPasswords.put("admin", "adminpswd");
	}

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
			WSPasswordCallback pwcb = (WSPasswordCallback) callbacks[i];
			String id = pwcb.getIdentifier();
			String pass = null;
			switch (pwcb.getUsage()) {
			case WSPasswordCallback.USERNAME_TOKEN_UNKNOWN:
			case WSPasswordCallback.USERNAME_TOKEN:
				pass = this.userPasswords.get(id);
				pwcb.setPassword(pass);
				break;
			case WSPasswordCallback.SIGNATURE:
			case WSPasswordCallback.DECRYPT:
				pass = this.keyPasswords.get(id);
				pwcb.setPassword(pass);
				break;
			}
		}
	}
}
