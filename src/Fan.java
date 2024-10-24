import javafx.application.Application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.stage.Stage;

public class Fan extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		FanPane fan = new FanPane();
	    
	    HBox hBox = new HBox(5);
	    hBox.setAlignment(Pos.CENTER);
    
	    BorderPane pane = new BorderPane();
	    pane.setCenter(fan);
	    pane.setBottom(hBox);
	    
	    // Create a scene and place it in the stage
	    Scene scene = new Scene(pane, 250, 230);
	    primaryStage.setTitle("Fan running"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	    
	    Timeline animation = new Timeline(
	      new KeyFrame(Duration.millis(100), e -> fan.move()));
	    animation.setCycleCount(Timeline.INDEFINITE);
	    animation.play(); // Start animation
	    
	    scene.widthProperty().addListener(e -> fan.setW(fan.getWidth()));
	    scene.heightProperty().addListener(e -> fan.setH(fan.getHeight()));
	    
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}

class FanPane extends Pane {
	  private double w = 200;
	  private double h = 200;
	  private double radius = Math.min(w, h) * 0.45;
	  private Arc arc[] = new Arc[4];   
	  private double startAngle = 30;
	  private Circle circle = new Circle(w / 2, h / 2, radius);
	    
	  public FanPane() {
	    circle.setStroke(Color.BLACK);
	    circle.setFill(Color.WHITE);
	    getChildren().add(circle);
	         
	    for (int i = 0; i < 4; i++) {
	      arc[i] = new Arc(w / 2, h / 2, radius * 0.9, radius * 0.9, startAngle + i * 90, 35);
	      arc[i].setFill(Color.RED); // Set fill color
	      arc[i].setType(ArcType.ROUND);
	      getChildren().addAll(arc[i]); 
	    } 
	  }
	  
	  private double increment = 5;
	  
	  public void reverse() {
	    increment = -increment;
	  }
	  
	  public void move() {
	    setStartAngle(startAngle + increment);
	  }
	    
	  public void setStartAngle(double angle) {
	    startAngle = angle;
	    setValues();
	  }
	  
	  public void setValues() {
	    radius = Math.min(w, h) * 0.45;
	    circle.setRadius(radius);
	    circle.setCenterX(w / 2);
	    circle.setCenterY(h / 2);
	         
	    for (int i = 0; i < 4; i++) {
	      arc[i].setRadiusX(radius * 0.9);
	      arc[i].setRadiusY(radius * 0.9);
	      arc[i].setCenterX(w / 2);
	      arc[i].setCenterY(h / 2);
	      arc[i].setStartAngle(startAngle + i * 90);
	    }     
	  }
	  
	  public void setW(double w) {
	    this.w = w;
	    setValues();
	  }
	  
	  public void setH(double h) {
	    this.h = h;
	    setValues();
	  }
	}
