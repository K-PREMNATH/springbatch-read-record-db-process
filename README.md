**Reference Documentation**
For further reference, please consider the following sections:

* Table Schema
  create table externalcustomer
  (
  id         int auto_increment
  primary key,
  firstname  varchar(255) not null,
  lastname   varchar(255) not null,
  age        int          not null,
  basic_wage double       not null,
  allowance  double       not null
  );

create table internalcustomer
(
id         int auto_increment
primary key,
firstname  varchar(255) not null,
lastname   varchar(255) not null,
age        int          not null,
total_wage double       not null
);

* Item Reader Query
  SELECT Id,FirstName,LastName,TotalWage
  FROM (
  SELECT
  id as Id,
  firstname AS FirstName,
  lastname AS LastName,
  total_wage AS TotalWage
  FROM InternalCustomer
  UNION ALL
  SELECT
  id as Id,
  firstname AS FirstName,
  lastname AS LastName,
  (basic_wage + allowance)  AS TotalWage
  FROM ExternalCustomer
  ) AS CombinedCustomers;
