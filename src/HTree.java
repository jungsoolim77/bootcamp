import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class HTree extends Application {
	BorderPane bp = new BorderPane();
	Pane pane = new Pane();
	TextField tfOrder = new TextField();
	int order = 0, length;
	int WIDTH = 500, HEIGHT = 550;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		setUI();
		Scene scene = new Scene(bp, WIDTH, HEIGHT);
		primaryStage.setTitle("HTree");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void setUI() {
		HBox hbox = new HBox();
		hbox.getChildren().addAll(new Label(" Enter an order: "), tfOrder);
		hbox.setAlignment(Pos.CENTER);
		tfOrder.setPrefColumnCount(10);
		tfOrder.setAlignment(Pos.CENTER);
		tfOrder.setOnAction(e-> {order = Integer.parseInt(tfOrder.getText()); paint();});
		bp.setBottom(hbox);
		bp.setCenter(pane);
		pane.widthProperty().addListener(ov-> {paint();});
		pane.heightProperty().addListener(ov-> {paint();});
	}

	private void paint() {

		pane.getChildren().clear();
		int w = (int) pane.getWidth() ;
		int h = (int) pane.getHeight();
		if (w > h) {
			length = h/3;
		}else {
			length = w/3;
		}
		if (order > 0) {
			drawTree((order - 1), length/2, w/2, h/2);
		}
	
	}
	
	private void drawTree(int order, int length, int x, int y) {

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

}
