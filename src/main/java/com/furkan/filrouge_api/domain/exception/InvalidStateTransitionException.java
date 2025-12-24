package com.furkan.filrouge_api.domain.exception;

public class InvalidStateTransitionException extends BusinessRuleException {
    public InvalidStateTransitionException(String message) {
        super(message);
    }

}
