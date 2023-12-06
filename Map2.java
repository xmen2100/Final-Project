import java.io.*;
import java.util.Scanner;

public class Map2 {
    // exit coord
    static int exitXcoord = 64; // x coord for exit (ALWAYS subtract 1)
    static int exitYcoord = 0; // y coord for exit (ALWAYS subtract 1)
    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);
        playMap(userInputScanner);
    }

    public static void playMap(Scanner userInputScanner) {
        // how many rows (for Y coord)
        int rows = 37;
        // how many columns (for X coord)
        int columns = 70;

        // 2d array of chars
        char[][] gameMap = generateMap(rows, columns);

        // starting player coords (ALWAYS make 1 LESS in x and y) if x = 10, choose x = 9, if y = 20, choose y = 19, etc
        int playerXcoord = 5; // x coord for player '#' are included
        int playerYcoord = 33; // y coord for player '#' are included

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

            // if reach exit, run new Java file
            if (exitReached) {
                try {
                    // compile and run map3 within map
                    executeMap3();

                    // Exit the program after running Map3
                    System.exit(0);
                } catch (IOException e) {
                    e.printStackTrace(); // display error message if error
                }
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
        System.out.println("Welcome to Map2");
        System.out.print("Enter your move (W/A/S/D): ");
        char move = sc.next().charAt(0);
        sc.nextLine();
        return move;
    }

    public static void executeMap3() throws IOException {
        // command to compile Map2
        String compileCommand = "javac Map3.java";

        // create a process builder to start a new program
        ProcessBuilder compileBuilder = new ProcessBuilder(compileCommand.split("\\s+"));
        // sets the working directory for the new process to the current working directory
        compileBuilder.directory(new File(System.getProperty("user.dir"))); // Set the working directory

        // redirect the output and error streams
        compileBuilder.redirectErrorStream(true);
        // starts process Map3
        Process compileProcess = compileBuilder.start();

        // obtain the output stream
        try (InputStream compileInputStream = compileProcess.getInputStream();
             Scanner compileScanner = new Scanner(compileInputStream)) {

            while (compileScanner.hasNextLine()) {
                System.out.println(compileScanner.nextLine());
            }

            // wait for the compilation process to complete
            int compileExitCode = compileProcess.waitFor();

            if (compileExitCode == 0) {
                // if compilation is successful, run Map3
                executeMap3Run();
            } else {
                System.out.println("Error: Map3 compilation failed!");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void executeMap3Run() throws IOException {
        // command to run Map3
        String runCommand = "java Map3";
    
        // create a process builder to start the new program
        ProcessBuilder runBuilder = new ProcessBuilder(runCommand.split("\\s+"));
        runBuilder.directory(new File(System.getProperty("user.dir"))); // Set the working directory
    
        // inherit input, output, and error streams
        runBuilder.inheritIO();
    
        // starts process Map3
        Process runProcess = runBuilder.start();
    
        try {
            // wait for the process to complete
            int runExitCode = runProcess.waitFor();
    
            if (runExitCode != 0) {
                // print an error message
                System.out.println("Error: Map3 run did not execute successfully!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
