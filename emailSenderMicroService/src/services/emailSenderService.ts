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

        const criacaoDeContaTemplate = `
        <div style="background: #ffde26ff; color: #fff; text-decoration: none; width:100%; height: 50px"></div>
        <h2>Sua conta foi criada com sucesso!</h2>
        <p>Seja bem-vindo ao <strong>BarberBooking</strong>!</p>
        <p>Sua conta já está pronta para uso. Agora você pode agendar seus horários com praticidade e rapidez.</p>
        <p><a href="https://time4barber.netlify.app/login" style="background: #093d16; color: #fff; padding: 10px 15px; text-decoration: none;">Acessar minha conta</a></p>
        <p>Se tiver qualquer dúvida, estamos à disposição.</p>
        <p><em>BarberBooking © 2025</em></p>
        <div style="background: #ffde26ff; color: #fff; text-decoration: none; width:100%; height: 50px"></div>
        `;

        const redefinicaoDeSenhaTemplate = `
        <div style="background: #ffde26ff; color: #fff; text-decoration: none; width:100%; height: 50px"></div>
        <h2>Redefinição de senha</h2>
        <p>Recebemos uma solicitação para redefinir a senha da sua conta no BarberBooking.</p>
        <p><a href="https://time4barber.netlify.app/login" style="background: #d33; color: #fff; padding: 10px 15px; text-decoration: none;">Redefinir senha</a></p>
        <p>Se você não fez essa solicitação, apenas ignore este e-mail.</p>
        <p><em>BarberBooking © 2025</em></p>
        <div style="background: #ffde26ff; color: #fff; text-decoration: none; width:100%; height: 50px"></div>
        `;

        const mailOptions = {
        from: 'barberbooking@hotmail.com',
        to: to,
        subject: subject,
        html: ''
        };

        switch (subject) {
            case "criacao":
                mailOptions.subject="Sua conta foi criada com sucesso!";
                mailOptions.html=criacaoDeContaTemplate;
                break;
            case "senha":
                mailOptions.subject="Redefina sua senha";
                mailOptions.html=redefinicaoDeSenhaTemplate;
                break;
            default:
                break;
        }

        this.transporter.sendMail(mailOptions, (error:any, info:any) => {
            if (error) {
                return console.log(error);
            }
            console.log('E-mail enviado:', info.response);
        });

    };
}
