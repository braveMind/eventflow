package com.event.flow.core;

/**
 * @author jun
 * @Date 17/9/3 .
 * @des:
 */
public class EventFlowException extends RuntimeException {
    public EventFlowException() {
        super();
    }

    public EventFlowException(String message) {
        super(message);
    }

    public EventFlowException(String message, Throwable cause) {
        super(message);
        super.initCause(cause);
    }

    public EventFlowException(Throwable cause) {
        super(cause);
    }


}
