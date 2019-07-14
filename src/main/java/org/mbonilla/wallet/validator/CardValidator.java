package org.mbonilla.wallet.validator;

import org.mbonilla.wallet.model.Card;
import org.mbonilla.wallet.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;

/**
 * @see https://www.geeksforgeeks.org/program-credit-card-number-validation/
 */
@Component
public class CardValidator implements Validator {

    @Autowired
    private CardService cardService;

    // Return true if the card number is valid
    private static boolean isValid(long number) {
        return (getSize(number) >= 13 &&
                getSize(number) <= 16) &&
                (prefixMatched(number, 4) ||
                        prefixMatched(number, 5) ||
                        prefixMatched(number, 37) ||
                        prefixMatched(number, 6)) &&
                ((sumOfDoubleEvenPlace(number) +
                        sumOfOddPlace(number)) % 10 == 0);
    }

    // Get the result from Step 2
    private static int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 2; i >= 0; i -= 2)
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);

        return sum;
    }

    // Return this number if it is a single digit, otherwise,
    // return the sum of the two digits
    private static int getDigit(int number) {
        if (number < 9)
            return number;
        return number / 10 + number % 10;
    }

    // Return sum of odd-place digits in number
    private static int sumOfOddPlace(long number) {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 1; i >= 0; i -= 2)
            sum += Integer.parseInt(num.charAt(i) + "");
        return sum;
    }

    // Return true if the digit d is a prefix for number
    private static boolean prefixMatched(long number, int d) {
        return getPrefix(number, getSize(d)) == d;
    }

    // Return the number of digits in d
    private static int getSize(long d) {
        String num = d + "";
        return num.length();
    }

    // Return the first k number of digits from
    // number. If the number of digits in number
    // is less than k, return number.
    private static long getPrefix(long number, int k) {
        if (getSize(number) > k) {
            String num = number + "";
            return Long.parseLong(num.substring(0, k));
        }
        return number;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Card.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Card card = (Card) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "number", "NotEmpty");
        if (cardService.findByNumber(card.getNumber()) != null) {
            errors.rejectValue("number", "Duplicate.cardForm.number");
        }
        if (card.getNumber() != null && !isValid(card.getNumber())) {
            errors.rejectValue("number", "NotValid.cardForm.number");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "month", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year", "NotEmpty");
        if (card.getYear() != null) {
            if (card.getYear() < LocalDate.now().getYear()
                    || (card.getYear() == LocalDate.now().getYear() && card.getMonth() != null && card.getMonth() < LocalDate.now().getMonthValue())) {
                errors.rejectValue("year", "Expired.cardForm.expiration");
            }
            if (card.getYear() > 3000) {
                errors.rejectValue("year", "Year.cardForm.expiration");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "secret", "NotEmpty");
        if (card.getSecret() != null && String.valueOf(card.getSecret()).length() != 3) {
            errors.rejectValue("secret", "Size.cardForm.secret");
        }
    }
}
