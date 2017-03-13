package com.aba.market.repo;

import net.maurerit.shoppingcart.domain.ShoppingCart;
import net.maurerit.shoppingcart.domain.ShoppingCartStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by MM66053 on 3/6/2017.
 */
@NoRepositoryBean
public interface BaseShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
    Integer nextIdForCustomer ( Long customerId );

    ShoppingCart findByCustomerIdAndStatus ( Long customerId, ShoppingCartStatus status );

    ShoppingCart findByShoppingCartId ( Long shoppingCartId );
}
