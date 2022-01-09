package com.ecommerce.product.web.errors;

import org.zalando.problem.Status;

public class BadRequestAlertException extends BaseException {

	public BadRequestAlertException(String defaultMessage, String entityName, String errorKey) {
		super(Status.BAD_REQUEST, defaultMessage, entityName, errorKey);
	}

}
