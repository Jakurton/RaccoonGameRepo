package lab7;

/*
 Written by Jacob Burton and Samuel Wood October&November 2018
 This class is the enemy. The enemy will be a raccoon that attacks the trash truck and causes the player to lose.
 */


public class Enemy {
	private int x;
	private int y;
	private String img;
	
	public Enemy(int pX, int pY, String img){
		this.x = pX;
		this.y = pY;
		this.img = img;
	}
		
	
	public int getpX() {
		return x;
	}

	public void setpX(int pX) {
		this.x = pX;
	}

	public int getpY() {
		return y;
	}

	public void setpY(int pY) {
		this.y = pY;
	}


	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
}
