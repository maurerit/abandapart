CREATE TABLE shopping_cart
(
  shopping_cart_id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  customer_id BIGINT(20) NOT NULL,
  status INT(11) NOT NULL
);

CREATE TABLE shopping_cart_item
(
  shopping_cart_id BIGINT(20) NOT NULL,
  item_id BIGINT(20) NOT NULL,
  quantity BIGINT(20) NOT NULL,
  price DOUBLE NOT NULL,
  status INT(11) NOT NULL,
  CONSTRAINT `PRIMARY` PRIMARY KEY (shopping_cart_id, item_id)
);

CREATE TABLE shopping_cart_status
(
  status INT(11) PRIMARY KEY NOT NULL,
  description VARCHAR(25)
);