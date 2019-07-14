package org.mbonilla.wallet.validator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mbonilla.wallet.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindException;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardValidatorTest {

    private static Long validCardNumber = 378282246310005L;
    @Autowired
    CardValidator cardValidator;

    @Test
    public void supports() {
        Assert.assertTrue(cardValidator.supports(Card.class));
    }

    @Test
    public void validateCard() {
        Card card = new Card();
        card.setNumber(validCardNumber);
        card.setMonth(1);
        card.setYear(LocalDate.now().getYear() + 1);
        card.setSecret(123);

        BindException errors = new BindException(card, "card");
        cardValidator.validate(card, errors);

        Assert.assertFalse(errors.hasErrors());
    }

    @Test
    public void validateCardInvalidNumber() {
        Card card = new Card();
        card.setNumber(111L);

        BindException errors = new BindException(card, "card");
        cardValidator.validate(card, errors);

        Assert.assertTrue(errors.hasErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount("number"));
        Assert.assertEquals("NotValid.cardForm.number", errors.getFieldError("number").getCode());
    }

    @Test
    public void validateCardInvalidYearExpiration() {
        Card card = new Card();
        card.setNumber(validCardNumber);
        card.setMonth(1);
        card.setYear(LocalDate.now().getYear() - 1);
        card.setSecret(123);

        BindException errors = new BindException(card, "card");
        cardValidator.validate(card, errors);

        Assert.assertTrue(errors.hasErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount("year"));
        Assert.assertEquals("Expired.cardForm.expiration", errors.getFieldError("year").getCode());
    }

    @Test
    public void validateCardInvalidYearMonthExpiration() {
        Card card = new Card();
        card.setNumber(validCardNumber);
        card.setMonth(LocalDate.now().getMonthValue() - 1);
        card.setYear(LocalDate.now().getYear());
        card.setSecret(123);

        BindException errors = new BindException(card, "card");
        cardValidator.validate(card, errors);

        Assert.assertTrue(errors.hasErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount("year"));
        Assert.assertEquals("Expired.cardForm.expiration", errors.getFieldError("year").getCode());
    }

    @Test
    public void validateCardInvalidYear() {
        Card card = new Card();
        card.setNumber(validCardNumber);
        card.setMonth(1);
        card.setYear(3015);
        card.setSecret(123);

        BindException errors = new BindException(card, "card");
        cardValidator.validate(card, errors);

        Assert.assertTrue(errors.hasErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount("year"));
        Assert.assertEquals("Year.cardForm.expiration", errors.getFieldError("year").getCode());
    }

    @Test
    public void validateCardInvalidSecretSmaller() {
        Card card = new Card();
        card.setNumber(validCardNumber);
        card.setMonth(1);
        card.setYear(LocalDate.now().getYear() + 1);
        card.setSecret(12);

        BindException errors = new BindException(card, "card");
        cardValidator.validate(card, errors);

        Assert.assertTrue(errors.hasErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount("secret"));
        Assert.assertEquals("Size.cardForm.secret", errors.getFieldError("secret").getCode());
    }

    @Test
    public void validateCardInvalidSecretBigger() {
        Card card = new Card();
        card.setNumber(validCardNumber);
        card.setMonth(1);
        card.setYear(LocalDate.now().getYear() + 1);
        card.setSecret(1234);

        BindException errors = new BindException(card, "card");
        cardValidator.validate(card, errors);

        Assert.assertTrue(errors.hasErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount("secret"));
        Assert.assertEquals("Size.cardForm.secret", errors.getFieldError("secret").getCode());
    }
}
