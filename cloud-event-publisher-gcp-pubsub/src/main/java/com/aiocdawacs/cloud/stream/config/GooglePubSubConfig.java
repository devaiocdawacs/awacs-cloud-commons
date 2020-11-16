package com.aiocdawacs.cloud.stream.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.AckMode;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.cloud.gcp.pubsub.support.BasicAcknowledgeablePubsubMessage;
import org.springframework.cloud.gcp.pubsub.support.GcpPubSubHeaders;
import org.springframework.cloud.gcp.pubsub.support.converter.JacksonPubSubMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.aiocdawacs.cloud.stream.model.CloudEvent;
import com.aiocdawacs.cloud.stream.service.AwacsCloudEventProviderEnum;
import com.aiocdawacs.cloud.stream.service.CloudEventPublisherService;
import com.aiocdawacs.cloud.stream.service.CloudEventPublisherServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableAutoConfiguration
//@ConditionalOnProperty(name = "io.awacs.google.pubsub.enabled",  havingValue = "true", matchIfMissing = true)
@IntegrationComponentScan(basePackages = "com.aiocdawacs.cloud.stream.config")

public class GooglePubSubConfig {

	private static final Log LOGGER = LogFactory.getLog(GooglePubSubConfig.class);

	public static final String TOPIC_NAME = AwacsCloudEventProviderEnum.GoogleCloudPlatformPubSub.getTopicName();

	@Bean
	public PubSubInboundChannelAdapter messageChannelAdapter(
			@Qualifier("pubsubInputChannel") MessageChannel inputChannel, PubSubTemplate pubSubTemplate) {
		PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, TOPIC_NAME);
		adapter.setOutputChannel(inputChannel);
		adapter.setAckMode(AckMode.AUTO);
		adapter.setPayloadType(CloudEvent.class);
		return adapter;
	}

	@Bean
	public MessageChannel pubsubInputChannel() {
		return new DirectChannel();
	}

	@ServiceActivator(inputChannel = "pubsubInputChannel")
	public void messageReceiver(CloudEvent payload,
			@Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message) throws Exception{
		LOGGER.info("Message arrived! Payload: " + payload);
		message.ack();
	}

	@Bean
	@ServiceActivator(inputChannel = "pubSubOutputChannel")
	public MessageHandler messageSender(PubSubTemplate pubSubTemplate) {
		PubSubMessageHandler adapter = new PubSubMessageHandler(pubSubTemplate, TOPIC_NAME);
		adapter.setPublishCallback(new ListenableFutureCallback<String>() {
			@Override
			public void onFailure(Throwable ex) {
				LOGGER.info("There was an error sending the message.");
			}

			@Override
			public void onSuccess(String result) {
				LOGGER.info("Message was sent successfully.");
			}
		});

		return adapter;
	}

	@MessagingGateway(defaultRequestChannel = "pubSubOutputChannel")
	public interface PubsubOutboundGateway {
		void sendToPubsub(CloudEvent event);
	}
	
	/**
	 * This bean enables serialization/deserialization of Java objects to JSON allowing you
	 * utilize JSON message payloads in Cloud Pub/Sub.
	 * @param objectMapper the object mapper to use
	 * @return a Jackson message converter
	 */
	@Bean
	public JacksonPubSubMessageConverter jacksonPubSubMessageConverter(ObjectMapper objectMapper) {
		return new JacksonPubSubMessageConverter(objectMapper);
	}
	
	@Bean
	@Primary
	@Qualifier("cloudEventPublisherService")
	public CloudEventPublisherService getCloudEventPublisherService(PubsubOutboundGateway messagingGateway) {
		return new CloudEventPublisherServiceImpl(messagingGateway);
	}
}