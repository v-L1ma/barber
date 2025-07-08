import { Client, LocalAuth, NoAuth } from 'whatsapp-web.js'; // Removido 'Message' e 'LocalAuth' para seguir seu código original
import * as qrcode from 'qrcode-terminal'; // Importado qrcode-terminal corretamente
import IAgendamento from '../interfaces/IAgendamento';

export default class NotificacaoWhatsapp {
    private client: Client;
    private clientIsReady: boolean = false
    private static instance: NotificacaoWhatsapp;

    constructor() {
        this.client = new Client({

            authStrategy: new LocalAuth({
                clientId: 'barber_whatsapp_session'
            }),
            puppeteer: {
                headless: true,
                args: [
                    '--no-sandbox',
                    '--disable-setuid-sandbox',
                    '--disable-dev-shm-usage',
                    '--disable-accelerated-2d-canvas',
                    '--no-first-run',
                    '--no-zygote',
                    '--single-process',
                    '--disable-gpu'
                ],
            }
        });

        this.client.on('qr', (qr: string) => { 
            qrcode.generate(qr, { small: true });
            console.log('QR RECEIVED', qr);
        });

        this.client.on('ready', () => {
            console.log('Client is ready!');
            this.clientIsReady = true
        });
        
        this.client.on('message', async (msg: any) => {
            if (msg.body == '!ping') {
                msg.reply('pong');
            }
        });

        this.client.initialize();
    }

    public async waitUntilReady(): Promise<void> {
        if (this.clientIsReady) return;
        await new Promise<void>((resolve) => {
            this.client.once('ready', () => {
            this.clientIsReady = true;
            resolve();
            });
        });
    }

    public static getInstance(): NotificacaoWhatsapp {
        if (!NotificacaoWhatsapp.instance) {
            NotificacaoWhatsapp.instance = new NotificacaoWhatsapp();
        }
        return NotificacaoWhatsapp.instance;
    }

    public async enviarMensagem(mensagem: string, agendamento: IAgendamento){

        if (!this.clientIsReady) {
            console.error('❌ Cliente não está pronto ou página ainda não carregada');
            return;
        }
        const chatId = `${agendamento.telefone}@c.us`;

        const dataAgendamento: Date = new Date(agendamento.data);

        //vejo se a data do agendamento é igual a data de amanhã
        const dataAmanha: Date = new Date();
        dataAmanha.setDate(dataAmanha.getDate()+1)

        console.log("Hoje é dia: ",dataAmanha)
        console.log("Agendamento marcado para o dia: ", dataAgendamento)

        if(    dataAgendamento.getDate()     === dataAmanha.getDate() 
            && dataAgendamento.getFullYear() === dataAmanha.getFullYear()
            && dataAgendamento.getMonth()    === dataAmanha.getMonth())
        {
            console.log("Enviar a notificacao")

            try {
                await this.client.sendMessage(chatId, mensagem)
                console.log("Mensagem enviada com sucesso")
            } catch (error) {
                console.log("Erro ao enviar a mensagem ", error)
            }
            
        }
    }
    
}

