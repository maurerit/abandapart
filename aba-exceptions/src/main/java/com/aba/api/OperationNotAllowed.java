package com.aba.api;

/**
 * Created by MM66053 on 3/1/2017.
 */
public class OperationNotAllowed extends ApiException {
    public OperationNotAllowed ( int code, String msg ) {
        super( code, msg );
    }
}
