package com.aba.market.repo.impl;

import com.aba.market.repo.BaseShoppingCartItemRepository;
import net.maurerit.shoppingcart.domain.ShoppingCartItem;
import net.maurerit.shoppingcart.domain.ShoppingCartItemPK;
import net.maurerit.shoppingcart.domain.ShoppingCartStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MM66053 on 3/6/2017.
 */
@Repository
public class ShoppingCartItemRepositoryImpl implements BaseShoppingCartItemRepository {
    @Autowired
    private JdbcTemplate template;

    @Override
    @Transactional
    public ShoppingCartItem save ( ShoppingCartItem entity ) {
        String insertQuery = "insert into shopping_cart_item (shopping_cart_id, item_id, quantity, price, status) " +
                "values " +
                "(?, ?, ?, ?, ?)";
        String updateQuery = "update shopping_cart_item set item_id = ?, quantity = ?, price = ?, status = ? where " +
                "shopping_cart_id = ?";

        int updated = 0;

        if ( exists( new ShoppingCartItemPK( entity.getShoppingCartId(), entity.getItemId() ) ) ) {
            updated = template.update( updateQuery, entity.getItemId(), entity.getQuantity(), entity.getPrice(),
                                       entity.getStatus()
                                             .ordinal(), entity.getShoppingCartId() );
        }
        else {
            updated = template.update( insertQuery, entity.getShoppingCartId(), entity.getItemId(),
                                       entity.getQuantity(),
                                       entity.getPrice(), entity.getStatus()
                                                                .ordinal() );
        }

        if ( updated > 1 ) {
            throw new IllegalStateException( "Shopping Cart save updated more than 1 record." );
        }

        return entity;
    }

    //NO IMPLEMENTATION
    @Override
    public <S extends ShoppingCartItem> Iterable<S> save ( Iterable<S> entities ) {
        return null;
    }

    @Override
    public ShoppingCartItem findOne ( ShoppingCartItemPK shoppingCartItemPK ) {
        String query = "select * from shopping_cart_item where shopping_cart_id = ? and item_id = ?";
        List<ShoppingCartItem> items = template.query( query,
                                                       ( rs, rowNum ) ->
                                                               new ShoppingCartItem(
                                                                       rs.getLong( "shopping_cart_id" ),
                                                                       rs.getLong( "item_id" ),
                                                                       rs.getLong( "quantity" ),
                                                                       rs.getDouble( "price" ),
                                                                       ShoppingCartStatus.values()[rs.getInt(
                                                                               "status" )]
                                                               ),
                                                       shoppingCartItemPK.getShoppingCartId(),
                                                       shoppingCartItemPK.getItemId() );

        if ( items != null && !items.isEmpty() ) {
            return items.get( 0 );
        }

        return null;
    }

    @Override
    public boolean exists ( ShoppingCartItemPK shoppingCartItemPK ) {
        return this.template.queryForObject(
                "select count(*) from shopping_cart_item where shopping_cart_id = ? and item_id = ?",
                new Object[]{ shoppingCartItemPK.getShoppingCartId(), shoppingCartItemPK.getItemId() },
                Integer.class ) > 0;
    }

    @Override
    public Iterable<ShoppingCartItem> findAll ( ) {
        return null;
    }

    @Override
    public Iterable<ShoppingCartItem> findAll ( Iterable<ShoppingCartItemPK> shoppingCartItemPKS ) {
        return null;
    }

    @Override
    public long count ( ) {
        return 0;
    }

    @Override
    public void delete ( ShoppingCartItemPK shoppingCartItemPK ) {

    }

    @Override
    public void delete ( ShoppingCartItem entity ) {

    }

    @Override
    public void delete ( Iterable<? extends ShoppingCartItem> entities ) {

    }

    @Override
    public void deleteAll ( ) {

    }

    @Override
    public List<ShoppingCartItem> findByShoppingCartId ( Long shoppingCartId ) {
        String query = "select * from shopping_cart_item where shopping_cart_id = ?";
        return template.query( query,
                               ( rs, rowNum ) ->
                                       new ShoppingCartItem(
                                               rs.getLong( "shopping_cart_id" ),
                                               rs.getLong( "item_id" ),
                                               rs.getLong( "quantity" ),
                                               rs.getDouble( "price" ),
                                               ShoppingCartStatus.values()[rs.getInt(
                                                       "status" )]
                                       ),
                               shoppingCartId );
    }
}
