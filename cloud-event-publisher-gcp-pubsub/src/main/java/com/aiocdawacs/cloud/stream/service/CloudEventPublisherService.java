package com.aiocdawacs.cloud.stream.service;

import com.aiocdawacs.cloud.stream.model.CloudEvent;

public interface CloudEventPublisherService {

	/**
	 * @param event
	 * @param provider
	 */
	public void publishMessage(CloudEvent event, AwacsCloudEventProviderEnum provider);
}
