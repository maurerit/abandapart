package net.maurerit.shoppingcart.service;

import com.aba.api.BadRequestException;
import com.aba.api.NotFoundException;
import com.aba.api.OperationNotAllowed;
import net.maurerit.shoppingcart.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mm66053 on 2/28/2017.
 */
public class AbstractService {
    @ResponseStatus( HttpStatus.NOT_FOUND )
    @ExceptionHandler( NotFoundException.class )
    public ApiResponse exception ( NotFoundException e ) {
        return new ApiResponse( ApiResponse.ERROR, e.getMessage() );
    }

    @ResponseStatus( HttpStatus.CONFLICT )
    @ExceptionHandler( OperationNotAllowed.class )
    public ApiResponse exception ( OperationNotAllowed e ) {
        return new ApiResponse( ApiResponse.WARNING, e.getMessage() );
    }

    @ResponseStatus( HttpStatus.BAD_REQUEST )
    @ExceptionHandler( BadRequestException.class )
    public ApiResponse exception ( BadRequestException e ) {
        return new ApiResponse( ApiResponse.ERROR, e.getMessage() );
    }
}
