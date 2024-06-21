package com.jayway.hopelink.user.contract.service;

import com.jayway.hopelink.user.dto.RegisterUserDTO;

public interface RegisterUser {
    void register(RegisterUserDTO request);
}
