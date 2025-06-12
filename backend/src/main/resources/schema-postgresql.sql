CREATE TABLE IF NOT EXISTS agendamento(
    id serial PRIMARY KEY,
    data DATE,
    horario TIME,
    cliente VARCHAR(50),
    servico VARCHAR(50)
);