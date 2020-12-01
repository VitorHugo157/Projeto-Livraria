CREATE DATABASE PROJETO_LIVRARIA
GO
USE PROJETO_LIVRARIA

CREATE TABLE [Cliente](
cpf_cliente				CHAR(11)		NOT NULL,
nome_cliente			VARCHAR(45)		NOT NULL,
nascimento_cliente		DATE			NOT NULL,
logradouro_cliente		VARCHAR(45)		NOT NULL,
num_logradouro_cliente	INT				NOT NULL	CHECK(num_logradouro_cliente > 0),
complemento				VARCHAR(45)		NULL,
telefone_cliente		VARCHAR(11)		NOT NULL,
email_cliente			VARCHAR(45)		NOT NULL
PRIMARY KEY(cpf_cliente)
)

CREATE TABLE [Livro](
codigo_livro			INT				NOT NULL	CHECK(codigo_livro > 0),
nome_livro				VARCHAR(45)		NOT NULL,
idioma_livro			VARCHAR(10)		NOT NULL,
ano_lancamento			DATE			NOT NULL
PRIMARY KEY(codigo_livro)
)

CREATE TABLE [Cliente_Livro](
cpf_cliente_livro		CHAR(11)		NOT NULL,
codigo_livro_cliente	INT				NOT NULL
PRIMARY KEY(cpf_cliente_livro, codigo_livro_cliente)
FOREIGN KEY(cpf_cliente_livro) REFERENCES [Cliente](cpf_cliente),
FOREIGN KEY(codigo_livro_cliente) REFERENCES [Livro](codigo_livro)
)

CREATE TABLE [Autor](
codigo_autor		INT				NOT NULL	CHECK(codigo_autor > 0),
nome_autor			VARCHAR(45)		NOT NULL,
nascimento_autor	DATE			NOT NULL,
nacionalidade_autor	VARCHAR(45)		NOT NULL,
biografia_autor		VARCHAR(100)	NOT NULL
PRIMARY KEY(codigo_autor)
)

CREATE TABLE [Livro_Autor](
codigo_livro_autor		INT		NOT NULL,
codigo_autor_livro		INT		NOT NULL
PRIMARY KEY(codigo_livro_autor, codigo_autor_livro)
FOREIGN KEY(codigo_livro_autor)	REFERENCES [Livro](codigo_livro),
FOREIGN KEY(codigo_autor_livro)	REFERENCES [Autor](codigo_autor)
)

CREATE TABLE [Editora](
codigo_editora			INT			NOT NULL	CHECK(codigo_editora > 0),
nome_editora			VARCHAR(45) NOT NULL,
logradouro_editora		VARCHAR(45)	NOT NULL,
num_logradouro_editora	INT			NOT NULL	CHECK(num_logradouro_editora > 0),
cep_editora				CHAR(08)	NOT NULL,
telefone_editora		VARCHAR(11)	NOT NULL
PRIMARY KEY(codigo_editora)
)

CREATE TABLE [Edicao](
isbn				INT				NOT NULL	CHECK(isbn > 0),
preco_edicao		DECIMAL(7, 2)	NOT NULL	CHECK(preco_edicao > 0),
ano_edicao			DATE			NOT NULL,
num_paginas_edicao	INT				NOT NULL	CHECK(num_paginas_edicao > 0),
qtd_estoque			INT				NOT NULL	CHECK(qtd_estoque > 0)
PRIMARY KEY(isbn)
)

CREATE TABLE [Edicao_Editora_Livro](
isbn_edicao			INT		NOT NULL,
codigo_editora		INT		NOT NULL,
codigo_livro		INT		NOT NULL
PRIMARY KEY(isbn_edicao, codigo_editora, codigo_livro)
FOREIGN KEY(isbn_edicao) REFERENCES [Edicao](isbn),
FOREIGN KEY(codigo_editora) REFERENCES [Editora](codigo_editora),
FOREIGN KEY(codigo_livro) REFERENCES [Livro](codigo_livro)
)

SELECT * FROM [Cliente]

SELECT * FROM [Livro]

SELECT * FROM [Cliente_Livro]

SELECT * FROM [Autor]

SELECT * FROM [Livro_Autor]

SELECT * FROM [Edicao]

SELECT * FROM [Editora]

SELECT * FROM [Edicao_Editora_Livro]

