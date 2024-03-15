package io.github.linpeilie.utils;

public class CharUtils {
    /**
     * 是否空白符<br>
     * 空白符包括空格、制表符、全角空格和不间断空格<br>
     *
     * @param c 字符
     * @return 是否空白符
     * @see Character#isWhitespace(int)
     * @see Character#isSpaceChar(int)
     * @since 4.0.10
     */
    public static boolean isBlankChar(char c) {
        return isBlankChar((int) c);
    }

    /**
     * 是否空白符<br>
     * 空白符包括空格、制表符、全角空格和不间断空格<br>
     *
     * @param c 字符
     * @return 是否空白符
     * @see Character#isWhitespace(int)
     * @see Character#isSpaceChar(int)
     * @since 4.0.10
     */
    public static boolean isBlankChar(int c) {
        return Character.isWhitespace(c)
               || Character.isSpaceChar(c)
               || c == '\ufeff'
               || c == '\u202a'
               || c == '\u0000'
               // issue#I5UGSQ，Hangul Filler
               || c == '\u3164'
               // Braille Pattern Blank
               || c == '\u2800'
               // MONGOLIAN VOWEL SEPARATOR
               || c == '\u180e';
    }
}
