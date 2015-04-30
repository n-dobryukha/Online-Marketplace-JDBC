package com.ndobriukha.onlinemarketplace.dao;

public class PersistException extends Exception {

	public PersistException() { }
	
	public PersistException(String msg) { super(msg); }
	
	public PersistException(Exception e) { super(e); }

}
