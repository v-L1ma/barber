import * as amqp from 'amqplib';
import IAgendamento from '../interfaces/IAgendamento';
import EnviarNotificacaoUseCase from '../useCases/EnviarNotificacaoUseCase';
import NotificacaoWhatsapp from './notificacaoWhatsApp';
import cron from 'node-cron';

export default class RabbitMQConnection{

    private fila: string = "FILA_WHATSAPP";
    
    private notificacaoWhatsApp = NotificacaoWhatsapp.getInstance();

    private agendamentos: IAgendamento[] = [];

    private connectionOptions: amqp.Options.Connect = {
        hostname: 'localhost',
        port: 5672,
        username: 'admin',
        password: "123456"
    }

    public async initialize():Promise<void>{
        
        await this.notificacaoWhatsApp.waitUntilReady();
        
        amqp.connect(this.connectionOptions)
            .then((conexao) => {
                conexao.createChannel().then((canal:any)=>{
                    console.log("Conexão estabelecida com a fila WhatsApp");

                    canal.consume(this.fila, (mensagem: amqp.ConsumeMessage)=>{
                        try {
                            const conteudo:string = mensagem.content.toString();
                            const mensagemTratada: IAgendamento = JSON.parse(conteudo);
                            console.log(mensagemTratada);
                            this.agendamentos.push(mensagemTratada);

                        } catch (error) {
                            console.log(error);
                        }
                    }, {noAck:false})

                    cron.schedule('0 8 * * *', async () => {
                        console.log("⏰ Verificando agendamentos do dia...");

                       for (const agendamento of this.agendamentos) {

                            const data = `${String(agendamento.data[2]).padStart(2, '0')}/${String(agendamento.data[1]).padStart(2, '0')}/${agendamento.data[0]}`;
                            const horario = `${String(agendamento.horario[0]).padStart(2, '0')}:${String(agendamento.horario[1]).padStart(2, '0')}`;

                            const texto = `💈 *BarberBooking* 💈\n\nOlá *${agendamento.cliente}*, passando para lembrar que você tem um agendamento conosco!\n\n📅 *Data:* ${data}\n⏰ *Horário:* ${horario}\n✂️ *Serviço:* ${agendamento.servico}`;
                                            
                            await this.notificacaoWhatsApp.enviarMensagem(texto, agendamento);
                        }
                    });

                }).catch((erro)=>{ console.log(erro) });

            }).catch((erro)=>{ console.log(erro); });
    }
    

}