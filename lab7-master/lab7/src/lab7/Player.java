package lab7;

import javafx.scene.input.KeyCode;

/*
 Written by Jacob Burton  and Samuel Wood October&November 2018
 This is the Player. The player should move around and collect trash.
*/

public class Player
{
	private String truckerName = "";
	private String imageFile = "";
	private int trash = 0;
	private int playerX = 0;
	private int playerY = 0;
	
	public Player(String truckerName, String imageFile, int trash, int playerX, int playerY)
	{
		
		super();
		this.playerX = playerX;
		this.playerY = playerY;
		this.truckerName = truckerName;
		this.imageFile = imageFile;
		this.trash = trash;
	}
	
	public int getPlayerX()
	{
		return playerX;
	}

	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}

	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}

	public int getPlayerY()
	{
		return playerY;
	}

	
	public String toString()
	{
		return "UserName: " + truckerName + "\n" + "Trash collected: " + trash;
	}

	public String getTruckerName() {
		return truckerName;
	}

	public int getTrash() {
		return trash;
	}
	
	public void movePlayer(KeyCode keyCode)
	{
		 
		if (keyCode == KeyCode.A)
		    {
		        setPlayerX(playerX - 10);

		    }
		   
		    else if (keyCode == KeyCode.S)
		    {
		        setPlayerY(playerY + 10);

		    }
		    
		    else if (keyCode == KeyCode.W)
		    {
		        setPlayerY(playerY - 10);

		    }
		    
		    else if (keyCode == KeyCode.D)
		    {
		        setPlayerX(playerX + 10);
		    }
	}
}

