package com.jayway.hopelink.user.contract.service;

import com.jayway.hopelink.user.dto.UserInfo;

public interface FindUser {
    UserInfo findByDocumentNumber(String email);
}
