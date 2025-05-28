CREATE TABLE IF NOT EXISTS cliente(
    id serial PRIMARY KEY,
    nome varchar(50),
    cpf varchar(50)
);

CREATE TABLE IF NOT EXISTS agendamento(
    id serial PRIMARY KEY,
    data varchar(10),
    horario varchar(5),
    cliente varchar(50),
    servico varchar(50)
);