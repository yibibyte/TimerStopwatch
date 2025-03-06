package ru.japp.stopwatchtimer.stopwatchtimer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeExample {
    public static void main(String[] args) {
        // Текущие дата и время
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        LocalDateTime current = LocalDateTime.now();

        System.out.println("Сегодняшняя дата: " + today);
        System.out.println("Текущее время: " + now);
        System.out.println("Текущая дата и время: " + current);

        // Форматирование даты
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = current.format(formatter);
        System.out.println("Отформатированная дата: " + formattedDate);

        // Манипуляции с датой и временем
        LocalDate nextWeek = today.plusWeeks(1); // Добавление недели
        LocalTime oneHourLater = now.plusHours(1); // Добавление часа
        System.out.println("Дата через неделю: " + nextWeek);
        System.out.println("Время через час: " + oneHourLater);
    }
}