package lab7;

public class TrashCan {
	private int x;
	private int y;
	private String img;
	
	public TrashCan(int x, int y) {
		this.x = x;
		this.y = y;
		this.img = "File:trashCan.png";
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String newImg) {
		this.img = newImg;
	}
}
