DELETE
FROM tasks
WHERE true;
DELETE
FROM summarysheets
WHERE true;

--
ALTER TABLE tasks AUTO_INCREMENT = 1;
ALTER TABLE summarysheets AUTO_INCREMENT = 1;


