package com.manzar.task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class WordCounter {

    public static final Comparator<Map.Entry<String, Long>> VALUE_COMPARATOR = Map.Entry.comparingByValue();
    public static final String FILE_NAME = "files/words.txt";
    public static final String NON_WORDS = "[^a-zA-Z]";
    public static final String WHITESPACE = " ";
    public static final String NEW_LINE = "\n";

    private WordCounter() {
    }

    public static String countWordsInFile() {
        List<String> lines = readLinesFromFile();

        Map<String, Long> wordFrequencyMap = lines.stream()
                .map(word -> word.replaceAll(NON_WORDS, WHITESPACE).trim().split(WHITESPACE)).
                flatMap(Arrays::stream).collect(groupingBy(Function.identity(), counting()));


        return wordFrequencyMap.entrySet().stream().sorted(VALUE_COMPARATOR.reversed()).
                map(a -> a.getKey() + WHITESPACE + a.getValue()).collect(joining(NEW_LINE));
    }

    private static List<String> readLinesFromFile() {
        try (FileReader fileReader = new FileReader((FILE_NAME));
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            return bufferedReader.lines().toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
