import java.util.Scanner;

public class Map {
    public static void main(String[] args) {
        // how many rows
        int rows = 27;
        // how many columns
        int columns = 50;

        // 2d array of chars
        char[][] gameMap = generateMap(rows, columns);

        // starting player coords
        int playerXcoord = 25;   // x coord for player
        int playerYcoord = 25;   // y coord for player

        // player
        gameMap[playerYcoord][playerXcoord] = '@';

        // print initial map
        printMap(gameMap);

        // game loop
        while (true) {
            // detect user input
            char move = getUserMove();

            // update player position
            updatePlayerPosition(gameMap, move, playerXcoord, playerYcoord);

            // update player position from keys pressed
            switch (move) {
                case 'W':
                    playerYcoord--;     // moves up
                    break;
                case 'A':
                    playerXcoord--;     // moves left
                    break;
                case 'S':
                    playerYcoord++;     // moves down
                    break;
                case 'D':
                    playerXcoord++;     // moves right
                    break;
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
                map[i][j] = '.';
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
    
        // Check if the new position is within bounds and is not a wall
        boolean isValidMove = false;
        switch (move) {
            case 'W':
                isValidMove = playerYcoord - 1 >= 0 && map[playerYcoord - 1][playerXcoord] != '#';
                break;
            case 'A':
                isValidMove = playerXcoord - 1 >= 0 && map[playerYcoord][playerXcoord - 1] != '#';
                break;
            case 'S':
                isValidMove = playerYcoord + 1 < rows && map[playerYcoord + 1][playerXcoord] != '#';
                break;
            case 'D':
                isValidMove = playerXcoord + 1 < columns && map[playerYcoord][playerXcoord + 1] != '#';
                break;
        }
    
        // Update player position only if it's a valid move
        if (isValidMove) {
            // Clear previous player position
            map[playerYcoord][playerXcoord] = '.';
    
            // Update player position
            switch (move) {
                case 'W':
                    playerYcoord--; // moves up
                    break;
                case 'A':
                    playerXcoord--; // moves left
                    break;
                case 'S':
                    playerYcoord++; // moves down
                    break;
                case 'D':
                    playerXcoord++; // moves right
                    break;
            }
    
            // Set new player position
            map[playerYcoord][playerXcoord] = '@';
        }
    }
 
}