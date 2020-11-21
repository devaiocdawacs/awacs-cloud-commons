package com.aiocdawacs.smart.pharmacy.advisor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdvisor.class);

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleNumberformatException(MethodArgumentTypeMismatchException ex,
			WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		body.put("message", ex.getMessage());
		body.put("x-powered-by", "AwacsInternational");

		publisher.publishEvent(ControllerErrorEventBuilder.build(ex.getMessage()));

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		body.put("message", ex.getMessage());
		body.put("status", status.value());
		body.put("x-powered-by", "AwacsInternational");

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);

		// apache mq
		publisher.publishEvent(ControllerErrorEventBuilder.build(Strings.join(errors, ',')));

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@Autowired
	private ApplicationEventPublisher publisher;

	@ExceptionHandler(value = { Unauthorized.class })
	public ResponseEntity<Object> handleUnauthorizedException(Unauthorized ex) {
		LOGGER.error("Unauthorized Exception: ", ex.getMessage());
		publisher.publishEvent(ControllerErrorEventBuilder.build(new String(ex.getMessage())));

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		body.put("message", ex.getMessage());
		body.put("x-powered-by", "AwacsInternational");

		return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleException(Exception ex) {
		LOGGER.error("Exception: ", ex.getMessage());
		publisher.publishEvent(ControllerErrorEventBuilder.build(new String("error occured in the smart pharmacy :" + ex.getMessage())));

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		body.put("message", ex.getMessage());
		body.put("x-powered-by", "AwacsInternational");

		List<String> errors = Arrays.asList(ex.getMessage());
		body.put("errors", errors);

		return new ResponseEntity<Object>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { Error.class })
	public ResponseEntity<Object> handleException(Error err) {
		LOGGER.error("Error: ", err.getMessage());
		publisher.publishEvent(ControllerErrorEventBuilder.build(new String("error occured in the smart pharmacy :" + err.getMessage())));

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		body.put("message", err.getMessage());
		body.put("x-powered-by", "AwacsInternational");

		List<String> errors = Arrays.asList(err.getMessage());
		body.put("errors", errors);

		return new ResponseEntity<Object>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException exc) {
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new String("File too large!"));
	}
} 

class ControllerErrorEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1795732886716875383L;
	LocalDateTime eventCreationTime = LocalDateTime.now();

	public ControllerErrorEvent(Object source) {
		super(source);

	}
}


class ControllerErrorEventBuilder {
	static ControllerErrorEvent build(String b){
		ControllerErrorEvent e = new ControllerErrorEvent(b);
		return e;
	}
}

@Component
class ErrorReporterToJms implements ApplicationListener<ControllerErrorEvent> {

	@Autowired 
	JmsTemplate jmsTemplate;

	@Override
	public void onApplicationEvent(ControllerErrorEvent event) {
		jmsTemplate.convertAndSend("controller.events.error", event);
	}

	/*
	 * @JmsListener(destination = "controller.events.error") public void
	 * subscribeJms(Message<ApplicationEvent> e) {
	 * System.out.println("Message Received - "+ e.getPayload().getSource()); }
	 */
}