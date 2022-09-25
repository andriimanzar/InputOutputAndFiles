package com.manzar.task2;

import java.util.List;

public class UserWriterAndReaderTest {
    public static void main(String[] args) {
        List<User> users = UserFileReader.readUsersFromFile();
        users.stream().forEach(System.out::println);
        UserJsonWriter.createAndFillUserJson(users);

    }
}
