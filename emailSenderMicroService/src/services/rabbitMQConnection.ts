import * as amqp from 'amqplib';
import IEmail from '../interfaces/IEmail';
import EmailSenderService from './emailSenderService';

export default class RabbitMQConnection{

    private fila: string = "FILA_EMAIL";

    private emailSenderService = EmailSenderService.getInstance();

    private connectionOptions: amqp.Options.Connect = {
        hostname: 'localhost',
        port: 5672,
        username: 'admin',
        password: "123456"
    }

    public async initialize():Promise<void>{
                
        amqp.connect(this.connectionOptions)
            .then((conexao) => {
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

                }).catch((erro)=>{ console.log(erro) });

            }).catch((erro)=>{ console.log(erro); });
    }
    

}