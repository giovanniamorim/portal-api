package org.sindifisco.resource.security;

import com.sun.mail.util.MailSSLSocketFactory;
import org.sindifisco.model.Usuario;
import org.sindifisco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/forgot-password")
public class ForgotPasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    private static Logger LOGGER = Logger.getLogger("InfoLogging");

    @PostConstruct
    public void init() throws GeneralSecurityException {
        // Crie uma instância de MailSSLSocketFactory com a validação de hostname desativada
        MailSSLSocketFactory socketFactory = new MailSSLSocketFactory();
        socketFactory.setTrustAllHosts(true);

        // Configure a propriedade mail.smtp.ssl.socketFactory com a instância criada
        Properties props = new Properties();
        props.put("mail.smtp.ssl.socketFactory", socketFactory);

        // Configure a sessão SMTP com as propriedades configuradas
        JavaMailSenderImpl sender = (JavaMailSenderImpl) mailSender;
        sender.setJavaMailProperties(props);
    }

    @PostMapping
    public ResponseEntity<?> processForgotPassword(@RequestBody Map<String, String> request) throws MessagingException, UnsupportedEncodingException {
        String email = request.get("email");
        Usuario user = userService.findUserByEmail(email);
        LOGGER.info("Chegou no metodo processForgotPassword e verificou o email: " + user);

        if (user == null) {
            return ResponseEntity.badRequest().body("Nenhum usuário encontrado para o email solicitado.");
        }
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);
        LOGGER.info("criou o token: " + token);
        String resetPasswordUrl = "https://app-47646.dc-us-1.absamcloud.com/portal-api/api/reset-password?token=" + token;
        LOGGER.info("criou a url: " + resetPasswordUrl);
        // Send the reset password email to the user
        return sendResetPasswordEmail(user.getEmail(), resetPasswordUrl);
    }


    private ResponseEntity<?> sendResetPasswordEmail(String email, String resetPasswordUrl) throws MessagingException, UnsupportedEncodingException {

        String mensagem = "Olá, <br><br>Recebemos uma solicitação para resetar sua senha no Portal Transparência. " +
                "Para continuar o processo, clique no link abaixo e siga as instruções na página:<br><br>" +
                resetPasswordUrl +
                "<br><br>Se você não solicitou a mudança de senha, por favor, " +
                "entre em contato com o Sindifisco para mais informações.<br>" +
                "<br>" +
                "Atenciosamente,<br>" +
                "A equipe Sindifisco-PA<br>"
                ;

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(email);
            helper.setSubject("Solicitação de Resete de Senha");
            helper.setText(mensagem, true);
            mailSender.send(message);
            return ResponseEntity.status(HttpStatus.OK).body("Email enviado com sucesso!") ;
        } catch (MailException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro tipo: MailException: " + e);
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro tipo: MessagingException: "  + e);
        }
    }


}
