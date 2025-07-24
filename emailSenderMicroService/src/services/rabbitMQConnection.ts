import * as amqp from 'amqplib';
import IEmail from '../interfaces/IEmail';
import EmailSenderService from './emailSenderService';

export default class RabbitMQConnection{

    private fila: string = "FILA_EMAIL";

    private emailSenderService = EmailSenderService.getInstance();

    private readonly connectionUrl: string;

    constructor() {
        const envUrl = process.env.RABBITMQ_URL;
        if (!envUrl) {
        throw new Error("RABBITMQ_URL não encontrado nas variaveis de ambiente.");
        }
        this.connectionUrl = envUrl;
    }

    public async initialize():Promise<void>{
                
        amqp.connect(this.connectionUrl)
            .then((conexao:any) => {
                conexao.createChannel().then((canal:any)=>{
                    console.log("Conexão estabelecida com a fila email");

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
