package com.jayway.hopelink.user.contract.repository;

import com.jayway.hopelink.user.contract.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserDocument, String> {
    Optional<UserDocument> findByDocumentNumberOrPhoneNumberOrEmail(String documentNumber, String phoneNumber, String email);
    Optional<UserDocument> findByDocumentNumber(String email);
}
