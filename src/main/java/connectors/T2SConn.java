/*
 * T2SConn.java
 * @author: jeff
*/

package connectors;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class T2SConn {

	private String username;
	private String password;

	public T2SConn() {
		try {
			configParameters();
		} catch(Exception e) {
			System.err.println("ERROR IN T2SConn.java");
			e.printStackTrace(System.err);
		}

	}

	private void configParameters() throws Exception {
		Map<String, String> env = System.getenv();

		if (env.containsKey("VCAP_SERVICES")) {
            // we are running on cloud foundry, let's grab the service details from vcap_services
            JSONParser parser = new JSONParser();
            JSONObject vcap = (JSONObject) parser.parse(env.get("VCAP_SERVICES"));
            JSONObject service = null;

            // We don't know exactly what the service is called, but it will contain "text_to_speech"
            for (Object key : vcap.keySet()) {
                String keyStr = (String) key;
                if (keyStr.toLowerCase().contains("text_to_speech")) {
                    service = (JSONObject) ((JSONArray) vcap.get(keyStr)).get(0);
                    break;
                }
            }

            if (service != null) {
                JSONObject creds = (JSONObject) service.get("credentials");
                this.username = (String) creds.get("username");
                this.password = (String) creds.get("password");
            } else {
				throw new Exception("No T2S service found. Make sure you have bound the correct services to your app.");
			}
        } else {
			throw new Exception("Environment Variable VCAP_SERVICES not found. Check your environment.");
		}
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
