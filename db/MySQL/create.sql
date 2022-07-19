drop database if exists Turismo;
create database Turismo;

use Turismo;

create table Pessoa(
    id bigint not null auto_increment,
    cpf varchar(18) not null,
    nome varchar(40) not null,
    telefone varchar(20),
    sexo char(1),
    data_nascimento date,
    email varchar(30),
    senha varchar(15),
    cliente_admin int,
    primary key (id, cpf));
    
create table Agencia(
    id bigint not null auto_increment,
    cnpj varchar(18) not null,
    email varchar(30),
    senha varchar(15) not null,
    nome varchar(30),
    descricao varchar(200),
    primary key (id, cnpj));
    
create table Pacote(
    id bigint not null auto_increment,
    cnpj varchar(18) not null,
    agencia_id bigint not null,
    cidade varchar(20),
    estado varchar(20),
    pais varchar(20),
    data_partida date,
    duracao_dias date,
    valor double precision, 
    descricao varchar(200),
    primary key (id, cnpj, agencia_id), 
    foreign key (agencia_id, cnpj) references Agencia(id, cnpj));
    
create table Foto(
    codigo bigint not null auto_increment,
    pacote_id bigint not null,
    cnpj varchar(18) not null,
    agencia_id bigint not null,   
    fotos_url varchar(30),
    primary key (codigo, cnpj, pacote_id, agencia_id),
    foreign key (pacote_id, cnpj, agencia_id) references Pacote(id, cnpj, agencia_id));

