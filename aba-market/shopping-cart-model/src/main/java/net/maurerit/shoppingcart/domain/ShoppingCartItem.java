package net.maurerit.shoppingcart.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mm66053 on 2/28/2017.
 */
@Entity
@Table( name = "shopping_cart_item", schema = "microservices" )
@IdClass( ShoppingCartItemPK.class )
public class ShoppingCartItem implements Serializable {
    @ApiModelProperty( notes = "The id of the shopping cart", access = "private" )
    private Long               shoppingCartId;
    @ApiModelProperty( notes = "The id of the item", required = true )
    private Long               itemId;
    @ApiModelProperty( notes = "Quantity being ordered", required = true )
    private Long               quantity;
    @ApiModelProperty( notes = "The price of the item when added to the cart", required = true )
    private double             price;
    @ApiModelProperty( hidden = true )
    @JsonIgnore
    private ShoppingCart       cart;
    private ShoppingCartStatus status;

    public ShoppingCartItem ( ) {
    }

    public ShoppingCartItem ( Long shoppingCartId, Long itemId, Long quantity, double price,
                              ShoppingCartStatus status )
    {
        this.shoppingCartId = shoppingCartId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    @Id
    @Column( name = "shopping_cart_id" )
    public Long getShoppingCartId ( ) {
        return shoppingCartId;
    }

    public void setShoppingCartId ( Long shoppingCartId ) {
        this.shoppingCartId = shoppingCartId;
    }

    @Id
    @Column( name = "item_id" )
    public Long getItemId ( ) {
        return itemId;
    }

    public void setItemId ( Long itemId ) {
        this.itemId = itemId;
    }

    @Basic
    @Column( name = "quantity" )
    public Long getQuantity ( ) {
        return quantity;
    }

    public void setQuantity ( Long quantity ) {
        this.quantity = quantity;
    }

    @Basic
    @Column( name = "price" )
    public double getPrice ( ) {
        return price;
    }

    public void setPrice ( double price ) {
        this.price = price;
    }

    @Basic
    @Column( name = "status" )
    @Enumerated( EnumType.ORDINAL )
    public ShoppingCartStatus getStatus ( ) {
        return status;
    }

    public void setStatus ( ShoppingCartStatus status ) {
        this.status = status;
    }

    @Override
    public int hashCode ( ) {
        int result;
        long temp;
        result = (int) ( shoppingCartId ^ ( shoppingCartId >>> 32 ) );
        result = 31 * result + (int) ( itemId ^ ( itemId >>> 32 ) );
        result = 31 * result + (int) ( quantity ^ ( quantity >>> 32 ) );
        temp = Double.doubleToLongBits( price );
        result = 31 * result + (int) ( temp ^ ( temp >>> 32 ) );
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

        ShoppingCartItem that = (ShoppingCartItem) o;

        if ( shoppingCartId != that.shoppingCartId ) {
            return false;
        }
        if ( itemId != that.itemId ) {
            return false;
        }
        if ( quantity != that.quantity ) {
            return false;
        }
        return Double.compare( that.price, price ) == 0;
    }

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "shopping_cart_id", updatable = false, insertable = false )
    public ShoppingCart getCart ( ) {
        return cart;
    }

    public void setCart ( ShoppingCart cart ) {
        this.cart = cart;
    }
}
