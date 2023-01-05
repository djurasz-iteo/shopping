INSERT INTO products(id, name, base_price) VALUES ('3fda1e2e-bfb2-47da-99e9-40c0ec33901a', 'Product No.1', 10) ON CONFLICT DO NOTHING;

INSERT INTO discounts(id, discount_descriptor, discount_properties) VALUES ('d7ff21de-4aa0-4ab4-b5db-c5307a74b695', 'PERCENTAGE', '{"percentage": 50}') ON CONFLICT DO NOTHING;
INSERT INTO discounts(id, discount_descriptor, discount_properties) VALUES ('d7ff21de-4aa0-4ab4-b5db-c5307a74b696', 'PERCENTAGE', '{"percentage": 10}') ON CONFLICT DO NOTHING;
INSERT INTO discounts(id, discount_descriptor, discount_properties) VALUES ('d7ff21de-4aa0-4ab4-b5db-c5307a74b697', 'PERCENTAGE', '{"percentage": 5}') ON CONFLICT DO NOTHING;