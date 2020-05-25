package com.example.easyshop.service;

import com.example.easyshop.entity.Role;
import com.example.easyshop.entity.Token;
import com.example.easyshop.entity.User;
import com.example.easyshop.repository.RoleRepository;
import com.example.easyshop.repository.TokenRepository;
import com.example.easyshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    private LoginTokenGenerator loginTokenGenerator;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void signUp(SignUpData signUpData) throws DuplicateUserException, TechnicalException, WeakPasswordException {
        if (userRepository.findByUsername(signUpData.getUsername()).isPresent()){
           throw new DuplicateUserException();
        }
        if (!passwordValidator.validate(signUpData.getPassword())){
            throw new WeakPasswordException();
        }
        try {
            User user = new User();
            user.setFirstname(signUpData.getFirstname());
            user.setLastname(signUpData.getLastname());
            user.setUsername(signUpData.getUsername());
            user.setPassword(signUpData.getPassword());
            userRepository.save(user);
        } catch (Throwable throwable) {
            throw new TechnicalException(throwable);
        }
    }

    @Override
    public LoginToken login(LoginData loginData) throws LoginFailedException, TechnicalException {
        Optional<User> user = userRepository.findByUsername(loginData.getUsername());
        if (!user.isPresent()){
            throw new LoginFailedException();
        }
        if (!user.get().getPassword().equals(loginData.getPassword())){
            throw new LoginFailedException();
        }
        try{
            LoginToken loginToken = loginTokenGenerator.generate(loginData.getUsername());
            Token token = new Token();
            token.setValue(loginToken.getValue());
            token.setUser(user.get());
            token.setCreationDate(new Date().getTime());
            tokenRepository.save(token);
            return loginToken;
        } catch (Throwable throwable) {
            throw new TechnicalException(throwable);
        }

    }

    @Override
    public void Logout(LoginToken loginToken) throws InvalidTokenException, TechnicalException {
        Token token = validateToken(loginToken);
        try {
            tokenRepository.delete(token);
        }catch (Throwable throwable) {
            throw new TechnicalException(throwable);
        }

    }

    @Override
    public Token validateToken(LoginToken loginToken) throws InvalidTokenException, TechnicalException {
        Optional<Token> token = tokenRepository.findByValue(loginToken.getValue());
        if(!token.isPresent()) {
            throw new InvalidTokenException();
        }

        try {
            return token.get();
        } catch (Throwable throwable) {
            throw new TechnicalException(throwable);
        }
    }

    @Override
    public void grantRole(UserData userData, RoleData roleData, LoginToken loginToken)
            throws InvalidTokenException,AccessDeniedException,UserNotFoundException, TechnicalException {
        Token token = validateToken(loginToken);
        List<RoleData> roleDataList = getRole(loginToken);
        if (!roleDataList.contains("ADMIN")) {
            throw new AccessDeniedException();
        }
        Optional<User> user = userRepository.findByUsername(userData.getUsername());
        if(!user.isPresent()){
            throw new UserNotFoundException();
        }
        try {
            Role role = new Role();
            role.setName(roleData.getName());
            user.get().getRoles().add(role);
            roleRepository.save(role);
        } catch (Throwable throwable) {
            throw new TechnicalException(throwable);
        }
    }

    @Override
    public void revokeRole(LoginToken loginToken, UserData userData, RoleData roleData)
            throws AccessDeniedException, InvalidTokenException,RoleNotFoundException, UserNotFoundException, TechnicalException {
        Token token = validateToken(loginToken);
        List<RoleData> roleDataList = getRole(loginToken);
        if (!roleDataList.contains("ADMIN")) {
            throw new AccessDeniedException();
        }
        Optional<User> user = userRepository.findByUsername(userData.getUsername());
        if(!user.isPresent()){
            throw new UserNotFoundException();
        }
        Optional<Role> role = roleRepository.findByName(roleData.getName());
        if (!role.isPresent()){
            throw new RoleNotFoundException();
        }
        try {
            roleRepository.delete(role.get());
        }catch (Throwable throwable) {
            throw new TechnicalException(throwable);
        }
    }

    @Override
    public List<RoleData> getRole(LoginToken loginToken) throws InvalidTokenException, TechnicalException {
        Token token =validateToken(loginToken);
        try {
            List<RoleData> rolesData = new ArrayList<>();
            for (Role role : token.getUser().getRoles()) {
                RoleData roleData = new RoleData();
                roleData.setName(role.getName());
                rolesData.add(roleData);
            }
            return rolesData;
        }catch (Throwable throwable) {
            throw new TechnicalException(throwable);
        }
    }

    @Override
    public UserData getUser(LoginToken loginToken) throws InvalidTokenException, TechnicalException {
        Token token = validateToken(loginToken);
        try {
            UserData userData = new UserData();
            userData.setFirstname(token.getUser().getFirstname());
            userData.setLastname(token.getUser().getLastname());
            userData.setPassword(token.getUser().getPassword());
            userData.setRoles(token.getUser().getRoles());
            return userData;
        }catch (Throwable throwable) {
            throw new TechnicalException(throwable);
        }
    }
}
