/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.ui;

import hex.domain.HexColor;
import hex.domain.Player;
import hex.logic.Game;
import java.util.Collection;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import org.codetome.hexameter.core.api.Hexagon;

import org.codetome.hexameter.core.api.HexagonalGridLayout;
import org.codetome.hexameter.core.api.HexagonOrientation;
import org.codetome.hexameter.core.api.HexagonalGrid;
import org.codetome.hexameter.core.api.HexagonalGridBuilder;

import static org.codetome.hexameter.core.api.HexagonalGridLayout.TRAPEZOID;
import static org.codetome.hexameter.core.api.HexagonOrientation.POINTY_TOP;
import org.codetome.hexameter.core.api.Point;
import rx.Observable;
import rx.functions.Action1;

/**
 * Hex game graphical user interface
 *
 * Implementation of the Hex board game graphical user interface using JavaFX
 * and the wonderful Hexameter library.
 *
 * (Hexameter: {
 *
 * @linktourl https://github.com/Hexworks/hexameter}).
 *
 * @author akir
 */
public class GUI extends Application {
    // background image location
    private static final String BACKGROUND_IMAGE = "/background.png";

    // does not apply to game screen
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;

    // board sizes
    private static final int SIZE_TINY = 7;
    private static final int SIZE_SMALL = 11;
    private static final int SIZE_MEDIUM = 13;
    private static final int SIZE_LARGE = 19;

    // Hexameter setup
    private static final HexagonalGridLayout GRID_LAYOUT = TRAPEZOID;
    private static final HexagonOrientation ORIENTATION = POINTY_TOP;
    private static final double RADIUS = 25;

    // game status
    private boolean isRunning = true;
    private Game game;

    // game screen
    private Label infoText;

    // start screen
    private TextField bluePlayerName;
    private TextField redPlayerName;
    private Button playButton;
    private RadioButton tiny;
    private RadioButton small;
    private RadioButton medium;
    private RadioButton large;

