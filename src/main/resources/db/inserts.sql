-- INSERTS PARA LA TABLA product
INSERT INTO product (id, created_at, description, name, price, updated_at) VALUES
(1, '2025-01-01', 'High-performance laptop', 'Notebook', 1200.00, '2025-01-02'),
(2, '2025-01-05', 'Gaming laptop', 'Notebook Gamer', 1800.00, '2025-01-06'),
(3, '2025-01-10', 'Wireless mouse', 'Mouse', 30.00, '2025-01-10'),
(4, '2025-02-01', 'Mechanical keyboard', 'Keyboard', 75.00, '2025-02-01'),
(5, '2025-02-05', '27-inch Monitor', 'Monitor', 250.00, '2025-02-06');

-- INSERTS PARA LA TABLA sale
INSERT INTO sale (id, date, total_amount) VALUES
(1, '2025-07-01 10:00:00', 1230.00),
(2, '2025-07-05 14:30:00', 1875.00),
(3, '2025-07-10 09:15:00', 60.00),
(4, '2025-07-12 16:45:00', 75.00),
(5, '2025-07-13 12:00:00', 500.00);

-- INSERTS PARA LA TABLA sale_item
INSERT INTO sale_item (id, quantity, unit_price, product_id, sale_id) VALUES
(1, 1, 1200.00, 1, 1),
(2, 1, 30.00, 3, 1),
(3, 1, 1800.00, 2, 2),
(4, 1, 75.00, 4, 3),
(5, 2, 250.00, 5, 5);
