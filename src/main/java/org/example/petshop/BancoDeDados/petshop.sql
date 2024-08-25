CREATE DATABASE petshop;
use petshop;

CREATE TABLE clientes (
    idCliente INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nome VARCHAR(45) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    telefone VARCHAR(20) NOT NULL
);

CREATE TABLE servicos (
    idServico INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    descricao VARCHAR(45) NOT NULL,
    valor VARCHAR(45) NOT NULL
);

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    usuario VARCHAR(45) NOT NULL,
    senha VARCHAR(45) NOT NULL,
    nivelAcesso INT
);

CREATE TABLE agendamentos (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    raca VARCHAR(45) NOT NULL,
    data VARCHAR(20) NOT NULL,
    observacoes VARCHAR(191),
    idCliente INT,
    idServico INT,
    FOREIGN KEY (idCliente) REFERENCES clientes(idCliente),
    FOREIGN KEY (idServico) REFERENCES servicos(idServico)
);

INSERT INTO usuarios (usuario, senha, nivelAcesso) VALUES ('administrador', 'yjCdHuzggyO/HYYD4+50fw==', 1),
('funcionario','yJePdDNvYLsi5ZPKxnl11w==',0);