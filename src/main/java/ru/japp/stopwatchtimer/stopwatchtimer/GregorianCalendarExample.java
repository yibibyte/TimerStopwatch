package ru.japp.stopwatchtimer.stopwatchtimer;

import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class GregorianCalendarExample {
    public static void main(String[] args) {
        // Создаем экземпляр GregorianCalendar
        GregorianCalendar gregorianCalendar = new GregorianCalendar();

        // Получаем компоненты даты/времени
        int year = gregorianCalendar.get(GregorianCalendar.YEAR);
        int month = gregorianCalendar.get(GregorianCalendar.MONTH) + 1; // месяцы индексируются с 0
        int dayOfMonth = gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
        int hour = gregorianCalendar.get(GregorianCalendar.HOUR_OF_DAY);
        int minute = gregorianCalendar.get(GregorianCalendar.MINUTE);
        int second = gregorianCalendar.get(GregorianCalendar.SECOND);

        System.out.printf("Год: %d\n", year);
        System.out.printf("Месяц: %d\n", month);
        System.out.printf("День месяца: %d\n", dayOfMonth);
        System.out.printf("Час: %d\n", hour);
        System.out.printf("Минуты: %d\n", minute);
        System.out.printf("Секунды: %d\n", second);

        // Форматируем дату
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(gregorianCalendar.getTime());
        System.out.println("Форматированная дата: " + formattedDate);

        // Устанавливаем конкретную дату
        gregorianCalendar.set(2023, 10, 15, 12, 30, 45); // год, месяц (индексируется с 0), день, часы, минуты, секунды
        System.out.println("Установленная дата: " + sdf.format(gregorianCalendar.getTime()));
    }
}