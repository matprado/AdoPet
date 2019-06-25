#CRIANDO E USANDO O DATABASE
CREATE DATABASE adopet;
USE adopet;

CREATE TABLE if not exists pets(
	
    pet_id INTEGER NOT NULL AUTO_INCREMENT primary key,
	species varchar(20) NOT NULL,
    nome varchar(50) NOT NULL,
    sexo VARCHAR(20) NOT NULL,
    detalhes VARCHAR(256) not null,
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
    user1_id INTEGER NOT NULL,
    user2_id INTEGER NOT NULL,
    confirma_user1 boolean NOT NULL,
    confirma_user2 boolean NOT NULL,
	pet_id INTEGER NOT NULL
);
ALTER TABLE chat ADD UNIQUE(chat_id);

CREATE TABLE IF NOT EXISTS mensagens(

    id_mensagem INTEGER NOT NULL AUTO_INCREMENT primary key,
    id_chat INTEGER NOT NULL,
    id_remetente integer NOT NULL,
    mensagem TEXT NOT NULL,
    key message_length (mensagem(256)),
    FOREIGN KEY(id_chat) REFERENCES chat(chat_id)
);
ALTER TABLE mensagens ADD UNIQUE(id_mensagem);


######COMANDOS DE USO ESPECIFICO#######

#DROPANDO TABLES
DROP TABLE mensagens;
DROP TABLE chat;
DROP TABLE pets;
DROP TABLE clientes;

#EXIBINDO TABLES
SELECT * FROM mensagens;
SELECT * FROM chat;
SELECT * FROM clientes;
SELECT * FROM pets;
