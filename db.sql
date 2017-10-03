create table wallet (
  id			    bigserial not null primary key,
  wallet			char(42),
  record_timestamp	bigint,
  current_hashrate	bigint,
  hashrate		    bigint,
  workers_offline	integer,
  workers_online	integer);

create table worker (
  id                bigserial not null primary key,
  name		        varchar(50),
  last_beat	        bigint,
  hr		        bigint,
  offline		    boolean,
  hr2		        bigint);

CREATE TABLE worker_to_wallet (
  wallet_id bigint NOT NULL REFERENCES wallet(id),
  worker_id bigint NOT NULL REFERENCES worker(id));