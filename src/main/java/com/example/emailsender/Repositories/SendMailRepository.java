package com.example.emailsender.Repositories;

import com.example.emailsender.Entities.EmailDetail;
import org.springframework.data.repository.CrudRepository;

public interface SendMailRepository extends CrudRepository<EmailDetail,Integer> {
}
