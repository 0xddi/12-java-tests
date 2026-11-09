import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntegerDecodeTest {
    // случай пустой строки ""
    @Test
    void empty() {
        var e = assertThrows(NumberFormatException.class, () -> Integer.decode(""));
        assertEquals("Zero length string", e.getMessage());
    }

    // случай с отрицательным числом, первый символ -
    @Test void negative() { assertEquals(-123, Integer.decode("-123")); }

    // случай с явно положительным числом, первый символ +
    @Test void plus() { assertEquals(123, Integer.decode("+123")); }

    // случай с обычным числом десятичной с. с.
    @Test void decimal() { assertEquals(123, Integer.decode("123")); }

    // случай с префиксом "0x" для шестнадцатеричной системы счисления
    @Test void hexLower() { assertEquals(255, Integer.decode("0xFF")); }

    // случай с префиксом "0X" для шестнадцатеричной системы счисления
    @Test void hexUpper() { assertEquals(255, Integer.decode("0XFF")); }

    // случай с префиксом для хекс-кода
    @Test void hashHex() { assertEquals(255, Integer.decode("#FF")); }

    // случай с префиксом "0" для восьмеричной системы счисления
    @Test void octal() { assertEquals(8, Integer.decode("010")); }

    // случай с некорректным расположением символа знака числа
    @Test void signInWrongPosition() {
        var e = assertThrows(NumberFormatException.class, () -> Integer.decode("--12"));
        assertEquals("Sign character in wrong position", e.getMessage());
    }

    // случай с Integer.MIN_VALUE
    @Test void minValue() { assertEquals(Integer.MIN_VALUE, Integer.decode("-2147483648")); }

    // проверяем проброс исключения от parseInt
    @Test void formatErrorNegative() {
        assertThrows(NumberFormatException.class, () -> Integer.decode("-abc"));
    }

    // проверяем проброс исключения от parseInt
    @Test void formatErrorPositive() {
        assertThrows(NumberFormatException.class, () -> Integer.decode("0xZZ"));
    }
}
