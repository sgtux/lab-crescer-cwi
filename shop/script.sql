-- dumb vulnerable shop, data population
CREATE TABLE USUARIO (
    id SERIAL PRIMARY KEY, 
    username VARCHAR(100) NOT NULL, 
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(200) NOT NULL,
    criado_em DATE NOT NULL,
    token VARCHAR(200),
    token_expira_em DATE
);

CREATE TABLE PRODUTO (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(200) NOT NULL,
    valor DECIMAL(5,2),
    url_foto VARCHAR(200) NOT NULL,
    categoria VARCHAR(100)
);

CREATE TABLE PEDIDO (
    id SERIAL PRIMARY KEY,
    id_usuario INT CONSTRAINT fk_pedido_usuario REFERENCES USUARIO(id) ,
    criado_em DATE,
    finalizado_em DATE    
);

CREATE TABLE PRODUTO_PEDIDO (
    id_pedido INT CONSTRAINT fk_produtopedido_pedido REFERENCES PEDIDO(id),
    id_produto INT CONSTRAINT fk_produto_pedido REFERENCES PRODUTO(id)
);

INSERT INTO USUARIO (username, email, senha, criado_em, token, token_expira_em) VALUES ('alice', 'alice@cwi.com.br', '123', '2021-04-18 14:49:16.698882', NULL, NULL);
INSERT INTO USUARIO (username, email, senha, criado_em, token, token_expira_em) VALUES ('bob', 'bob@cwi.com.br', '123', '2021-07-25 18:32:48.698882', NULL, NULL);
INSERT INTO USUARIO (username, email, senha, criado_em, token, token_expira_em) VALUES ('carlos', 'carlos@cwi.com.br', '123', '2021-02-05 23:12:32.698882', NULL, NULL);

INSERT INTO PRODUTOS (descricao, valor, url_foto, categoria) VALUES ('Notebook', 3897.45, '/notebook.png', 'Eletrônicos');
INSERT INTO PRODUTOS (descricao, valor, url_foto, categoria) VALUES ('Sofá', 243.65, '/sofa.png', 'Mobília');
INSERT INTO PRODUTOS (descricao, valor, url_foto, categoria) VALUES ('Mesa', 675.89, '/mesa.png', 'Mobília');