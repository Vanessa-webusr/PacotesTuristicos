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
    ativo int,
    primary key (id),
    foreign key (pacote_id) references Pacote(id),
    foreign key (pessoa_id) references Pessoa(id));

<<<<<<< HEAD
insert into Agencia(cnpj, email, senha, nome, descricao) values ('11111111111', 'agencia1@gmail.com.br', 'abc123', 'Agencia 1', 'descricao agencia de viagem 1');
insert into Agencia(cnpj, email, senha, nome, descricao) values ('22222222222', 'agencia2@gmail.com.br', 'abc123', 'Agencia 2', 'descricao agencia de viagem 2');
insert into Agencia(cnpj, email, senha, nome, descricao) values ('33333333333', 'agencia3@gmail.com.br', 'abc123', 'Agencia 3', 'descricao agencia de viagem 3');
insert into Agencia(cnpj, email, senha, nome, descricao) values ('44444444444', 'agencia4@gmail.com.br', 'abc123', 'Agencia 4', 'descricao agencia de viagem 4');
insert into Agencia(cnpj, email, senha, nome, descricao) values ('55555555555', 'agencia5@gmail.com.br', 'abc123', 'Agencia 5', 'descricao agencia de viagem 5');
    
insert into Pessoa(cpf, nome, telefone, sexo, data_nascimento, email, senha, cliente_admin) values ('111111111', 'Administrador', '00900000000', 'O', '2000-01-01', 'admin@gmail.com', 'abc123', 1);
insert into Pessoa(cpf, nome, telefone, sexo, data_nascimento, email, senha, cliente_admin) values ('222222222', 'Joao', '11911111111', 'H', '1990-05-08', 'joao@gmail.com', 'abc123', 0);
insert into Pessoa(cpf, nome, telefone, sexo, data_nascimento, email, senha, cliente_admin) values ('333333333', 'Maria', '22922222222', 'M', '1995-06-09', 'maria@gmail.com', 'abc123', 0);
insert into Pessoa(cpf, nome, telefone, sexo, data_nascimento, email, senha, cliente_admin) values ('444444444', 'Jose', '33933333333', 'H', '1999-07-10', 'jose@gmail.com', 'abc123', 0);
insert into Pessoa(cpf, nome, telefone, sexo, data_nascimento, email, senha, cliente_admin) values ('555555555', 'Gabriela', '44944444444', 'M', '2000-08-11', 'gabriela@gmail.com', 'abc123', 0); 
          
insert into Pacote(cnpj, agencia_id, cidade, estado, pais, data_partida, duracao_dias, valor, descricao) values ('11111111111', 1, 'Sao Carlos', 'SP', 'Brasil', '2022-08-12', 15, 1000.0, 'Terra do Tusca');
insert into Pacote(cnpj, agencia_id, cidade, estado, pais, data_partida, duracao_dias, valor, descricao) values ('11111111111', 1, 'Sao Paulo', 'SP', 'Brasil', '2022-07-31', 10, 800.0, 'Maior cidade do Brasil');
insert into Pacote(cnpj, agencia_id, cidade, estado, pais, data_partida, duracao_dias, valor, descricao) values ('11111111111', 1, 'Rio de Janeiro', 'RJ', 'Brasil', '2022-09-30', 7, 600.0, 'Cidade mais bonita do Brasil');
insert into Pacote(cnpj, agencia_id, cidade, estado, pais, data_partida, duracao_dias, valor, descricao) values ('22222222222', 2, 'Sao Paulo', 'SP', 'Brasil', '2022-10-31', 5, 400.0, 'Capital do estado de Sao Paulo');
insert into Pacote(cnpj, agencia_id, cidade, estado, pais, data_partida, duracao_dias, valor, descricao) values ('22222222222', 2, 'Los Angeles', 'CA', 'Estados Unidos', '2022-11-15', 3, 1500.0, 'Cidade mais bonita do mundo');
insert into Pacote(cnpj, agencia_id, cidade, estado, pais, data_partida, duracao_dias, valor, descricao) values ('22222222222', 2, 'New York', 'NY', 'Estados Unidos', '2022-07-31', 2, 1000.0, 'A grande maça');
insert into Pacote(cnpj, agencia_id, cidade, estado, pais, data_partida, duracao_dias, valor, descricao) values ('33333333333', 3, 'Londres', 'GL', 'Inglaterra', '2022-08-15', 15, 1000.0, 'Terra da Rainha');
insert into Pacote(cnpj, agencia_id, cidade, estado, pais, data_partida, duracao_dias, valor, descricao) values ('33333333333', 3, 'Paris', 'FR', 'França', '2022-09-30', 10, 800.0, 'Cidade da luz');
insert into Pacote(cnpj, agencia_id, cidade, estado, pais, data_partida, duracao_dias, valor, descricao) values ('44444444444', 3, 'Roma', 'IT', 'Italia', '2022-10-31', 7, 600.0, 'Terra dos romanos');
=======
insert into Agencia(cnpj, email, senha, nome, descricao) values ('11111111111', 'email@gmail.com', 'senha', 'LMPV Viagens', 'embarque nesse carrossel');    
    
