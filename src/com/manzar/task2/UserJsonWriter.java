package com.manzar.task2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UserJsonWriter {

    public static final String JSON_FILE_NAME = "files/user.json";
    public static final String WHITESPACE = " ";

    private static BufferedWriter bufferedWriter;

    private UserJsonWriter() {
    }

    public static void createAndFillUserJson(List<User> userList) {
        try (FileWriter fileWriter = new FileWriter(JSON_FILE_NAME)) {
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("[\n");
            int lastIndex = userList.size() - 1;
            fillUsersInfoWithoutLast(userList, lastIndex);
            fillUserInfo(userList.get(lastIndex));
            bufferedWriter.write("\n]");
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void fillUserInfo(User user) throws IOException {
        bufferedWriter.write(WHITESPACE.repeat(2));
        bufferedWriter.write("{\n");
        bufferedWriter.write(WHITESPACE.repeat(4));
        bufferedWriter.write("\"name\": " + "\"" + user.getName() + "\",\n");
        bufferedWriter.write(WHITESPACE.repeat(4));
        bufferedWriter.write("\"age\": " + user.getAge() + "\n");
        bufferedWriter.write(WHITESPACE.repeat(2));
        bufferedWriter.write("}");
    }

    private static void fillUsersInfoWithoutLast(List<User> userList, int lastIndex) throws IOException {
        for (int i = 0; i < lastIndex; i++) {
            fillUserInfo(userList.get(i));
            bufferedWriter.write(",\n");
        }
    }
}
