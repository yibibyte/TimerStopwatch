package ru.japp.stopwatchtimer.stopwatchtimer;

import java.time.Duration;
import java.time.Instant;

public class DurationAndInstantExample {
    public static void main(String[] args) {
        // Получаем текущее время
        Instant now = Instant.now();
        System.out.println("Текущий момент времени: " + now);

        // Создаем длительность в секундах
        Duration duration = Duration.ofSeconds(3600); // 1 час
        System.out.println("Продолжительность: " + duration);

        // Добавляем продолжительность к текущему моменту времени
        Instant future = now.plus(duration);
        System.out.println("Будущий момент времени: " + future);

        // Измеряем разницу между двумя моментами времени
        Instant past = Instant.parse("2023-11-01T10:15:30Z"); // фиксированный момент времени
        Duration between = Duration.between(past, now);
        System.out.println("Разница во времени: " + between);
    }
}