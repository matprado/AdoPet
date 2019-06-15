CREATE DATABASE adopet;

USE adopet;

CREATE TABLE if not exists pets(
	
    pet_id INTEGER NOT NULL AUTO_INCREMENT primary key,
	species varchar(20) NOT NULL,
    nome varchar(50) NOT NULL,
    sexo VARCHAR(20) NOT NULL,
    detalhes VARCHAR(100) not null,
    id_doador LONG NOT NULL,
    imagem MEDIUMBLOB
);

ALTER TABLE pets ADD UNIQUE(pet_id);

CREATE TABLE if not exists clientes(
	
    cliente_id integer NOT NULL AUTO_INCREMENT primary key,
    username VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(75) NOT NULL,
	nome VARCHAR(50) NOT NULL,
    cpf varchar(15) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    cep VARCHAR(20)
	
);
ALTER TABLE clientes ADD UNIQUE(cliente_id);


CREATE TABLE IF NOT EXISTS chat(

    chat_id INTEGER  NOT NULL AUTO_INCREMENT primary key,
    user1_id integer NOT NULL,
    user2_id integer NOT NULL    
);

ALTER TABLE chat ADD UNIQUE(chat_id);

CREATE TABLE IF NOT EXISTS mensagens(

    id_mensagem INTEGER NOT NULL AUTO_INCREMENT primary key,
    id_chat INTEGER NOT NULL,
    id_remetente integer NOT NULL,
    mensagem TEXT NOT NULL,
    key message_length (mensagem(255)),
    FOREIGN KEY(id_chat) REFERENCES chat(chat_id)
);

ALTER TABLE mensagens ADD UNIQUE(id_mensagem);

SELECT * FROM pets;

DELETE FROM pets WHERE pet_id=1;

DELETE FROM clientes WHERE cliente_id=2;
