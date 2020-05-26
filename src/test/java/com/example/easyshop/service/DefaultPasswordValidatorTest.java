package com.example.easyshop.service;

import com.example.easyshop.EasyShopApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // @RunWith: integrate spring with junit
@SpringBootTest(classes = {EasyShopApplication.class})
class DefaultPasswordValidatorTest {

    private PasswordValidator passwordValidator = new DefaultPasswordValidator();

    @Test
    void testNullInput() {
        try {
            passwordValidator.validate(null);
            Assertions.fail();
        }catch (IllegalArgumentException e) {
        }catch(Exception e) {
            Assertions.fail();
        }
    }

    @Test
    void testPasswordWithLessThanSixCharecter() {
       boolean result =  passwordValidator.validate("12345");
       Assertions.assertTrue(!result);
    }

    void testPasswordWithSixOrMoreCharecters() {
        boolean result1 =  passwordValidator.validate("123456");
        Assertions.assertTrue(result1);

        boolean result2 =  passwordValidator.validate("12345678");
        Assertions.assertTrue(result2);
    }
}
