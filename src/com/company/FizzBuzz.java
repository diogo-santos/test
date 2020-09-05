package com.company;

public class FizzBuzz {

    public static void main(String[] args) {
        assert "Fizz".equals(fizzBuzz(3));
        assert "Buzz".equals(fizzBuzz(5));
        assert "FizzBuzz".equals(fizzBuzz(15));
        assert "7".equals(fizzBuzz(7));

        for (int number=1; number < 1000; number+=2) {
            System.out.println(fizzBuzz(number));
        }
    }

    private static String fizzBuzz(int number) {
        String message = "";
        if (number % 3 == 0) {
            message += "Fizz";
        }
        if (number % 5 == 0) {
            message += "Buzz";
        } else if (message.isEmpty()) {
            message += number;
        }
        return message;
    }
}
