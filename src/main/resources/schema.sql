create table if not exists Ingredient
(
  id   varchar(4)  not null,
  name varchar(25) not null,
  type varchar(10) not null
);

create table if not exists Shaurma
(
  id        identity,
  name      varchar(50) not null,
  createdAt timestamp   not null
);
create table if not exists Shaurma_Ingredients
(
  shaurma    bigint     not null,
  ingredient varchar(4) not null
);
alter table Shaurma_Ingredients
  add foreign key (shaurma) references Shaurma (id);
alter table Shaurma_Ingredients
  add foreign key (ingredient) references Ingredient (id);
create table if not exists Shaurma_Order
(
  id             identity,
  deliveryName   varchar(50) not null,
  deliveryStreet varchar(50) not null,
  deliveryCity   varchar(50) not null,
  deliveryState  varchar(2)  not null,
  deliveryZip    varchar(10) not null,
  ccNumber       varchar(16) not null,
  ccExpiration   varchar(5)  not null,
  ccCVV          varchar(3)  not null,
  placedAt       timestamp   not null
);
create table if not exists Shaurma_Order_Shaurmas
(
  shaurmaOrder bigint not null,
  shaurma      bigint not null
);

alter table Shaurma_Order_Shaurmas
  add foreign key (shaurmaOrder) references Shaurma_Order (id);
alter table Shaurma_Order_Shaurmas
  add foreign key (shaurma) references Shaurma (id);

