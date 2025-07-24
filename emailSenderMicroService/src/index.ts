import RabbitMQConnection from "./services/rabbitMQConnection";
import http from 'http';

const port = process.env.PORT || 3000;

http.createServer((_, res) => {
  res.writeHead(200);
  res.end('Servidor ativo.');
}).listen(port, () => {
  console.log(`Servidor escutando na porta ${port}`);
});

const rabbitMQConnection = new RabbitMQConnection();

rabbitMQConnection.initialize();
