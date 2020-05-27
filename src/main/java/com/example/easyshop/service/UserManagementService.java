package com.example.easyshop.service;

import com.example.easyshop.entity.Token;

import java.util.List;

public interface UserManagementService {

    void signUp(SignUpData signUpData) throws DuplicateUserException, TechnicalException, WeakPasswordException;

    LoginToken login(LoginData loginData) throws LoginFailedException, TechnicalException;

    void logout(LoginToken loginToken) throws InvalidTokenException, TechnicalException;

    Token validateToken(LoginToken loginToken) throws InvalidTokenException, TechnicalException;

    void grantRole(UserData userData, RoleData roleData, LoginToken loginToken)
            throws  InvalidTokenException, AccessDeniedException, UserNotFoundException,   TechnicalException;

    void revokeRole(LoginToken loginToken, UserData userData, RoleData roleData)
            throws AccessDeniedException,InvalidTokenException,RoleNotFoundException, UserNotFoundException, TechnicalException;

    List<RoleData> getRole(LoginToken loginToken) throws InvalidTokenException, TechnicalException;

    UserData getUser(LoginToken loginToken) throws InvalidTokenException, TechnicalException;
}
