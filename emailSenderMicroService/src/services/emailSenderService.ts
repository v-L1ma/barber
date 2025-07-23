import nodemailer from 'nodemailer';
import dotenv from 'dotenv';
dotenv.config();

export default class EmailSenderService{

    private static instance: EmailSenderService;

    private transporter = nodemailer.createTransport({
        service: 'gmail',
        auth: {
            user: process.env.EMAIL_USER,
            pass: process.env.EMAIL_PASS
        }
    });

    public static getInstance(): EmailSenderService {
        if (!EmailSenderService.instance) {
            EmailSenderService.instance = new EmailSenderService();
        }
        return EmailSenderService.instance;
    }

    public async enviarEmail(to:string, subject:string){

        const criacaoDeContaTemplate:string = "Assunto: Sua conta foi criada com sucesso!"+
        "Olá {{nome}},"+
        "Seja bem-vindo ao BarberBooking!"+
        "Sua conta foi criada com sucesso e já está pronta para uso. Agora você pode agendar seus horários com praticidade e rapidez."+
        "Acesse sua conta pelo link abaixo:"+
        "{{link_login}}"+
        "Se tiver qualquer dúvida, estamos à disposição."+
        "BarberBooking © 2025";

        const redefinicaoDeSenhaTemplate:string = "Assunto: Redefinição de senha"
        "Olá {{nome}},"+
        "Recebemos uma solicitação para redefinir a senha da sua conta no BarberBooking."+
        "Para criar uma nova senha, acesse o link abaixo:"+
        "{{link_redefinir}}"+
        "Se você não fez essa solicitação, apenas ignore este e-mail. Sua senha atual continuará válida."+
        "BarberBooking © 2025";

        const mailOptions = {
        from: 'barberbooking@hotmail.com',
        to: to,
        subject: subject,
        text: 'Corpo do e-mail'
        };

        switch (subject) {
            case "criacao":
                mailOptions.subject="Sua conta foi criada com sucesso!";
                mailOptions.text=criacaoDeContaTemplate;
                break;
            case "senha":
                mailOptions.subject="Redefina sua senha";
                mailOptions.text=redefinicaoDeSenhaTemplate;
                break;
            default:
                break;
        }

        this.transporter.sendMail(mailOptions, (error, info) => {
            if (error) {
                return console.log(error);
            }
            console.log('E-mail enviado:', info.response);
        });

    };

    


}


