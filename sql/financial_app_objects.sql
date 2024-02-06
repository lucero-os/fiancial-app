Tables 
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    mail VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE notification_types (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    code VARCHAR(50) NOT NULL
);

CREATE TABLE notifications (
    id SERIAL PRIMARY KEY,
    notification_type_id INTEGER,
    name VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    CONSTRAINT fk_notification_type FOREIGN KEY (type_id) REFERENCES notification_types(id)
);

CREATE TABLE user_notifications (
    user_id SERIAL,
    notification_id INTEGER,
    PRIMARY KEY (user_id, notification_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_notification FOREIGN KEY (notification_id) REFERENCES notifications(id)
);

CREATE TABLE subscriptions (
    id SERIAL PRIMARY KEY,
    duration INTEGER NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE user_subscriptions (
    id SERIAL PRIMARY KEY,
    user_id INTEGER,
    subscription_id INTEGER,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_subscription FOREIGN KEY (subscription_id) REFERENCES subscriptions(id),
    CONSTRAINT chk_dates CHECK (start_date <= COALESCE(end_date, start_date)) 
);

CREATE TABLE payment_integrations (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE,
    code VARCHAR(100) UNIQUE
);

CREATE TABLE payment_details (
    id SERIAL PRIMARY KEY,
    amount DECIMAL(10, 2) NOT NULL,
    payment_external_reference VARCHAR(255) NOT NULL,
    card_last_four_numbers VARCHAR(4)
);

CREATE TABLE payments (
    id SERIAL PRIMARY KEY,
    user_subscription_id INTEGER,
    payment_integration_id INTEGER,
    payment_details_id INTEGER,
    payment_date DATE NOT NULL,
    FOREIGN KEY (user_subscription_id) REFERENCES user_subscriptions(id),
    FOREIGN KEY (payment_integration_id) REFERENCES payment_integrations(id),
    FOREIGN KEY (payment_details_id) REFERENCES payment_details(id)
);

-- Indexes
CREATE INDEX idx_user_subscriptions_user_id ON user_subscriptions(user_id, end_date);
CREATE INDEX idx_user_notifications_user_id ON user_notifications(user_id);
CREATE INDEX idx_payments_user_subscription_id ON payments(user_subscription_id);

-- Base data
INSERT INTO notification_types (name, code) VALUES ('Email notification', 'email');
INSERT INTO notifications (type_id, name, description) VALUES (1, 'Newsletter', 'Generic newsletter for every user');
INSERT INTO payment_integrations (name, code) VALUES ('Stripe', 'Stripe');
INSERT INTO subscriptions (duration, price) VALUES (1, 9.99);
INSERT INTO subscriptions (duration, price) VALUES (12, 90);
