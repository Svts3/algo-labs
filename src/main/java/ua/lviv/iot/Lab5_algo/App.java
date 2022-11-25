package ua.lviv.iot.Lab5_algo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class App {

    public static Map<Character, Integer> createOffsetTable(String pattern) {

        Map<Character, Integer> offsetTable = new HashMap<>();

        for (int i = 0; i <= 255; i++) {
            offsetTable.put((char) i, pattern.length());
        }
        for (int i = 0; i < pattern.length() - 1; i++) {
            offsetTable.put(pattern.charAt(i), pattern.length() - i - 1);
        }
        return offsetTable;
    }

    public static Boolean findSubString(String text, String pattern) {

        Map<Character, Integer> offsetTable = createOffsetTable(pattern);

        if (text.length() < pattern.length()) {
            System.out.println("Text length can't be bigger than pattern length");
            return false;
        }

        Integer patternLength = pattern.length();

        Integer i = patternLength - 1;

        Integer j = patternLength - 1;

        Integer k = patternLength - 1;

        while (j >= 0 && i <= text.length() - 1) {

            j = pattern.length() - 1;
            k = i;
            while (j >= 0) {

                if (text.charAt(k) != pattern.charAt(j)) {
                    break;
                }
                k--;
                j--;
            }

            i += offsetTable.get(text.charAt(i));

        }
        if (k >= text.length() - patternLength) {

            return false;

        } else {

            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(findSubString(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Eu mi bibendum neque egestas congue quisque. Ipsum nunc aliquet bibendum enim facilisis gravida neque. Eu feugiat pretium nibh ipsum consequat nisl abcd vel pretium. Volutpat est velit egestas dui id ornare",
                "abcd"));
    }

}
