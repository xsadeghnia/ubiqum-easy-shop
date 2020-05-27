package com.example.easyshop.service;

import com.example.easyshop.EasyShopApplication;
import com.example.easyshop.entity.Token;
import com.example.easyshop.entity.User;
import com.example.easyshop.repository.TokenRepository;
import com.example.easyshop.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class) // @RunWith: integrate spring with junit
@SpringBootTest(classes = {EasyShopApplication.class})
@ContextConfiguration(loader= AnnotationConfigContextLoader.class)
class UserManagementServiceImplTest {

    @Configuration
    static class ContextConfiguration {

        @Bean
        public UserManagementService getService() {
            return new UserManagementServiceImpl();
        }
    }

    @Autowired
    private UserManagementService userManagementService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Test
    void testInjections() {
        Assertions.assertNotNull(userRepository);
        Assertions.assertNotNull(tokenRepository);
    }

    // Run the following before each test
    @BeforeEach
    void setUp() {
        tokenRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testSignUp() throws DuplicateUserException, WeakPasswordException, TechnicalException {

        SignUpData signUpData = new SignUpData();
        signUpData.setFirstname("Alex");
        signUpData.setLastname("Bob");
        signUpData.setUsername("alex");
        signUpData.setPassword("alex12345");

        userManagementService.signUp(signUpData);

        List<User> users = userRepository.findAll();

        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals("Alex", users.get(0).getFirstname());
        Assertions.assertEquals("Bob", users.get(0).getLastname());
        Assertions.assertEquals("alex", users.get(0).getUsername());
        Assertions.assertEquals("alex12345", users.get(0).getPassword());
    }

    @Test
    void testDuplicateUsername() {

        SignUpData signUpData = new SignUpData();
        signUpData.setFirstname("Alex");
        signUpData.setLastname("Bob");
        signUpData.setUsername("alex");
        signUpData.setPassword("alex12345");

        try {
            userManagementService.signUp(signUpData);
        } catch(Exception e) {
            Assertions.fail();
        }

        List<User> users = userRepository.findAll();

        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals("Alex", users.get(0).getFirstname());
        Assertions.assertEquals("Bob", users.get(0).getLastname());
        Assertions.assertEquals("alex", users.get(0).getUsername());
        Assertions.assertEquals("alex12345", users.get(0).getPassword());

        signUpData = new SignUpData();
        signUpData.setFirstname("Foo");
        signUpData.setLastname("Bar");
        signUpData.setUsername("alex");
        signUpData.setPassword("foo12345");

        try {
            userManagementService.signUp(signUpData);
            Assertions.fail();
        } catch (DuplicateUserException e) {
        } catch(Exception e) {
            Assertions.fail();
        }

        users = userRepository.findAll();

        Assertions.assertEquals(1, users.size());


    }

    @Test
    void testNullSignupData(){
        try {
            userManagementService.signUp(null);
            Assertions.fail();
        } catch (IllegalArgumentException e){
        } catch(Exception e){
            Assertions.fail();
        }
    }

    @Test
    void testNullUsername(){

        SignUpData signUpData = new SignUpData();
        signUpData.setFirstname("Alex");
        signUpData.setLastname("Bob");
        signUpData.setUsername("");
        signUpData.setPassword("alex12345");

        try {
            userManagementService.signUp(signUpData);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
        }catch(Exception e) {
            Assertions.fail();
        }
    }

    @Test
    void testWeakPassword() {

        SignUpData signUpData = new SignUpData();
        signUpData.setFirstname("Alex");
        signUpData.setLastname("Bob");
        signUpData.setUsername("alex");
        signUpData.setPassword("alex1");

        try {
            userManagementService.signUp(signUpData);
            Assertions.fail();
        } catch (WeakPasswordException e) {
        }catch(Exception e) {
            Assertions.fail();
        }
    }

    @Test
    void testLogin() throws DuplicateUserException, WeakPasswordException, TechnicalException, LoginFailedException {

        SignUpData signUpData = new SignUpData();
        signUpData.setFirstname("Alex");
        signUpData.setLastname("Bob");
        signUpData.setUsername("alex");
        signUpData.setPassword("alex12345");

        userManagementService.signUp(signUpData);

        List<User> users = userRepository.findAll();

        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals("Alex", users.get(0).getFirstname());
        Assertions.assertEquals("Bob", users.get(0).getLastname());
        Assertions.assertEquals("alex", users.get(0).getUsername());
        Assertions.assertEquals("alex12345", users.get(0).getPassword());


        LoginData loginData = new LoginData();
        loginData.setUsername("alex");
        loginData.setPassword("alex12345");

        LoginToken loginToken = userManagementService.login(loginData);

        List<Token> tokens = tokenRepository.findAll();

        Assertions.assertEquals(1, tokens.size());
        Assertions.assertEquals( tokens.get(0).getValue(), loginToken.getValue());
    }

    @Test
    void testLoginWithNull() {
        try {
            userManagementService.login(null);
            Assertions.fail();
        } catch (IllegalArgumentException e){
        } catch(Exception e){
            Assertions.fail();
        }
    }

    @Test
    void testLoginWithWrongUsername() throws DuplicateUserException, WeakPasswordException, TechnicalException {

        SignUpData signUpData = new SignUpData();
        signUpData.setFirstname("Alex");
        signUpData.setLastname("Bob");
        signUpData.setUsername("alex");
        signUpData.setPassword("alex12345");

        userManagementService.signUp(signUpData);

        List<User> users = userRepository.findAll();

        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals("Alex", users.get(0).getFirstname());
        Assertions.assertEquals("Bob", users.get(0).getLastname());
        Assertions.assertEquals("alex", users.get(0).getUsername());
        Assertions.assertEquals("alex12345", users.get(0).getPassword());


        LoginData loginData = new LoginData();
        loginData.setUsername("foo");
        loginData.setPassword("alex12345");

        try {
            userManagementService.login(loginData);
            Assertions.fail();
        } catch (LoginFailedException e){
        }catch(Exception e){
            Assertions.fail();
        }
    }

    @Test
    void testLoginWithWrongPassword() throws DuplicateUserException, WeakPasswordException, TechnicalException {
        SignUpData signUpData = new SignUpData();
        signUpData.setFirstname("Alex");
        signUpData.setLastname("Bob");
        signUpData.setUsername("alex");
        signUpData.setPassword("alex12345");

        userManagementService.signUp(signUpData);

        List<User> users = userRepository.findAll();

        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals("Alex", users.get(0).getFirstname());
        Assertions.assertEquals("Bob", users.get(0).getLastname());
        Assertions.assertEquals("alex", users.get(0).getUsername());
        Assertions.assertEquals("alex12345", users.get(0).getPassword());


        LoginData loginData = new LoginData();
        loginData.setUsername("alex");
        loginData.setPassword("foo12345");

        try {
            userManagementService.login(loginData);
            Assertions.fail();
        } catch (LoginFailedException e){
        }catch(Exception e){
            Assertions.fail();
        }
    }

    @Test
    void testLogout()
            throws DuplicateUserException, WeakPasswordException, TechnicalException, LoginFailedException, InvalidTokenException {
        SignUpData signUpData = new SignUpData();
        signUpData.setFirstname("Alex");
        signUpData.setLastname("Bob");
        signUpData.setUsername("alex");
        signUpData.setPassword("alex12345");

        userManagementService.signUp(signUpData);

        List<User> users = userRepository.findAll();
        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals("Alex", users.get(0).getFirstname());
        Assertions.assertEquals("Bob", users.get(0).getLastname());
        Assertions.assertEquals("alex", users.get(0).getUsername());
        Assertions.assertEquals("alex12345", users.get(0).getPassword());

        LoginData loginData = new LoginData();
        loginData.setUsername("alex");
        loginData.setPassword("alex12345");

        LoginToken loginToken = userManagementService.login(loginData);

        List<Token> tokens = tokenRepository.findAll();
        Assertions.assertEquals(1, tokens.size());
        Assertions.assertEquals( tokens.get(0).getValue(), loginToken.getValue());

        userManagementService.logout(loginToken);

        tokens = tokenRepository.findAll();
        Assertions.assertEquals(0, tokens.size());
    }

    @Test
    void testLogoutWithNullLoginToken() {
        try {
            userManagementService.logout(null);
            Assertions.fail();
        }catch (IllegalArgumentException e){
        }catch (Exception e){
            Assertions.fail();
        }
    }

    @Test
    void testLogoutWithWrongToken() {

        // Generate random String
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        LoginToken loginToken = new LoginToken();
        loginToken.setValue(generatedString);
        try {
            userManagementService.logout(loginToken);
            Assertions.fail();
        }catch (InvalidTokenException e){
        }catch (Exception e){
            Assertions.fail();
        }
    }

    @Test
    void testValidateToken() throws DuplicateUserException, WeakPasswordException, TechnicalException, LoginFailedException, InvalidTokenException {
        SignUpData signUpData = new SignUpData();
        signUpData.setFirstname("Alex");
        signUpData.setLastname("Bob");
        signUpData.setUsername("alex");
        signUpData.setPassword("alex12345");

        userManagementService.signUp(signUpData);

        List<User> users = userRepository.findAll();
        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals("Alex", users.get(0).getFirstname());
        Assertions.assertEquals("Bob", users.get(0).getLastname());
        Assertions.assertEquals("alex", users.get(0).getUsername());
        Assertions.assertEquals("alex12345", users.get(0).getPassword());

        LoginData loginData = new LoginData();
        loginData.setUsername("alex");
        loginData.setPassword("alex12345");

        LoginToken loginToken = userManagementService.login(loginData);

        String tokenValue = loginToken.getValue();
        List<Token> tokens = tokenRepository.findAll();
        Assertions.assertEquals(tokenValue,tokens.get(0).getValue() );

        Token tokenAfterValidation = userManagementService.validateToken(loginToken);
        Assertions.assertEquals(tokenAfterValidation.getValue(),tokens.get(0).getValue() );
    }

    @Test
    void testValidateTokenWithNullLoginToken() {
        try{
            userManagementService.validateToken(null);
            Assertions.fail();
        }catch (IllegalArgumentException e){
        }catch (Exception e){
            Assertions.fail();
        }
    }
}
