CREATE TABLE paciente(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) not null,
    email VARCHAR(255) not null unique ,
    phone VARCHAR(11) not null,
    cpf VARCHAR(11) not null unique
);