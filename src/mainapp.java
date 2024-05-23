import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class mainapp extends Application {
    // Original coordinates of the label before each dragging
    private double originX;
    private double originY;

    @Override
    public void start(Stage primaryStage) {
        try {
            Pane root = new Pane();
            Scene scene = new Scene(root, 400, 400);

            Label text = new Label("Drag me!");
            text.setPrefWidth(100);
            text.setPrefHeight(50);
            text.setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));

            root.getChildren().add(text);

            // Move the text by dragging
            text.setOnMousePressed(e -> {
                originX = e.getX();
                originY = e.getY();
            });

            text.setOnMouseDragged(e -> {
                double dx = e.getX() - originX;
                double dy = e.getY() - originY;
                move(text, dx, dy);
            });

            primaryStage.setScene(scene);
            primaryStage.setTitle("Move demo");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void move(Label content, double dx, double dy) {
        double x = content.getBoundsInParent().getMinX();
        double y = content.getBoundsInParent().getMinY();
        content.setText(String.format("x: %.2f y: %.2f", x + dx, y + dy));
        content.relocate(x + dx, y + dy);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
