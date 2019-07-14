package org.mbonilla.wallet.validator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mbonilla.wallet.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserValidatorTest {
    @Autowired
    UserValidator userValidator;

    @Test
    public void supports() {
        Assert.assertTrue(userValidator.supports(User.class));
    }

    @Test
    public void validateUser() {
        User user = new User();
        user.setUsername("validUsername");
        user.setPassword("validPassword123");
        user.setPasswordConfirm("validPassword123");
        user.setPasswordConfirm("validPassword123");

        BindException errors = new BindException(user, "user");
        userValidator.validate(user, errors);

        Assert.assertFalse(errors.hasErrors());
    }

    @Test
    public void validateNotEmptyUsername() {
        User user = new User();
        BindException errors = new BindException(user, "user");
        userValidator.validate(user, errors);
        Assert.assertTrue(errors.hasErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount("username"));
        Assert.assertEquals("NotEmpty", errors.getFieldError("username").getCode());
    }

    @Test
    public void validateSizeUsernameSmall() {
        User user = new User();
        user.setUsername("small");
        BindException errors = new BindException(user, "user");
        userValidator.validate(user, errors);
        Assert.assertTrue(errors.hasErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount("username"));
        Assert.assertEquals("Size.userForm.username", errors.getFieldError("username").getCode());
    }

    @Test
    public void validateSizeUsernameBig() {
        User user = new User();
        user.setUsername("bigbigbigbigbigbigbigbigbigbigbigbigbigbigbigbigbigbigbigbigbigbigbigbgibgibgi");
        BindException errors = new BindException(user, "user");
        userValidator.validate(user, errors);
        Assert.assertTrue(errors.hasErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount("username"));
        Assert.assertEquals("Size.userForm.username", errors.getFieldError("username").getCode());
    }

    @Test
    public void validateSizePasswordSmall() {
        User user = new User();
        user.setUsername("validUsername");
        user.setPassword("small");
        BindException errors = new BindException(user, "user");
        userValidator.validate(user, errors);
        Assert.assertTrue(errors.hasErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount("password"));
        Assert.assertEquals("Size.userForm.password", errors.getFieldError("password").getCode());
    }

    @Test
    public void validateSizePasswordBig() {
        User user = new User();
        user.setUsername("validUsername");
        user.setPassword("bigbigbigbigbigbigbigbigbigbigbigbigbigbigbigbigbigbigbigbigbigbigbigbgibgibgi");
        BindException errors = new BindException(user, "user");
        userValidator.validate(user, errors);
        Assert.assertTrue(errors.hasErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount("password"));
        Assert.assertEquals("Size.userForm.password", errors.getFieldError("password").getCode());
    }

    @Test
    public void validateDiffPassword() {
        User user = new User();
        user.setUsername("validUsername");
        user.setPassword("validPassword");
        user.setPasswordConfirm("diffPassword");
        BindException errors = new BindException(user, "user");
        userValidator.validate(user, errors);
        Assert.assertTrue(errors.hasErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount("passwordConfirm"));
        Assert.assertEquals("Diff.userForm.passwordConfirm", errors.getFieldError("passwordConfirm").getCode());
    }

}
