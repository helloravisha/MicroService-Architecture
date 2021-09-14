package com.dolby.pastebin.exception;

import com.dolby.pastebin.error.Error;

/**
 * Represents the service exception that can be thrown as part of pastebin processing API's
 *
 * @author ravi shanker katta
 * @since 2021 Sep
 * @version 1.0
 */
public class ServiceException extends RuntimeException {
    public Error getError() {
        return error;
    }

    private Error error;

    public ServiceException(Error error) {
        super();
        this.error = error;
    }
}