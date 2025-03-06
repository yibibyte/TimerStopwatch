package ru.japp.stopwatchtimer.stopwatchtimer;

import java.time.Period;
import java.time.LocalDate;

public class PeriodExample {
    public static void main(String[] args) {
        // Получаем сегодняшнюю дату
        LocalDate today = LocalDate.now();
        System.out.println("Сегодня: " + today);

        // Создаем период
        Period period = Period.ofYears(1).plusMonths(6).plusDays(20); // 1 год, 6 месяцев и 20 дней
        System.out.println("Период: " + period);

        // Добавляем период к текущей дате
        LocalDate futureDate = today.plus(period);
        System.out.println("Будущая дата: " + futureDate);

        // Вычисляем разницу между двумя датами
        LocalDate pastDate = LocalDate.of(2023, 5, 15);
        Period difference = Period.between(pastDate, today);
        System.out.println("Разница между датами: " + difference);
    }
}