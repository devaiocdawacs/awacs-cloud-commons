package com.aiocdawacs.cloud.stream.service;

import org.springframework.stereotype.Service;

import com.aiocdawacs.cloud.stream.model.CloudEvent;

@Service
public interface CloudEventPublisherService {

	/**
	 * @param event
	 * @param provider
	 */
	public void publishMessage(CloudEvent event, AwacsCloudEventProviderEnum provider);
}
