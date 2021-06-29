insert into user values ('123456', 'kuba', 'domagala', '1234', 'kuba' );
insert into account values ('23232323', 'Savings');

insert into bank values (1, 'JD-Bank');

insert into user_accounts values ('123456','23232323' );

insert into bank_users values (1, '123456');

insert into bank_accounts values (1, '23232323');

insert into transaction values (1, 50.0, 'abc', '2020-12-12');

insert into account_transactions values ('23232323', 1);