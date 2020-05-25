package com.example.easyshop.service;

import com.example.easyshop.EasyShopApplication;
import com.example.easyshop.entity.User;
import com.example.easyshop.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) // @RunWith: integrate spring with junit
@SpringBootTest(classes = {EasyShopApplication.class})
class UserManagementServiceImplTest {

    @Autowired
    private UserManagementService userManagementService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testInjections() {
        Assertions.assertNotNull(userManagementService);
        Assertions.assertNotNull(userRepository);
    }

    @Test
    void testSignUp() throws DuplicateUserException, WeakPasswordException, TechnicalException {

        userRepository.deleteAll();

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
}
