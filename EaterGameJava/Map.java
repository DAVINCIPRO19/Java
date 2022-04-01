import java.util.*;
import java.io.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.application.Application;


public class Map extends Pane {
    private int UNIT = 50;
    private int size;
    private Integer[][] map;
    private Position start;

    Map(String file_name) {
        Scanner scan = null;
        try {
            scan = new Scanner(new File(file_name));
            size = scan.nextInt();
            map = new Integer[size][size];
            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    int value = scan.nextInt();
                    if(value == 2){
                        start = new Position(i, j);
                        map[i][j] = 0;
                    }
                    else {
                        map[i][j] = value;
                    }   
                }
            }
        }catch(Exception e) {
            e.getMessage();
        }
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                Rectangle rec = new Rectangle(j*UNIT, i*UNIT, UNIT, UNIT);
                if(getValue(i, j) == 0 || getValue(i, j) == 2) {
                    rec.setFill(Color.BLACK);
                }
                else {
                    rec.setFill(new ImagePattern(new Image("Bricks.png")));
                }
                getChildren().add(rec);
            }
        }
    }
    int getUnit() {
        return UNIT;
    }
    int getSize() {
        return size;
    }
    int getValue(int row, int column) {
        return map[row][column];
    }
    Position getStartPosition() {
        return start;
    }
}