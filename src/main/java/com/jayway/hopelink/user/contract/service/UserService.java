package com.jayway.hopelink.user.contract.service;

import com.jayway.hopelink.user.contract.repository.UserRepository;
import com.jayway.hopelink.user.dto.RegisterUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements RegisterUser, FindUser{
    private final UserRepository userRepository;
    @Override
    public void register(RegisterUserDTO request) {

        //userRepository.save(RegisterUserDTO.toDocument(request));
    }
}
