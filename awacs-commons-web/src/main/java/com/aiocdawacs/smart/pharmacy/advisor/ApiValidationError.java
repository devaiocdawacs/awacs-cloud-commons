package com.aiocdawacs.smart.pharmacy.advisor;


class ApiValidationError extends ApiSubError {

	private String object;
	private String field;
	private Object rejectedValue;
	private String message;

	ApiValidationError(String object, String message) {
		this.object = object;
		this.message = message;
	}

	@Override
	public boolean equals(Object obj) {
		return true;
	}
}