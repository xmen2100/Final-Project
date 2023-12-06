import java.util.Scanner;

public class Map3 {
    // exit coord
    static int exitXcoord = 109; // x coord for exit (ALWAYS subtract 1)
    static int exitYcoord = 0; // y coord for exit (ALWAYS subtract 1)

    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);
        playMap(userInputScanner);
    }

    public static void playMap(Scanner userInputScanner) {
        // how many rows (for Y coord)
        int rows = 45;
        // how many columns (for X coord)
        int columns = 120;

        // 2d array of chars
        char[][] gameMap = generateMap(rows, columns);

        // starting player coords (ALWAYS make 1 LESS in x and y) if x = 10, choose x = 9, if y = 20, choose y = 19, etc
        int playerXcoord = 9; // x coord for player '#' are included
        int playerYcoord = 41; // y coord for player '#' are included

        // player
        gameMap[playerYcoord][playerXcoord] = '@';

        // print initial map
        printMap(gameMap);

        // game loop
        boolean exitReached = false;
        while (!exitReached) {
            // detect user input
            char move = getUserMove(userInputScanner);

            // update player coordinates
            int newPlayerXcoord = playerXcoord;
            int newPlayerYcoord = playerYcoord;

            switch (move) {
                case 'W':
                    newPlayerYcoord--; // moves up
                    break;
                case 'A':
                    newPlayerXcoord--; // moves left
                    break;
                case 'S':
                    newPlayerYcoord++; // moves down
                    break;
                case 'D':
                    newPlayerXcoord++; // moves right
                    break;
            }

            // Check if the new position is within bounds and is not a wall
            boolean isValidMove = newPlayerYcoord >= 0 && newPlayerYcoord < rows && newPlayerXcoord >= 0
                    && newPlayerXcoord < columns && gameMap[newPlayerYcoord][newPlayerXcoord] != '#';

            // Check if the player reached the exit
            exitReached = newPlayerYcoord == exitYcoord && newPlayerXcoord == exitXcoord;

            // Update player position only if it's a valid move
            if (isValidMove) {
                // Clear previous player position
                gameMap[playerYcoord][playerXcoord] = '-';

                // Set new player position
                gameMap[newPlayerYcoord][newPlayerXcoord] = '@';

                // Update player coordinates after the move is validated
                playerXcoord = newPlayerXcoord;
                playerYcoord = newPlayerYcoord;
            }

            // clear console
            System.out.println("\u001b[2J\u001b[H");
            System.out.flush();

            // print updated map
            printMap(gameMap);

            // if reach exit, win game!
            if (exitReached) {
                    // PRINT WIN MESSAGE
                    System.out.print("You completed all 3 Maps!");

                    // Exit the program after running Map3
                    System.exit(0);
                } 
            }
        }

    public static char[][] generateMap(int x, int y) {
        // plug in the number of rows and columns in the map
        char[][] map = new char[x][y];

        // these are empty spaces
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                map[i][j] = '-';
            }
        }

        // these are walls
        for (int i = 0; i < x; i++) {
            map[i][0] = '#'; // Left wall
            map[i][y - 1] = '#'; // Right wall
        }

        for (int j = 0; j < y; j++) {
            map[0][j] = '#'; // Top wall
            map[x - 1][j] = '#'; // Bottom wall
        }

        map[exitYcoord][exitXcoord] = 'X'; // X for exit

        return map;
    }

    public static void printMap(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static char getUserMove(Scanner sc) {
        System.out.println("Welcome to Map3");
        System.out.print("Enter your move (W/A/S/D): ");
        char move = sc.next().charAt(0);
        sc.nextLine();
        return move;
    }
}