insert into Pessoa(cpf, nome, telefone, sexo, data_nascimento, email, senha, cliente_admin) values ('22222222222', 'Lara', '119999999999', 'F', '1997-10-09', 'lara@gmail.com', '1234567', 1);    
    
insert into Pessoa(cpf, nome, telefone, sexo, data_nascimento, email, senha, cliente_admin) values ('33333333333', 'Fabio', '1640028922', 'M', '2000-08-20', 'cliente@gmail.com', '123456', 0);        
    
insert into Pacote(cnpj, agencia_id, cidade, estado, pais, data_partida, duracao_dias, valor, descricao) values ('0000000000000', 1, 'San Charles', 'SP', 'Brasil', '2022-01-12', 15, 1000.00, 'Tuscou');
>>>>>>> a6b1c76bd2f97c0aa558a71dc1d0fd733cb5d167

insert into Foto(pacote_id, imagem) values(1, 'https://s2.glbimg.com/Mqwvldscd_dduyARe-XIyHiMkDw=/0x0:620x465/984x0/smart/filters:strip_icc()/s.glbimg.com/jo/g1/f/original/2015/09/09/prefeitura_sao_carlos_final_1.jpg');
insert into Foto(pacote_id, imagem) values(1, 'https://emc.acidadeon.com/dbimagens/sao_carlos_790x444_09092020090902.jpg');
insert into Foto(pacote_id, imagem) values(1, 'https://www.gasbrasiliano.com.br/media/upload/noticias/2014/5/destaque/1_1395173273_saocarlosgeral.jpg');
insert into Foto(pacote_id, imagem) values(1, 'https://cdn.saocarlosagora.com.br/img/pc/780/780/dn_noticia_imagem/2015/11/motorista-nao-consegue-fazer-curva-e-capota-carro-na-rotatoria-da-ufscar-1.jpg?c=1');
insert into Foto(pacote_id, imagem) values(2, 'https://upload.wikimedia.org/wikipedia/commons/2/26/Bairro_dos_jardins_em_s%C3%A3o_paulo.jpg');
insert into Foto(pacote_id, imagem) values(2, 'https://a.cdn-hotels.com/gdcs/production48/d552/fce632fb-e113-4af1-88ab-1d909fe0a8a0.jpg?impolicy=fcrop&w=800&h=533&q=medium');
insert into Foto(pacote_id, imagem) values(2, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/16/fe/64/8d/masp-en-la-avenida-paulista.jpg?w=1200&h=1200&s=1');
insert into Foto(pacote_id, imagem) values(3, 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Cidade_Maravilhosa.jpg/268px-Cidade_Maravilhosa.jpg');
insert into Foto(pacote_id, imagem) values(3, 'https://a.cdn-hotels.com/gdcs/production187/d1216/5791a1f0-c31d-11e8-9739-0242ac110006.jpg?impolicy=fcrop&w=800&h=533&q=medium');
insert into Foto(pacote_id, imagem) values(4, 'https://observatoriog.bol.uol.com.br/wordpress/wp-content/uploads/2019/08/cropped-sao-paulo-cabecalho.jpg');
insert into Foto(pacote_id, imagem) values(4, 'https://upload.wikimedia.org/wikipedia/commons/2/26/Bairro_dos_jardins_em_s%C3%A3o_paulo.jpg');
insert into Foto(pacote_id, imagem) values(5, 'https://viagemeturismo.abril.com.br/wp-content/uploads/2016/12/sunset-boulevard-los-angeles-estados-unidos1.jpeg?quality=70&strip=info&w=928&w=636');
insert into Foto(pacote_id, imagem) values(5, 'https://lh3.googleusercontent.com/RRLXw8BVma4Tzwyhwnzqi6JiYRahatiNDdkReTqfUZv6hImHN72Xu9yTThOAVaA');
insert into Foto(pacote_id, imagem) values(5, 'https://viagemeturismo.abril.com.br/wp-content/uploads/2016/12/santa-monica-los-angeles-eua-stevebev-creative-commons.jpeg?quality=70&strip=info&w=922&w=636');
insert into Foto(pacote_id, imagem) values(5, 'https://www.visiteosusa.com.br/sites/default/files/styles/hero_l/public/images/hero_media_image/2017-01/Getty_596330521_SantaMonicaPierVeniceBeach_Web72DPI_0.jpg?h=f8f07ecc&itok=QBhhVqG4');
insert into Foto(pacote_id, imagem) values(5, 'https://estadosunidosbrasil.com.br/files/2013/05/losangeles1-635x411.jpg');
insert into Foto(pacote_id, imagem) values(5, 'https://www.przewodnik-usa.pl/wp-content/uploads/2020/05/Los-Angeles-centrum.jpg');
insert into Foto(pacote_id, imagem) values(5, 'https://www.dicasdeviagem.com/wp-content/uploads/2019/02/hollywood-sign-1.jpg');
insert into Foto(pacote_id, imagem) values(5, 'https://dicasdacalifornia.com.br/wp-content/uploads/2015/05/ponto-turistico-observatorio-em-los-angeles.jpg');
insert into Foto(pacote_id, imagem) values(5, 'http://uploads.metropoles.com/wp-content/uploads/2022/03/10141243/Los_Angeles_California_Pacote_de_Viagem.jpg');
insert into Foto(pacote_id, imagem) values(5, 'https://www.ie.com.br/wp-content/uploads/2022/03/principais-pontos-turisticos-los-angeles-1024x540.jpeg');
insert into Foto(pacote_id, imagem) values(6, 'https://a.cdn-hotels.com/gdcs/production102/d328/e79c53d0-c31d-11e8-825c-0242ac110006.jpg?impolicy=fcrop&w=800&h=533&q=medium');
insert into Foto(pacote_id, imagem) values(6, 'https://a.cdn-hotels.com/gdcs/production138/d1142/08366677-a8d0-4979-84d5-b54f93ca00e5.jpg?impolicy=fcrop&w=800&h=533&q=medium');
insert into Foto(pacote_id, imagem) values(6, 'https://cdn2.civitatis.com/estados-unidos/nueva-york/guia/times-square-grid-m.jpg');
insert into Foto(pacote_id, imagem) values(7, 'https://culturainglesamg.com.br/app/uploads/2020/01/7.png');
insert into Foto(pacote_id, imagem) values(7, 'https://media.ticmate.com//resources/ticmate_live/upload_go/9e062c852a50939e-LondonEyeMobile4.jpg');
insert into Foto(pacote_id, imagem) values(7, 'https://www.flytap.com/-/media/Flytap/new-tap-pages/destinations/europe/uk/london/destinations-london-banner-mobile-1024x553.jpg');
insert into Foto(pacote_id, imagem) values(7, 'https://media.gettyimages.com/photos/the-big-ben-in-london-and-the-house-of-parliament-picture-id957174246?s=612x612');
insert into Foto(pacote_id, imagem) values(8, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/1b/4b/59/86/caption.jpg?w=500&h=300&s=1');
insert into Foto(pacote_id, imagem) values(8, 'https://f7j8i5n9.stackpathcdn.com/wp-content/uploads/2015/03/regiao-avenida-champs-elysees-paris-franca.png');
insert into Foto(pacote_id, imagem) values(8, 'https://viagemeturismo.abril.com.br/wp-content/uploads/2016/10/paris-verao-franca.jpeg?quality=70&strip=info&w=920&w=636');
insert into Foto(pacote_id, imagem) values(9, 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Colosseo_2020.jpg/640px-Colosseo_2020.jpg');

<<<<<<< HEAD
insert into Compra(pacote_id, pessoa_id, valor, ativo) values(1, 1, 1000.0, 1);
insert into Compra(pacote_id, pessoa_id, valor, ativo) values(2, 1, 2000.0, 1);
insert into Compra(pacote_id, pessoa_id, valor, ativo) values(6, 1, 2000.0, 0);
insert into Compra(pacote_id, pessoa_id, valor, ativo) values(9, 1, 500.0, 1);
insert into Compra(pacote_id, pessoa_id, valor, ativo) values(3, 2, 3000.0, 0);
insert into Compra(pacote_id, pessoa_id, valor, ativo) values(4, 2, 4000.0, 1);
insert into Compra(pacote_id, pessoa_id, valor, ativo) values(5, 2, 5000.0, 1);
insert into Compra(pacote_id, pessoa_id, valor, ativo) values(6, 2, 6000.0, 1);
insert into Compra(pacote_id, pessoa_id, valor, ativo) values(7, 2, 7000.0, 1);
insert into Compra(pacote_id, pessoa_id, valor, ativo) values(2, 3, 8000.0, 1);
insert into Compra(pacote_id, pessoa_id, valor, ativo) values(3, 3, 9000.0, 0);
insert into Compra(pacote_id, pessoa_id, valor, ativo) values(8, 3, 1000.0, 1);
insert into Compra(pacote_id, pessoa_id, valor, ativo) values(4, 4, 2000.0, 1);
insert into Compra(pacote_id, pessoa_id, valor, ativo) values(5, 4, 3000.0, 1);    
=======
insert into Compra(pacote_id, pessoa_id, valor, ativo) values(1, 1, 1000.00, 1);    
>>>>>>> a6b1c76bd2f97c0aa558a71dc1d0fd733cb5d167
    
