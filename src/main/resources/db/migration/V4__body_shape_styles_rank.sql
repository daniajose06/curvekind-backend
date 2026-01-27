ALTER TABLE body_shape_styles
ADD COLUMN IF NOT EXISTS rank INT NOT NULL DEFAULT 100;

-- Optional: add an index to help ordering queries
CREATE INDEX IF NOT EXISTS idx_body_shape_styles_rank
ON body_shape_styles(body_shape_id, rank);

-- Ordering for PEAR
UPDATE body_shape_styles bss
SET rank = CASE s.code
    WHEN 'A_LINE_DRESS' THEN 1
    WHEN 'FIT_AND_FLARE' THEN 2
    WHEN 'WRAP_DRESS' THEN 3
    WHEN 'EMPIRE_WAIST' THEN 4
    ELSE 100
END
FROM body_shapes bs
JOIN styles s ON s.id = bss.style_id
WHERE bss.body_shape_id = bs.id
  AND bs.code = 'PEAR';
