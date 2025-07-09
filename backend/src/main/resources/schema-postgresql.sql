CREATE TABLE IF NOT EXISTS agendamento(
    id serial PRIMARY KEY,
    data DATE,
    horario TIME,
    servico VARCHAR(50),
    clienteId serial REFERENCES cliente(id)
);

CREATE TABLE IF NOT EXISTS cliente(
    id serial PRIMARY KEY,
    nome VARCHAR(50),
    dataNascimento DATE,
    email VARCHAR(50),
    celular VARCHAR(13),
    senha VARCHAR(25)
);