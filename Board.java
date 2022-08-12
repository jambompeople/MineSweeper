
import java.lang.Math;

public class Board{
	private boolean win = false;
	private boolean cont = false;
	private int boardSize;
	private int flagAmount = 0;
	private BoardObject[][] board;
	private String[][] display;

	public Board(int boardSize){
		this.boardSize = boardSize;
		board = new BoardObject[boardSize][boardSize];
		display = new String[boardSize][boardSize];
		BoardObject currentObj = new BoardObject();
		for(int i = 0; i<boardSize; i++){
			for(int j = 0; j<boardSize; j++){
				board[i][j] = currentObj;
				display[i][j] = "*";
				currentObj.setX(i);
				currentObj.setY(j);
				int r = (int)(Math.random()*8);
				if(r<7){
					currentObj = new BoardObject();
				}else{
					currentObj = new Bomb();
					flagAmount++;
				}
			}
		}
	}

	public void updateDisplay(){
		for(int i = 0; i<boardSize; i++){
			for(int j = 0; j<boardSize; j++){
				if(board[i][j].getFlagged()){
					display[i][j] = "@";
				}else if(!board[i][j].getBlocked()){
					display[i][j] = Integer.toString(getBombNum(i,j));
				}else{
					display[i][j] = "*";
				}
			}
		}
	}



	public int getBombNum(int x, int y){
		int count = 0;
		int i = x;
		int j = y;
		boolean up = i==0;
		boolean left = j==0;
		boolean right = j==boardSize-1;
		boolean down = i==boardSize-1;
		if(!down&&!right&&board[i+1][j+1].getIsBomb()){
			count++;
		}
		if(!left&&!down&&board[i+1][j-1].getIsBomb()){
			count++;
		}
		if(!right&&board[i][j+1].getIsBomb()){
			count++;
		}
		if(!left&&board[i][j-1].getIsBomb()){
			count++;
		}
		if(!up&&!right&&board[i-1][j+1].getIsBomb()){
			count++;
		}
		if(!left&&!up&&board[i-1][j-1].getIsBomb()){
			count++;
		}
		if(!up&&board[i-1][j].getIsBomb()){
			count++;
		}
		if(!down&&board[i+1][j].getIsBomb()){
			count++;
		}

		//too lazy to figure out a better way, brute force is the way
		//here is the issue

		return count;
	}

	public void show(){
		for(String[] i:display){
			for(String e:i){
				System.out.print(e + " ");
			}
			System.out.println("");
		}
	}

	private void occupy(int x, int y){
		boolean corner = x==0||y==0||x==boardSize-1||y==boardSize-1;
		boolean isFlagged = board[x][y].getFlagged();
		boolean isbomb = board[x][y].getIsBomb();
		boolean numberBiggerthan0 = getBombNum(x,y)>0;
		boolean unblocked = !board[x][y].getBlocked();
		if(corner){
			board[x][y].setBlocked(false);
			return;
		}
		if(isbomb||unblocked){
			return;
		}
		if(numberBiggerthan0){
			board[x][y].setBlocked(false);
			return;
		}
		if(isFlagged){
			board[x][y].setFlagged(false);
			board[x][y].setBlocked(false);
		}
		
		board[x][y].setBlocked(false);
		occupy(x+1,y);
		occupy(x-1,y);
		occupy(x,y+1);
		occupy(x,y-1);
	}

	public void blockReveal(int x, int y){
		if(board[x][y].getIsBomb()){
			end();
		}else if(getBombNum(x,y)>0){
			board[x][y].setBlocked(false);
			return;
		}else{
			//How?
			//go if unblocked with no number then move on
			//if unblocked with number, end
			//if blocked without number, go on
			//if blocked with number end
			//at every node a new path starts
			//utilize a new function that uses recursion
			occupy(x,y);
		}
	}

	//game win function
	public void checkWin(){
		int z = 0;
		for(int i = 0; i<boardSize; i++){
			for(int j = 0; j<boardSize; j++){
				if(board[i][j].getFlagged()){
					if(!board[i][j].getIsBomb()){
						win = false;
						return;
					}
					z++;
				}
			}
		}
		if(z==flagAmount){
			win = true;
		}else if(cont){
			win = true;
		}else{
			win = false;
		}
		
	}

	public boolean getWin(){
		return win;
	}

	public void end(){
		cont = true;
		System.out.println("Oops, you have stepped on a bomb, game ends");
	}

	public void flag(int x,int y){
		if(flagAmount>0){
			board[x][y].setFlagged(true);
			flagAmount--;
		}else{
			System.out.println("no more flag to be used, unflag to flag more");
		}
	}

	public void unflag(int x, int y){
		if(board[x][y].getFlagged()){
			board[x][y].setFlagged(false);
			flagAmount++;
		}else{
			System.out.println("not Flagged, unable to unflag the block");
		}
	}


	public int getFlagAmount(){
		return flagAmount;
	}
}
