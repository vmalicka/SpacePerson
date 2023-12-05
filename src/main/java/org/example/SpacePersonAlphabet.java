package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class SpacePersonAlphabet {

    private static final Map<Character, Character> spacePersonAlphabet = new HashMap<>();

    static {
        spacePersonAlphabet.put('a', '~');
        spacePersonAlphabet.put('b', '!');
        spacePersonAlphabet.put('c', '@');
        spacePersonAlphabet.put('d', '#');
        spacePersonAlphabet.put('e', '$');
        spacePersonAlphabet.put('f', '%');
        spacePersonAlphabet.put('g', '^');
        spacePersonAlphabet.put('h', '&');
        spacePersonAlphabet.put('i', '*');
        spacePersonAlphabet.put('j', '(');
        spacePersonAlphabet.put('k', ')');
        spacePersonAlphabet.put('l', '_');
        spacePersonAlphabet.put('m', '+');
        spacePersonAlphabet.put('n', '`');
        spacePersonAlphabet.put('o', '-');
        spacePersonAlphabet.put('p', '=');
        spacePersonAlphabet.put('q', '[');
        spacePersonAlphabet.put('r', ']');
        spacePersonAlphabet.put('s', '{');
        spacePersonAlphabet.put('t', '}');
        spacePersonAlphabet.put('u', '|');
        spacePersonAlphabet.put('v', ':');
        spacePersonAlphabet.put('w', ';');
        spacePersonAlphabet.put('x', '"');
        spacePersonAlphabet.put('y', '<');
        spacePersonAlphabet.put('z', '>');
        spacePersonAlphabet.put(' ', ' ');
    }

    public static String englishToSpacePerson(String englishString) {
        StringBuilder spacePersonString = new StringBuilder();
        for (char c : englishString.toCharArray()) {
            char converted = spacePersonAlphabet.getOrDefault(Character.toLowerCase(c), c);
            spacePersonString.append(converted);
        }
        return spacePersonString.toString();
    }

    public static String hashSpacePersonString(String spacePersonString) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(spacePersonString.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String caesarCipher(String text) {
        StringBuilder shiftedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                int shift = Character.isUpperCase(c) ? 65 : 97;
                shiftedText.append((char) ((c - shift + 5) % 26 + shift));
            } else {
                shiftedText.append(c);
            }
        }
        return shiftedText.toString();
    }

    public static void bruteForceCaesarCipher(String englishString) {
        for (int shift = 0; shift < 26; shift++) {
            StringBuilder shiftedText = new StringBuilder();
            for (char c : englishString.toCharArray()) {
                if (Character.isLetter(c)) {
                    int base = Character.isUpperCase(c) ? 65 : 97;
                    shiftedText.append((char) ((c - base + shift) % 26 + base));
                } else {
                    shiftedText.append(c);
                }
            }
            System.out.println("Shift " + shift + ": " + shiftedText);
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Example usage
        String englishInput = "Hello, World!";
        String spacePersonString = englishToSpacePerson(englishInput);
        System.out.println("Space Person string: " + spacePersonString);

        String hashedValue = hashSpacePersonString(spacePersonString);
        System.out.println("SHA256 hash value: " + hashedValue);

        String caesarShiftedText = caesarCipher(englishInput);
        System.out.println("Caesar cipher (5-character shift) on English string: " + caesarShiftedText);

        System.out.println("\nBrute force Caesar cipher (0-25 shifts) on English string:");
        bruteForceCaesarCipher(englishInput);
    }
}