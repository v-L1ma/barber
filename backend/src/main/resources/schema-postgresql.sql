CREATE TABLE IF NOT EXISTS cliente(
    id serial PRIMARY KEY,
    nome varchar(50),
    cpf varchar(50)
);

CREATE TABLE IF NOT EXISTS agendamento(
    id serial PRIMARY KEY,
    data DATE,
    horario TIME,
    cliente VARCHAR(50),
    servico VARCHAR(50)
);