# Uma aplicação vulnerável indicada para testes e entendimento de conceitos de segurança em Aplicações WEB

Requisitos:
- Java 17
- Node 19
- Docker
- Docker Compose
- Postgres instalado. (Caso não tenha o Docker e Docker Compose)



## Rodar a aplicação:
1. Subir o banco de dados utilizando o Docker Compose. Rodar o comando abaixo na pasta raíz do projeto.
```
docker-compose up
```

2. Executar o script de criação do banco de dados que se encontra em dvsn-api/script.sql.

3. Entrar na pasta do front (dvsn-client) e dar build no projeto, para que os arquivos sejam colocados ná pasta estática da api.

NPM:
```
npm install
npm build
```
YARN:
```
yarn
yarn build
```


4. Abrir a o IntelliJ e rodar a aplicação.