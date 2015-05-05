--	Create Table Users 
CREATE TABLE Users (
	id INTEGER NOT NULL,
	fullName VARCHAR2 (50) NULL,
	billingAddress VARCHAR2 (100) NULL,
	login VARCHAR2 (20) NULL,
	password VARCHAR2 (20) NULL,
	email VARCHAR2 (50) NULL
	);
	
	CREATE UNIQUE INDEX Users_PK ON Users(id);
		
	CREATE UNIQUE INDEX Users_login_IDX ON Users(login);
		
	ALTER TABLE Users
		ADD CONSTRAINT Users_PK Primary Key(id);

--	Create Table Items
CREATE TABLE Items (
	id INTEGER NOT NULL,
	sellerId INTEGER NOT NULL,
	description VARCHAR2 (200),
	startPrice NUMBER(*,2),
	timeLeft NUMBER(*,0),
	startBidding TIMESTAMP (6),
	buyItNow CHAR(1),
	bidIncrement NUMBER
	);
   
	CREATE UNIQUE INDEX Items_PK ON Items(id);
   
	ALTER TABLE Items
		ADD CONSTRAINT Items_PK Primary Key(id);

	ALTER TABLE Items
		ADD CONSTRAINT Items_buyItNow_CHK CHECK (buyItNow in ('N','Y')) ENABLE;
		
	ALTER TABLE Items
		ADD CONSTRAINT Items_FK FOREIGN KEY (sellerId)
		REFERENCES Users(id) ENABLE;

--	Create Table Bids
CREATE TABLE Bids (
	id INTEGER NOT NULL,
	bidderId INTEGER NOT NULL,
	itemId INTEGER NOT NULL,
	bid NUMBER(*,2)
	);
   
	CREATE UNIQUE INDEX Bids_PK ON Bids(id);
   
	CREATE UNIQUE INDEX Bids_Bidder_IDX ON Bids(bidderId);
   
	CREATE UNIQUE INDEX Bids_Item_IDX ON Bids(itemId);
   
	ALTER TABLE Bids
		ADD CONSTRAINT Bids_PK Primary Key(id);
		
	ALTER TABLE Bids
		ADD CONSTRAINT Bids_FK1 FOREIGN KEY (bidderId)
		REFERENCES Users(id) ENABLE;
		
	ALTER TABLE Bids
		ADD CONSTRAINT Bids_FK2 FOREIGN KEY (itemId)
		REFERENCES Items(id) ENABLE;

Commit;