package com.aba.api;

/**
 * Created by mm66053 on 2/28/2017.
 */
public class NotFoundException extends ApiException {
    public NotFoundException ( int code, String msg ) {
        super( 404, msg );
    }
}
