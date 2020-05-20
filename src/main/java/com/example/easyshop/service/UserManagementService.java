package com.example.easyshop.service;

public interface UserManagementService {

    void signUp(SignUpData signUpData) throws DuplicateUserException, TechnicalException, WeakPasswordException;

    LoginToken login(LoginData loginData) throws LoginFailedException, TechnicalException;

    void Logout(LoginToken loginToken) throws TechnicalException;

    void validateToken(LoginToken loginToken) throws InvalidTokenException, TechnicalException;

    void grantRole(UserData userData, RoleData roleData) throws GrantFailedException,TechnicalException;

    void revokeRole(UserData userData, RoleData roleData) throws RevokeFailedException,TechnicalException;

    RoleData getRole(LoginToken loginToken) throws InvalidTokenException, TechnicalException;

    UserData getUser(LoginToken loginToken) throws InvalidTokenException, TechnicalException;
}
