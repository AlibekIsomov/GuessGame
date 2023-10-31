package com.example.demo1;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;
import java.util.Random;

public class GuessApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Guess Game");
        dialog.setHeaderText("To play the game, enter your file location");
        dialog.setContentText("File Location:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String pathname = result.get();

            Random random = new Random();
            int number = random.nextInt(10);

            TextInputDialog guessDialog = new TextInputDialog();
            guessDialog.setTitle("File Guess Game");
            guessDialog.setHeaderText("Silly game! Guess the number between 1 and 10");
            guessDialog.setContentText("Your Guess:");

            Optional<String> guessResult = guessDialog.showAndWait();
            if (guessResult.isPresent()) {
                int guess = Integer.parseInt(guessResult.get());

                if (guess == number) {
                    showAlert("You Won!");
                } else {
                    showAlert("You Lost!");

                    File file = new File(pathname);
                    boolean isDeleted = file.delete();

                    if (isDeleted) {
                        showAlert("File deleted successfully.");
                    } else {
                        showAlert("Unable to delete the file.");
                    }
                }
            }
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("File Guess Game");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}