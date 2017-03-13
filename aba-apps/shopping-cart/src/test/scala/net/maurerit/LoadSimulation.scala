package net.maurerit

import scala.concurrent.duration._
import scala.util.Random

class LoadSimulation extends Simulation {
  val httpProtocol = http
    .baseURL("http://localhost:8080")
    .inferHtmlResources()
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.8")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
  val cartOperations = scenario("Get the Users current cart")
    .feed(ShoppingCart.users)
    .exec(
      ShoppingCart.getCurrentCart,
      ShoppingCart.getByCartId
    )
    .repeat(80) {
      feed(ShoppingCart.items)
        .feed(ShoppingCart.price)
        .feed(ShoppingCart.quantity)
        .exec(
          ShoppingCart.addItemToCart
        )
    }
    .exec(
      ShoppingCart.checkout
    )

  object ShoppingCart {
    val headers = Map(
      "Content-Type" -> "application/json",
      "Origin" -> "http://localhost:8080:"
    )

    val users = Iterator.continually(
      Map("userId" -> Random.nextLong())
    )
    val items = Iterator.continually(
      Map("itemId" -> Random.nextLong())
    )
    val price = Iterator.continually(
      Map("price" -> Random.nextLong())
    )
    val quantity = Iterator.continually(
      Map("quantity" -> Random.nextInt(Integer.MAX_VALUE))
    )

    val getCurrentCart = exec(
      http("get_current_cart")
        .post("/cart")
        .headers(headers)
        .body(StringBody("${userId}"))
        .check(jsonPath("$.shoppingCartId").saveAs("shoppingCartId"))
    )

    val getByCartId = exec(
      http("get_by_cart_id")
        .get("/cart/${shoppingCartId}")
        .headers(headers)
    )

    val addItemToCart = exec(
      http("add_item_to_cart")
        .post("/cart/${shoppingCartId}")
        .body(StringBody("""{ "itemId": ${itemId}, "price":${price}, "quantity":${quantity}, "status":"SHOPPING" }""")).asJSON
    )

    val checkout = exec(
      http("checkout")
        .patch("/cart/${shoppingCartId}")
    )
  }

  setUp(
    cartOperations.inject(
      rampUsers(160) over (10 seconds)
    )
  ).protocols(httpProtocol)
}
