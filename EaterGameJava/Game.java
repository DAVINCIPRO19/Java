import java.io.*;
import java.util.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.application.Application;

public class Game extends Application{
	private Map map;
	private Player player;
	private Food food;
	static String file_name = "";

	public static void main(String[] args) {
		file_name = args[0];
		launch(args);
	}
	public void start(Stage primaryStage) {
		map = new Map(file_name);
		player = new MyPlayer(map);
		food = new Food(map, player);
		System.out.println("Size of map: " + map.getSize());
		map.setOnKeyPressed(e -> {
			switch(e.getCode()) {
				case RIGHT: 
					if(player.getPosition().getX() + 1 < map.getSize() && map.getValue(player.getPosition().getY(), player.getPosition().getX() + 1) != 1) {

						player.moveRight();
					}
					else {
						System.out.println("Invalid direction!");
					}
					break;
				case LEFT: 
					if(player.getPosition().getX() - 1 >= 0 && map.getValue(player.getPosition().getY(), player.getPosition().getX() - 1) != 1) {
						player.moveLeft();
					}
					else {
						System.out.println("Invalid direction!");
					}
					break;
				case UP:
					if(player.getPosition().getY() - 1 >= 0 && map.getValue(player.getPosition().getY() - 1, player.getPosition().getX()) != 1) {
						player.moveUp();
					}
					else {
						System.out.println("Invalid direction!");
					}
					break;
				case DOWN:
					if(player.getPosition().getY() + 1 < map.getSize() && map.getValue(player.getPosition().getY() + 1, player.getPosition().getX()) != 1) {
						player.moveDown();
					}
					else {
						System.out.println("Invalid direction!");
					}
					break;
				default: System.out.println("Key is not correct!");
			}
		});
		primaryStage.setScene(new Scene(map, map.getSize()*map.getUnit(), map.getSize()*map.getUnit()));
		primaryStage.setTitle("Eater");
		primaryStage.show();
		map.requestFocus();
	}  
}
