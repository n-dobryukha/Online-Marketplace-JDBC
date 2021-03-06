--	Create Table USERS 
CREATE TABLE USERS (
	ID INTEGER NOT NULL,
	FULL_NAME VARCHAR2 (50) NULL,
	BILLING_ADDRESS VARCHAR2 (100) NULL,
	LOGIN VARCHAR2 (20) NULL,
	PASSWORD VARCHAR2 (20) NULL,
	EMAIL VARCHAR2 (50) NULL
	);
	
	CREATE UNIQUE INDEX USERS_PK ON Users(id);
		
	CREATE UNIQUE INDEX USERS_LOGIN_IDX ON USERS(LOGIN);
		
	ALTER TABLE USERS
		ADD CONSTRAINT USERS_PK Primary Key(ID);

--	Create Table ITEMS
CREATE TABLE ITEMS (
	ID INTEGER NOT NULL,
	SELLER_ID INTEGER NOT NULL,
	TITLE VARCHAR2 (50),
	DESCRIPTION VARCHAR2 (200),
	START_PRICE NUMBER(*,2),
	TIME_LEFT NUMBER(*,0),
	START_BIDDING TIMESTAMP (6),
	BUY_IT_NOW CHAR(1),
	BID_INCREMENT NUMBER,
	SOLD CHAR(1)
	);
   
	CREATE UNIQUE INDEX ITEMS_PK ON ITEMS(ID);
   
	ALTER TABLE ITEMS
		ADD CONSTRAINT ITEMS_PK Primary Key(ID);

	ALTER TABLE ITEMS
		ADD CONSTRAINT ITEMS_BUY_IT_NOW_CHK CHECK (BUY_IT_NOW in ('N','Y')) ENABLE;
		
	ALTER TABLE ITEMS
		ADD CONSTRAINT ITEMS_SOLD_CHK CHECK (sold in ('N','Y')) ENABLE;
		
	ALTER TABLE ITEMS
		ADD CONSTRAINT ITEMS_FK FOREIGN KEY (SELLER_ID)
		REFERENCES USERS(ID) ENABLE;

--	Create Table BIDS
CREATE TABLE BIDS (
	ID INTEGER NOT NULL,
	BIDDER_ID INTEGER NOT NULL,
	ITEM_ID INTEGER NOT NULL,
	AMOUNT NUMBER(*,2)
	);
   
	CREATE UNIQUE INDEX BIDS_PK ON BIDS(ID);
   
	CREATE INDEX BIDS_BIDDER_IDX ON BIDS(BIDDER_ID);
   
	CREATE INDEX BIDS_ITEM_IDX ON BIDS(ITEM_ID);
   
	ALTER TABLE BIDS
		ADD CONSTRAINT BIDS_PK Primary Key(ID);
		
	ALTER TABLE BIDS
		ADD CONSTRAINT BIDS_FK1 FOREIGN KEY (BIDDER_ID)
		REFERENCES USERS(ID) ENABLE;
		
	ALTER TABLE BIDS
		ADD CONSTRAINT BIDS_FK2 FOREIGN KEY (ITEM_ID)
		REFERENCES ITEMS(ID) ENABLE;

Commit;