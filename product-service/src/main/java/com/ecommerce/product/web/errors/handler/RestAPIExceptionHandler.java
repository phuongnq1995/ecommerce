package com.ecommerce.product.web.errors.handler;

import com.ecommerce.product.web.errors.BadRequestAlertException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@ControllerAdvice
public class RestAPIExceptionHandler implements ProblemHandling {

	@ExceptionHandler
	public ResponseEntity<Problem> handleBadRequestAlertException(BadRequestAlertException ex, NativeWebRequest request) {
		return create(
			ex,
			request,
			new HttpHeaders()
		);
	}
}
