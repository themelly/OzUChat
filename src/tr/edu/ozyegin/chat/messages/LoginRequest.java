package tr.edu.ozyegin.chat.messages;


/**
 * <p>
 * This is sent from the client to the server. The server
 * will normally respond with a {@code LoginResponse} object.
 * </p>
 * 
 * @author safkan
 *
 */
public class LoginRequest {
	/**
	 * The user name.
	 */
	public String username;
	
	/**
	 * The password for the given user name.
	 */
	public String password;

}
