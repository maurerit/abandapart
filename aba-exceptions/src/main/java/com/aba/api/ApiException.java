package com.aba.api;

/**
 * Created by mm66053 on 2/28/2017.
 */
public class ApiException extends RuntimeException {
    private int code;

    public ApiException ( int code, String msg ) {
        super( msg );
        this.code = code;
    }
}
