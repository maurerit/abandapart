package net.maurerit.shoppingcart.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by mm66053 on 2/28/2017.
 */
public class ShoppingCartItemPK implements Serializable {
    private Long shoppingCartId;
    private Long itemId;

    public ShoppingCartItemPK ( ) {
    }

    public ShoppingCartItemPK ( Long shoppingCartId, Long itemId ) {
        this.shoppingCartId = shoppingCartId;
        this.itemId = itemId;
    }

    @Column( name = "shopping_cart_id" )
    @Id
    public Long getShoppingCartId ( ) {
        return shoppingCartId;
    }

    public void setShoppingCartId ( Long shoppingCartId ) {
        this.shoppingCartId = shoppingCartId;
    }

    @Column( name = "item_id" )
    @Id
    public Long getItemId ( ) {
        return itemId;
    }

    public void setItemId ( Long itemId ) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode ( ) {
        int result = (int) ( shoppingCartId ^ ( shoppingCartId >>> 32 ) );
        result = 31 * result + (int) ( itemId ^ ( itemId >>> 32 ) );
        return result;
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        ShoppingCartItemPK that = (ShoppingCartItemPK) o;

        if ( shoppingCartId != that.shoppingCartId ) {
            return false;
        }
        return itemId == that.itemId;
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        ShoppingCartItemPK that = (ShoppingCartItemPK) o;

        if ( shoppingCartId != that.shoppingCartId ) {
            return false;
        }
        return itemId == that.itemId;
    }
}
