CREATE DATABASE project

USE project

CREATE TABLE if not exists pets(
	
    pet_id LONG NOT NULL,
	species varchar(20) NOT NULL,
    nome varchar(50) NOT NULL,
    sexo VARCHAR(20) NOT NULL,
    detalhes VARCHAR(100) not null,
	nome_doador VARCHAR(50) not null
)

CREATE TABLE if not exists clientes(
	
    cliente_id LONG NOT NULL,
    username VARCHAR(50) NOT NULL,
    senha VARCHAR(75) NOT NULL,
	nome VARCHAR(50) NOT NULL,
    idade int NOT NULL,
    cpf varchar(15) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    cep VARCHAR(20)
	
)

CREATE TABLE IF NOT EXISTS adocao(
	pet_id int NOT NULL,
    cliente_id int NOT NULL

)



