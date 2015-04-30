CREATE TRIGGER Users_BIS_TRG
	BEFORE INSERT ON Users
	FOR EACH ROW
BEGIN
	IF INSERTING AND :NEW.id IS NULL THEN
		SELECT Users_PK_SEQ.NEXTVAL INTO :NEW.id FROM SYS.DUAL;
	END IF;
END;
/
CREATE TRIGGER Items_BIS_TRG
	BEFORE INSERT ON Items
	FOR EACH ROW 
BEGIN
	IF INSERTING AND :NEW.id IS NULL THEN
		SELECT Items_PK_SEQ.NEXTVAL INTO :NEW.id FROM SYS.DUAL;
	END IF;
END;
/
CREATE TRIGGER Bids_BIS_TRG
	BEFORE INSERT ON Bids
	FOR EACH ROW 
BEGIN
	IF INSERTING AND :NEW.id IS NULL THEN
		SELECT Bids_PK_SEQ.NEXTVAL INTO :NEW.id FROM SYS.DUAL;
	END IF;
END;
/
Commit;