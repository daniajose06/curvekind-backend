-- ==========================
-- CurveKind V1: Core Catalog
-- ==========================

CREATE TABLE body_shapes (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(40) UNIQUE NOT NULL,
    display_name VARCHAR(80) NOT NULL,
    description TEXT
);

CREATE TABLE styles (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(60) UNIQUE NOT NULL,
    display_name VARCHAR(120) NOT NULL,
    description TEXT
);

CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    category VARCHAR(80),
    price_cents INT NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE product_sizes (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL REFERENCES products(id) ON DELETE CASCADE,
    size_code VARCHAR(10) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    UNIQUE(product_id,size_code)
);

-- Product can suit multiple body shapes (many-to-many)
CREATE TABLE product_body_shapes (
  product_id BIGINT NOT NULL REFERENCES products(id) ON DELETE CASCADE,
  body_shape_id BIGINT NOT NULL REFERENCES body_shapes(id) ON DELETE CASCADE,
  PRIMARY KEY (product_id, body_shape_id)
);

-- Product can belong to multiple styles (many-to-many)
CREATE TABLE product_styles (
  product_id BIGINT NOT NULL REFERENCES products(id) ON DELETE CASCADE,
  style_id BIGINT NOT NULL REFERENCES styles(id) ON DELETE CASCADE,
  PRIMARY KEY (product_id, style_id)
);

-- Helpful indexes
CREATE INDEX idx_products_category ON products(category);
CREATE INDEX idx_product_sizes_product ON product_sizes(product_id);
CREATE INDEX idx_pbs_body_shape ON product_body_shapes(body_shape_id);
CREATE INDEX idx_ps_style ON product_styles(style_id);


-- Insert into body shapes
INSERT INTO body_shapes(code, display_name, description) VALUES
('HOURGLASS', 'Hourglass (X-Shape)', 'Balanced bust/hips with defined waist'),
('PEAR', 'Pear (Triangle)', 'Hips wider than shoulders'),
('APPLE', 'Apple (Round/Oval)', 'Fuller midsection with slimmer legs/hips'),
('RECTANGLE', 'Rectangle (Straight/H-Shape)', 'Similar bust/waist/hips, straighter silhouette'),
('INVERTED_TRIANGLE', 'Inverted Triangle (T-Shape)', 'Shoulders/bust wider than hips');

-- Insert common styles (extend anytime)
INSERT INTO styles(code, display_name, description) VALUES
('A_LINE_DRESS', 'A-line Dress', 'Fitted at top, flares out from waist/hips'),
('FIT_AND_FLARE', 'Fit-and-Flare Dress', 'Fitted bodice with a flared skirt'),
('WRAP_DRESS', 'Wrap Dress', 'Wrap front with adjustable waist'),
('EMPIRE_WAIST', 'Empire Waist Dress', 'High waistline under bust with flowing skirt');