package br.com.ecommerce.api.services.excepitions;

public class ObjectNotFoundExecpition  extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundExecpition(String msg) {
		super(msg);
	}
	public ObjectNotFoundExecpition (String msg, Throwable cause) {
		super(msg,cause);
	}

}
