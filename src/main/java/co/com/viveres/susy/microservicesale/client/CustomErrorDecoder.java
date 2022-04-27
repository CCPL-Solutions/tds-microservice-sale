package co.com.viveres.susy.microservicesale.client;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import co.com.viveres.susy.microservicecommons.entity.MessageEntity;
import co.com.viveres.susy.microservicecommons.exceptions.GenericException;
import co.com.viveres.susy.microservicecommons.repository.IMessageRepository;
import co.com.viveres.susy.microservicesale.util.ResponseMessages;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
	
	@Autowired
	private IMessageRepository messageRepository;

	@Override
	public Exception decode(String methodKey, Response response) {

		log.info(response.body().toString());
		HttpStatus responseStatus = HttpStatus.valueOf(response.status());
		
		MessageEntity message = this.messageRepository
				.findById(ResponseMessages.ERROR_INTERNAL_PROCESSING_TX)
				.orElseThrow(NoSuchElementException::new);

		if (responseStatus.is5xxServerError()) {
			return new GenericException(message);		
		} else if (responseStatus.is4xxClientError()) {
			return new GenericException(message);
		} else {
			return new Exception("Generic exception");
		}
	}

}
