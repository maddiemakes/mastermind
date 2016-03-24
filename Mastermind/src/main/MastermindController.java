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

    public static Pair<Color, Image> empty = new Pair<>(Color.valueOf("#616161f5"), null);
    public static Pair<Color, Image> blue = new Pair<>(Color.BLUE,new Image(new File("Mastermind/src/main/pictures/blue.png").toURI().toString()));
    public static Pair<Color, Image> brown = new Pair<>(Color.SADDLEBROWN,new Image(new File("Mastermind/src/main/pictures/brown.png").toURI().toString()));
    public static Pair<Color, Image> green = new Pair<>(Color.GREEN,new Image(new File("Mastermind/src/main/pictures/green.png").toURI().toString()));
    public static Pair<Color, Image> orange = new Pair<>(Color.ORANGE,new Image(new File("Mastermind/src/main/pictures/orange.png").toURI().toString()));
    public static Pair<Color, Image> purple = new Pair<>(Color.PURPLE,new Image(new File("Mastermind/src/main/pictures/purple.png").toURI().toString()));
    public static Pair<Color, Image> red = new Pair<>(Color.RED,new Image(new File("Mastermind/src/main/pictures/red.png").toURI().toString()));
    public static Pair<Color, Image> white = new Pair<>(Color.WHITE,new Image(new File("Mastermind/src/main/pictures/white.png").toURI().toString()));
    public static Pair<Color, Image> yellow = new Pair<>(Color.YELLOW,new Image(new File("Mastermind/src/main/pictures/yellow.png").toURI().toString()));

    public static List<Pair<Color, Image>> colorPairs = new ArrayList<Pair<Color, Image>>(9) {{
        add(empty);
        add(blue);
        add(brown);
        add(green);
        add(orange);
        add(purple);
        add(red);
        add(white);
        add(yellow);
    }};

    public int currentColorName = 0;
    public static int[] password = new int[Settings.NUM_SPACES];
    public static int[] guess = {1,1,2,2};
    public static int[] computerPegs = {0,0,0,0};
    /*
    public Pair<Color, Image> currentColorName = null;
    public Pair<Color, Image> passwordMove1Color;
    public Pair<Color, Image> passwordMove2Color;
    public Pair<Color, Image> passwordMove3Color;
    public Pair<Color, Image> passwordMove4Color;
    public Pair<Color, Image> computerMove1Color;
    public Pair<Color, Image> computerMove2Color;
    public Pair<Color, Image> computerMove3Color;
    public Pair<Color, Image> computerMove4Color;
    public Pair<Color, Image> computerPeg1Color;
    public Pair<Color, Image> computerPeg2Color;
    public Pair<Color, Image> computerPeg3Color;
    public Pair<Color, Image> computerPeg4Color;
    */


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
//        if (currentColorName != null) {
        if (currentColorName != 0) {
            if (event.getSource().equals(passwordMove1Image) || event.getSource().equals(passwordMove1Slot)) {
                changeSlotColor(passwordMove1Image,currentColorName);
//                passwordMove1Color = currentColorName;
                password[0] = currentColorName;
            } else if (event.getSource().equals(passwordMove2Image) || event.getSource().equals(passwordMove2Slot)) {
                changeSlotColor(passwordMove2Image,currentColorName);
//                passwordMove2Color = currentColorName;
                password[1] = currentColorName;
            } else if (event.getSource().equals(passwordMove3Image) || event.getSource().equals(passwordMove3Slot)) {
                changeSlotColor(passwordMove3Image,currentColorName);
//                passwordMove3Color = currentColorName;
                password[2] = currentColorName;
            } else if (event.getSource().equals(passwordMove4Image) || event.getSource().equals(passwordMove4Slot)) {
                changeSlotColor(passwordMove4Image,currentColorName);
//                passwordMove4Color = currentColorName;
                password[3] = currentColorName;
            }
        }
    }

    //randomizes the password
    @FXML
    void randomPassword(ActionEvent event) {
        Random rand = new Random();
        /*
        passwordMove1Color = randomColor(rand.nextInt(8)+1);
        changeSlotColor(passwordMove1Image, passwordMove1Color);
        passwordMove2Color = randomColor(rand.nextInt(8)+1);
        changeSlotColor(passwordMove2Image, passwordMove2Color);
        passwordMove3Color = randomColor(rand.nextInt(8)+1);
        changeSlotColor(passwordMove3Image, passwordMove3Color);
        passwordMove4Color = randomColor(rand.nextInt(8)+1);
        changeSlotColor(passwordMove4Image, passwordMove4Color);
        */
        for (int i = 0; i < password.length; i++) {
            password[i] = rand.nextInt(8) + 1;
        }
        setPassword();
    }

    public void setPassword() {
        changeSlotColor(passwordMove1Image, password[0]);
        changeSlotColor(passwordMove2Image, password[1]);
        changeSlotColor(passwordMove3Image, password[2]);
        changeSlotColor(passwordMove4Image, password[3]);
    }

    public void setGuess() {
        changeSlotColor(computerMove1Image, guess[0]);
        changeSlotColor(computerMove2Image, guess[1]);
        changeSlotColor(computerMove3Image, guess[2]);
        changeSlotColor(computerMove4Image, guess[3]);
    }

    //sets the image of a password/computer move slot
    public void changeSlotColor(Circle slot, int color) {
        slot.setEffect(new ImageInput(colorPairs.get(color).getValue(), -10, -10));
        slot.setVisible(true);
    }

    //returns the name of a random color
    public Pair<Color, Image> randomColor(int rand) {
        /*
        switch(rand) {
            case 1: return colorPairs.get(1);
            case 2: return brown;
            case 3: return green;
            case 4: return orange;
            case 5: return purple;
            case 6: return red;
            case 7: return white;
            case 8: return yellow;
        }
        return null;
        */
        return colorPairs.get(rand);
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
      /*
        Random rand = new Random();
        computerMove1Color = randomColor(rand.nextInt(8)+1);
        changeSlotColor(computerMove1Image, computerMove1Color);
        computerMove2Color = randomColor(rand.nextInt(8)+1);
        changeSlotColor(computerMove2Image, computerMove2Color);
        computerMove3Color = randomColor(rand.nextInt(8)+1);
        changeSlotColor(computerMove3Image, computerMove3Color);
        computerMove4Color = randomColor(rand.nextInt(8)+1);
        changeSlotColor(computerMove4Image, computerMove4Color);
      */
        /*
        computerMove1Color = colorPairs.get(1);
        computerMove2Color = colorPairs.get(1);
        computerMove3Color = colorPairs.get(2);
        computerMove4Color = colorPairs.get(2);
        changeSlotColor(computerMove1Image, computerMove1Color);
        changeSlotColor(computerMove2Image, computerMove2Color);
        changeSlotColor(computerMove3Image, computerMove3Color);
        changeSlotColor(computerMove4Image, computerMove4Color);
        */
        setGuess();

        Mastermind.AI();

        //runs pegCheck
//        pegCheck();

    }

    public void paintPegs(int redPegs, int whitePegs) {
        for (int k = 0; k < redPegs; k++) {
            computerPegs[k] = 6;
        }
        int l;
        for(l = redPegs; l < whitePegs + redPegs; l++) {
            computerPegs[l] = 7;
        }
        for (;l < 4; l++) {
            computerPegs[l] = 0;
        }
        computerMovePeg1.setFill(colorPairs.get(computerPegs[0]).getKey());
        computerMovePeg2.setFill(colorPairs.get(computerPegs[1]).getKey());
        computerMovePeg3.setFill(colorPairs.get(computerPegs[2]).getKey());
        computerMovePeg4.setFill(colorPairs.get(computerPegs[3]).getKey());
//        for (int i = 0; i < computerPegs.length; i++) {
//            if (computerPegs[i] != 0) {
//                computerMovePeg1.setFill(colorPairs.get(computerPegs[i]).getKey());
//            }
//        }
    }

    public void pegCheck() {

        int whitePegs = 0, redPegs = 0;
        boolean[] flag = new boolean[Settings.NUM_SPACES];
        for (int i=0; i < Settings.NUM_SPACES; i++) {
            flag[i] = false;
        }
        for (int i=0; i < Settings.NUM_SPACES; i++) {
//            if (computerColors.get(i) == passwordColors.get(i) && !flag[i]) {
            if (guess[i] == password[i] && !flag[i]) {
                redPegs++;
            } else {
                for (int j=0; j < Settings.NUM_SPACES; j++) {
//                    if (j!=i && computerColors.get(i) == passwordColors.get(j) && !flag[j]) {
                    if (j!=i && guess[i] == password[j] && !flag[j]) {
                        whitePegs++;
                        flag[j] = true;
                        break;
                    }
                }
            }
        }
        for (int k = 0; k < redPegs; k++) {
//            computerPegColors.set(k, colorPairs.get(6));
            computerPegs[k] = 6;
        }
        int l;
        for(l = redPegs; l < whitePegs + redPegs; l++) {
//            computerPegColors.set(l, colorPairs.get(7));
            computerPegs[l] = 7;
        }
//        computerPeg1Color = computerPegColors.get(0);
//        computerPeg2Color = computerPegColors.get(1);
//        computerPeg3Color = computerPegColors.get(2);
//        computerPeg4Color = computerPegColors.get(3);
        for (int i = 0; i < computerPegs.length; i++) {
            if (computerPegs[i] != 0) {
                computerMovePeg1.setFill(colorPairs.get(computerPegs[i]).getKey());
            }
        }
//        if(computerPeg1Color != 0) { computerMovePeg1.setFill(computerPeg1Color.getKey()); }
//        if(computerPeg2Color != 0) { computerMovePeg2.setFill(computerPeg2Color.getKey()); }
//        if(computerPeg3Color != 0) { computerMovePeg3.setFill(computerPeg3Color.getKey()); }
//        if(computerPeg4Color != 0) { computerMovePeg4.setFill(computerPeg4Color.getKey()); }

    }

    //This method pushes the computer's current guess into the "previous moves" scroll pane
