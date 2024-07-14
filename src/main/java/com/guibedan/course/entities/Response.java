package com.guibedan.course.entities;

public class Response<T> {

	private String message;
	private boolean success;
	private int status;
	private T content;
	
	public Response() {}
	
	public Response(String message, boolean success, int status, T content) {
		this.message = message;
		this.success = success;
		this.status = status;
		this.content = content;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Response [message=" + message + ", success=" + success + ", status=" + status + "]";
	}
	
}
