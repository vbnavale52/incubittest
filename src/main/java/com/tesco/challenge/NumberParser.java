/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesco.challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple number parser.
 */
public class NumberParser {

    private static final String DEFAULT_DELIMITER = "[,\\n]";
    private static final String CUSTOM_DELIMITER_PATTERN = "^//(.)\\n(.*)$";
    private static final int CUSTOM_DELIMITER_DELIMITER_GROUP = 1;
    private static final int CUSTOM_DELIMITER_NUMBERS_GROUP = 2;

    /**
     * Parses the input and returns an List of Integers.
     * <p>
     * @param input either "" or a delimited set of numbers.
     * @return a parsed List of Integers from the input.
     */
    public List<Integer> getNumbers(String input) {
        List<Integer> numbers = new ArrayList<>();
        for (String number : tokenise(input)) {
            numbers.add(Integer.valueOf(number));
        }
        return numbers;
    }

    private String[] tokenise(String input) {
        String delimiter = DEFAULT_DELIMITER;
        String numbers = input;
        if (input.startsWith("//")) {
            Matcher delimiterMatcher = Pattern.compile(CUSTOM_DELIMITER_PATTERN).matcher(input);
            if (!delimiterMatcher.matches()) {
                throw new IllegalArgumentException("Unable to parse input: " + input);
            }
            delimiter = delimiterMatcher.group(CUSTOM_DELIMITER_DELIMITER_GROUP);
            numbers = delimiterMatcher.group(CUSTOM_DELIMITER_NUMBERS_GROUP);
        }
        return numbers.split(delimiter);
    }
}