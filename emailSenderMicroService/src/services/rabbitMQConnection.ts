import * as amqp from 'amqplib';
import IEmail from '../interfaces/IEmail';
import EmailSenderService from './emailSenderService';

export default class RabbitMQConnection{

    private fila: string = "FILA_EMAIL";

    private emailSenderService = EmailSenderService.getInstance();

    private connectionOptions: amqp.Options.Connect = {
        hostname: process.env.RABBITMQ_HOSTNAME,
        port: 5672,
        username: process.env.RABBITMQ_USERNAME,
        password: process.env.RABBITMQ_PASSWORD
    }

    public async initialize():Promise<void>{
                
        amqp.connect(this.connectionOptions)
            .then((conexao:any) => {
                conexao.createChannel().then((canal:any)=>{
                    console.log("Conexão estabelecida com a fila WhatsApp");

                    canal.consume(this.fila, (mensagem: amqp.ConsumeMessage)=>{
                        try {
                            const conteudo:string = mensagem.content.toString();
                            const mensagemTratada: IEmail = JSON.parse(conteudo);
                            console.log(mensagemTratada);
                            this.emailSenderService.enviarEmail(mensagemTratada.email, mensagemTratada.subject);

                        } catch (error) {
                            console.log(error);
                        }
                    }, {noAck:false})

                }).catch((erro:any)=>{ console.log(erro) });

            }).catch((erro:any)=>{ console.log(erro); });
    }
    

}