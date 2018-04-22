/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.ui;

import hex.domain.HexColor;
import hex.logic.Game;
import java.util.Collection;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
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
 * (Hexameter: {@linktourl https://github.com/Hexworks/hexameter}).
 *
 * @author akir
 */
public class GUI extends Application {

    private static final int SIZE = 5;
    private static final int GRID_HEIGHT = SIZE;
    private static final int GRID_WIDTH = SIZE;
    private static final HexagonalGridLayout GRID_LAYOUT = TRAPEZOID;
    private static final HexagonOrientation ORIENTATION = POINTY_TOP;
    private static final double RADIUS = 40;

    private Game game;

    @Override
    public void start(Stage stage) throws Exception {
        game = new Game(SIZE);

        Group root = new Group();
        Scene scene = new Scene(root);

        HexagonalGrid grid = initializeGrid();
        addHexagons(root, grid);

        stage.setScene(scene);
        stage.setTitle("Let's play Hex!");
        stage.show();
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
        // bind mouse clicks to act on the board
        p.setOnMouseClicked(event -> {
            game.playAt(x, y);
            if (game.getCurrentPlayerColor() == HexColor.RED) {
                p.setFill(Color.RED);
            } else {
                p.setFill(Color.BLUE);
            }
        });
    }

    private HexagonalGrid initializeGrid() {
        HexagonalGridBuilder builder = new HexagonalGridBuilder()
                .setGridHeight(GRID_HEIGHT)
                .setGridWidth(GRID_WIDTH)
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
        return polygon;
    }

    public static void main(String[] args) {
        launch(GUI.class);
    }
}
