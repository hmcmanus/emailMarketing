# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table email (
  id                        bigint not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  email_address             varchar(255),
  constraint pk_email primary key (id))
;

create sequence email_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists email;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists email_seq;

