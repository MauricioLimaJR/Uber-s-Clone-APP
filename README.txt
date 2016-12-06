this is a project to LPOO discipline from Pernambuco university.
The deal here is create a program, in Java language, that sees like the Uber program.

HOW TO USE:
(translate it**)

Requesitos:

1. Eclipse IDE (ou outra IDE compatível);
2. JDK e JVM (mais recente);
3. JavaFX plugin;
4. MySQL Server (Workbench, Xampp, Wampp, etc);

Montando ambiente de trabalho:

1. Instalar o Eclipse, bem como os produtos Java;
2. Para instalar o JavaFX plugin:
   a. Acessar o Eclipse MarketPlace, no botão Help,
   b. Pesquisar por "javafx",
   c. instalar última versão ou ver 2.4.
3. Instalar algum criador de servidor mySQL:
a. Escolher algum programa compatível,

3.Obs> Os passos seguintes são para recriar um 
	banco de dados idêntico ao ja configurado;
   b. Instalar e criar uma Database chamada "local",
   c. Criar as tabelas de acordo com os arquivos
      contidos na pasta "database files".

3.Obs> Os passos seguintes são para configurar um 
	novo bando de dados;
   b. criar um novo banco de dados ou usar um ja criado:
   c. Acessar o pacote "package br.acme.database"
   d. Abrir a classe "ConnectionMaker.java";
   e. Alterar o parâmetro da linha 14:
   "jdbc:mysql://localhost/local", "root", "4321"
   onde:
   "jdbc:mysql://(campo1)/(campo2)", "(campo3)", "(campo4)"
   (campo1) = endereço do banco de dados (localhost geralmente)
   (campo2) = nome do banco de dados
   (campo3) = nome do usuário do banco de dados
   (campo4) = senha do usuário do banco de dados
   f. salvar alterações;

Executando o Projeto

1. Após realizar as configurações, para rodar o projeto:
   a. Acessar o pacote "package br.acme.ui";
   b. Abrir a classe "MainWindow.java";
   c. Executar o código como uma aplicaçao java;

OBS:: Existe um único gerente para este sistema
	para acessar sua conta:
	email gerente = adm@gmail.com
	senha gerente = admin

OBS:: Todos os dados deste programa são fictíceis,
	com excessão dos cpfs utilizados, pois são
	aplicados a validações reais. Assim, é li-
	vre utilizar qualquer endereço de email pa-
	ra cadastros.
	Pesquisar no Google : "gerador de cpf" para
	adquirir cpf que sejam válidos.