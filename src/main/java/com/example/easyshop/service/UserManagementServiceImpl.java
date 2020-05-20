package com.example.easyshop.service;

import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {
    @Override
    public void signUp(SignUpData signUpData) throws DuplicateUserException, TechnicalException, WeakPasswordException {

    }

    @Override
    public LoginToken login(LoginData loginData) throws LoginFailedException, TechnicalException {
        return null;
    }

    @Override
    public void Logout(LoginToken loginToken) throws TechnicalException {

    }

    @Override
    public void validateToken(LoginToken loginToken) throws InvalidTokenException, TechnicalException {

    }

    @Override
    public void grantRole(UserData userData, RoleData roleData) throws GrantFailedException, TechnicalException {

    }

    @Override
    public void revokeRole(UserData userData, RoleData roleData) throws RevokeFailedException, TechnicalException {

    }

    @Override
    public RoleData getRole(LoginToken loginToken) throws InvalidTokenException, TechnicalException {
        return null;
    }

    @Override
    public UserData getUser(LoginToken loginToken) throws InvalidTokenException, TechnicalException {
        return null;
    }
}
