package com.smes.web;

import javax.servlet.http.HttpSession;

import com.smes.view.frm.Credential;

public class CredentialHandler {
	private static final String CREDENTIAL = "CREDENTIAL";
	protected static void setCredential (HttpSession session, Credential credential){
		session.setAttribute(CREDENTIAL, credential);
	}
	
	protected static void validateCredential (HttpSession session){
		Object obj = session.getAttribute(CREDENTIAL);
		if (obj == null)
			throw new SecurityException("Invalid credential");
	}
	
	protected static Credential getCredential (HttpSession session){
		return (Credential)session.getAttribute(CREDENTIAL);
	}
}
