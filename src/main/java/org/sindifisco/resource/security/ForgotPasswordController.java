package org.sindifisco.resource.security;

import org.sindifisco.model.Usuario;
import org.sindifisco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Map;
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

    @PostMapping
    public ResponseEntity<?> processForgotPassword(@RequestBody Map<String, String> request) throws MessagingException, UnsupportedEncodingException {
        String email = request.get("email");
        Usuario user = userService.findUserByEmail(email);
        LOGGER.info("Chegou no metodo processForgotPassword e verificou o email: " + user);

        if (user == null) {
            return ResponseEntity.badRequest().body("No user found with this email address.");
        }

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);
        LOGGER.info("criou o token: " + token);

        String resetPasswordUrl = "/api/reset-password?token=" + token;

        LOGGER.info("criou a url: " + resetPasswordUrl);

        // Send the reset password email to the user



        return sendResetPasswordEmail(user.getEmail(), resetPasswordUrl);
    }



    private ResponseEntity<?> sendResetPasswordEmail(String email, String resetPasswordUrl) throws MessagingException, UnsupportedEncodingException {



        try {
            LOGGER.info("Entrou no try catch e chamou o metodo sendResetPasswordEmail usando o email: " + email);
            MimeMessage message = mailSender.createMimeMessage();
            LOGGER.info("Messsage: " + message);
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(email);
            LOGGER.info("Email: " + email);
            helper.setSubject("Password reset request");
            LOGGER.info("Info: " + helper);
            helper.setText("Please click the link below to reset your password:\n" + resetPasswordUrl);
            LOGGER.info("chamou antes o metodo com os dados: " + message);
            mailSender.send(message);
            LOGGER.info("chamou depois o metodo com os dados: " + message);
            return ResponseEntity.status(HttpStatus.OK).body("Email enviado com sucesso!") ;
        } catch (MailException e){
            LOGGER.warning("Erro tipo: MailException: " + email + " - Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro tipo: MailException: " + e);
        } catch (MessagingException e) {
            LOGGER.warning("Erro tipo: MessagingException: " + email + " - Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro tipo: MessagingException: "  + e);
        }
    }


}
