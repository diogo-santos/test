package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public class FindDigit {
    private static final String ADD = "+";
    private static final String SUBTRACT = "-";
    private static final String MULTIPLY = "*";
    private static final String DIVIDE = "/";
    private static final String OPERATION_REGEX = "[+\\-*/=]";
    private static final String X_DIGIT = "x";

    public static void main(String[] args) {
        assert '4' == findDigit("3x + 12 = 46");
        assert '2' == findDigit("4 - 2 = x");
        assert '0' == findDigit("1x0 * 12 = 1200");
        assert '0' == findDigit("10 - x = 10");
        assert '0' == findDigit("10 + x = 10");
        assert '0' == findDigit("10 * x = 0");
        assert '0' == findDigit("10 / x = 0");
        assert '1' == findDigit("10 - x = 9");
        assert '1' == findDigit("10 + x = 11");
        assert '1' == findDigit("10 * x = 10");
        assert '1' == findDigit("10 / x = 10");
    }

    private static char findDigit(final String formula) {
        if (isNull(formula) || formula.trim().isEmpty() || !formula.contains(X_DIGIT)) {
            throw new IllegalArgumentException("Invalid formula, it cannot be empty and must contain one missing digit");
        }
        String[] formulaSplit = formula.split(OPERATION_REGEX);
        if (formulaSplit.length != 3) {
            throw new IllegalArgumentException("Invalid formula, it should have 3 numbers");
        }
        String number1 = formulaSplit[0].trim();
        String number2 = formulaSplit[1].trim();
        String number3 = formulaSplit[2].trim();

        Matcher matcher = Pattern.compile(OPERATION_REGEX).matcher(formula);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid formula, it should have a Math operation");
        }
        String operation = matcher.group();

        int equationResult;
        int xIndex;
        if ((xIndex = number1.indexOf(X_DIGIT)) != -1) {
            equationResult = calculate(number3, number2, operation, true);
        } else if ((xIndex = number2.indexOf(X_DIGIT)) != -1) {
            equationResult = calculate(number3, number1, operation, true);
        } else if ((xIndex = number3.indexOf(X_DIGIT)) != -1) {
            equationResult = calculate(number1, number2, operation, false);
        } else {
            throw new IllegalArgumentException("Invalid formula, it should have one unknown digit");
        }

        return String.valueOf(equationResult).charAt(xIndex);
    }

    private static int calculate(final String numberStr1, final String numberStr2, final String operation, boolean invert) {
        int number1 = parseInt(numberStr1);
        int number2 = parseInt(numberStr2);

        int result = 0;
        if (ADD.equals(operation)) {
            result = invert? number1 - number2 : number1 + number2;
        } else if (SUBTRACT.equals(operation)) {
            result = number1 - number2;
        } else if (MULTIPLY.equals(operation)) {
            result = invert? number1 / number2 : number1 * number2;
        } else if (DIVIDE.equals(operation)) {
            result = invert? number1 * number2 : number1 / number2;
        }

        return Math.abs(result);
    }

    private static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Invalid number %s", number));
        }
    }
}
