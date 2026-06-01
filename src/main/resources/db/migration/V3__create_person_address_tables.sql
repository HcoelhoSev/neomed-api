CREATE TABLE persons (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    phone VARCHAR(20),
    gender VARCHAR(20),
    cpf VARCHAR(14) UNIQUE,
    rg VARCHAR(20),
    email VARCHAR(150),
    birth_date DATE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP,

    CONSTRAINT chk_person_gender
        CHECK (gender IS NULL OR gender IN ('MALE', 'FEMALE', 'OTHER'))
);

CREATE TABLE addresses (
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR(20),
    street VARCHAR(150) NOT NULL,
    number VARCHAR(20),
    complement VARCHAR(100),
    neighborhood VARCHAR(100),
    city VARCHAR(100) NOT NULL,
    state CHAR(2) NOT NULL,
    country VARCHAR(80) NOT NULL DEFAULT 'Brazil',
    zip_code VARCHAR(10) NOT NULL,

    CONSTRAINT chk_address_type
        CHECK (type IS NULL OR type IN ('RESIDENTIAL', 'COMMERCIAL'))
);

CREATE TABLE person_addresses (
    person_id BIGINT NOT NULL,
    address_id BIGINT NOT NULL,
    main BOOLEAN NOT NULL DEFAULT FALSE,

    PRIMARY KEY (person_id, address_id),

    CONSTRAINT fk_person_addresses_person
        FOREIGN KEY (person_id) REFERENCES persons(id) ON DELETE CASCADE,

    CONSTRAINT fk_person_addresses_address
        FOREIGN KEY (address_id) REFERENCES addresses(id) ON DELETE CASCADE
);

ALTER TABLE users
ADD CONSTRAINT fk_users_person
FOREIGN KEY (person_id) REFERENCES persons(id);

CREATE INDEX idx_person_addresses_person_id ON person_addresses(person_id);
CREATE INDEX idx_person_addresses_address_id ON person_addresses(address_id);
CREATE INDEX idx_users_person_id ON users(person_id);