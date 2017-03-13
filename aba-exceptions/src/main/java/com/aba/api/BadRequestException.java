package com.aba.api;

/**
 * Created by maurerit on 3/7/17.
 */
public class BadRequestException extends ApiException {
    public BadRequestException ( int code, String msg ) {
        super( code, msg );
    }
}
