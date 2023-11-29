-- Table for categories
CREATE TABLE Categories (
    categoryCode VARCHAR(255) NOT NULL UNIQUE,
    categoryName VARCHAR(255) NOT NULL,
    description TEXT,
    parentCategoryCode VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (categoryCode),
    FOREIGN KEY (parentCategoryCode) REFERENCES Categories(categoryCode) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table for products
CREATE TABLE Products (
    productCode VARCHAR(255) NOT NULL UNIQUE,
    productName VARCHAR(255) NOT NULL,
    description TEXT,
    stockQuantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (productCode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Junction table for the many-to-many relationship between products and categories
CREATE TABLE ProductCategories (
    productCode VARCHAR(255),
    categoryCode VARCHAR(255),
    PRIMARY KEY (productCode, categoryCode),
    FOREIGN KEY (productCode) REFERENCES Products(productCode) ON DELETE CASCADE,
    FOREIGN KEY (categoryCode) REFERENCES Categories(categoryCode) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Insert Categories
INSERT INTO Categories (categoryCode, categoryName, description) VALUES 
('ANTIB', 'Antibiotics', 'Medications for bacterial infections'),
('PAINK', 'Pain Killers', 'Medications for pain relief'),
('VITMN', 'Vitamins', 'Supplements and vitamins for various health needs');

-- Insert Products
INSERT INTO Products (productCode, productName, description, stockQuantity, price) VALUES 
('AMOX500', 'Amoxicillin 500mg', 'Used to treat a wide variety of bacterial infections', 50, 0.30),
('IBU400', 'Ibuprofen 400mg', 'Nonsteroidal anti-inflammatory drug used for treating pain', 100, 0.10),
('VITC1000', 'Vitamin C 1000mg', 'Vitamin C supplement for immunity boosting', 150, 0.20);

-- Inserting new products
INSERT INTO Products (productCode, productName, description, stockQuantity, price) VALUES 
('TCRM500', 'Topical Antibiotic Cream', 'Cream with antibiotic properties for skin care', 80, 7.50),
('COLDMED1', 'Cold Medicine', 'Medication for cold and flu symptoms', 150, 4.99);

-- Inserting new category
INSERT INTO Categories (categoryCode, categoryName, description) VALUES 
('SKINC', 'Skin Care', 'Products for skin health and treatment');


-- Additional Linking of Products and Categories
INSERT INTO ProductCategories (productCode, categoryCode) VALUES 
('AMOX500', 'ANTIB'),   -- Amoxicillin is an Antibiotic
('AMOX500', 'PAINK'),   -- Amoxicillin may also be categorized as a Pain Killer in some contexts
('IBU400', 'PAINK'),    -- Ibuprofen is a Pain Killer
('IBU400', 'ANTIB'),    -- Ibuprofen could also be considered an Antibiotic for its anti-inflammatory properties
('VITC1000', 'VITMN');  -- Vitamin C is a Vitamin

INSERT INTO ProductCategories (productCode, categoryCode) VALUES 
-- Let's say we have a topical cream that could be classified under both Skin Care and Antibiotics
('TCRM500', 'SKINC'),   -- Topical Cream for Skin Care
('TCRM500', 'ANTIB'),   -- Topical Cream could have antibiotic properties

-- A cold medicine that's both a Cold remedy and could be considered a Pain Killer
('COLDMED1', 'COLD'),   -- Cold Medicine for Colds
('COLDMED1', 'PAINK');  -- Cold Medicine can also relieve pain


CREATE TABLE CustomerCarts (
    cartId VARCHAR(20) NOT NULL PRIMARY KEY,
    customerName VARCHAR(255) NULL,
    totalAmount DOUBLE NOT NULL
);

CREATE TABLE CartProducts (
    cartId VARCHAR(20) NOT NULL,
    productCode VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    productPrice DOUBLE NOT NULL,
    FOREIGN KEY (cartId) REFERENCES CustomerCarts(cartId),
    FOREIGN KEY (productCode) REFERENCES Products(productCode)
);
