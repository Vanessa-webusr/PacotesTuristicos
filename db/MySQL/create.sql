
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

insert into Agencia(cnpj, email, senha, nome, descricao) values ('11111111111', 'email@gmail.com.br', 'senha', 'LMPV Viagens', 'embarque nesse carrossel');    
    
insert into Pessoa(cpf, nome, telefone, sexo, data_nascimento, email, senha, cliente_admin) values ('22222222222', 'Lara', '119999999999', 'F', '1997-10-09', 'lara@gmail.com', '1234567', 1);    
    
insert into Pessoa(cpf, nome, telefone, sexo, data_nascimento, email, senha, cliente_admin) values ('33333333333', 'Fabio', '1640028922', 'M', '2000-08-20', 'cliente@gmail.com', '123456', 0);        
    
insert into Pacote(cnpj, agencia_id, cidade, estado, pais, data_partida, duracao_dias, valor, descricao) values ('0000000000000', 1, 'San Charles', 'SP', 'Brasil', '2022-01-12', 15, 1000.0, 'Tuscou');

insert into Foto(pacote_id, imagem) values(1, 'https://s2.glbimg.com/Mqwvldscd_dduyARe-XIyHiMkDw=/0x0:620x465/984x0/smart/filters:strip_icc()/s.glbimg.com/jo/g1/f/original/2015/09/09/prefeitura_sao_carlos_final_1.jpg');

insert into Compra(pacote_id, pessoa_id, valor) values(1, 1, 1000.0);    
    
    
