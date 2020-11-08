package subscene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Timer extends Application {
 
 Label lb;
 Stage windows;
 private final Integer starttime=0;
 private Integer seconds= starttime;
 public Timer(){
	 
 }
 @Override
 public void start(Stage primaryStage) {
  windows= primaryStage;
  Group root= new Group();
  lb= new Label();
  lb.setFont(Font.font(30));
  lb.setTextFill(Color.WHITE);
  
   doTime();
  
  HBox layout= new HBox(5);
  layout.getChildren().add(lb);
  root.getChildren().add(layout);
  Scene scene= new Scene(root, 50,50, Color.BLACK);
  windows.setScene(scene);
  windows.show();
  
 }
 
 private void doTime() {
  Timeline time= new Timeline();
  
  
  KeyFrame frame= new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){

   @Override
   public void handle(ActionEvent event) {
    
    
    seconds++;
    lb.setText(seconds.toString());
    
   }
   
  });
  
  time.setCycleCount(Timeline.INDEFINITE);
  time.getKeyFrames().add(frame);
  if(time!=null){
   time.stop();
  }
  time.play();
  
 }

 public static void main(String[] args) {
  launch(args);
 }
}