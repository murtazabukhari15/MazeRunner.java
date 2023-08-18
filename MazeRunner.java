import java.util.Scanner;

public class MazeRunner {
	public static char[][] maze = {
			{'#', '#', '#', '#', '#', '#', '#'},
			{'#', 'P', '.', '.', '.', '.', '#'},
			{'#', '#', '#', '#', '.', '#', '#'},
			{'#', '.', '.', '.', '.', '.', '#'},
			{'#', '#', '#', '#', '.', '#', '#'},
			{'#', '.', '.', '.', '.', 'E', '#'},
			{'#', '#', '#', '#', '#', '#', '#'}
		};
	
	public static int numOfSteps;
	public static int score;
	public static int highscore = 0;
	
	
	public static void main(String[]args) {
		
		mainMenu();

			
	}
	
	public static void mainMenu() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the corresponding number for the following:");
		System.out.println("1.Play Game");
		System.out.println("2.Instructions");
		System.out.println("3.Credits");
		System.out.println("4.High Score");
		System.out.println("5.Exit Game");
		
		
		
		boolean playAgain = true;
		
		while (playAgain) {
			
			int choice = scan.nextInt();
			
			switch(choice) {
			
			case 1:
				playGame();
				break;
			
			case 2:	
				showInstructions();
				break;
			
			case 3:
				showCredits();
				break;
			
			case 4:
				showHighScore();
				break;
			
			case 5:
				exit();
				playAgain = false;
				break;
			
			default:
				System.out.println("Please select a valid option");
			}
			
			if (playAgain) {
				System.out.println("Do you want to play again? (yes/no)");
				scan.nextLine();
				String response = scan.nextLine();
				if (response == "yes") {
					continue;				
				}
				else if (response == "no") {
					playAgain= false;
					exit();
				}
			}
		}
		scan.close();
	}
	
	
	public static void playGame() {
		Scanner scan = new Scanner(System.in);
        numOfSteps = 0;
        score = 0;
		printMaze();
		while(true) {
			
			char move = getMove(scan);
            boolean validMove = movePlayer(move);

            if (validMove) {
                numOfSteps++;
                updateScore();
                printMaze();

                if (hasPlayerWon()==false) {
                    System.out.println("Congratulations! You've reached the exit!");
                    if (score > highscore) {
                        setHighscore(score);
                        System.out.println("New highscore: " + highscore);
                    }
                    break;
                }
            } 
            else {
                System.out.println("Invalid move. Try again.");
            }
        }
		displayResult();
        scan.close();

    }
	
	
	
    public static boolean hasPlayerWon() {
        
        if (maze[5][5] == 'P') {
        	return false;
        }
        return true;
    }
    
    public static char getMove(Scanner scan) {
        System.out.print("Enter your move (w/a/s/d): ");
        return scan.next().charAt(0);
    }
	
    public static void showInstructions() {
		System.out.println("MazeRunner lets the player 'P' lose in a maze");
		System.out.println("where the player must escape from the exit 'E'");
		System.out.println("while avoiding walls '#'");
		System.out.println("Happy Hunting");
	}
	
	public static void showCredits() {
	
		System.out.println("This Game was developed by Raza Studios with the");
		System.out.println("ambition to provide its users with a unique experience");
		System.out.println("Thankyou for choosing to Play MazeRunner");
		System.out.println("Game v1.0");
	}
	
	public static void setHighscore(int score) {
		highscore = score;
	}
	
	public static void showHighScore() {
		System.out.println("Highscore: " + highscore);
	}
	
	public static void exit() {
		
		System.out.println("Thankyou for Playing");
	}
	
	
	public static void printMaze() {
		
		
		for(int i=0;i<maze.length;i++) {
			for(int j=0; j<maze[0].length;j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
    public static int[] initializeMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 'P') {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
    
    
	public static boolean movePlayer(char move) {
	        int[] playerPosition = initializeMaze();

	        int newRow = playerPosition[0];
	        int newCol = playerPosition[1];

	        switch (move) {
	            case 'w':
	                newRow--;
	                break;
	            case 's':
	                newRow++;
	                break;
	            case 'a':
	                newCol--;
	                break;
	            case 'd':
	                newCol++;
	                break;
	            default:
	                return false;
	        }

	        if (isValidMove(newRow, newCol)) {
	            maze[playerPosition[0]][playerPosition[1]] = '.';
	            maze[newRow][newCol] = 'P';
	            return true;
	        }

	        return false;
	    }
		
	
    public static boolean isValidMove(int newRow, int newCol) {
        return newRow >= 0 && newRow < maze.length &&
               newCol >= 0 && newCol < maze[0].length &&
               maze[newRow][newCol] != '#';
    }

    public static void updateScore() {
    	score++;
    }

    public static void displayResult() {
		System.out.println("Thanks for playing!");
        System.out.println("Number of steps taken: " + numOfSteps);
        System.out.println("Your score: " + score);
    }
}
