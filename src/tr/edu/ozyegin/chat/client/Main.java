package tr.edu.ozyegin.chat.client;

import tr.edu.ozyegin.chat.messages.LoginRequest;
import tr.edu.ozyegin.chat.messages.LoginResponse;
import tr.edu.ozyegin.chat.messages.MessageHistoryResponse;
import tr.edu.ozyegin.chat.messages.MessageResponse;
import tr.edu.ozyegin.chat.messages.PersonListRequest;
import tr.edu.ozyegin.chat.messages.PersonListResponse;

public class Main {

	public static void main(String[] args) throws Exception {

		MockChatClient client = new MockChatClient();
		
		
		
		client.registerChatMessageListener(new ChatMessageListener() {
			
			@Override
			public void personListResponseReceived(PersonListResponse personListResponse) {
				System.out.println("PEOPLE");
				
				for (String s : personListResponse.personList) {
					System.out.println("[" + s + "]");
				}
				
			}
			
			@Override
			public void messageResponseReceived(MessageResponse messageResponse) {
				System.out.println("[" + messageResponse.time + "] " + messageResponse.sender + " : " + messageResponse.message);
				
			}
			
			@Override
			public void messageHistoryResponseReceived(MessageHistoryResponse messageHistoryResponse) {
				System.out.println("PREVIOUS MESSAGES");
				
				for (MessageResponse m : messageHistoryResponse.messages) {
					messageResponseReceived(m);
				}
				
			}
			
			@Override
			public void loginResponseReceived(LoginResponse loginResponse) {
				System.out.println("Login: " + loginResponse.success);
				
			}
		});
		
		client.connect("localhost", 7777);
		
		LoginRequest loginRequest = new LoginRequest();
		
		loginRequest.username = "safkan";
		loginRequest.password = "safkan";
		

		client.sendMessage(loginRequest);

		PersonListRequest personListRequest = new PersonListRequest();
		
		
		client.sendMessage(personListRequest);
		
		
		Thread.sleep(15000);
		
	}

}
