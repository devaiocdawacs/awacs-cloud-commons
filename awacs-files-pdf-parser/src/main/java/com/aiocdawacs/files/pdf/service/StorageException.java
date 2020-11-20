package com.aiocdawacs.files.pdf.service;

public class StorageException extends RuntimeException {

	private static final long serialVersionUID = -5244005724998802737L;

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}
}