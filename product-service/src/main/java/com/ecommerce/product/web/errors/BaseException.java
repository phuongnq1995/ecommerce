package com.ecommerce.product.web.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseException extends AbstractThrowableProblem {

	private static final long serialVersionUID = 1L;
	private static final URI TYPE = URI.create("https://example.org/bad-request");

	protected String entityName;
	protected String errorKey;
	protected Status status;

	public BaseException(Status status, String defaultMessage, String entityName, String errorKey) {

		this(ErrorConstants.DEFAULT_TYPE, status, defaultMessage, entityName, errorKey);
	}

	public BaseException(URI type, Status status, String defaultMessage, String entityName, String errorKey) {
		super(TYPE, defaultMessage, status, null, null, null, getAlertParameters(entityName, errorKey));
		this.entityName = entityName;
		this.errorKey = errorKey;
	}

	private static Map<String, Object> getAlertParameters(String entityName, String errorKey) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("message", "error." + errorKey);
		parameters.put("params", entityName);
		return parameters;
	}

	public String getEntityName() {
		return entityName;
	}

	public String getErrorKey() {
		return errorKey;
	}
}
