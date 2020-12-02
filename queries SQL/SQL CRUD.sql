USE PROJETO_LIVRARIA

INSERT INTO [Autor] (codigo_autor, nome_autor, nascimento_autor, nacionalidade_autor, biografia_autor)
VALUES 
(1, 'Antonio', '15/06/1990', 'Brasileiro', 'Desenvolvedor de sistemas'),
(2, 'João', '10/09/1989', 'Brasileiro', 'Analista de sistemas')

UPDATE [Autor]
SET nome_autor = 'João da Silva'
WHERE codigo_autor = 2

DELETE [Autor] WHERE codigo_autor = 1

SELECT * FROM [Autor] WHERE nome_autor LIKE '%a%'



--Populando dados na tabela [Cliente]
INSERT INTO [Cliente] (cpf_cliente, nome_cliente, nascimento_cliente, logradouro_cliente, num_logradouro_cliente, complemento, telefone_cliente, email_cliente)
VALUES
('12345678910', 'Jaqueline', '30/06/2000', 'Rua tal', 123, NULL, '11123456789', 'jaqueline@email.com'),
('10987654321', 'Gabriel', '19/08/1990', 'Rua abc', 456, 'Casa', '1112345678', 'gabriel@email.com')

UPDATE [Cliente]
SET complemento = 'Casa do fundo'
WHERE cpf_cliente = '10987654321'

DELETE [Cliente]
WHERE cpf_cliente = '12345678910'

SELECT SUBSTRING(cpf_cliente, 1, 3) + '.' + SUBSTRING(cpf_cliente, 4, 3) +
'.' + SUBSTRING(cpf_cliente, 7, 3)  + '-' + SUBSTRING(cpf_cliente, 10, 2) AS cpf, 
nome_cliente, nascimento_cliente, logradouro_cliente, num_logradouro_cliente, complemento, 
CASE WHEN (LEN(telefone_cliente) = 11) THEN '(' + SUBSTRING(telefone_cliente, 1, 2) + ')' + 
SUBSTRING(telefone_cliente, 3, 5) + '-' + SUBSTRING(telefone_cliente, 8, 4) ELSE 
'(' + SUBSTRING(telefone_cliente, 1, 2) + ')' + SUBSTRING(telefone_cliente, 3, 4) + '-' + SUBSTRING(telefone_cliente, 7, 4)
END AS tel_cliente, email_cliente
FROM [Cliente] WHERE nome_cliente LIKE '%a%'



--Populando dados na tabela [Livro]
INSERT INTO [Livro] (codigo_livro, nome_livro, idioma_livro, ano_lancamento)
VALUES 
(1, 'Banco de dados', 'PT-BR', '05/04/2005'),
(2, 'Engenharia de software III', 'PT-BR', '19/09/2010')

UPDATE [Livro]
SET nome_livro = 'Principios de Engenharia de software'
WHERE codigo_livro = 2

DELETE [Livro] WHERE codigo_livro = 2

SELECT * FROM [Livro] WHERE codigo_livro = 1



--Populando dados na tabela [Edicao]
INSERT INTO [Edicao] (isbn, preco_edicao, ano_edicao, num_paginas_edicao, qtd_estoque)
VALUES
(123, 99.90, '06/08/2006', 198, 99),
(456, 84.99, '20/09/2012', 250, 121)

UPDATE [Edicao]
SET preco_edicao = 129.99
WHERE isbn = 123

DELETE [Edicao] WHERE isbn = 456

SELECT * FROM [Edicao] WHERE isbn = 123



--Populando dados na tabela [Editora]
INSERT INTO [Editora] (codigo_editora, nome_editora, logradouro_editora, num_logradouro_editora, cep_editora, telefone_editora)
VALUES 
(1, 'Pearson', 'Rua haia', 171, '01234567', '11912345678'),
(2, 'Fulana books', 'Rua aguia', 155, '01234891', '11912435695')

