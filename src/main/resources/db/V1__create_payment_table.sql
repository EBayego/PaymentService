CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,
    credit_card_number VARCHAR(19),
    amount DECIMAL(10, 2),
    payment_date DATE,
    description TEXT
);