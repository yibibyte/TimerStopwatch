package ru.japp.stopwatchtimer.stopwatchtimer;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ClockExample {
    public static void main(String[] args) {
        // Получаем текущее время по умолчанию
        Clock clock = Clock.systemDefaultZone();
        LocalDateTime now = LocalDateTime.now(clock);
        System.out.println("Текущее время по умолчанию: " + now);

        // Получаем текущее время в конкретной временной зоне
        ZoneId zoneId = ZoneId.of("America/New_York");
        Clock nyClock = Clock.system(zoneId);
        LocalDateTime nyNow = LocalDateTime.now(nyClock);
        System.out.println("Текущее время в Нью-Йорке: " + nyNow);
    }
}