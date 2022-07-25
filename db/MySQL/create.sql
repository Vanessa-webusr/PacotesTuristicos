<<<<<<< HEAD
drop database if exists Turismo;
create database Turismo;

use Turismo;

create table Pessoa(
    id bigint not null auto_increment,
    cpf varchar(18) unique not null,
    nome varchar(40) not null,
    telefone varchar(20),
    sexo char(1),
    data_nascimento date,
    email varchar(30),
    senha varchar(15),
    cliente_admin int,
    primary key (id));
    
create table Agencia(
    id bigint not null auto_increment,
    cnpj varchar(18) unique not null,
    email varchar(30),
    senha varchar(15) not null,
    nome varchar(30),
    descricao varchar(200),
    primary key (id));
    
create table Pacote(
    id bigint not null auto_increment,
    cnpj varchar(18) not null,
    agencia_id bigint not null,
    cidade varchar(20),
    estado varchar(20),
    pais varchar(20),
    data_partida date,
    duracao_dias int,
    valor double precision, 
    descricao varchar(200),
    primary key (id), 
    foreign key (agencia_id) references Agencia(id));
    
create table Foto(
    id bigint not null auto_increment,
    pacote_id bigint not null,  
    imagem MEDIUMTEXT,
    primary key (id),
    foreign key (pacote_id) references Pacote(id));

create table Compra(
    id bigint not null auto_increment,
    pacote_id bigint not null,
    pessoa_id bigint not null,
    valor double precision,
    primary key (id),
    foreign key (pacote_id) references Pacote(id),
    foreign key (pessoa_id) references Pessoa(id));
=======
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

>>>>>>> main
