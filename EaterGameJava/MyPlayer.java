import java.util.*;
import java.io.*;
import javafx.event.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import javafx.animation.PathTransition;
import javafx.animation.FillTransition;
import javafx.util.Duration;
import javafx.animation.Timeline;

public class MyPlayer implements Player {
	private Circle ball;
	private Map map;
	private Position position;
	private PathTransition pt;

	MyPlayer(Map m) {
		map = m;
		position = map.getStartPosition();
		ball = new Circle(position.getX()*map.getUnit() + map.getUnit()/2, position.getY()*map.getUnit() + map.getUnit()/2, map.getUnit()/2.5);
		FillTransition ft = new FillTransition(Duration.millis(2000), ball, Color.RED, Color.BLUE);
		ft.setCycleCount(Timeline.INDEFINITE);
		ft.setAutoReverse(true);
		ft.play();
		map.getChildren().add(ball);
	}
	public void moveRight() {
		Line path = new Line((position.getX() + 0.5)*map.getUnit(), (position.getY() + 0.5)*map.getUnit(), (position.getX() + 1.5)*map.getUnit(), (position.getY() + 0.5)*map.getUnit());
		pt = new PathTransition(Duration.millis(200), path, ball);
		pt.play();
		position.setX(position.getX() + 1);
		ball.setCenterX((position.getX() + 0.5)*map.getUnit());
	}
	public void moveLeft() {
		Line path = new Line((position.getX() + 0.5)*map.getUnit(), (position.getY() + 0.5)*map.getUnit(), (position.getX() - 0.5)*map.getUnit(), (position.getY() + 0.5)*map.getUnit());
		pt = new PathTransition(Duration.millis(200), path, ball);
		pt.play();
		position.setX(position.getX() - 1);
		ball.setCenterX((position.getX() + 0.5)*map.getUnit());
	}
	public void moveUp() {
		Line path = new Line((position.getX() + 0.5)*map.getUnit(), (position.getY() + 0.5)*map.getUnit(), (position.getX() + 0.5)*map.getUnit(), (position.getY() - 0.5)*map.getUnit());
		pt = new PathTransition(Duration.millis(200), path, ball);
		pt.play();
		position.setY(position.getY() - 1);
		ball.setCenterY((position.getY() + 0.5)*map.getUnit());
	}
	public void moveDown() {
		Line path = new Line((position.getX() + 0.5)*map.getUnit(), (position.getY() + 0.5)*map.getUnit(), (position.getX() + 0.5)*map.getUnit(), (position.getY() + 1.5)*map.getUnit());
		pt = new PathTransition(Duration.millis(200), path, ball);
		pt.play();
		position.setY(position.getY() + 1);
		ball.setCenterY((position.getY() + 0.5)*map.getUnit());
	}
	public Position getPosition() {
		return position;
	}
}