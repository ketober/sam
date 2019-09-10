package com.ai.sam.exception;


public class SequenceException extends  RuntimeException  {

    private static final long serialVersionUID = -4549461949759705608L;

    public SequenceException(String message) {
        super(message);
    }

    public SequenceException(Throwable e) {
        super(e);
    }

    public SequenceException(String message, Throwable cause) {
        super(message, cause);
    }

}
