package com.aiocdawacs.files.service;

public class StorageFileNotFoundException extends Exception {

	private static final long serialVersionUID = -5936523871720102057L;

	public StorageFileNotFoundException() {
		super();
	}

	public StorageFileNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public StorageFileNotFoundException(String message) {
		super(message);
	}

	public StorageFileNotFoundException(Throwable cause) {
		super(cause);
	}
}
