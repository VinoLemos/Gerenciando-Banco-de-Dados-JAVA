# Efetuando transações na tabela do banco de dados com Java

Este repositório contém um exemplo de como acessar e efetuar transações em uma tabela no banco de dados MySQL.

O arquivo database.sql que vem incluído na pasta do projeto, contém a query de criação da tabela,
já com os valores.

Há um bloco com linhas de query a serem executadas.

No início do bloco, desativamos o setAutoCommit(), que auto confirma e aplica as alterações no banco de dados.

Este bloco está protegido por um try catch: Caso não ocorra nenhum erro,
os comandos serão executados através do comando commit(), e as alterações serão aplicadas na tabela.

Caso ocorra algum erro, executara o comando "rollback()", voltando ao estado anterior às alterações, desta forma nós protegemos
a integridade do nosso banco de dados.

