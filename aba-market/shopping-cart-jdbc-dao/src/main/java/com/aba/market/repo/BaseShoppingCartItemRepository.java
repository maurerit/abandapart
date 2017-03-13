package com.aba.market.repo;

import net.maurerit.shoppingcart.domain.ShoppingCartItem;
import net.maurerit.shoppingcart.domain.ShoppingCartItemPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by MM66053 on 3/6/2017.
 */
@NoRepositoryBean
public interface BaseShoppingCartItemRepository extends CrudRepository<ShoppingCartItem, ShoppingCartItemPK> {
    List<ShoppingCartItem> findByShoppingCartId ( Long shoppingCartId );
}
