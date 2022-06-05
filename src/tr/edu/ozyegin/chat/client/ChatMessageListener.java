package tr.edu.ozyegin.chat.client;

import tr.edu.ozyegin.chat.messages.LoginResponse;
import tr.edu.ozyegin.chat.messages.MessageHistoryResponse;
import tr.edu.ozyegin.chat.messages.MessageResponse;
import tr.edu.ozyegin.chat.messages.PersonListResponse;

public interface ChatMessageListener {
	public void loginResponseReceived(LoginResponse loginResponse);
	public void messageHistoryResponseReceived(MessageHistoryResponse messageHistoryResponse);
	public void messageResponseReceived(MessageResponse messageResponse);
	public void personListResponseReceived(PersonListResponse personListResponse);
	
}
