package lab7;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 Written by Jacob Burton and Samuel Wood October&November 2018
 This is the game scene/stage. Just the playing field.
 */

public class Game extends Application
{
	
	private String truckerName = "";
	private String imageFile = "";
	private int trash = 0;
	private int playerX = 0;
	private int playerY = 0;
	private int enemyX = 900;
	private int enemyY = 550;
	
	private int trashCollected = 0;
	
	// Need to be global scope to be accessed by other methods.
	private ArrayList<TrashCan> trashCoords = new ArrayList<TrashCan>();
	
	private ArrayList<Integer> scores = new ArrayList<Integer>();
	
	private static int items = 5;
	
	private FlowPane gameRoot = new FlowPane();

	private Enemy enemy = new Enemy(enemyX,enemyY,"raccoon.png");

	private Image enemyimg = new Image("File:"+enemy.getImg());
	private ImageView enemyView = new ImageView(enemyimg);
	
	private Player player = new Player(truckerName, imageFile, trash, playerX, playerY);
	
	private Stage gameStage;
	
	public static void main(String[] args)
	{
		launch(args);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Image wasdImage = new Image("file:wasd.jpg");
		ImageView wasdView = new ImageView(wasdImage);
	    wasdView.setPreserveRatio(true);  
	    wasdView.setFitHeight(125); 

		primaryStage.setTitle("GarbageCollector");
		
		FlowPane pane = new FlowPane();
				
		Button startButton = new Button("Start");
		
		pane.getChildren().addAll(startButton, wasdView);
		
		Group root = new Group(pane);
		
		startButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent startClick)
			{
				Image playerImage = new Image("file:trashtruckpng.png");
				ImageView playerView = new ImageView(playerImage);
				playerView.setFitWidth(120); 
				playerView.setPreserveRatio(true);
			    playerView.setManaged(false);

				enemyView.setFitWidth(50);
				enemyView.setPreserveRatio(true);
				enemyView.setManaged(false);
				enemyView.setLayoutX(enemy.getpX());
				enemyView.setLayoutY(enemy.getpY());
				enemyView.toFront();
				
				gameRoot.getChildren().add(playerView);
				gameRoot.getChildren().add(enemyView);

				addTrashCans();

				Scene sceneStart = new Scene(gameRoot, 1000, 600);
				
				sceneStart.setOnKeyPressed(a -> {
				   player.movePlayer(a.getCode());
				   playerView.setLayoutY(player.getPlayerY());
				   playerView.setLayoutX(player.getPlayerX());
				   
				   trashPickup();
				});

				// Adds trash cans
				Stage gameStage = (Stage) ((Node) startClick.getSource()).getScene().getWindow();											
				gameStage.setScene(sceneStart);
				gameStage.show();
				moveRaccoon();

			}
		});
		
		Scene sceneInitial = new Scene(root, 1000, 600);
		primaryStage.setScene(sceneInitial);
		primaryStage.show();
	}
	
	private void moveRaccoon() {
		if (player.getPlayerX() > enemy.getpX()) enemyX+=10;
		else if (player.getPlayerX() < enemy.getpX()) enemyX-=10;
		
		if (player.getPlayerY() > enemy.getpY()) enemyY+=10;
		else if (player.getPlayerY() < enemy.getpY()) enemyY-=10;
		
		enemy.setpX(enemyX);
		enemy.setpY(enemyY);

		enemyView.setLayoutX(enemy.getpX());
		enemyView.setLayoutY(enemy.getpY());
		
		raccoonHit();
	}
	
	
	private void addTrashCans(){
		
		// Just adds two at first. We increment this every level.
		for (int i = 0; i < items; i++) {
			Random r = new Random();
			int randomY = r.nextInt((560) + 1);
			int randomX = r.nextInt((960) + 1);
			TrashCan newCan = new TrashCan(randomX, randomY);
			trashCoords.add(newCan);
			Image can = new Image(trashCoords.get(i).getImg());
		    ImageView canView = new ImageView(can);
		    canView.setFitWidth(50);
		    canView.setPreserveRatio(true);
		    canView.setManaged(false);
		    canView.setLayoutX(trashCoords.get(i).getX());
		    canView.setLayoutY(trashCoords.get(i).getY());
			gameRoot.getChildren().add(canView);
		}
	}
	
	private void raccoonHit()		//enemy collision with player = end game
	{
		int topP = player.getPlayerY();
		int botP = player.getPlayerY() + 120;
		int leftP = player.getPlayerX();
		int rightP = player.getPlayerX() + 120;
		
		int topR = enemy.getpY();
		int botR = enemy.getpY() + 50;
		int leftR = enemy.getpX();
		int rightR = enemy.getpX() + 50;
		
		if (areRectsColliding(leftP, rightP, topP, botP, leftR, rightR, topR, botR))
		{
			System.out.println("omg it works");
			
			loseGame();
			
		} else {
			setTimeout(()->moveRaccoon(), 300);
		}
	}
	
	private void trashPickup()
	{
		for(int i = 0; i < trashCoords.size(); i++)
		{
			int topP = player.getPlayerY();
			int botP = player.getPlayerY() + 120;
			int leftP = player.getPlayerX();
			int rightP = player.getPlayerX() + 120;
			
			int topT = trashCoords.get(i).getY();
			int leftT = trashCoords.get(i).getX();
			int botT = trashCoords.get(i).getY() + 50;
			int rightT = trashCoords.get(i).getX() + 50;
			
			if (areRectsColliding(leftP, rightP, topP, botP, leftT, rightT, topT, botT))
			{
				System.out.println("alsndasd");
				
				trashCollected++;
				
				trashCoords.remove(i);
			}
		}
	}
	// Makeshift set time out that I did not write...https://stackoverflow.com/questions/26311470/what-is-the-equivalent-of-javascript-settimeout-in-java

	private static void setTimeout(Runnable runnable, int delay){
	    new Thread(() -> {
	        try {
	            Thread.sleep(delay);
	            runnable.run();
	        }
	        catch (Exception e){
	            System.err.println(e);
	        }
	    }).start();
	}
	
	private boolean areRectsColliding(int object1Left, int object1Right, int object1Top, int object1Bot, int object2Left, int object2Right, int object2Top, int object2Bot)
	{
		if (object1Left < object2Right && object1Right > object2Left && object1Top < object2Bot && object1Bot > object2Top)
			{
			return true;
			
			}
		else
			{
			return false;
			
			}
	}
	
	private void loseGame()
	{
		GridPane loseRoot = new GridPane();
	    
		scores.add(trashCollected);
		
		Text highScores = new Text();
		
		for (int i = 0; i < scores.size(); i++)
		{
			System.out.println(scores.get(i) + "\n");
		}
			
		GridPane.setRowIndex(highScores, 0);
	    GridPane.setColumnIndex(highScores, 0);
	    
		Button continueButton = new Button("Click to retry");
		GridPane.setRowIndex(continueButton, 7);
	    GridPane.setColumnIndex(continueButton, 0);
		
		loseRoot.getChildren().addAll(continueButton, highScores);

		Scene loseScreen = new Scene(loseRoot, 1000, 600);
		gameStage.setScene(loseScreen);

	}
}
