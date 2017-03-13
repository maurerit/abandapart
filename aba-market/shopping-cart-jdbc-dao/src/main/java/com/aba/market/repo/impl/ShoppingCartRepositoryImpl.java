package com.aba.market.repo.impl;

import com.aba.api.NotFoundException;
import com.aba.market.repo.BaseShoppingCartItemRepository;
import com.aba.market.repo.BaseShoppingCartRepository;
import net.maurerit.shoppingcart.domain.ShoppingCart;
import net.maurerit.shoppingcart.domain.ShoppingCartStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by MM66053 on 3/6/2017.
 */
@Repository
public class ShoppingCartRepositoryImpl implements BaseShoppingCartRepository {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private BaseShoppingCartItemRepository itemRepo;

    @Override
    public Integer nextIdForCustomer ( Long customerId ) {
        String query = "select coalesce(max(sc.shopping_cart_id),1) from shopping_cart sc where sc.customer_id = ?";
        return this.template.queryForObject( query, new Long[]{ customerId }, Integer.class );
    }

    @Override
    public ShoppingCart findByCustomerIdAndStatus ( Long customerId, ShoppingCartStatus status ) {
        ShoppingCart foundCart = null;
        String query = "select * from shopping_cart where customer_id = ? and status = ?";

        List<ShoppingCart> cartsFound = this.template.query( query, new Object[]{ customerId, status.ordinal() },
                                                             ( rs, rowNum ) -> new ShoppingCart(
                                                                     rs.getLong( "shopping_cart_id" ),
                                                                     rs.getLong( "customer_id" ),
                                                                     ShoppingCartStatus.values()[rs.getInt(
                                                                             "status" )] ) );

        if ( cartsFound != null && !cartsFound.isEmpty() ) {
            foundCart = cartsFound.get( 0 );

            foundCart.setItems( this.itemRepo.findByShoppingCartId( foundCart.getShoppingCartId() ) );
        }

        return foundCart;
    }

    @Override
    public ShoppingCart findByShoppingCartId ( Long shoppingCartId ) {
        String query = "select * from shopping_cart where shopping_cart_id = ?";
        if ( !exists( shoppingCartId ) ) {
            throw new NotFoundException( 404, "Shopping cart: " + shoppingCartId + " not found" );
        }
        return this.template.queryForObject( query, new Long[]{ shoppingCartId },
                                             ( rs, rowNum ) -> new ShoppingCart( rs.getLong( "shopping_cart_id" ),
                                                                                 rs.getLong( "customer_id" ),
                                                                                 ShoppingCartStatus.values()[rs.getInt(
                                                                                         "status" )] ) );
    }

    @Override
    @Transactional
    public ShoppingCart save ( ShoppingCart entity ) {
        String insertQuery = "insert into shopping_cart (customer_id, status) values (?, ?)";
        String updateQuery = "update shopping_cart set customer_id = ?, status = ? where shopping_cart_id = ?";

        if ( entity.getShoppingCartId() != null ) {
            this.template.update( updateQuery, entity.getCustomerId(), entity.getStatus()
                                                                             .ordinal(), entity.getShoppingCartId() );
        }
        else {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            this.template.update(
                    connection -> {
                        PreparedStatement ps =
                                connection.prepareStatement( insertQuery, new String[]{ "id" } );
                        ps.setLong( 1, entity.getCustomerId() );
                        ps.setInt( 2, entity.getStatus()
                                            .ordinal() );
                        return ps;
                    },
                    keyHolder );

            entity.setShoppingCartId( keyHolder.getKey()
                                               .longValue() );
        }

        return entity;
    }

    //NO IMPLEMENTATION
    @Override
    public <S extends ShoppingCart> Iterable<S> save ( Iterable<S> entities ) {
        return null;
    }

    @Override
    public ShoppingCart findOne ( Long aLong ) {
        return null;
    }

    @Override
    public boolean exists ( Long aLong ) {
        return this.template.queryForObject(
                "select count(*) from shopping_cart where shopping_cart_id = ?",
                new Object[]{ aLong },
                Integer.class ) > 0;
    }

    @Override
    public Iterable<ShoppingCart> findAll ( ) {
        return null;
    }

    @Override
    public Iterable<ShoppingCart> findAll ( Iterable<Long> longs ) {
        return null;
    }

    @Override
    public long count ( ) {
        return 0;
    }

    @Override
    public void delete ( Long aLong ) {

    }

    @Override
    public void delete ( ShoppingCart entity ) {

    }

    @Override
    public void delete ( Iterable<? extends ShoppingCart> entities ) {

    }

    @Override
    public void deleteAll ( ) {

    }
}
