create database control_clientes;

use control_clientes;

create table clientes(id_cliente int not null auto_increment,nombre varchar(45), apellido varchar(45), email varchar(45), telefono int, saldo double, primary key (id_cliente));