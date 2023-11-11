create database if not exists dbDomDomLanches;
use dbDomDomLanches;
create table tbUsuarios(
idUser int primary key auto_increment, 
Usuario varchar(50) not null,
Fone varchar(15),
Login varchar(15) not null unique,
Senha varchar(10)not null
);
alter table tbUsuarios add column perfil varchar(20) not null;
update tbUsuarios set perfil = 'admim' where idUser=1;
update tbUsuarios set perfil = 'user' where idUser=2;

describe tbUsuarios;
insert  into tbUsuarios(idUser,Usuario,Fone,Login,Senha)
values(1,'Adonai DomDom','1234567898','DomDom','12345');
insert  into tbUsuarios(Usuario,Fone,Login,Senha)
values('Tela','1234567898','tela','123');
select * from tbUsuarios;
update tbUsuarios set fone='0' where idUser='2';
create table tbClientes(
idCliente int primary key auto_increment,
nomeCliente varchar(50) not null,
cidadeCliente varchar(50) not null,
bairroCliente varchar(50) not null,
endCliente varchar(50) not null,
cepCliente varchar(50)  not null,
emailCLiente varchar(50) not null
);
create table tbCardapio(
idLanche int primary key auto_increment,
nomeLanche varchar(30) not null,
descLanche varchar(30) not null,
valorLanche decimal(4,2) not null);

create table tbBebidas(
idBebida int primary key auto_increment,
nomeBebida varchar(10) not null,          
valorBebidada decimal(4,2) not null);

create table tbPedidos(
idPedido int primary key auto_increment,
dataPedido timestamp default current_timestamp,
idCliente int,
idLanche int not null,
idBebida int not null,
foreign key (idCliente) references tbClientes (idCliente),
foreign key (idLanche) references tbCardapio (idLanche),
foreign key (idBebida) references tbBebidas (idBebida));









