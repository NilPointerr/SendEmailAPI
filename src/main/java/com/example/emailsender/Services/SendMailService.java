package com.example.emailsender.Services;

import com.example.emailsender.Entities.EmailDetail;
import com.example.emailsender.Repositories.SendMailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SendMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SendMailRepository sendMailRepository;

    public void sendMail(String to,String subject,String body){
        SimpleMailMessage msg  = new SimpleMailMessage();
        msg.setFrom("nilesh.ultragames@gmail.com");
//        msg.setTo("rk3356188@gmail.com");
        msg.setTo(to);
        System.out.println(to);
        msg.setSubject(subject);
        msg.setText(body);
        System.out.println("Mail Sent");
        javaMailSender.send(msg);



    }

    public EmailDetail createEmailDetail(EmailDetail emailDetail){
        System.out.println(emailDetail.getRecepent());
        return sendMailRepository.save(emailDetail);
    }

    public ResponseEntity<String> sendMailById(Integer id){
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            Optional<EmailDetail> emailDetail = sendMailRepository.findById(id);

            if (emailDetail.isPresent()) {
                String to = emailDetail.get().getRecepent();
                System.out.println("service " + to);
                msg.setTo(to);
                String subject = emailDetail.get().getSubject();
                System.out.println("service " + subject);
                msg.setSubject(subject);
                String body = emailDetail.get().getBody();
                System.out.println("service " + body);
                msg.setText(body);
                msg.setFrom("nilesh.ultragames@gmail.com");
                javaMailSender.send(msg);
                return ResponseEntity.ok("message sent successfully");
            } else {
                System.out.println("user not  found "+id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID: " + id + " does not exist");
            }
        } catch (NoSuchElementException e) {
            System.out.println(7 +"  this is doesn't exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID: " + id + " does not exist");
        }
    }
}
