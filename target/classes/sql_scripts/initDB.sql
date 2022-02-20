CREATE SCHEMA devj140;

CREATE TABLE devj140.users
(
    login    VARCHAR(16) PRIMARY KEY,
    password VARCHAR(16) NOT NULL
);

CREATE TABLE devj140.accounts
(
    id          INT,
    first_name  VARCHAR(50),
    last_name   VARCHAR(50),
    email       VARCHAR(50),
    gender      VARCHAR(50),
    credit_card VARCHAR(50),
    balance     VARCHAR(50)
);
insert into devj140.accounts (id, first_name, last_name, email, gender, credit_card, balance)
values (1, 'Ottilie', 'Bartoleyn', 'obartoleyn0@comcast.net', 'Male', '3541266941828383', '$7.03');
insert into devj140.accounts (id, first_name, last_name, email, gender, credit_card, balance)
values (2, 'Kellyann', 'Stenett', 'kstenett1@slashdot.org', 'Male', '5602257789641120', '$2.29');
insert into devj140.accounts (id, first_name, last_name, email, gender, credit_card, balance)
values (3, 'Deny', 'Bonham', 'dbonham2@paypal.com', 'Female', '3571707937862459', '$4.12');
insert into devj140.accounts (id, first_name, last_name, email, gender, credit_card, balance)
values (4, 'Fax', 'D''Ambrogio', 'fdambrogio3@gmpg.org', 'Polygender', '5007665098724406', '$1.87');
insert into devj140.accounts (id, first_name, last_name, email, gender, credit_card, balance)
values (5, 'Mozelle', 'Cake', 'mcake4@friendfeed.com', 'Female', '6759211112737005', '$6.20');
insert into devj140.accounts (id, first_name, last_name, email, gender, credit_card, balance)
values (6, 'Jorey', 'Bonar', 'jbonar5@digg.com', 'Female', '337941817412215', '$6.65');
