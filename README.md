# NSRP CHALLENGE

Aplicação com finalidade de cadastro de socio torcedor.

#### Sobre a aplicação

A utilização da aplicação será através das API's disponibilizadas, o payload e as URI's poderão ser consultadas na documentação
disponibilizada na ferramenta swagger.

O cliente poderá ter uma ou "N" campanhas, as campanhas tem como base o time do coração do cliente.
Para o cadastro de novos clientes, serão associadas todas as campanhas vigentes para o time do coração do cliente,
para atualização de clientes existentes, serão vinculadas todas as campanhas vigentes que o cliente ainda não possui.

O cliente poderá ter apenas um cadastro ativo, a chave do cadastro será sempre o e-mail do cliente.

A associação entre o cliente e a campanha do time do coração ocorre de forma assíncrona, ou seja, ela ocorre depois do 
cadastro do cliente. Desta forma, a aplicação poderá distribuir o processo e o cadastro não será impedido caso o serviço de 
campanhas esteja indisponível no momento.

As API's de consulta de cliente, dependem do serviço de campanha para retornar a informação "quente" sobre o cadastro do cliente,
ou seja, a informação atualizada. O cadastro do cliente possui apenas o identificador do time, foi optado por não armazenar
a informação completa para evitar problemas de sincronia do dado neste modelo de arquitetura.

#### Técnologias utilizadas

* Java 8
* Spring Boot
* Spring JPA
* Rest
* Active MQ
* JPA
* JMS
* Swagger
* Jacoco
* H2

#### Execução da aplicação

A execução da aplicação poderá ser feita através das formas abaixo: 

* Importar o projeto em qualquer IDE Java compatível com as tecnologias citadas, o start
  da aplicação será feito através da execução da classe _NsrpChallengeApplication_

* Na raiz do projeto, executar o comando `mvn spring-boot:run`

* Executar o comando `mvn clean install` e em seguida o comando `java -jar target/nsrp-challenge-socio-torcedor-0.0.1-SNAPSHOT.jar`

* A configuração padrão estará pronta para rodar em docker, juntamente com o docker-compose do projeto.
Para executar localmente sem depender do ambiente, basta adicionar a configuração de profile do spring `-Dspring.profiles.active=test`
antes de iniciar a aplicação para execução com base H2 em memória.

#### Cobertura de testes

A cobertura de testes unitários estará disponível na pasta target/site/ do projeto, para acessar o conteúdo será necessário
executar o comando

`mvn clean test`

O conteúdo poderá ser visualizado em qualquer navegador executando o arquivo disponível em _target/site/jacoco/index.html_

#### Documentação das API's

A ferramenta swagger foi utilizada para documentar a utilização das API's disponíveis, o acesso poderá ser feito através
do link [http://localhost:8081/nsrp-challenge-socio-torcedor/swagger-ui.html](http://localhost:8081/nsrp-challenge-socio-torcedor/swagger-ui.html)  quando a aplicação
estiver disponível.

#### Deploy

O build no maven irá gerar por padrão (caso os testes não falhem), uma imagem em docker com o nome `nsrp-challenge-campanha`
e tag `latest`. A imagem gerada estará pronta para ser utilizada no docker-compose configurado para trabalhar com os 
demais serviços disponíveis, o banco de dados Mysql e o ActiveMQ.
