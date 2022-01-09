package com.ecommerce.product.web.errors;

import org.zalando.problem.Status;

public class NotFoundAlertException extends BaseException {

	public NotFoundAlertException(String defaultMessage, String entityName, String errorKey) {
		super(Status.NOT_FOUND, defaultMessage, entityName, errorKey);
	}

}