UPDATE [Editora]
SET nome_editora = 'Books lt'
WHERE codigo_editora = 2

DELETE [Editora] WHERE codigo_editora = 2

SELECT * FROM [Editora] WHERE nome_editora LIKE '%a%'



--Populando dados na tabela [Edicao_Editora_Livro]
INSERT INTO [Edicao_Editora_Livro] (isbn_edicao, codigo_editora, codigo_livro)
VALUES 
(123, 1, 1),
(456, 2, 2)

DELETE [Edicao_Editora_Livro]
WHERE isbn_edicao = 123 AND codigo_editora = 1 AND codigo_livro = 1

SELECT eel.isbn_edicao AS isbn, eel.codigo_editora AS codigo_editora, eel.codigo_livro AS codigo_livro, 
edicao.preco_edicao AS preco, edicao.ano_edicao AS ano_edicao, 
editora.nome_editora AS nome_editora, 
CASE WHEN (LEN(editora.telefone_editora) = 11) THEN '(' + SUBSTRING(editora.telefone_editora, 1, 2) + ')' + 
SUBSTRING(editora.telefone_editora, 3, 5) + '-' + SUBSTRING(editora.telefone_editora, 8, 4) ELSE 
'(' + SUBSTRING(editora.telefone_editora, 1, 2) + ')' + SUBSTRING(editora.telefone_editora, 3, 4) + '-' + SUBSTRING(editora.telefone_editora, 7, 4) 
END AS tel_editora, 
l.nome_livro AS nome_livro, l.idioma_livro AS idioma_livro, l.ano_lancamento 
FROM [Edicao] edicao, [Editora] editora, [Livro] l, [Edicao_Editora_Livro] eel 
WHERE edicao.isbn = eel.isbn_edicao AND 
editora.codigo_editora = eel.codigo_editora AND 
l.codigo_livro = eel.codigo_livro



--Populando dados na tabela [Cliente_Livro]
INSERT INTO [Cliente_Livro] (cpf_cliente_livro, codigo_livro_cliente)
VALUES
('12345678910', 2),
('10987654321', 1)

UPDATE [Cliente_Livro]
SET codigo_livro_cliente = 2
WHERE cpf_cliente_livro = '10987654321'

DELETE [Cliente_Livro] 
WHERE cpf_cliente_livro = '12345678910' 
	AND codigo_livro_cliente = 2

SELECT SUBSTRING(cpf_cliente, 1, 3) + '.' + SUBSTRING(cpf_cliente, 4, 3) + 
'.' + SUBSTRING(cpf_cliente, 7, 3)  + '-' + SUBSTRING(cpf_cliente, 10, 2) AS cpf, cl.codigo_livro_cliente AS codigo_livro, 
c.nome_cliente AS nome_cliente, 
l.nome_livro AS nome_livro, l.idioma_livro AS idioma_livro, l.ano_lancamento AS ano_lancamento 
FROM [Cliente] c, [Livro] l, [Cliente_Livro] cl 
WHERE c.cpf_cliente = cl.cpf_cliente_livro AND 
l.codigo_livro = cl.codigo_livro_cliente




--Populando dados na tabela [Livro_Autor]
INSERT INTO [Livro_Autor] (codigo_livro_autor, codigo_autor_livro)
VALUES 
(2, 1),
(1, 2)

DELETE [Livro_Autor] 
WHERE codigo_livro_autor = 1 AND codigo_autor_livro = 2

SELECT la.codigo_livro_autor AS codigo_livro, la.codigo_autor_livro AS codigo_autor,
l.nome_livro AS nome_livro, l.idioma_livro AS idioma_livro, l.ano_lancamento AS ano_lancamento,
a.nome_autor AS nome_autor, a.nacionalidade_autor AS nacionalidade_autor
FROM [Livro] l, [Autor] a, [Livro_Autor] la
WHERE l.codigo_livro = la.codigo_livro_autor AND a.codigo_autor = la.codigo_autor_livro
