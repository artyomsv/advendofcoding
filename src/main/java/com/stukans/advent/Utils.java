package com.stukans.advent;

public class Utils {

    public static Character[] convert(char[] hands) {
        Character[] characterArray = new Character[hands.length];
        for (int i = 0; i < hands.length; i++) {
            characterArray[i] = hands[i];
        }
        return characterArray;
    }

    public static char[] convert(Character[] hands) {
        char[] characterArray = new char[hands.length];
        for (int i = 0; i < hands.length; i++) {
            characterArray[i] = hands[i];
        }
        return characterArray;
    }
}