    // global stage
    private Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        startScreen(stage);
    }

    private void startScreen(Stage stage) {
        BorderPane pane = new BorderPane();

        GridPane gameSetup = getGameSetupLayout(stage);
        gameSetup.setAlignment(Pos.CENTER);

        pane.setCenter(gameSetup);

        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Let's play Hex!");

        // focus to play button
        playButton.requestFocus();

        stage.show();
    }

    private void gameEndScreen(Stage stage) {
        BorderPane pane = new BorderPane();

        GridPane innerPane = new GridPane();
        innerPane.setAlignment(Pos.CENTER);

        String winnerName = game.getCurrentPlayer().getName();
        Label winner = new Label(winnerName + " WON!");
        winner.setAlignment(Pos.CENTER);
        innerPane.add(winner, 0, 0);

        playButton = new Button("Play again!");
        playButton.setOnAction(event -> gameScreen(stage));

        Button gameSetupButton = new Button("Game setup");
        gameSetupButton.setOnAction(event -> startScreen(stage));

        Button quitButton = new Button("Quit..");
        quitButton.setOnAction(event -> stage.close());

        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(playButton, gameSetupButton, quitButton);

        innerPane.add(buttonBox, 0, 1);
        pane.setCenter(innerPane);

        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setTitle("More Hex?");
        stage.show();
    }

    private void gameScreen(Stage stage) {
        setDefaults();
        int size = getBoardSize();
        boolean blueStarts = true;
        game = new Game(size, bluePlayerName.getText(), redPlayerName.getText());

        BorderPane pane = new BorderPane();
        VBox top = new VBox();
        infoText = new Label(game.getCurrentPlayer().getName() + " starts the game..");
        infoText.setAlignment(Pos.CENTER);

        HBox gameInfo = new HBox();
        gameInfo.setAlignment(Pos.CENTER);
        gameInfo.getChildren().add(infoText);

        pane.setTop(gameInfo);
        setBackground(pane);

        Group gameWindow = new Group();
        pane.setCenter(gameWindow);

        HexagonalGrid grid = initializeGrid(size);
        addHexagons(gameWindow, grid);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Let's play Hex!");
        stage.show();

        isRunning = true;
    }

    private void setBackground(BorderPane pane) {
        Image image = new Image(BACKGROUND_IMAGE);
        BackgroundSize backgroundSize = new BackgroundSize(
                BackgroundSize.AUTO,
                BackgroundSize.AUTO,
                false, false, false, true);

        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);

        Background background = new Background(backgroundImage);
        pane.setBackground(background);
    }

    private GridPane getGameSetupLayout(Stage stage) {
        GridPane gameSetup = new GridPane();
        gameSetup.setHgap(20);
        gameSetup.setVgap(10);

        gameSetup.add(new Label("Who's playing Blue?"), 0, 0);
        gameSetup.add(new Label("Red, please enter your name!"), 2, 0);

        bluePlayerName = new TextField();
        bluePlayerName.setPromptText("Blue");
        redPlayerName = new TextField();
        redPlayerName.setPromptText("Red");

        gameSetup.add(bluePlayerName, 0, 1);
        gameSetup.add(redPlayerName, 2, 1);

        gameSetup.add(getBoardSizeSelect(), 1, 4);

        playButton = new Button("Play!");
        playButton.setOnAction(event -> gameScreen(stage));

        gameSetup.add(playButton, 1, 3);

        return gameSetup;
    }

    private VBox getBoardSizeSelect() {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);

        Label label = new Label("Select board size: ");
        ToggleGroup group = new ToggleGroup();

        tiny = new RadioButton("7x7");
        tiny.setToggleGroup(group);

        small = new RadioButton("11x11");
        small.setToggleGroup(group);

        medium = new RadioButton("13x13");
        medium.setToggleGroup(group);
        medium.setSelected(true);

        large = new RadioButton("19x19");
        large.setToggleGroup(group);

        box.getChildren().addAll(label, tiny, small, medium, large);
        return box;
    }

    private void setDefaults() {
        if (bluePlayerName.getText().equals("")) {
            bluePlayerName.setText("Blue");
        }
        if (redPlayerName.getText().equals("")) {
            redPlayerName.setText("Red");
        }
    }

    private int getBoardSize() {
        if (small.isSelected()) {
            return SIZE_SMALL;
        }
        if (large.isSelected()) {
            return SIZE_LARGE;
        }
        if (tiny.isSelected()) {
            return SIZE_TINY;
        }

        return SIZE_MEDIUM;
    }

    private void addHexagons(Group root, HexagonalGrid grid) {
        Observable<Hexagon> hexagons = grid.getHexagons();

        hexagons.forEach(new Action1<Hexagon>() {

            @Override
            public void call(Hexagon hexagon) {
                Polygon p = pointsToPolygon(hexagon.getPoints());
                // converting from cube coordinates to axial coordinates here,
                // hence z = y and discarding the cube y coordinate.
                //
                // we are adding plus one here, because of the "ghost" edges
                // implemented in board logic
                int x = hexagon.getGridX() + 1;
                int y = hexagon.getGridZ() + 1;
                bindMouseClick(p, x, y);

                // add the polygon representing this Hexagon to UI
                root.getChildren().add(p);
            }
        });
    }

    private void bindMouseClick(Polygon p, int x, int y) {
        // plus one because of ghost edges
        int realX = x + 1;
        int realY = y + 1;

        // bind mouse clicks to act on the board
        p.setOnMouseClicked(event -> handleMouseClick(p, realX, realY));
    }

    private void handleMouseClick(Polygon p, int x, int y) {
        if (!isRunning) {
            return;
        }
        if (!game.playAt(x, y)) {
            return;
        }
        updateCell(p);
        checkForWin();
        game.switchTurns();
        infoText.setText("Your turn, " + game.getCurrentPlayer().getName() + "!");
    }

    private void updateCell(Polygon p) {
        if (game.getCurrentPlayer().getColor() == HexColor.RED) {
            p.setFill(Color.RED);
        } else {
            p.setFill(Color.BLUE);
        }
    }

    private void checkForWin() {
        if (game.isWin()) {
            isRunning = false;
            gameEndScreen(mainStage);
        }
    }

    private HexagonalGrid initializeGrid(int size) {
        HexagonalGridBuilder builder = new HexagonalGridBuilder()
                .setGridHeight(size)
                .setGridWidth(size)
                .setGridLayout(GRID_LAYOUT)
                .setOrientation(ORIENTATION)
                .setRadius(RADIUS);

        HexagonalGrid grid = builder.build();
        return grid;
    }

    private Polygon pointsToPolygon(Collection<Point> points) {
        Double[] arr = new Double[12];
        int idx = 0;

        for (Point p : points) {
            arr[idx] = p.getCoordinateX();
            arr[idx + 1] = p.getCoordinateY();
            idx += 2;
        }

        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(arr);
        polygon.setStroke(Color.SILVER);
        return polygon;
    }

    public static void main(String[] args) {
        launch(GUI.class);
    }
}
