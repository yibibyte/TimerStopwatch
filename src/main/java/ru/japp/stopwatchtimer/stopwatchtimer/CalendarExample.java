package ru.japp.stopwatchtimer.stopwatchtimer;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class CalendarExample {
    public static void main(String[] args) {
        // Получаем текущее время
        Calendar calendar = Calendar.getInstance();

        // Получаем отдельные компоненты даты/времени
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // месяцы индексируются с 0
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        System.out.printf("Год: %d\n", year);
        System.out.printf("Месяц: %d\n", month);
        System.out.printf("День месяца: %d\n", dayOfMonth);
        System.out.printf("Час: %d\n", hour);
        System.out.printf("Минуты: %d\n", minute);
        System.out.printf("Секунды: %d\n", second);

        // Форматируем дату
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(calendar.getTime());
        System.out.println("Форматированная дата: " + formattedDate);

        // Добавляем дни
        calendar.add(Calendar.DATE, 7); // добавляем 7 дней
        System.out.println("Новая дата после добавления 7 дней: " + sdf.format(calendar.getTime()));
    }
}