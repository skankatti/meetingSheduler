package meeting.mailSender;

public class MailResponse {
	
	private String message;
	
	private boolean status;

	public String getMessageString() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
