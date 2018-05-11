package sample;

/**
 * A simple JavaFX GUI program that displays a line
 * number and warning if it's longer than 80 characters.
 * Also displays the absolute path name of the file.
 * @author: Semaj Primm
 * @date: 10/05/18
 * @version: 1.0
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {

    // Declares and initializes scene objects
    private VBox root = new VBox();
    private HBox mainContent = new HBox();
    private Label title = new Label("Character Counter");
    private Label label = new Label("File Path:");
    private TextField filePathField = new TextField();
    private Button openButton = new Button("Open");
    private Label result = new Label();
    private final static int MAX_LINE_LENGTH = 80;

    @Override
    public void start(Stage primaryStage) {
        // Sets width of file path field to 300
        filePathField.setPrefWidth(300);
        // Adds nodes to mainContent container
        mainContent.getChildren().addAll(label, filePathField, openButton);
        // Sets mainContent container alignment to center
        mainContent.setAlignment(Pos.CENTER);
        // Sets spacing of mainContent children
        mainContent.setSpacing(20);
        // Adds nodes to root container
        root.getChildren().addAll(title, mainContent, result);
        // Sets root alignment to center
        root.setAlignment(Pos.CENTER);
        // Sets spacing of root layout
        root.setSpacing(20);
        // Sets event handler for openButton
        openButton.setOnAction(e-> openFileExplorer());
        // Sets scene container to root and window dimensions
        Scene scene = new Scene(root, 600, 400);
        // Sets title of program window
        primaryStage.setTitle("Character Limit Counter");
        // Sets scene of stage
        primaryStage.setScene(scene);
        // Shows stage
        primaryStage.show();
    }

    private void openFileExplorer(){
        // Saves line number
        int lineNumber = 1;
        // Clears result label if it's not empty
        if(!result.getText().isEmpty())
            result.setText("");
        // Create file chooser object
        FileChooser fileChooser = new FileChooser();
        // Sets title of file chooser window
        fileChooser.setTitle("Open A File");
        // Creates and initializes file object to new stage
        File f = fileChooser.showOpenDialog(new Stage());
        // Holds pathname of file
        String filePath = "";
        try{
            // Gets filepath from file object
            filePath = f.getAbsolutePath();
            // Sets textfield to absolute file pathname
            filePathField.setText(filePath);

        } catch(NullPointerException e){
            // Handles null pointer exception
            System.out.println("NullPointerException");
        }

        // Creates and declare scanner object
        Scanner scan = null;
        try {
            scan = new Scanner(f);
            // Scans each line
            while(scan.hasNext()){
                // Save scanned line in variable line
                String line = scan.nextLine();
                // Displays line number that are overflowing
                if(line.length() > MAX_LINE_LENGTH)
                    result.setText(result.getText() + "Line " + lineNumber + " is too long\n");
                // Increments line number by 1
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            // Handles exceptions if file not found
            System.out.println("File not found");
        } catch (NullPointerException e){
            // Handles null pointer exception
            System.out.println("NullPointerException");
            // Handles index out of bounds exception
        } catch(IndexOutOfBoundsException e){
            System.out.println("IndexOutOfBoundsException");
        } finally {
            // Displays result if no lines are overflowing
            if(result.getText().isEmpty())
                result.setText("No overflowing lines");
            // Close scanner if not null
            if (scan != null) {
                scan.close();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
