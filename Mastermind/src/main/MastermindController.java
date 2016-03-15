import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MastermindController implements Initializable {

    private Image blue = new Image(new File("Mastermind/src/main/pictures/blue.png").toURI().toString());
    private Image brown = new Image(new File("Mastermind/src/main/pictures/brown.png").toURI().toString());
    private Image green = new Image(new File("Mastermind/src/main/pictures/green.png").toURI().toString());
    private Image orange = new Image(new File("Mastermind/src/main/pictures/orange.png").toURI().toString());
    private Image purple = new Image(new File("Mastermind/src/main/pictures/purple.png").toURI().toString());
    private Image red = new Image(new File("Mastermind/src/main/pictures/red.png").toURI().toString());
    private Image white = new Image(new File("Mastermind/src/main/pictures/white.png").toURI().toString());
    private Image yellow = new Image(new File("Mastermind/src/main/pictures/yellow.png").toURI().toString());
    private Image currentColor=null;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private ComboBox<String> passwordColorSelector;

    @FXML
    private Button passwordSaveButton;

    @FXML
    private Button passwordRandomButton;

    @FXML
    private Circle passwordMove1Slot;

    @FXML
    private Circle passwordMove1Image;

    @FXML
    private Circle passwordMove2Slot;

    @FXML
    private Circle passwordMove2Image;

    @FXML
    private Circle passwordMove3Slot;

    @FXML
    private Circle passwordMove3Image;

    @FXML
    private Circle passwordMove4Slot;

    @FXML
    private Circle passwordMove4Image;

    @FXML
    private HBox computerMoves;

    @FXML
    private Circle computerMove1Slot;

    @FXML
    private Circle computerMove1Image;

    @FXML
    private Circle computerMove2Slot;

    @FXML
    private Circle computerMove2Image;

    @FXML
    private Circle computerMove3Slot;

    @FXML
    private Circle computerMove3Image;

    @FXML
    private Circle computerMove4Slot;

    @FXML
    private Circle computerMove4Image;

    @FXML
    private GridPane computerMovePegs;

    @FXML
    private Rectangle computerMovePeg1;

    @FXML
    private Rectangle computerMovePeg2;

    @FXML
    private Rectangle computerMovePeg3;

    @FXML
    private Rectangle computerMovePeg4;

    @FXML
    private HBox oldMoveBox;

    @FXML
    private Rectangle oldMoveBackground;

    @FXML
    private Circle oldMove1Slot;

    @FXML
    private Circle oldMove2Slot;

    @FXML
    private Circle oldMove3Slot;

    @FXML
    private Circle oldMove4Slot;

    @FXML
    private Rectangle oldMovePeg1;

    @FXML
    private Rectangle oldMovePeg2;

    @FXML
    private Rectangle oldMovePeg3;

    @FXML
    private Rectangle oldMovePeg4;

    @FXML
    void setSlotColor(MouseEvent event) {
        if(currentColor!=null) {
            if (event.getSource().equals(passwordMove1Image) || event.getSource().equals(passwordMove1Slot)) {
                System.out.println("Password 1");
                passwordMove1Image.setEffect(new ImageInput(currentColor, -10, -10));
                passwordMove1Image.setVisible(true);
            } else if (event.getSource().equals(passwordMove2Image) || event.getSource().equals(passwordMove2Slot)) {
                System.out.println("Password 2");
                passwordMove2Image.setEffect(new ImageInput(currentColor, -10, -10));
                passwordMove2Image.setVisible(true);
            } else if (event.getSource().equals(passwordMove3Image) || event.getSource().equals(passwordMove3Slot)) {
                System.out.println("Password 3");
                passwordMove3Image.setEffect(new ImageInput(currentColor, -10, -10));
                passwordMove3Image.setVisible(true);
            } else if (event.getSource().equals(passwordMove4Image) || event.getSource().equals(passwordMove4Slot)) {
                System.out.println("Password 4");
                passwordMove4Image.setEffect(new ImageInput(currentColor, -10, -10));
                passwordMove4Image.setVisible(true);
            }
        }
    }

    @FXML
    void randomPassword(ActionEvent event) {
        Random rand = new Random();
        randomColor(rand.nextInt(9));
        passwordMove1Image.setEffect(new ImageInput(currentColor, -10, -10));
        passwordMove1Image.setVisible(true);
        randomColor(rand.nextInt(8));
        passwordMove2Image.setEffect(new ImageInput(currentColor, -10, -10));
        passwordMove2Image.setVisible(true);
        randomColor(rand.nextInt(8));
        passwordMove3Image.setEffect(new ImageInput(currentColor, -10, -10));
        passwordMove3Image.setVisible(true);
        randomColor(rand.nextInt(8));
        passwordMove4Image.setEffect(new ImageInput(currentColor, -10, -10));
        passwordMove4Image.setVisible(true);

    }

    public void randomColor(int rand) {
        switch(rand) {
            case 1:
                currentColor = blue;
                break;
            case 2:
                currentColor = brown;
                break;
            case 3:
                currentColor = green;
                break;
            case 4:
                currentColor = orange;
                break;
            case 5:
                currentColor = purple;
                break;
            case 6:
                currentColor = red;
                break;
            case 7:
                currentColor = white;
                break;
            case 8:
                currentColor = yellow;
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        passwordColorSelector.getItems().setAll(blue,red);
        passwordColorSelector.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                switch (newValue) {
                    case "Blue": currentColor = blue; break;
                    case "Brown": currentColor = brown; break;
                    case "Green": currentColor = green; break;
                    case "Orange": currentColor = orange; break;
                    case "Purple": currentColor = purple; break;
                    case "Red": currentColor = red; break;
                    case "White": currentColor = white; break;
                    case "Yellow": currentColor = yellow; break;
                }
            }
        });

    }

//    @FXML
//    void 1f1c32(ActionEvent event) {
//
//    }
//
//    @FXML
//    void b21212(ActionEvent event) {
//
//    }
//
//    @FXML
//    void 00000082(ActionEvent event) {
//
//    }
//
//    @FXML
//    void 616161f5(ActionEvent event) {
//
//    }
//
//    @FXML
//    void 636363f5(ActionEvent event) {
//
//    }
//
//    @FXML
//    void 636363f5(ActionEvent event) {
//
//    }
//
//    @FXML
//    void 636363f5(ActionEvent event) {
//
//    }
//
//    @FXML
//    void ff7777(ActionEvent event) {
//
//    }
//
//    @FXML
//    void ff7777(ActionEvent event) {
//
//    }
//
//    @FXML
//    void ff7777(ActionEvent event) {
//
//    }
//
//    @FXML
//    void ff7777(ActionEvent event) {
//
//    }
//
//    @FXML
//    void 616161f5(ActionEvent event) {
//
//    }
//
//    @FXML
//    void 616161f5(ActionEvent event) {
//
//    }
//
//    @FXML
//    void 616161f5(ActionEvent event) {
//
//    }
//
//    @FXML
//    void 616161f5(ActionEvent event) {
//
//    }
//
//    @FXML
//    void 0000009f(ActionEvent event) {
//
//    }

}