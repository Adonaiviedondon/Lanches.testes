create database if not exists dbDomDomLanches;
use dbDomDomLanches;
create table tbUsuarios(
idUser int primary key auto_increment, 
Usuario varchar(50) not null,
Fone varchar(15),
Login varchar(15) not null unique,
Senha varchar(10)not null,
Perfil varchar(10) not null
);

describe tbUsuarios;
insert  into tbUsuarios(idUser,Usuario,Fone,Login,Senha,Perfil)
values(1,'Adonai DomDom','1234567898','Dom','123','admin');
insert  into tbUsuarios(Usuario,Fone,Login,Senha,Perfil)
values('Tela','1234567898','tela','123','User');
select * from tbUsuarios;

create table tbClientes(
idCliente int primary key auto_increment,
nomeCliente varchar(50) not null,
cidadeCliente varchar(50) not null,
bairroCliente varchar(50) not null,
endCliente varchar(50) not null,
numCliente varchar(10) not null,
cepCliente varchar(50)  not null,
emailCLiente varchar(50) not null,
foneCliente varchar(15) not null
);
create table tbProduto(
idProduto int primary key auto_increment,
nomeLanche varchar(30) not null,
descLanche varchar(30) not null,
valorLanche decimal(4,2) not null);

create table tbPedidos(
idPedido int primary key auto_increment,
dataPedido timestamp default current_timestamp,
idCliente int,
foreign key (idCliente) references tbClientes (idCliente));

create table tbProdutosPedidos(
idPedido int not null,
idProduto int not null,
foreign key (idPedido) references tbPedidos (idPedido),
foreign key (idProduto) references tbProduto (idProduto));


