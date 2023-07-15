package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author zhang
 */
public class Percolation {
    int[][] grid; /**
    status of each grid unit: 0 -> blocked
     1 -> open
 */
    private WeightedQuickUnionUF UF;
    private int numOfOpenBlocks;
    private int size; //get the side length of grid to check bound
    private int source, end; //超级源点
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        UF = new WeightedQuickUnionUF(N * N + 3); // plus 2 is required for source and end
        grid  = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = 0;
            }
        }
        numOfOpenBlocks = 0;
        size = grid.length;
        source = N * N + 1; //as long as the number is not in the numbered grid(randomly given number)
        end = N * N + 2; //same as above
    }               // create N-by-N grid, with all sites initially blocked
    public void open(int row, int col) {
        isInBound(row, col);
        if (grid[row][col] == 0) {
            grid[row][col] = 1;
            numOfOpenBlocks++;
            checkAndUnion(row - 1, col, row, col);
            checkAndUnion(row + 1, col, row, col);
            checkAndUnion(row, col - 1, row, col);
            checkAndUnion(row, col + 1, row, col);
        }
        if (row == 0) {
            UF.union(xyTo1D(row, col), source);
        } else if (row == grid.length - 1) {
            UF.union(end, xyTo1D(row, col));
        }

    }       // open the site (row, col) if it is not open already
    private int xyTo1D(int r, int c) {
        return r * size + c;
    }
    /**
     *
     * @param r coordinate of the left, top, right, bottom grid elements that are to be checked
     * @param c the same as above
     */
    private void checkAndUnion(int r, int c, int originX, int originY) {
        if (r >= 0 && r < size && c >= 0 && c < size && grid[r][c] == 1) {
            UF.union(xyTo1D(r, c), xyTo1D(originX, originY));
        }
    }
    public boolean isOpen(int row, int col) {
        isInBound(row, col);
        return grid[row][col] > 0;
    } // is the site (row, col) open?
    public boolean isFull(int row, int col) {
        isInBound(row, col); // is the site (row, col) full?
        return UF.connected(xyTo1D(row, col), source);
        //if the tile is connected to the top of the grid, then it is full
    }
    public int numberOfOpenSites() {
        return numOfOpenBlocks; // number of open sites
    }

    public boolean percolates() {
        return UF.connected(source, end);
    }
    public static void main(String[] args) {
        PercolationFactory factory = new PercolationFactory();
        PercolationStats test = new PercolationStats(20, 100, factory);
        System.out.println(test.mean());
        // use for unit testing (not required)
    }
    private void isInBound(int row, int col) {
        if (row < 0 || row > size - 1 || col < 0 || col > size - 1) {
            throw new IndexOutOfBoundsException();
        }
    }
}
