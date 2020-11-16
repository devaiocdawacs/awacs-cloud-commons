package com.aiocdawacs.cloud.stream.service;

import java.time.LocalDateTime;
import java.util.UUID;

import com.aiocdawacs.cloud.stream.model.CloudEvent;
import com.aiocdawacs.cloud.stream.model.CloudEventType;

public class CloudEventBuilder {

	private CloudEvent _this = new CloudEvent();

	public CloudEventBuilder withName(String name) {
		_this.setEventName(name);
		return this;
	}

	public CloudEventBuilder withSource(String source) {
		_this.setEventSource(source);
		return this;
	}

	public CloudEventBuilder withType(CloudEventType type) {
		_this.setEventType(type);
		return this;
	}
	
	public CloudEventBuilder withInfo(String eventInfo) {
		_this.setEventInfo(eventInfo);
		return this;
	}
	
	public CloudEventBuilder withAuthToken(String authToken) {
		_this.setAuthToken(authToken);
		return this;
	}
	
	public CloudEventBuilder withErrorMessage(String errorMessage) {
		_this.setErrorMessage(errorMessage);
		return this;
	}
	
	public CloudEventBuilder withSessionId(String sessionId) {
		_this.setSessionId(sessionId);
		return this;
	}
	
	public CloudEventBuilder withSessionCreatedAt(String sessionCreatedAt) {
		_this.setSessionCreatedAt(sessionCreatedAt);
		return this;
	}
	
	public CloudEventBuilder withUserId(String userId) {
		_this.setUserId(userId);
		return this;
	}
	
	public CloudEventBuilder withAccessURL(String accessURL) {
		_this.setAccessURL(accessURL);
		return this;
	}
	

	public CloudEvent build() {
		_this.setEventId(UUID.randomUUID().toString().replaceAll("-", ""));
		_this.setEventCreatedAt(LocalDateTime.now());
		return _this;
	}
}
