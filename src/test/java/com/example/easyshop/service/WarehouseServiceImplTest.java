package com.example.easyshop.service;

import com.example.easyshop.EasyShopApplication;
import com.example.easyshop.entity.Role;
import com.example.easyshop.entity.Token;
import com.example.easyshop.entity.User;
import com.example.easyshop.repository.CategoryRepository;
import com.example.easyshop.repository.RoleRepository;
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

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class) // @RunWith: integrate spring with junit
@SpringBootTest(classes = {EasyShopApplication.class})
@ContextConfiguration(loader= AnnotationConfigContextLoader.class)
class WarehouseServiceImplTest  {

    @Configuration
    static class ContextConfiguration {

        @Bean
        public UserManagementService getUserMgmtService() {
            return new UserManagementServiceImpl();
        }

        @Bean
        public WarehouseService getWarehouseService() {
            return new WarehouseServiceImpl();
        }
    }

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private UserManagementService userManagementService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private RoleRepository roleRepository;


//    private LoginToken signupAndLogin() throws DuplicateUserException, WeakPasswordException, TechnicalException, LoginFailedException, UserNotFoundException, AccessDeniedException, InvalidTokenException {
//
//        SignUpData signUpData = new SignUpData();
//        signUpData.setFirstname("Admin");
//        signUpData.setLastname("Admini");
//        signUpData.setUsername("admin");
//        signUpData.setPassword("admin12345");
//        userManagementService.signUp(signUpData);
//
//
//        List<User> users = userRepository.findAll();
//        Assertions.assertEquals(1, users.size());
//        Assertions.assertEquals("Admin", users.get(0).getFirstname());
//        Assertions.assertEquals("Admini", users.get(0).getLastname());
//        Assertions.assertEquals("admin", users.get(0).getUsername());
//        Assertions.assertEquals("admin12345", users.get(0).getPassword());
//
//        LoginData loginData = new LoginData();
//        loginData.setUsername("admin");
//        loginData.setPassword("admin12345");
//        LoginToken loginToken = userManagementService.login(loginData);
//
//        List<Token> tokens =tokenRepository.findAll();
//        Assertions.assertEquals(1, tokens.size());
//        Assertions.assertEquals(tokens.get(0).getValue(),loginToken.getValue());
//
//        RoleData roleData = new RoleData();
//        roleData.setName("ADMIN");
//        UserData userData = new UserData();
//        userData.setUsername("admin");
//        userManagementService.grantRole(userData, roleData, loginToken);
//
//        return loginToken;
//
//    }

    // Run the following before each test
    @BeforeEach
    void setUp() {
//        tokenRepository.deleteAll();
//        userRepository.deleteAll();
//        roleRepository.deleteAll();
//
//        Role adminRole = new Role();
//        adminRole.setName("ADMIN");
//        roleRepository.save(adminRole);
//
//        Role userRol = new Role();
//        userRol.setName("USER");
//        roleRepository.save(userRol);
    }

    @Test
    void testAddCategory()
            throws ValidationFailedException,
            InvalidTokenException, TechnicalException,
            AccessDeniedException, CategoryNotFoundException,
            LoginFailedException, UserNotFoundException,
            DuplicateUserException, WeakPasswordException {

        // LoginToken loginToken = signupAndLogin();

//        CategoryData categoryData = new CategoryData();
//        categoryData.setName("Foundation");
//        categoryData.setParentName("Cosmetic");
//
//        warehouseService.addCategory(,categoryData);
//
//        CategoryRepository categoryRepository.
//
//        Assertions.assertEquals("", );
//        Assertions.assertEquals("Alex", users.get(0).getFirstname());


    }


}

