import java.util.Scanner;

public class Main{
	public static void main(String[] args){
		Board board = new Board(12);
		Scanner input = new Scanner(System.in);
		Scanner x = new Scanner(System.in);
		Scanner y = new Scanner(System.in);
		board.updateDisplay();
		board.show();
		while(!board.getWin()){
			System.out.println("reveal a block or flag a block, enter 1 to flag, 2 to reveal a block, or 3 do undo a flag");
			int answer = input.nextInt();
			if(answer == 1){
				System.out.println("please choose x");
				int i = x.nextInt();
				System.out.println("please choose y");
				int j = y.nextInt();
				board.flag(i,j);
			}else if(answer==2){
				System.out.println("please choose x");
				int i = x.nextInt();
				System.out.println("please choose y");
				int j = y.nextInt();
				board.blockReveal(i,j);
			}else{
				System.out.println("please choose x");
				int i = x.nextInt();
				System.out.println("please choose y");
				int j = y.nextInt();
				board.unflag(i,j);
			}
			board.checkWin();
			board.updateDisplay();
			board.show();
		}
		System.out.println("if you didn't get the message that said you lost, you have won then (Jackson was too lazy to code the mechanic for printing the winning message)");
	}
}