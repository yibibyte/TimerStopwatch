package ru.japp.stopwatchtimer.stopwatchtimer;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerStopwatchApp extends Application {

    private Label timerLabel;
    private Label stopwatchLabel;
    private TextField timerInput;
    private Button startTimerButton;
    private Button stopTimerButton;
    private Button startStopwatchButton;
    private Button stopStopwatchButton;
    private Button lapStopwatchButton;
    private Button resetStopwatchButton;
    private List<String> laps = new ArrayList<>();
    private Instant stopwatchStartTime;
    private Instant lastLapTime;
    private AnimationTimer stopwatchAnimation;
    private ScheduledExecutorService timerExecutor;
    private volatile int remainingSeconds;
    private ListView<String> lapsListView = new ListView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Таймер и секундомер");
        primaryStage.setOnCloseRequest(event -> {
            shutdownApplication();
        });
        // Timer UI
        timerLabel = new Label("00:00:00");
        timerInput = new TextField();
        timerInput.setPromptText("Введите время в секундах");
        startTimerButton = new Button("Таймер запуска");
        stopTimerButton = new Button("Таймер остановки");
        stopTimerButton.setDisable(true);

        HBox timerBox = new HBox(10, timerInput, startTimerButton, stopTimerButton);
        VBox timerPanel = new VBox(10, timerLabel, timerBox);

        // Stopwatch UI
        stopwatchLabel = new Label("00:00:00.000");
        startStopwatchButton = new Button("Старт");
        stopStopwatchButton = new Button("Стоп");
        lapStopwatchButton = new Button("Круг");
        resetStopwatchButton = new Button("Сброс");
        stopStopwatchButton.setDisable(true);
        lapStopwatchButton.setDisable(true);
        resetStopwatchButton.setDisable(true);

        HBox stopwatchButtonBox = new HBox(10, startStopwatchButton, stopStopwatchButton, lapStopwatchButton, resetStopwatchButton);
        //VBox stopwatchPanel = new VBox(10, stopwatchLabel, stopwatchButtonBox);
        lapsListView.setPrefHeight(150);
        lapsListView.scrollTo(0);
        VBox stopwatchPanel = new VBox(10, stopwatchLabel, stopwatchButtonBox, new Label("Laps:"), lapsListView);

        // Main layout
        VBox root = new VBox(20, timerPanel, stopwatchPanel);
        root.setPadding(new Insets(20));
        Scene scene = new Scene(root, 500, 400); // Ширина 500, высота 400
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Запрет изменения размера
        primaryStage.show();

        // Timer logic
        startTimerButton.setOnAction(e -> startTimer());
        stopTimerButton.setOnAction(e -> stopTimer());

        // Stopwatch logic
        startStopwatchButton.setOnAction(e -> startStopwatch());
        stopStopwatchButton.setOnAction(e -> stopStopwatch());
        lapStopwatchButton.setOnAction(e -> lapStopwatch());
        resetStopwatchButton.setOnAction(e -> resetStopwatch());

        // Stopwatch animation
        stopwatchAnimation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (stopwatchStartTime != null) {
                    Duration duration = Duration.between(stopwatchStartTime, Instant.now());
                    stopwatchLabel.setText(formatDuration(duration));
                }
            }
        };
    }

    private void startTimer() {
        try {
            remainingSeconds = Integer.parseInt(timerInput.getText());

            if (remainingSeconds <= 0) {
                timerLabel.setText("Недопустимое время");
                return;
            }

            timerLabel.setText(formatDuration(Duration.ofSeconds(remainingSeconds)));
            startTimerButton.setDisable(true);
            stopTimerButton.setDisable(false);

            timerExecutor = Executors.newSingleThreadScheduledExecutor();
            timerExecutor.scheduleAtFixedRate(() -> {
                Platform.runLater(() -> {
                    remainingSeconds--; // Уменьшаем значение переменной
                    if (remainingSeconds >= 0) {
                        timerLabel.setText(formatDuration(Duration.ofSeconds(remainingSeconds)));
                        if (remainingSeconds == 0) {
                            timerLabel.setText("Время вышло!");
                            playSound();
                            stopTimer();
                        }
                    }
                });
            }, 1, 1, TimeUnit.SECONDS);
        } catch (NumberFormatException e) {
            timerLabel.setText("Неверный ввод");
        }
    }

    private void stopTimer() {
        if (timerExecutor != null && !timerExecutor.isShutdown()) {
            timerExecutor.shutdownNow();
        }
        Platform.runLater(() -> {
            startTimerButton.setDisable(false);
            stopTimerButton.setDisable(true);
        });
    }

    private void shutdownApplication() {
        // Останавливаем таймер
        if (timerExecutor != null && !timerExecutor.isShutdown()) {
            timerExecutor.shutdownNow();
        }
    }

    private void startStopwatch() {
        stopwatchStartTime = Instant.now();
        lastLapTime = stopwatchStartTime;
        stopwatchAnimation.start();
        startStopwatchButton.setDisable(true);
        stopStopwatchButton.setDisable(false);
        lapStopwatchButton.setDisable(false);
        resetStopwatchButton.setDisable(true);
    }

    private void stopStopwatch() {
        stopwatchAnimation.stop();
        startStopwatchButton.setDisable(false);
        stopStopwatchButton.setDisable(true);
        lapStopwatchButton.setDisable(true);
        resetStopwatchButton.setDisable(false);
    }

    private void lapStopwatch() {
        Instant now = Instant.now();
        Duration lapDuration = Duration.between(lastLapTime, now);
        Duration totalDuration = Duration.between(stopwatchStartTime, now);

        String lapEntry = String.format("За %d-й круг: %s. Общее время: %s",
                lapsListView.getItems().size() + 1,
                formatDuration(lapDuration),
                formatDuration(totalDuration));

        lapsListView.getItems().add(0, lapEntry); // Добавляем в начало списка
        lastLapTime = now;
    }

    private void resetStopwatch() {
        stopwatchAnimation.stop();
        stopwatchLabel.setText("00:00:00.000");
        laps.clear();
        startStopwatchButton.setDisable(false);
        resetStopwatchButton.setDisable(true);
        lapsListView.getItems().clear(); // Добавляем в начало списка
    }

    private String formatDuration(Duration duration) {
        int totalSeconds = duration.toSecondsPart();
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private void playSound() {
        try {
            File soundFile = new File("alert_mini.wav"); // Убедитесь, что файл существует c названием alert.wav
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Получить контроль громкости
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // Установить громкость (в децибелах)
            float min = gainControl.getMinimum(); // Минимальное значение (обычно -80.0)
            float max = gainControl.getMaximum(); // Максимальное значение (обычно 6.0)

            gainControl.setValue(-20.0f); // Установить громкость -20.0f (в децибелах)

            clip.start();
            clip.loop(1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}