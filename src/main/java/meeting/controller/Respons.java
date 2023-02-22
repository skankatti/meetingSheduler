package meeting.controller;

public class Respons<T> {
	
	/** The message */
	private String message;

	/** The data. */
	private T data;

	public Respons() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Respons(String message, T data) {
		super();

		this.message = message;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	
	

}
