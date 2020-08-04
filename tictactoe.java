import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class tictactoe {
	
	static ArrayList<Integer> player1Board = new ArrayList<Integer>(); 
	static ArrayList<Integer> player2Board = new ArrayList<Integer>(); 
	
	public static void main(String[] args) {

		// initiates the scanner for inputs
		// prompt the user to input y/n for multiplayer
		System.out.println("Multiplayer? y/n");
		Scanner scan = new Scanner(System.in);
		boolean isMultiplayer = false;
		while (scan.hasNext()) {
			String response = scan.nextLine();
			if (response.equals("y")) {
				System.out.println("You are playing a friend.");
				isMultiplayer = true;
				break;
			} else if (response.equals("n")) {
				System.out.println("You are playing the computer.");
				break;
			} else {
				System.out.println("Illegal input. Please try again.");
			}
		}
		
		// display the gameboard
		char [] [] gameboard = {{' ', '|', ' ', '|', ' '}, 
		{'-', '+', '-', '+', '-'}, 
		{' ', '|', ' ', '|', ' '}, 
		{'-', '+', '-', '+', '-'}, 
		{' ', '|', ' ', '|', ' '}};
		printgameboard(gameboard);
		
		// keep playing until someone wins
		while(true) {
			System.out.println("Enter your placement (1-9):");
			int playerpos = scan.nextInt(); 
			while(player1Board.contains(playerpos) || player2Board.contains(playerpos)) {
				System.out.println("Position taken! Enter a correct Position"); 
				playerpos = scan.nextInt();
			}
			placepiece(gameboard, playerpos, "player");
			
			if (isMultiplayer) {
				printgameboard(gameboard);
			}

			String result = checkWinner(); 
			if(result.length() > 0) {
				System.out.println(result); 
				scan.close();
				break; 
			}
			

			// defaults to single player
			Random rand = new Random(); 
			int player2pos = rand.nextInt(9) + 1; 

			if (isMultiplayer) {
				System.out.println("Player 2 please enter your placement (1-9):");
				player2pos = scan.nextInt(); 
				while(player1Board.contains(player2pos) || player2Board.contains(player2pos)) {
					System.out.println("Position taken! Enter a correct Position"); 
					player2pos = scan.nextInt();
				}
			} else {
				while(player1Board.contains(player2pos) || player2Board.contains(player2pos)) {
					player2pos = rand.nextInt(9) + 1; 
				}
			}
			placepiece(gameboard, player2pos, "player2"); 
			
			printgameboard(gameboard);
			
			result = checkWinner(); 
			if(result.length() > 0) {
				System.out.println(result); 
				break; 
			}
			
		}
		
	}
	
	public static void printgameboard(char [] [] gameboard) {
		
		for(char [] row : gameboard) {
			for(char c : row) {
				System.out.print(c);
				
			}		 	
			System.out.println();
		}
	}
	
	public static void placepiece(char[] [] gameboard, int pos, String user) {
		
		char symbol = ' '; 
		
		if(user.equals("player")) {
			symbol = 'X'; 
			player1Board.add(pos); 
		} else if(user.equals("player2")) { 
			symbol = 'O'; 
			player2Board.add(pos); 
		}
		
		switch(pos) {
			case 1: 
			gameboard[0][0] = symbol; 
			break; 
			case 2: 
			gameboard[0][2] = symbol; 
			break; 
			case 3: 
			gameboard[0][4] = symbol; 
			break; 
			case 4: 
			gameboard[2][0] = symbol; 
			break; 
			case 5: 
			gameboard[2][2] = symbol; 
			break; 
			case 6: 
			gameboard[2][4] = symbol; 
			break; 
			case 7: 
			gameboard[4][0] = symbol; 
			break; 
			case 8: 
			gameboard[4][2] = symbol; 
			break; 
			case 9: 
			gameboard[4][4] = symbol; 
			break; 
			default: 
			break; 
			
		}
	}
	
	public static String checkWinner() { 
		
		List topRow = Arrays.asList(1, 2, 3); 
		List midRow = Arrays.asList(4, 5, 6); 
		List botRow = Arrays.asList(7, 8, 9); 
		List leftCol = Arrays.asList(1, 4, 7); 
		List midCol = Arrays.asList(2, 5, 8); 
		List rightCol = Arrays.asList(3, 6, 9); 
		List cross1 = Arrays.asList(1, 5, 9); 
		List cross2 = Arrays.asList(7, 5, 3); 
		
		List<List> winning = new ArrayList<List>(); 
		winning.add(topRow); 
		winning.add(midRow); 
		winning.add(botRow); 
		winning.add(leftCol); 
		winning.add(midCol); 
		winning.add(rightCol); 
		winning.add(cross1);
		winning.add(cross2); 
		
		for(List l : winning) { 
			if(player1Board.containsAll(l)) {
				return "Congratulations player1 won!"; 
			} else if(player2Board.contains(l)) { 
				return "Congratulations player2 won!"; 
			} else if(player1Board.size() + player2Board.size() == 9) {
				return "The game is a tie!";
			}
		}
		return ""; 
	}
}
