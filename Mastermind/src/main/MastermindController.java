package main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class MastermindController implements Initializable {

    private Pair<Color, Image> blue = new Pair<>(Color.BLUE,new Image(new File("Mastermind/src/main/pictures/blue.png").toURI().toString()));
    private Pair<Color, Image> brown = new Pair<>(Color.SADDLEBROWN,new Image(new File("Mastermind/src/main/pictures/brown.png").toURI().toString()));
    private Pair<Color, Image> green = new Pair<>(Color.GREEN,new Image(new File("Mastermind/src/main/pictures/green.png").toURI().toString()));
    private Pair<Color, Image> orange = new Pair<>(Color.ORANGE,new Image(new File("Mastermind/src/main/pictures/orange.png").toURI().toString()));
    private Pair<Color, Image> purple = new Pair<>(Color.PURPLE,new Image(new File("Mastermind/src/main/pictures/purple.png").toURI().toString()));
    private Pair<Color, Image> red = new Pair<>(Color.RED,new Image(new File("Mastermind/src/main/pictures/red.png").toURI().toString()));
    private Pair<Color, Image> white = new Pair<>(Color.WHITE,new Image(new File("Mastermind/src/main/pictures/white.png").toURI().toString()));
    private Pair<Color, Image> yellow = new Pair<>(Color.YELLOW,new Image(new File("Mastermind/src/main/pictures/yellow.png").toURI().toString()));
    private Pair<Color, Image> empty = new Pair<>(Color.valueOf("#616161f5"), null);

    //TEST CODE
    private Pair<Color, Image> currentColorName = null;
    private Pair<Color, Image> passwordMove1Color;
    private Pair<Color, Image> passwordMove2Color;
    private Pair<Color, Image> passwordMove3Color;
    private Pair<Color, Image> passwordMove4Color;
    private Pair<Color, Image> computerMove1Color;
    private Pair<Color, Image> computerMove2Color;
    private Pair<Color, Image> computerMove3Color;
    private Pair<Color, Image> computerMove4Color;
    private Pair<Color, Image> computerPeg1Color;
    private Pair<Color, Image> computerPeg2Color;
    private Pair<Color, Image> computerPeg3Color;
    private Pair<Color, Image> computerPeg4Color;
    //END TEST CODE


    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button storeButton;

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
    private ScrollPane oldMoveScrollPane;

    @FXML
    private Rectangle oldMoveBackground;

    @FXML
    private VBox oldMoveList;


    //changes password slot color when clicked to current color (combo box)
    @FXML
    void setSlotColor(MouseEvent event) {
        if (currentColorName != null) {
            if (event.getSource().equals(passwordMove1Image) || event.getSource().equals(passwordMove1Slot)) {
                changeSlotColor(passwordMove1Image,currentColorName);
                passwordMove1Color = currentColorName;
            } else if (event.getSource().equals(passwordMove2Image) || event.getSource().equals(passwordMove2Slot)) {
                changeSlotColor(passwordMove2Image,currentColorName);
                passwordMove2Color = currentColorName;
            } else if (event.getSource().equals(passwordMove3Image) || event.getSource().equals(passwordMove3Slot)) {
                changeSlotColor(passwordMove3Image,currentColorName);
                passwordMove3Color = currentColorName;
            } else if (event.getSource().equals(passwordMove4Image) || event.getSource().equals(passwordMove4Slot)) {
                changeSlotColor(passwordMove4Image,currentColorName);
                passwordMove4Color = currentColorName;
            }
        }
    }

    //randomizes the password
    @FXML
    void randomPassword(ActionEvent event) {
        Random rand = new Random();
        passwordMove1Color = randomColor(rand.nextInt(8)+1);
        changeSlotColor(passwordMove1Image, passwordMove1Color);
        passwordMove2Color = randomColor(rand.nextInt(8)+1);
        changeSlotColor(passwordMove2Image, passwordMove2Color);
        passwordMove3Color = randomColor(rand.nextInt(8)+1);
        changeSlotColor(passwordMove3Image, passwordMove3Color);
        passwordMove4Color = randomColor(rand.nextInt(8)+1);
        changeSlotColor(passwordMove4Image, passwordMove4Color);
    }

    //sets the image of a password/computer move slot
    public void changeSlotColor(Circle slot, Pair<Color, Image> color) {
        slot.setEffect(new ImageInput(color.getValue(), -10, -10));
        slot.setVisible(true);
    }

    //returns the name of a random color
    public Pair<Color, Image> randomColor(int rand) {
        switch(rand) {
            case 1: return blue;
            case 2: return brown;
            case 3: return green;
            case 4: return orange;
            case 5: return purple;
            case 6: return red;
            case 7: return white;
            case 8: return yellow;
        }
        return null;
    }

    //TODO
    //This is going to save the password and have the computer attempt to solve it
    @FXML
    void savePassword(ActionEvent event) {
        //TODO

        //TEST CODE
        //All code below this is just for testing

        //This sets the computer's guess to the current password
      /*
        computerMove1Color = passwordMove1Color;
        computerMove2Color = passwordMove2Color;
        computerMove3Color = passwordMove3Color;
        computerMove4Color = passwordMove4Color;
        changeSlotColor(computerMove1Image, computerMove1Color);
        changeSlotColor(computerMove2Image, computerMove2Color);
        changeSlotColor(computerMove3Image, computerMove3Color);
        changeSlotColor(computerMove4Image, computerMove4Color);
      */

        //Computer makes random moves
//      /*
        Random rand = new Random();
        computerMove1Color = randomColor(rand.nextInt(8)+1);
        changeSlotColor(computerMove1Image, computerMove1Color);
        computerMove2Color = randomColor(rand.nextInt(8)+1);
        changeSlotColor(computerMove2Image, computerMove2Color);
        computerMove3Color = randomColor(rand.nextInt(8)+1);
        changeSlotColor(computerMove3Image, computerMove3Color);
        computerMove4Color = randomColor(rand.nextInt(8)+1);
        changeSlotColor(computerMove4Image, computerMove4Color);
//      */

        //runs pegCheck
        pegCheck();

    }

    public void pegCheck() {

        List<Pair<Color, Image>> passwordColors = new ArrayList<>(4);
        passwordColors.add(0, passwordMove1Color);
        passwordColors.add(1, passwordMove2Color);
        passwordColors.add(2, passwordMove3Color);
        passwordColors.add(3, passwordMove4Color);

        List<Pair<Color, Image>> computerColors = new ArrayList<>(4);
        computerColors.add(0, computerMove1Color);
        computerColors.add(1, computerMove2Color);
        computerColors.add(2, computerMove3Color);
        computerColors.add(3, computerMove4Color);
        List<Pair<Color, Image>> computerPegColors = new ArrayList<>(4);
        for(int i=0;i<4;i++) computerPegColors.add(i, empty);

        int whitePegs = 0, redPegs = 0;
        boolean[] flag = new boolean[Settings.NUM_SPACES];
        for (int i=0; i < Settings.NUM_SPACES; i++) {
            flag[i] = false;
        }
        for (int i=0; i < Settings.NUM_SPACES; i++) {
            if (computerColors.get(i) == passwordColors.get(i) && !flag[i]) {
                redPegs++;
            } else {
                for (int j=0; j < Settings.NUM_SPACES; j++) {
                    if (j!=i && computerColors.get(i) == passwordColors.get(j) && !flag[j]) {
                        whitePegs++;
                        flag[j] = true;
                        break;
                    }
                }
            }
        }
        for (int k = 0; k < redPegs; k++) {
            computerPegColors.set(k, red);
        }
        int l;
        for(l = redPegs; l < whitePegs + redPegs; l++) {
            computerPegColors.set(l, white);
        }
        computerPeg1Color = computerPegColors.get(0);
        computerPeg2Color = computerPegColors.get(1);
        computerPeg3Color = computerPegColors.get(2);
        computerPeg4Color = computerPegColors.get(3);
        if(computerPeg1Color != null) { computerMovePeg1.setFill(computerPeg1Color.getKey()); }
        if(computerPeg2Color != null) { computerMovePeg2.setFill(computerPeg2Color.getKey()); }
        if(computerPeg3Color != null) { computerMovePeg3.setFill(computerPeg3Color.getKey()); }
        if(computerPeg4Color != null) { computerMovePeg4.setFill(computerPeg4Color.getKey()); }

    }

    //This method pushes the computer's current guess into the "previous moves" scroll pane
    @FXML
    void computerNextMove(ActionEvent event) {

        HBox oldMove = new HBox();
        oldMove.setPrefSize(485,100);

//        Circle oldMove1 = new Circle(30, colorNameToColor(computerMove1Color));
        Circle oldMove1 = new Circle(30, computerMove1Color.getKey());
        oldMove1.setStroke(Color.BLACK);
        Circle oldMove2 = new Circle(30, computerMove2Color.getKey());
        oldMove2.setStroke(Color.BLACK);
        Circle oldMove3 = new Circle(30, computerMove3Color.getKey());
        oldMove3.setStroke(Color.BLACK);
        Circle oldMove4 = new Circle(30, computerMove4Color.getKey());
        oldMove4.setStroke(Color.BLACK);

        Line separator = new Line(100,5,100,95);
        GridPane pegs = new GridPane();

        Rectangle oldMovePeg1 = new Rectangle(30,30, Color.rgb(0,0,0,0));
        if(computerPeg1Color != empty) { oldMovePeg1.setFill(computerPeg1Color.getKey()); }
        oldMovePeg1.setStroke(Color.BLACK);
        Rectangle oldMovePeg2 = new Rectangle(30,30, Color.rgb(0,0,0,0));
        if(computerPeg2Color != empty) { oldMovePeg2.setFill(computerPeg2Color.getKey()); }
        oldMovePeg2.setStroke(Color.BLACK);
        Rectangle oldMovePeg3 = new Rectangle(30,30, Color.rgb(0,0,0,0));
        if(computerPeg3Color != empty) { oldMovePeg3.setFill(computerPeg3Color.getKey()); }
        oldMovePeg3.setStroke(Color.BLACK);
        Rectangle oldMovePeg4 = new Rectangle(30,30, Color.rgb(0,0,0,0));
        if(computerPeg4Color != empty) { oldMovePeg4.setFill(computerPeg4Color.getKey()); }
        oldMovePeg4.setStroke(Color.BLACK);

        GridPane.setConstraints(oldMovePeg1, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS, new Insets(0,0,0,10));
        GridPane.setConstraints(oldMovePeg2, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS, new Insets(0,0,0,10));
        GridPane.setConstraints(oldMovePeg3, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS, new Insets(0,0,0,10));
        GridPane.setConstraints(oldMovePeg4, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS, new Insets(0,0,0,10));

        pegs.getChildren().addAll(oldMovePeg1, oldMovePeg2, oldMovePeg3, oldMovePeg4);
        oldMove.getChildren().addAll(oldMove1,oldMove2,oldMove3,oldMove4, separator, pegs);

        oldMove.setMargin(oldMove1, new Insets(20,15,10,20));
        oldMove.setMargin(oldMove2, new Insets(20,15,0,15));
        oldMove.setMargin(oldMove3, new Insets(20,15,0,15));
        oldMove.setMargin(oldMove4, new Insets(20,10,0,15));
        oldMove.setMargin(separator, new Insets(5,10,0,10));

        oldMoveList.getChildren().add(0, oldMove);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        passwordColorSelector.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                switch (newValue) {
                    case "Blue":    currentColorName = blue;    break;
                    case "Brown":   currentColorName = brown;   break;
                    case "Green":   currentColorName = green;   break;
                    case "Orange":  currentColorName = orange;  break;
                    case "Purple":  currentColorName = purple;  break;
                    case "Red":     currentColorName = red;     break;
                    case "White":   currentColorName = white;   break;
                    case "Yellow":  currentColorName = yellow;  break;
                }
            }
        });

    }

}