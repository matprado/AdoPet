CREATE DATABASE project

USE project

CREATE TABLE if not exists pets(
	
    pet_id LONG NOT NULL,
	species varchar(20) NOT NULL,
    nome varchar(50) NOT NULL,
    sexo VARCHAR(20) NOT NULL,
    detalhes VARCHAR(100) not null,
	nome_doador VARCHAR(50) not null,
    caminho_imagem_pet blob
)

ALTER TABLE pets ADD UNIQUE(pet_id);
ALTER TABLE pets ADD PRIMARY KEY (pet_id)

CREATE TABLE if not exists clientes(
	
    cliente_id LONG NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(75) NOT NULL,
	nome VARCHAR(50) NOT NULL,
    idade int NOT NULL,
    cpf varchar(15) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    cep VARCHAR(20)
	
)
ALTER TABLE clientes ADD UNIQUE(cliente_id)
ALTER TABLE clientes ADD PRIMARY KEY(cliente_id)

/*
CREATE TABLE IF NOT EXISTS adocao(
	pet_id int NOT NULL,
    cliente_id int NOT NULL

)
*/

CREATE TABLE IF NOT EXISTS chat(

    chat_id LONG  NOT NULL AUTO_INCREMENT,
    user1_id LONG NOT NULL,
    user2_id LONG NOT NULL
    
    
)

ALTER TABLE chat ADD UNIQUE(chat_id)
ALTER TABLE chat ADD PRIMARY KEY(chat_id)

CREATE TABLE IF NOT EXISTS mensagens(

    id_mensagem LONG NOT NULL,
    id_chat LONG NOT NULL,
    id_remetente LONG NOT NULL,
    mensagem TEXT NOT NULL,
    FOREIGN KEY(id_chat) REFERENCES chat(chat_id)
)

ALTER TABLE mensagens ADD UNIQUE(id_mensagem)
ALTER TABLE mensagens ADD PRIMARY KEY(id_mensagem)