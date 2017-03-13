package net.maurerit.shoppingcart.domain;

/**
 * Created by mm66053 on 2/28/2017.
 */
public enum ShoppingCartStatus {
    SHOPPING( "Shopping" ),
    ORDERED( "Ordered" ),
    IN_PROGRESS( "In Progress" ),
    GATHERING_MATERIALS( "Gathering Materials" ),
    SHIPPING( "Shipping" ),
    COMPLETE( "Complete" );

    private final String status;

    ShoppingCartStatus ( String status ) {
        this.status = status;
    }
}
