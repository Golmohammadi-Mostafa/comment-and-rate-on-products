
        INSERT INTO category (id, code, title) VALUES (1, 'fashion', 'fashion e-commerce');
        INSERT INTO category (id, code, title) VALUES (2, 'food', 'food and personal e-commerce');
        INSERT INTO category (id, code, title) VALUES (3, 'electronics', 'electronics and media e-commerce');


        INSERT INTO product (id, name, price, category_id) VALUES (1, 'mobile', 1700, 3);
        INSERT INTO product (id, name, price, category_id) VALUES (2, 'suit', 2500, 1);
        INSERT INTO product (id, name, price, category_id) VALUES (3, 'apple', 2, 2);


        INSERT INTO comment (id, text, product_id, user_id) VALUES (1, 'excellent', 1, 2);
        INSERT INTO comment (id, text, product_id, user_id) VALUES (2, 'expensive', 2, 2);
        INSERT INTO comment (id, text, product_id, user_id) VALUES (3, 'delicious', 3, 3);

        INSERT INTO rate (id, rating_level, product_id, usert_id) VALUES (1, '5', 1, 2);
        INSERT INTO rate (id, rating_level, product_id, usert_id) VALUES (2, '3', 2, 2);
        INSERT INTO rate (id, rating_level, product_id, usert_id) VALUES (3, '3', 2, 3);
        INSERT INTO rate (id, rating_level, product_id, usert_id) VALUES (4, '4', 3, 3);
        INSERT INTO rate (id, rating_level, product_id, usert_id) VALUES (5, '5', 3, 2);
