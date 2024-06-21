package com.jayway.hopelink.user.contract.repository;

import com.jayway.hopelink.user.contract.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDocument, String> {
}
