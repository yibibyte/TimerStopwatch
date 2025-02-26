module ru.japp.stopwatchtimer.stopwatchtimer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens ru.japp.stopwatchtimer.stopwatchtimer to javafx.fxml;
    exports ru.japp.stopwatchtimer.stopwatchtimer;
}