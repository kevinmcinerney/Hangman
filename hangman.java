/**
* Course title:  		Introduction to Java Programming
* Course code: 			COMP 30580
* Reader:        		Mr. Mark Scanlon B.A., M.Sc.
* Teaching Assistant:	Rahul Mangulkar et al., rahul.mangulkar@ucdconnect.ie
* Student:				Kevin Mc Inerney (B.A.) (Psych.) (Int.), kevinmcinerney8@gmail.com, +353 857591164
* Exercise:				Assignment 2, Q3
* Date:					16/11/2013
* Description:			See Q3
*
*/
import java.util.Scanner;
import java.util.Arrays;
public class Q3 {
	/* Main Method */
	public static void main(String[] args) {
		
		/* Select word randomly from list */
		String [] wordList = {"family", "friends", "circus", "chocolate", "television", "rabbit"};
		int random = (int)(Math.random() * 6);
		String randomWord = wordList[random];
		
		/* Chosen word as char array */
		char [] arrayWord = randomWord.toCharArray();
		
		/* An array of '*' as a blank canvas */
		char [] arrayBlankTemp = new char[arrayWord.length];
		Arrays.fill(arrayBlankTemp, '*');
		
		/* Alphabet array to match with guesses */
		char [] arrayPicksAlpha = new char[26]; 
		int alphaCounter = 0;
		for (char ch = 'a'; ch <= 'z'; ch++){
			arrayPicksAlpha[alphaCounter] = ch;
			alphaCounter++;
		}
		
		/* Numerical array to shadows alphabet array and be incremented 
		 * according to which letters are guessed */
		int[] arrayPicksNum = new int[26];
		
		/* Default 'y' for play again */
		Character playAgainObject = new Character('y');
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("==================Rules==================\n");
		System.out.println("        Guess the word in Ten");
		System.out.println("      Or you sleep with the fish\n");
		System.out.println("=========================================\n");
		
		
		/* Counts user's guesses */
		int count = 1;
		do{
			/* Get user guess and make it into a char object. Also display blank word */
			System.out.println(count + ")  : Guess a letter ->     | " + String.valueOf(arrayBlankTemp) + " |\t\t\n"); 
			String guessString = input.next();
			char guessChar = guessString.charAt(0);
			Character guess = new Character(Character.toLowerCase(guessChar));
			
			for (int i = 0; i < arrayWord.length; i++){
				if (guess.equals(arrayWord[i])){
					hits++;
					break;
					
				}
			}
			
			/* Increment a number array for counting letter which have been picked */
			for (int i = 0; i < 26; i++){
				if (guess.equals(arrayPicksAlpha[i])){
					arrayPicksNum[i]++;
				}
			}
			
			/* If a letter has been picked more than once, ask user to chose again */
			while (isPickedTwice(guess, arrayPicksAlpha, arrayPicksNum)){
				count++;
				System.out.println("That's picked already.\nPick #" + count + ": Pick another one ->"); 
				guessString = input.next();
				guessChar = guessString.charAt(0);
				guess = new Character(Character.toLowerCase(guessChar));
			}
			
		/* Print the word display */
		
		hangMan(count);
		System.out.println("\n\n=========================================\n");
		arrayBlankTemp = arrayReveal(arrayWord, guess, arrayBlankTemp);
		
		/* If the array has no more blanks, print a well done message,
		 * ask user if they want to play again, and reset all values.
		 */
		if (isComplete(arrayBlankTemp) == true){
			System.out.println("\t| You got it! The word was " + String.valueOf(arrayBlankTemp) +"\n");
			System.out.println("\t| You had a total of " + (count - hits) + " misses\n");
			System.out.println("\tDo you want to play again?   <y>   <n>");
			String playAgain = input.next();
			char playAgainChar = playAgain.charAt(0);
			playAgainObject = new Character(playAgainChar);
			
			/* Reset word choice */
			int randomNew = (int)(Math.random() * 6);
			randomWord = wordList[randomNew];
			arrayWord = randomWord.toCharArray();
			
			/* Rest blank array */
			arrayBlankTemp = new char[arrayWord.length];
			Arrays.fill(arrayBlankTemp, '*');
			
			/* Reset number array */
			Arrays.fill(arrayPicksNum, 0);
			
			/* Reset user attempt counter */
			count = 0;
			hits = 0;
			}
		count++;
		
		
		/* Loop while asterisks still exist and the player wants to continue playing */
		}while(isComplete(arrayBlankTemp) == false && playAgainObject.equals('y'));
	}
	
	/* Takes in exiting array and a return new array with correct guesses added */
	public static char [] arrayReveal(char []arrayWord, char guess, char [] arrayBlank){
		/* Array for storing index locations of guessed letter/s */
		char [] arrayIndex = new char[arrayWord.length];
		Character guessObject = new Character(guess);
		
		/* Increment arrayIndex where letters are found */
		for (int i = 0; i < arrayWord.length; i++){
			if (guessObject.equals(arrayWord[i])){
				arrayIndex[i]++;
			}
		}
		
		/* Change asterisk/s to guessed letter/s according to indexArray */
		for(int i = 0; i < arrayWord.length; i++){
			if (arrayIndex[i] > 0){
				arrayBlank[i] = guess;
			}
		}
		return arrayBlank;
	}
	
	/* Return true if all the letters have been found */
	public static boolean isComplete (char[] arrayBlankTemp){
		
		boolean isComplete = false;
		for (int i = 0; i < arrayBlankTemp.length; i++){
			if (arrayBlankTemp[i] == '*'){
				isComplete = false;
				break;
			}
			else
			{
				isComplete = true;
			}
		}
		return isComplete;
	}	
	
	/* If a letter has been picked more than once, return true */
	public static boolean isPickedTwice (Character guess, char [] arrayPicksAlpha, int [] arrayPicksNum){
		
		boolean isPickedTwice = false;
		
		for (int i = 0; i < 26; i++){
			if (guess.equals(arrayPicksAlpha[i]) && arrayPicksNum[i] > 1){
				isPickedTwice = true;
			}
		}
	return isPickedTwice;
	}


	public static void hangMan(int count){
	
		if (count < 10){
			for (int i = 1;i <= count; i++){
				
				switch(i){
				case 1: System.out.println(" |====U===|==|\n      |   |==|");break;
				case 2: System.out.println("      |    \\=|\n    \\_0_/    |");break;
				case 3: System.out.println("      |	   |");break;
				case 4: System.out.println("      ^	   |");break;
				case 5: System.out.println("     / \\	   |");break;
				case 6: System.out.println("             |");break;
				case 7: System.out.println(" |~   ~   ~  |\n |  ~   ~   ~| \n |~ ^ ^ ^ ^ ~| \n | ^ ^ ^ ^ ^ | \n |___________|\n\n");break;
				case 8: System.out.println((10 - count) + " CHANCES LEFT BEFORE YOU DROWN!");break;
				
				}
			}
		}
		else
		{
		System.out.println(" |====U===|===|");
		System.out.println("      |    |==|");
		System.out.println("      |    \\ =|");
		System.out.println("      |       |");
		System.out.println("      |       |");
		System.out.println("      |       |");
		System.out.println("|~ ~ ~|~ ~ ~ ~|");
		System.out.println("|   \\_0_/ ~   |");
		System.out.println("|  ~   |      |");
		System.out.println("|      ^	~   |");
		System.out.println("| ~   / \\	    |");
		System.out.println("|  ~   ~    ~ |");
		System.out.println("|_____________|");
		System.out.println("\n---Continue playing in heaven----");
		}
	}
	
	public static int hits = 0;
	
}

