package ru.japp.stopwatchtimer.stopwatchtimer;

import java.time.Year;

public class YearExample {
    public static void main(String[] args) {
        // Получаем текущий год
        Year currentYear = Year.now();
        System.out.println("Текущий год: " + currentYear);

        // Проверка високосного года
        boolean isLeapYear = currentYear.isLeap();
        System.out.println("Является ли текущий год високосным? " + isLeapYear);

        // Операции над годами
        Year nextYear = currentYear.plusYears(1);
        System.out.println("Следующий год: " + nextYear);
    }
}