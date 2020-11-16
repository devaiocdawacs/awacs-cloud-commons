package com.aiocdawacs.cloud.stream.model;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CloudEvent {

	private String eventId;
	private String eventName;
	private String eventSource;
	private String eventInfo;

	private CloudEventType eventType;
	private String errorMessage;

	private String sessionId;
	private String accessURL;

	private String authToken;
	private String userId;
	private String sessionCreatedAt;
	private LocalDateTime eventCreatedAt;


	public CloudEvent() {
		super();
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventSource() {
		return eventSource;
	}

	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
	}

	public CloudEventType getEventType() {
		return eventType;
	}

	public void setEventType(CloudEventType eventType) {
		this.eventType = eventType;
	}

	public LocalDateTime getEventCreatedAt() {
		return eventCreatedAt;
	}

	public void setEventCreatedAt(LocalDateTime eventCreatedAt) {
		this.eventCreatedAt = eventCreatedAt;
	}
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getSessionCreatedAt() {
		return sessionCreatedAt;
	}

	public void setSessionCreatedAt(String sessionCreatedAt) {
		this.sessionCreatedAt = sessionCreatedAt;
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(String eventInfo) {
		this.eventInfo = eventInfo;
	}

	public String getAccessURL() {
		return accessURL;
	}

	public void setAccessURL(String accessURL) {
		this.accessURL = accessURL;
	}
	@Override
	public String toString() {
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
				.create();

		return gson.toJson(this);
	}
}


class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime> {

	public JsonElement serialize(LocalDateTime date, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); // "yyyy-mm-dd"
	}
}