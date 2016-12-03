package com.mondimedia.memory.common;

public class BusinessException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 7916719668329216178L;

	public BusinessException(String msg) {
		super(msg);
	}

	public BusinessException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
