package ru.japp.stopwatchtimer.stopwatchtimer;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeExample {
    public static void main(String[] args) {
        // Получаем текущее время в определенной временной зоне
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        ZonedDateTime zdt = ZonedDateTime.now(zoneId);
        System.out.println("Текущее время в Москве: " + zdt);

        // Конвертация в другой временной пояс
        ZoneId anotherZoneId = ZoneId.of("America/New_York");
        ZonedDateTime convertedZDT = zdt.withZoneSameInstant(anotherZoneId);
        System.out.println("Текущее время в Нью-Йорке: " + convertedZDT);

        // Форматирование даты и времени
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss zzzz");
        String formattedDate = zdt.format(formatter);
        System.out.println("Форматированное время в Москве: " + formattedDate);
    }
}