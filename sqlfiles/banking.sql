--Just putting these here for refreshing later.
DROP TABLE BANKACCOUNTS;

CREATE TABLE BANKACCOUNTS(
	ACCOUNTNUMBER NUMBER(10) PRIMARY KEY,
	ACCOUNTTYPE varchar2(255) NOT NULL check(LENGTH(accountType) > 1),
	FIRSTNAME varchar2(255) NOT NULL check(LENGTH(FIRSTNAME) > 1),
	LASTNAME varchar2(255) NOT NULL check(LENGTH(LASTNAME) > 1),
	USERNAME varchar2(255) NOT NULL check(LENGTH(USERNAME) > 1),
	PASSWORD varchar2(255) NOT NULL check(LENGTH(PASSWORD) > 1),
	BALANCE DECIMAL(38, 2)
);

DROP SEQUENCE bank_seq;

CREATE SEQUENCE bank_seq
	START WITH 100000
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER bank_seq_trigger
BEFORE INSERT ON BANKACCOUNTS
FOR EACH ROW
BEGIN
	IF : NEW.ACCOUNTNUMBER IS NULL THEN
	SELECT bank_seq.nextval INTO : NEW.ACCOUNTNUMBER FROM dual;
	END IF;
END;

INSERT INTO BANKACCOUNTS(ACCOUNTNUMBER, ACCOUNTTYPE, FIRSTNAME, LASTNAME, USERNAME, PASSWORD, BALANCE)
VALUES(100000, 'CUSTOMER', 'John', 'Jacobsen', 'jj_number1', 'isTHISaGOODpassword?', 100001.28);

SELECT * FROM BANKACCOUNTS;