module SnakeAndLadder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;

    opens game to javafx.fxml;
    exports game;
}

