CREATE TABLE body_shape_styles (
  body_shape_id BIGINT NOT NULL REFERENCES body_shapes(id) ON DELETE CASCADE,
  style_id BIGINT NOT NULL REFERENCES styles(id) ON DELETE CASCADE,
  PRIMARY KEY (body_shape_id, style_id)
);

-- Pear (Triangle)
INSERT INTO body_shape_styles (body_shape_id, style_id)
SELECT bs.id, s.id
FROM body_shapes bs, styles s
WHERE bs.code = 'PEAR'
  AND s.code IN ('A_LINE_DRESS', 'WRAP_DRESS', 'FIT_AND_FLARE', 'EMPIRE_WAIST');

-- Apple (Round)
INSERT INTO body_shape_styles (body_shape_id, style_id)
SELECT bs.id, s.id
FROM body_shapes bs, styles s
WHERE bs.code = 'APPLE'
  AND s.code IN ('WRAP_DRESS', 'EMPIRE_WAIST');

-- Hourglass
INSERT INTO body_shape_styles (body_shape_id, style_id)
SELECT bs.id, s.id
FROM body_shapes bs, styles s
WHERE bs.code = 'HOURGLASS'
  AND s.code IN ('WRAP_DRESS', 'FIT_AND_FLARE');
