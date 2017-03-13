package net.maurerit.shoppingcart.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mm66053 on 2/28/2017.
 */
@Entity
@Table( name = "shopping_cart", schema = "microservices" )
public class ShoppingCart implements Serializable {
    @ApiModelProperty( notes = "The id of the shopping cart", access = "private" )
    private Long                   shoppingCartId;
    @ApiModelProperty( notes = "The id of the customer", required = true )
    private Long                   customerId;
    @ApiModelProperty( notes = "The status of the shopping cart", required = true )
    private ShoppingCartStatus     status;
    @ApiModelProperty( notes = "All of the items in this shopping cart" )
    private List<ShoppingCartItem> items;

    public ShoppingCart ( ) {
    }

    public ShoppingCart ( Long customerId ) {
        this.customerId = customerId;
    }

    public ShoppingCart ( Long shoppingCartId, Long customerId, ShoppingCartStatus status ) {
        this.shoppingCartId = shoppingCartId;
        this.customerId = customerId;
        this.status = status;
    }

    @Id
    @Column( name = "shopping_cart_id" )
    @GeneratedValue( strategy = GenerationType.AUTO )
    public Long getShoppingCartId ( ) {
        return shoppingCartId;
    }

    public void setShoppingCartId ( Long shoppingCartId ) {
        this.shoppingCartId = shoppingCartId;
    }

    @Basic
    @Column( name = "customer_id" )
    public Long getCustomerId ( ) {
        return customerId;
    }

    public void setCustomerId ( Long customerId ) {
        this.customerId = customerId;
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
        int result = (int) ( shoppingCartId ^ ( shoppingCartId >>> 32 ) );
        result = 31 * result + (int) ( customerId ^ ( customerId >>> 32 ) );
        result = 31 * result + ( status != null ? status.hashCode() : 0 );
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

        ShoppingCart that = (ShoppingCart) o;

        if ( shoppingCartId != that.shoppingCartId ) {
            return false;
        }
        if ( customerId != that.customerId ) {
            return false;
        }
        return status == that.status;
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        ShoppingCart that = (ShoppingCart) o;

        if ( shoppingCartId != that.shoppingCartId ) {
            return false;
        }
        if ( customerId != that.customerId ) {
            return false;
        }
        return status == that.status;
    }

    @OneToMany( mappedBy = "cart", fetch = FetchType.EAGER )
    public List<ShoppingCartItem> getItems ( ) {
        return items;
    }

    public void setItems ( List<ShoppingCartItem> items ) {
        this.items = items;
    }
}
