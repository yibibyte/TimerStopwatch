package ru.japp.stopwatchtimer.stopwatchtimer;

import java.util.Date;

public class DateExample {
    public static void main(String[] args) {
        // Получение текущей даты и времени
        Date currentDate = new Date();
        System.out.println("Текущая дата и время: " + currentDate);

        // Преобразование даты в миллисекунды
        long timeInMillis = currentDate.getTime();
        System.out.println("Время в миллисекундах: " + timeInMillis);

        // Создание новой даты на основе строкового значения
        try {
            Date parsedDate = new Date("December 25, 2019");
            System.out.println("Разобранная дата: " + parsedDate);
        } catch (Exception e) {
            e.printStackTrace(); // Обработка ошибки парсинга
        }
    }
}