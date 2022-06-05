package tr.edu.ozyegin.chat.messages;


/**
 * <p>
 * This is sent from the server as a response
 * to a {@code LoginRequest} message, signifying
 * whether the login was successful or not.
 * </p>
 * 
 * @author safkan
 *
 */
public class LoginResponse {
	/**
	 * {@code true} if login successful, {@code false} otherwise.
	 */
	public boolean success;
	
}
