package com.manzar.task2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UserJsonWriter {

    public static final String JSON_FILE_NAME = "files/user.json";
    public static final String WHITESPACE = " ";
    public static final String PRINT_NAME = "\"name\": " + "\"%s\",";
    public static final String PRINT_AGE = "\"age\": %d";

    private static BufferedWriter bufferedWriter;

    private UserJsonWriter() {
    }

    public static void fillUserJson(List<User> userList) {
        try (FileWriter fileWriter = new FileWriter(JSON_FILE_NAME)) {
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("[");
            bufferedWriter.newLine();
            int lastIndex = userList.size() - 1;
            fillUsersInfoWithoutLast(userList, lastIndex);
            fillUserInfo(userList.get(lastIndex));
            bufferedWriter.newLine();
            bufferedWriter.write("]");
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeBufferedReader();
        }
    }

    private static void fillUserInfo(User user) throws IOException {
        printWhitespaces(2);
        bufferedWriter.write("{");
        writeName(user.name());
        writeAge(user.age());
        bufferedWriter.newLine();
        printWhitespaces(2);
        bufferedWriter.write("}");
    }

    private static void writeName(String name) throws IOException {
        bufferedWriter.newLine();
        printWhitespaces(4);
        bufferedWriter.write(String.format(PRINT_NAME, name));
    }

    private static void writeAge(int age) throws IOException {
        bufferedWriter.newLine();
        printWhitespaces(4);
        bufferedWriter.write(String.format(PRINT_AGE, age));
    }

    private static void printWhitespaces(int n) throws IOException {
        bufferedWriter.write(WHITESPACE.repeat(n));
    }

    private static void fillUsersInfoWithoutLast(List<User> userList, int lastIndex) throws IOException {
        for (int i = 0; i < lastIndex; i++) {
            fillUserInfo(userList.get(i));
            bufferedWriter.write(",");
            bufferedWriter.newLine();
        }
    }

    private static void closeBufferedReader() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
