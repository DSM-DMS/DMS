//package test;
//
//import java.sql.SQLException;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import com.dms.planb.support.ActionPerformer;
//
//public class ClientDescriber {
//	public static void main(String[] args) throws JSONException {
//		JSONObject object = new JSONObject().put("id", "testid3").put("password", "testpassword").put("session_key", "testkey").put("permission", 1);
//		
//		try {
//			ActionPerformer.doInsert(100, object);
//		} catch (JSONException | SQLException e) {
//			e.printStackTrace();
//		}
//	}
//}
