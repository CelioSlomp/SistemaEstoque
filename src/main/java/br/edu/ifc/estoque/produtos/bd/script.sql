create table compra(
	idProduto integer,
    valor double,
    quantidade integer
);

create table valores(
	idProduto integer,
    valor double,
    quantidade integer
);

CREATE TABLE cliente (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  bomPag int NOT NULL,
  PRIMARY KEY (id)
)

CREATE TABLE produto (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(45) NOT NULL,
  marca varchar(45) NOT NULL,
  tipo int NOT NULL,
  descricao varchar(255) DEFAULT NULL,
  unidade varchar(8) NOT NULL,
  PRIMARY KEY (id)
)

CREATE TABLE venda (
  idCliente int NOT NULL,
  idProduto int NOT NULL,
  quantidade int NOT NULL,
  valor double NOT NULL
)

select * from produto;
select * from cliente;
select * from valores;
select * from compra;
select * from produto, valores where id=idproduto;
delete from valores;
delete from compra;