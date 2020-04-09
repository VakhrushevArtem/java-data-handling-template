package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     *
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        BigDecimal bigDecimalA = BigDecimal.valueOf(a);
        BigDecimal bigDecimalB = BigDecimal.valueOf(b);
        return bigDecimalA.divide(bigDecimalB, range, RoundingMode.HALF_UP);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        int simpleDigit = 2;
        int count = 1;
        while (count <= range) {
            simpleDigit++;
            int j = 0;
            for (int i = 1; i <= simpleDigit; i++) {
                if ((simpleDigit % i) == 0) {
                    j++;
                }
            }
            if (j == 2) {
                count++;
            }
        }
        return BigInteger.valueOf(simpleDigit);
    }
}
