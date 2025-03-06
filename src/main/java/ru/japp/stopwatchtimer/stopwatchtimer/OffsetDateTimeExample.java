package ru.japp.stopwatchtimer.stopwatchtimer;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class OffsetDateTimeExample {
    public static void main(String[] args) {
        // Получаем текущее время с смещением UTC+03:00
        OffsetDateTime offsetDateTime = OffsetDateTime.now(ZoneOffset.of("+03:00"));
        System.out.println("Текущее время с UTC+03:00: " + offsetDateTime);

        // Конвертируем смещение
        OffsetDateTime withAnotherOffset = offsetDateTime.withOffsetSameInstant(ZoneOffset.of("-05:00"));
        System.out.println("То же самое время с UTC-05:00: " + withAnotherOffset);

        // Форматирование даты и времени
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss XX");
        String formattedDate = offsetDateTime.format(formatter);
        System.out.println("Форматированное время с UTC+03:00: " + formattedDate);
    }
}