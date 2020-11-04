package com.calc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Calc {
    private String enter;

    public Calc(String arg) throws Exception {
        enter = arg;
        checkEnter(enter);
    }

    private String arabicToRoman(int number) {
        if (number == 0){
            return " ";
        }
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    private void romanToArabic(){
        String[] a = enter.split(" ");
        for (int i = 0; i <= 2; i = i + 2){
            switch (a[i]) {
                case "I" -> a[i] = "1";
                case "II" -> a[i] = "2";
                case "III" -> a[i] = "3";
                case "IV" -> a[i] = "4";
                case "V" -> a[i] = "5";
                case "VI" -> a[i] = "6";
                case "VII" -> a[i] = "7";
                case "VIII" -> a[i] = "8";
                case "IX" -> a[i] = "9";
                case "X" -> a[i] = "10";
            }
        }
        enter = a[0] + " " + a[1] + " " + a[2];
    }

    private void checkEnter(String enter) throws Exception {
        ArrayList<String> rim0 = new ArrayList<>();
        rim0.add("I");
        rim0.add("II");
        rim0.add("III");
        rim0.add("IV");
        rim0.add("V");
        rim0.add("VI");
        rim0.add("VII");
        rim0.add("VIII");
        rim0.add("IX");
        rim0.add("X");
        HashSet<String> rim = new HashSet<>(rim0);

        ArrayList<String> arab0 = new ArrayList<>();
        arab0.add("1");
        arab0.add("2");
        arab0.add("3");
        arab0.add("4");
        arab0.add("5");
        arab0.add("6");
        arab0.add("7");
        arab0.add("8");
        arab0.add("9");
        arab0.add("10");
        HashSet<String> arab = new HashSet<>(arab0);

        ArrayList<String> znak0 = new ArrayList<>();
        znak0.add("+");
        znak0.add("-");
        znak0.add("*");
        znak0.add("/");
        HashSet<String> znak = new HashSet<>(znak0);

        String[] arr = enter.split(" ");
        if (arr.length > 3 | arr.length < 3){ // проверяем, что в введенной строке 3 элемента
            throw new Exception("Invalid input (The valid data entry format is: 1 + 1, 2 - 1, II - I.)");
        } else if (!znak.contains(arr[1])){ // проверяем что 2-ым элементом является допустимый знак
            throw new Exception("Invalid input (Allowed math signs: + - / *)");
        } else if (arab.contains(arr[0]) & arab.contains(arr[2])){ // проверяем что были введены только арабские цифры
            System.out.println(calculation());
        } else if (rim.contains(arr[0]) & rim.contains(arr[2])){ //  проверяем что были введены только римские цифры
            romanToArabic(); // переводим римские цифры в арабские
            System.out.println(arabicToRoman(calculation()));
        } else {
            throw new Exception("Invalid input (You can enter ONLY Arabic or ONLY Roman numerals) ->" +
                    "\n-> (numbers from 1 to 10 are accepted)");
        }
    }

    private int calculation(){
        // считывание первого числа
        int first_number = Integer.parseInt(enter.split(" ")[0]);
        // считывание второго числа
        int second_number = Integer.parseInt(enter.split(" ")[2]);
        // считывание знака
        char sign = enter.split(" ")[1].charAt(0);
        // рассчеты
        int result = 0;
        switch (sign) {
            case '+' -> result = plus(first_number, second_number);
            case '-' -> result = minus(first_number, second_number);
            case '*' -> result = multiply(first_number, second_number);
            case '/' -> result = split(first_number, second_number);
        }
        return result;
    }

    private int plus(int a, int b){
        return a+b;
    }

    private int minus(int a, int b){
        return a-b;
    }

    private int multiply(int a, int b){
        return a*b;
    }

    private int split(int a, int b){
        return a/b;
    }
}
