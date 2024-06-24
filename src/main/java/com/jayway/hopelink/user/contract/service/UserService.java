package com.jayway.hopelink.user.contract.service;

import com.jayway.hopelink.user.contract.repository.UserRepository;
import com.jayway.hopelink.user.dto.RegisterUserDTO;
import com.jayway.hopelink.user.dto.UserInfo;
import com.jayway.hopelink.user.exception.AlreadyUserExistsException;
import com.jayway.hopelink.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public UserInfo findByDocumentNumber(String documentNumber) {
        ensureDocumentNumberIsValid(documentNumber);
        var userFounded = userRepository.findByDocumentNumber(documentNumber)
                .orElseThrow(()-> new UserNotFoundException(documentNumber));
        return UserInfo.from(userFounded);
    }

    private void ensureDocumentNumberIsValid(String documentNumber) {
        Optional.ofNullable(documentNumber)
                .filter(value -> value.matches("[0-9]{8}"))
                .orElseThrow(()-> new IllegalArgumentException("Document number is not valid"));
    }
}
