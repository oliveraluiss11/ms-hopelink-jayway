package com.jayway.hopelink.user.contract.service;

import com.jayway.hopelink.user.contract.repository.UserRepository;
import com.jayway.hopelink.user.dto.RegisterUserDTO;
import com.jayway.hopelink.user.exception.AlreadyUserExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements RegisterUser, FindUser{
    private final UserRepository userRepository;
    @Override
    public void register(RegisterUserDTO request) {
        request.ensureAttributes();
        this.validUserExists(request);
        userRepository.save(RegisterUserDTO.toEntity(request));
    }

    private void validUserExists(RegisterUserDTO registerUserDTO){
        userRepository.findByDocumentNumberOrPhoneNumberOrEmail(
                        registerUserDTO.getDocumentNumber(),
                        registerUserDTO.getPhoneNumber(),
                        registerUserDTO.getEmail())
                .ifPresent(user -> {
                    throw new AlreadyUserExistsException(registerUserDTO.getDocumentNumber());
                });
    }
}
