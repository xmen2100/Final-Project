import java.util.Scanner;

public class Map {
    public static void main(String[] args) {
        // how many rows (for Y coord)
        int rows = 27;
        // how many columns (for X coord)
        int columns = 55;
    
        // 2d array of chars
        char[][] gameMap = generateMap(rows, columns);
    
        // starting player coords (ALWAYS make 1 LESS in x and y) if x = 10, choose x = 9, if y = 20, choose y = 19, etc
        int playerXcoord = 5;   // x coord for player
        int playerYcoord = 24;   // y coord for player

        // player
        gameMap[playerYcoord][playerXcoord] = '@';
    
        // print initial map
        printMap(gameMap);
    
        // game loop
        while (true) {
            // detect user input
            char move = getUserMove();
    
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
            boolean isValidMove = newPlayerYcoord >= 0 && newPlayerYcoord < rows &&
                                  newPlayerXcoord >= 0 && newPlayerXcoord < columns &&
                                  gameMap[newPlayerYcoord][newPlayerXcoord] != '#';
    
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
        }
    }
    

    public static char[][] generateMap(int x, int y) {
        // plug in number of rows and columns in map
        char[][] map = new char[x][y];

        // these are empty spaces
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                map[i][j] = '-';
            }
        }

        // these are walls 
        for (int i = 0; i < x; i++) {
            map[i][0] = '#';          // Left wall
            map[i][y - 1] = '#';      // Right wall
        }

        for (int j = 0; j < y; j++) {
            map[0][j] = '#';          // Top wall
            map[x - 1][j] = '#';      // Bottom wall
        }
        

        int exitXcoord = 45;     // x coord for exit
        int exitYcoord = 0;     // y coord for exit
        map[exitYcoord][exitXcoord] = 'E';      // E for exit

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

    public static char getUserMove() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your move (W/A/S/D): ");

        char move = sc.next().charAt(0);

        sc.nextLine();

        return move;
    }

    public static void updatePlayerPosition(char[][] map, char move, int playerXcoord, int playerYcoord) {
        int rows = map.length;
        int columns = map[0].length;
    
        // Calculate the new position based on the move
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
        boolean isValidMove = newPlayerYcoord >= 0 && newPlayerYcoord < rows &&
                              newPlayerXcoord >= 0 && newPlayerXcoord < columns &&
                              map[newPlayerYcoord][newPlayerXcoord] != '#';
    
        // Update player position only if it's a valid move
        if (isValidMove) {
            // Clear previous player position
            map[playerYcoord][playerXcoord] = '-';
    
            // Set new player position
            map[newPlayerYcoord][newPlayerXcoord] = '@';
        }
    }
    
 
}