//    @FXML
    public void computerNextMove() {

        HBox oldMove = new HBox();
        oldMove.setPrefSize(485,100);

//        Circle oldMove1 = new Circle(30, colorNameToColor(computerMove1Color));
//        Circle oldMove1 = new Circle(30, computerMove1Color.getKey());
        Circle oldMove1 = new Circle(30, colorPairs.get(guess[0]).getKey());
        oldMove1.setStroke(Color.BLACK);
//        Circle oldMove2 = new Circle(30, computerMove2Color.getKey());
        Circle oldMove2 = new Circle(30, colorPairs.get(guess[1]).getKey());
        oldMove2.setStroke(Color.BLACK);
//        Circle oldMove3 = new Circle(30, computerMove3Color.getKey());
        Circle oldMove3 = new Circle(30, colorPairs.get(guess[2]).getKey());
        oldMove3.setStroke(Color.BLACK);
//        Circle oldMove4 = new Circle(30, computerMove4Color.getKey());
        Circle oldMove4 = new Circle(30, colorPairs.get(guess[3]).getKey());
        oldMove4.setStroke(Color.BLACK);

        Line separator = new Line(100,5,100,95);
        GridPane pegs = new GridPane();

        Rectangle oldMovePeg1 = new Rectangle(30,30, Color.rgb(0,0,0,0));
//        if(computerPeg1Color != empty) { oldMovePeg1.setFill(computerPeg1Color.getKey()); }
        if(computerPegs[0] != 0) { oldMovePeg1.setFill(colorPairs.get(computerPegs[0]).getKey()); }
        oldMovePeg1.setStroke(Color.BLACK);
        Rectangle oldMovePeg2 = new Rectangle(30,30, Color.rgb(0,0,0,0));
//        if(computerPeg2Color != empty) { oldMovePeg2.setFill(computerPeg2Color.getKey()); }
        if(computerPegs[1] != 0) { oldMovePeg2.setFill(colorPairs.get(computerPegs[1]).getKey()); }
        oldMovePeg2.setStroke(Color.BLACK);
        Rectangle oldMovePeg3 = new Rectangle(30,30, Color.rgb(0,0,0,0));
//        if(computerPeg3Color != empty) { oldMovePeg3.setFill(computerPeg3Color.getKey()); }
        if(computerPegs[2] != 0) { oldMovePeg3.setFill(colorPairs.get(computerPegs[2]).getKey()); }
        oldMovePeg3.setStroke(Color.BLACK);
        Rectangle oldMovePeg4 = new Rectangle(30,30, Color.rgb(0,0,0,0));
//        if(computerPeg4Color != empty) { oldMovePeg4.setFill(computerPeg4Color.getKey()); }
        if(computerPegs[3] != 0) { oldMovePeg4.setFill(colorPairs.get(computerPegs[3]).getKey()); }
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
                    case "Blue":    currentColorName = 1;    break;
                    case "Brown":   currentColorName = 2;    break;
                    case "Green":   currentColorName = 3;    break;
                    case "Orange":  currentColorName = 4;    break;
                    case "Purple":  currentColorName = 5;    break;
                    case "Red":     currentColorName = 6;    break;
                    case "White":   currentColorName = 7;    break;
                    case "Yellow":  currentColorName = 8;    break;
                }
            }
        });

    }

}