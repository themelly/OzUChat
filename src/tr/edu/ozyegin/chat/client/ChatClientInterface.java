package tr.edu.ozyegin.chat.client;
import java.awt.*;
public interface ChatClientInterface {

	/**
	 * Connect to a server located at the given address
	 * and listening at the given port number.
	 * 
	 * @param address The address of the chat server.
	 * @param port The port number of the chat server.
	 */
	public void connect(String address, int port);
	
	
	/**
	 * Register a listener to process messages incoming from the chat
	 * server. Note that this should be called only once, and calling
	 * it twice is an error.
	 * 
	 * @param chatMessageListener a {@code ChatMessageListener}
	 */
	public void registerChatMessageListener(ChatMessageListener chatMessageListener);
	
	
	/**
	 * Send a message to the server. The {@code message} must be an instance 
	 * of the following:
	 * 
	 * <br/>
	 * {@code LoginRequest}
	 * <br/>
	 * {@code MessageHistoryRequest}
	 * <br/>
	 * {@code MessageRequest}
	 * <br/>
	 * {@code PersonListRequest}
	 * 
	 * @param message The message to be sent to the server.
	 */
	public void sendMessage(Object message);
	
	
}
