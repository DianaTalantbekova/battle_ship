class Board {
    static final int SIZE = 8;
    private char[][] grid;
    
    public Board() {
        this.grid = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = '-';
            }
        }
    }
    
    public void printBoard() {
        System.out.println("  A B C D E F G H");
        
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            
            System.out.println();
        }
    }
    
    public void markMiss(int row, int col) {
        grid[row][col] = 'o';
    }
    
    public void markHit(int row, int col) {
        grid[row][col] = 'U';
    }
    
    public void markSunk(int row, int col) {
        grid[row][col] = 'X';
        markAdjacentCells(row, col, 'o');
    }
    
    private void markAdjacentCells(int row, int col, char mark) {
        markCell(row - 1, col - 1, mark);
        markCell(row - 1, col, mark);
        markCell(row - 1, col + 1, mark);
        markCell(row, col - 1, mark);
        markCell(row, col + 1, mark);
        markCell(row + 1, col - 1, mark);
        markCell(row + 1, col, mark);
        markCell(row + 1, col + 1, mark);
    }
    
    private void markCell(int row, int col, char mark) {
        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && grid[row][col] == '-') {
            grid[row][col] = mark;
        }
    }
}