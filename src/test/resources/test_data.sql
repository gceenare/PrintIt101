-- Test data for users and customers
INSERT INTO users (first_name, last_name, user_name, password, role)
VALUES ('John', 'Doe', 'johndoe', 'password123', 'CUSTOMER');

-- Get the last inserted user_id
SET @user_id = LAST_INSERT_ID();

INSERT INTO customers (user_id, customer_discount)
VALUES (@user_id, 10.0);
