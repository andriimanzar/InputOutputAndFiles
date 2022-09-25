package com.manzar.task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PhoneNumberReader {

    public static final String FILE_NAME = "files/file.txt";
    public static final String FIRST_PATTERN = "(\\d{3})-(\\d{3})-(\\d{4})";
    public static final String SECOND_PATTERN = "^\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}$";

    private PhoneNumberReader() {
    }

    public static void readFileAndPrintValidPhoneNumbers() {
        try (FileReader fileReader = new FileReader((FILE_NAME));
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            bufferedReader.lines().filter(PhoneNumberReader::phoneNumberIsValid).forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean phoneNumberIsValid(String phoneNumber) {
        return phoneNumber.matches(FIRST_PATTERN) || phoneNumber.matches(SECOND_PATTERN);
    }
}
