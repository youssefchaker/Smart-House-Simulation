package ui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class JavaFXApplication extends Application {
    private static TextArea textArea;

    @Override
    public void start(Stage primaryStage) {
        textArea = new TextArea();
        Scene scene = new Scene(textArea, 400, 300);

        primaryStage.setTitle("Smart House");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void appendMessage(String message) {
        Platform.runLater(() -> textArea.appendText(message + "\n"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
