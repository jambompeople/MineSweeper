

public class BoardObject{
	private int x;
	private int y;
	private boolean blocked = true;
	private boolean flagged = false;
	private boolean isBomb = false;

	public int getX(){
		return x;
	}

	public void setX(int x){
		this.x = x;
	}

	public int getY(){
		return y;
	}

	public void setY(int y){
		this.y = y;
	}

	public boolean getBlocked(){
		return blocked;
	}

	public void setBlocked(boolean blocked){
		this.blocked = blocked;
	}

	public boolean getFlagged(){
		return flagged;
	}

	public void setFlagged(boolean flagged){
		this.flagged = flagged;
	}

	public boolean getIsBomb(){
		return isBomb;
	}

	public void setIsBomb(boolean isBomb){
		this.isBomb = isBomb;
	}
}