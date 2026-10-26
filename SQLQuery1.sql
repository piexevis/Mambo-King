-- Creacion de base de datos
create database Consulta

--Tablas
create table Cliente(
IdCliente int primary key identity(1,1) not null ,
Nombre varchar(50) not null,
Edad int not null,
Correo varchar(50) not null,
Rut varchar(10) not null,
)
create table Consulta(
IdConsulta int primary key identity(1,1) not null ,
Fecha varchar(50) not null,
Hora varchar(50) not null,
Tipo varchar(50) not null,
Medico varchar(50) not null,
Costo int not null,
)

alter table Consulta
add IdCliente int;
alter table Consulta 
add constraint FK_Consulta_cliente
Foreign key (IdCliente) references Cliente(IdCliente);

alter table Cliente
add Constraint UQ_Cliente_Rut unique (Rut)
