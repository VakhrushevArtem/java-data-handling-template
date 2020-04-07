package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {
        String str = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"));
            str = reader.readLine();
            Pattern patternNumberCard = Pattern.compile("\\d{4} \\d{4} \\d{4} \\d{4}");
            Matcher matcher = patternNumberCard.matcher(str);
            while (matcher.find()) {
                String cardNumber = matcher.group();
                String changedCardNumber = cardNumber.replaceAll(cardNumber.substring(5, 14), "**** ****");
                str = str.replaceAll(cardNumber, changedCardNumber);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String str = null;
        String temp = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"));
            str = reader.readLine();
            str = str.replaceAll("\\$\\{payment_amount}", temp.valueOf((int) paymentAmount));
            str = str.replaceAll("\\$\\{balance}", temp.valueOf((int) balance));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
