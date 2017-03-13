package net.maurerit.shoppingcart.service;

import com.aba.api.BadRequestException;
import com.aba.api.NotFoundException;
import com.aba.api.OperationNotAllowed;
import com.aba.market.repo.BaseShoppingCartItemRepository;
import com.aba.market.repo.BaseShoppingCartRepository;
import io.swagger.annotations.*;
import net.maurerit.shoppingcart.domain.ShoppingCart;
import net.maurerit.shoppingcart.domain.ShoppingCartItem;
import net.maurerit.shoppingcart.domain.ShoppingCartItemPK;
import net.maurerit.shoppingcart.domain.ShoppingCartStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mm66053 on 2/28/2017.
 */
@Api( basePath = "/cart", value = "Shopping Cart", description = "Operations with Shopping Carts", produces =
        "application/json" )
@RestController
public class ShoppingCartService extends AbstractService {
    private static final Logger LOGGER = LoggerFactory.getLogger( ShoppingCartService.class );

    @Autowired
    private BaseShoppingCartRepository cartRepo;

    @Autowired
    private BaseShoppingCartItemRepository itemRepo;

    @ApiOperation( notes = "Gets the current shopping cart for a given customer, if none exists then one is created",
            value = "Current shopping cart", nickname = "getShoppingCart", tags = { "Shopping Cart" } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "Nice!", response = ShoppingCart.class )
    } )
    @RequestMapping( value = "/cart", method = RequestMethod.POST, produces = "application/json" )
    public ResponseEntity<ShoppingCart> getShoppingCart (
            @ApiParam( value = "ID of the customer for the current cart", required = true )
            @RequestBody
                    Long customerId
    )
    {
        ShoppingCart cart = cartRepo.findByCustomerIdAndStatus( customerId, ShoppingCartStatus.SHOPPING );

        if ( cart == null ) {
            cart = new ShoppingCart();
            cart.setCustomerId( customerId );
            cart.setStatus( ShoppingCartStatus.SHOPPING );
        }

        cart = cartRepo.save( cart );

        return ResponseEntity.ok( cart );
    }

    @ApiOperation( notes = "Returns a shopping cart", value = "Find by cart id", nickname = "getByCartId", tags = {
            "Shopping Cart" } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "Nice!", response = ShoppingCart.class ),
            @ApiResponse( code = 400, message = "Invalid ID supplied", response = net.maurerit.shoppingcart.model
                    .ApiResponse.class ),
            @ApiResponse( code = 404, message = "Shopping Cart Not Found", response = net.maurerit.shoppingcart.model
                    .ApiResponse.class )
    } )
    @RequestMapping( value = "/cart/{cartId}", method = RequestMethod.GET, produces = "application/json" )
    public ResponseEntity<ShoppingCart> getByCartId (
            @ApiParam( value = "ID of the cart that needs to be fetched", required = true )
            @PathVariable( "cartId" )
                    Long cartId
    )
    {
        ShoppingCart cart = cartRepo.findByShoppingCartId( cartId );

        if ( cart != null ) {
            return ResponseEntity.ok( cart );
        }
        else {
            throw new NotFoundException( net.maurerit.shoppingcart.model.ApiResponse.ERROR,
                                         "Cart " + cartId + " not found" );
        }
    }

    @ApiOperation( notes = "Checks the shopping cart out (submits it to be fulfilled)", value = "Checkout with the " +
            "carts contents", nickname = "checkout", tags = { "Shopping Cart" } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "Nice!", response = ShoppingCart.class ),
            @ApiResponse( code = 404, message = "Shopping Cart Not Found", response = net.maurerit.shoppingcart.model
                    .ApiResponse.class )
    } )
    @RequestMapping( value = "/cart/{cartId}", method = RequestMethod.PATCH, produces = "application/json" )
    public ResponseEntity<Boolean> checkout (
            @ApiParam( value = "ID of the cart that needs to be fetched", required = true )
            @PathVariable( "cartId" )
                    Long cartId
    )
    {
        ShoppingCart cart = cartRepo.findByShoppingCartId( cartId );
        cart.setStatus( ShoppingCartStatus.ORDERED );
        cart = cartRepo.save( cart );

        if ( cart.getItems() != null && !cart.getItems()
                                             .isEmpty() )
        {
            cart.getItems()
                .forEach( item -> {
                    item.setStatus( ShoppingCartStatus.ORDERED );
                    //TODO: Do this in a batch
                    itemRepo.save( item );
                } );
        }

        if ( cart != null ) {
            return ResponseEntity.ok( true );
        }
        else {
            throw new NotFoundException( net.maurerit.shoppingcart.model.ApiResponse.ERROR,
                                         "Cart " + cartId + " not found" );
        }
    }

    @ApiOperation( notes = "Adds an item to the cart", value = "Add item to cart", nickname = "addItemToCard", tags =
            { "Shopping Cart" } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "Nice!", response = ShoppingCart.class ),
            @ApiResponse( code = 400, message = "Invalid ID supplied", response = net.maurerit.shoppingcart.model
                    .ApiResponse.class ),
            @ApiResponse( code = 404, message = "Shopping Cart Not Found", response = net.maurerit.shoppingcart.model
                    .ApiResponse.class ),
            @ApiResponse( code = 409, message = "Item already exists, modify quantity instead", response = net
                    .maurerit.shoppingcart.model.ApiResponse.class )
    } )
    @RequestMapping( value = "/cart/{cartId}", method = RequestMethod.POST, produces = "application/json" )
    public ResponseEntity<Boolean> addItemToCard (
            @ApiParam( value = "ID of the cart that needs to be fetched", required = true )
            @PathVariable( "cartId" )
                    Long cartId,
            @ApiParam( value = "Cart Item to be added", required = true )
            @RequestBody
                    ShoppingCartItem item
    )
    {
        if ( item == null ) {
            throw new BadRequestException( net.maurerit.shoppingcart.model.ApiResponse.ERROR, "Item cannot be null" );
        }

        ShoppingCart cart = cartRepo.findByShoppingCartId( cartId );

        if ( cart != null ) {
            ShoppingCartItem dbItem = itemRepo.findOne(
                    new ShoppingCartItemPK( cart.getShoppingCartId(), item.getItemId() ) );

            if ( dbItem == null ) {
                item.setShoppingCartId( cart.getShoppingCartId() );
                itemRepo.save( item );
            }
            else {
                throw new OperationNotAllowed( net.maurerit.shoppingcart.model.ApiResponse.WARNING,
                                               "Item id: " + item.getItemId() + " already exists in cart: " + cart
                                                       .getShoppingCartId() );
            }
        }

        if ( cart == null ) {
            throw new NotFoundException( net.maurerit.shoppingcart.model.ApiResponse.ERROR,
                                         "Cart " + cartId + " not found" );
        }

        return ResponseEntity.ok( true );
    }

    @ApiOperation( notes = "Update the quantity of an item in a cart", value = "Quantity Update", nickname =
            "updateItemQuantity", tags = { "Shopping Cart" } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "Nice!", response = ShoppingCart.class ),
            @ApiResponse( code = 404, message = "Cart/Item not found", response = net.maurerit.shoppingcart.model
                    .ApiResponse.class ),
    } )
    @RequestMapping( value = "/cart/{cartId}/{itemId}", method = RequestMethod.PATCH, produces = "application/json" )
    public ResponseEntity<Boolean> updateCartItem (
            @ApiParam( value = "ID of the cart for the item needing updated", required = true )
            @PathVariable( "cartId" )
                    Long cartId,
            @ApiParam( value = "Item ID in the cart to be updated", required = true )
            @PathVariable( "itemId" )
                    Long itemId,
            //TODO: make a new model object that isn't a full shopping cart item.
            @RequestBody
                    ShoppingCartItem item
    )
    {
        ShoppingCart cart = cartRepo.findByShoppingCartId( cartId );

        if ( cart != null && item != null ) {
            ShoppingCartItem dbItem = itemRepo.findOne(
                    new ShoppingCartItemPK( cart.getShoppingCartId(), item.getItemId() ) );

            if ( dbItem == null ) {
                throw new NotFoundException( net.maurerit.shoppingcart.model.ApiResponse.ERROR,
                                             "Item: " + itemId + " does not exist in cart: " + cartId + "." );
            }
            else {
                item.setShoppingCartId( cart.getShoppingCartId() );
                itemRepo.save( item );
            }
        }
        else {
            throw new NotFoundException( net.maurerit.shoppingcart.model.ApiResponse.ERROR,
                                         "Cart: " + cartId + " does not exist." );
        }

        return ResponseEntity.ok( true );
    }
}
