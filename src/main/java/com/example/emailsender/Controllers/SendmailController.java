package com.example.emailsender.Controllers;

import com.example.emailsender.Entities.EmailDetail;
import com.example.emailsender.Services.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/sendmail")
public class SendmailController {

    @Autowired
    private SendMailService sendMailService;

    @PostMapping("/create")
    public EmailDetail createEmailDetail(@RequestBody EmailDetail emailDetail) {
        return sendMailService.createEmailDetail(emailDetail);
    }

    @GetMapping("/send")
    public String sendMail(@RequestBody EmailDetail emailDetail) {
        String to = emailDetail.getRecepent();
        System.out.println(to);
        String subject = emailDetail.getSubject();
        String body = emailDetail.getBody();

        sendMailService.sendMail(to, subject, body);

        return "Email sent succesfully!";
    }

//
//    @GetMapping("/byid/{id}")
//    public ResponseEntity<String> sendMailById(@PathVariable Integer id) throws Exception {
//        sendMailService.sendMailById(id);
//        return ResponseEntity.ok("Email sent successfully!"); // You can customize the response message as needed.
//    }


    @GetMapping("/byid/{id}")
    public ResponseEntity<String> sendEmailById(@PathVariable Integer id) {
        try {
            return sendMailService.sendMailById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while sending the email.");
        }

    }
}