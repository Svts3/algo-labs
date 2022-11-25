package ua.lviv.iot;

import java.util.HashMap;

public class Main {

    public static HashMap<Character, Integer> makeOffsetTable(String pattern) {
        HashMap<Character, Integer> offsetTable = new HashMap<Character, Integer>();
        for (int i = 0; i <= 255; i++) {
            offsetTable.put((char) i, pattern.length());
        }
        for (int i = 0; i < pattern.length() - 1; i++) {
            offsetTable.put(pattern.charAt(i), pattern.length() - i - 1);
        }
        return offsetTable;
    }

    public static int findSubstring(String text, String pattern) {
        HashMap<Character, Integer> offsetTable = makeOffsetTable(pattern);
        if (text.length() < pattern.length()) {
            return -1;
        }

        int i = pattern.length() - 1;
        int j = i;
        int k = i;

        while (j >= 0 && i <= text.length() - 1) {
            j = pattern.length() - 1;
            k = i;
            while (j >= 0 && text.charAt(k) == pattern.charAt(j)) {
                k--;
                j--;
            }
            i += offsetTable.get(text.charAt(i));
        }
        if (k >= text.length() - pattern.length()) {
            return -1;
        } else {
            return k + 1;
        }
    }
    

    public static void main(String[] args) {
        System.out.println(findSubstring("gewpetergwgerheoiajidsstepan", "peter"));
    }

}
