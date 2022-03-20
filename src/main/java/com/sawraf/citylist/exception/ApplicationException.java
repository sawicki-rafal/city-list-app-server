package com.sawraf.citylist.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper class for Exceptions thrown in application
 */
public class ApplicationException extends RuntimeException {

    public static final String EMPTY_MESSAGE = "";

    private MessageCode messageCode;

    private List<Object> messageArgs = new ArrayList<>();

    public ApplicationException(String message) {
        super(message);
    }

    /**
     * ApplicationException constructor
     *
     * @param messageCode used in response messages for Exceptions
     * @param args        used in response messages for Exceptions
     */
    public ApplicationException(MessageCode messageCode, Object... args) {
        super(EMPTY_MESSAGE);
        this.messageCode = messageCode;
        this.messageArgs.addAll(List.of(args));
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * ApplicationException constructor
     *
     * @param messageCode used in response messages for Exceptions
     * @param cause       origin of ApplicationException
     * @param args        used in response messages for Exceptions
     */
    public ApplicationException(MessageCode messageCode, Throwable cause, String... args) {
        super(EMPTY_MESSAGE, cause);
        this.messageCode = messageCode;
        this.messageArgs.addAll(List.of(args));
    }

    public MessageCode getMessageCode() {
        return messageCode;
    }

    public List<Object> getMessageArgs() {
        return messageArgs;
    }
}
