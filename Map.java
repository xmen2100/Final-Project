public class Map {
    public static void main(String[] args) {
        // how many rows
        int rows = 100;
        // how many columns
        int columns = 100;

        // 2d array of chars
        char[][] gameMap = generateMap(rows, columns);
        printMap(gameMap);
    }

    public static char[][] generateMap(int x, int y) {
        // plug in number of rows and columns in map
        char[][] map = new char[x][y];

        // Fill the map with empty spaces
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                map[i][j] = ' ';
            }
        }

        // Add walls 
        for (int i = 0; i < x; i++) {
            map[i][0] = '|';          // Left wall
            map[i][y - 1] = '|';      // Right wall
        }

        for (int j = 0; j < y; j++) {
            map[0][j] = '_';          // Top wall
            map[x - 1][j] = '-';      // Bottom wall
        }

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
}