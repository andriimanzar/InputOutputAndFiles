package com.manzar.task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserFileReader {

    public static final String FILE_NAME = "files/user.txt";

    private UserFileReader() {
    }

    public static List<User> readUsersFromFile() {
        List<User> users = new ArrayList<>();
        try (FileReader fileReader = new FileReader((FILE_NAME));
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            bufferedReader.readLine();
            while (bufferedReader.ready()) {
                User user = parseUserFromRow(bufferedReader.readLine());
                users.add(user);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    private static User parseUserFromRow(String row) {
        String[] fields = row.split(" ");
        String name = fields[0];
        int age = Integer.parseInt(fields[1]);
        return new User(name, age);
    }
}

