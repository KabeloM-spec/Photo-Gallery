package com.example.gallery;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.setId("grid");

        //Inserting Images
        for (int i = 1; i <= 9; i++) {
            String src = "/com/example/gallery/img" + i + ".jpg";
            Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(src)));
            ImageView imgV = new ImageView();
            imgV.setImage(img);
            imgV.setFitWidth(150);
            imgV.setFitHeight(210);

            //Hovering on Images
            imgV.setOnMouseEntered(e -> {
                imgV.setStyle("-fx-opacity:50%");
            });
            imgV.setOnMouseExited(e -> {
                imgV.setStyle("-fx-opacity:100%");
            });

            int row = (i - 1) / 3;
            int col = (i - 1) % 3;

            int fullView = i;
            imgV.setOnMouseClicked(event -> {
                fullImg(fullView, primaryStage);
            });

            gridPane.add(imgV, col, row);
        }

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(gridPane);

        Scene scene = new Scene(stackPane, 520, 800);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setTitle("GALLERY");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void fullImg(int index, Stage stage)
    {
        ImageView view = new ImageView();

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        HBox box = new HBox();
        box.setStyle("-fx-spacing: 10px; -fx-alignment: center;");
        box.setAlignment(Pos.CENTER);


        String src = "/com/example/gallery/img" + index + ".jpg";
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(src)));
        view.setImage(img);
        view.setFitWidth(520);
        view.setFitHeight(700);


        Button btnClose = new Button("Close");
        btnClose.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-cursor: hand;");
        btnClose.setOnAction(e -> {
            try {
                start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        Button btnNext = new Button("Next");
        btnNext.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-cursor: hand;");
        btnNext.setOnAction(e -> {
            fullImg(index + 1, stage);
        });

        Button btnPrev = new Button("Previous");
        btnPrev.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-cursor: hand;");
        btnPrev.setOnAction(e -> {
            fullImg(index - 1, stage);
        });

        box.getChildren().addAll(btnPrev,btnClose,btnNext);
        root.getChildren().addAll(view,box);

        Scene scene = new Scene(root, 520, 800);
        stage.setScene(scene);
        stage.setTitle("Image " + index);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
