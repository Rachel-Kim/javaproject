CREATE TABLE USERTABLE(
username CHAR(20) NOT NULL PRIMARY KEY Unique,
password CHAR(20) NOT NULL,
nickname CHAR(20),
email CHAR(30),
sex boolean,
online boolean,
image blob,
logindate blob 
);

CREATE TABLE Dictionary(
Word CHAR(30) PRIMARY KEY,
NumZanJinshan INT,
NumZanYoudao INT,
NumZanBing INT
);

CREATE TABLE Login(
username CHAR(20) NOT NULL PRIMARY KEY);

CREATE TABLE Wordcard(
sender CHAR(20) NOT NULL,
receiver CHAR(20) NOT NULL,
word CHAR(30) NOT NULL,
type CHAR(20) NOT NULL
);

CREATE TABLE JinshanPraise(
username CHAR(20) NOT NULL,
Word CHAR(30) NOT NULL,
CONSTRAINT CKey PRIMARY KEY(username,Word));

Create Trigger AddPraiseJinshan
After INSERT on JinshanPraise 
for each row
Update Dictionary
Set NumZanJinshan = NumZanJinshan + 1 where Word = new.Word;

Create Trigger DelPraiseJinshan
After DELETE on JinshanPraise 
for each row
Update Dictionary
Set NumZanJinshan = NumZanJinshan - 1 where Word = old.Word;

CREATE TABLE YouDaoPraise(
username CHAR(20) NOT NULL,
Word CHAR(30) NOT NULL,
CONSTRAINT CKey PRIMARY KEY(username,Word));

Create Trigger AddPraiseYouDao
After INSERT on YouDaoPraise 
for each row
Update Dictionary
Set NumZanYouDao = NumZanYouDao + 1 where Word = new.Word;

Create Trigger DelPraiseYouDao
After DELETE on YouDaoPraise 
for each row
Update Dictionary
Set NumZanYouDao = NumZanYouDao - 1 where Word = old.Word;

CREATE TABLE BingPraise(
username CHAR(20) NOT NULL,
Word CHAR(30) NOT NULL,
CONSTRAINT CKey PRIMARY KEY(username,Word));

Create Trigger AddPraiseBing
After INSERT on BingPraise 
for each row
Update Dictionary
Set NumZanBing = NumZanBing + 1 where Word = new.Word;

Create Trigger DelPraiseBing
After DELETE on BingPraise 
for each row
Update Dictionary
Set NumZanBing = NumZanBing - 1 where Word = old.Word;