import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final int topIndex;
    private final int bottomIndex;
    private int openedSites;
    private final boolean [] opened;
    private final WeightedQuickUnionUF percolate;
    private final WeightedQuickUnionUF full;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n must be greater than zero");
        this.n = n;
        percolate = new WeightedQuickUnionUF(n*n+2);
        full = new WeightedQuickUnionUF(n*n+1);
        opened = new boolean[n*n+2];
        topIndex = 0;
        bottomIndex = n * n + 1;
        opened[topIndex] = true;
        opened[bottomIndex] = true;


    }
    private int indexOf(int row, int col) {
        if (row < 1 || row > n) {
            throw new IllegalArgumentException("Row is out of bounds.");
        }
        if (col < 1 || col > n) {
            throw new IllegalArgumentException("Column is out of bounds.");
        }
        return (row-1) * n + col;
    }
    private void tryUnion(int rowA, int colA, int rowB, int colB) {
        if (rowB > 0 && rowB <= n && colB > 0 && colB <= n
        && isOpen(rowB, colB)) {
            percolate.union(indexOf(rowA, colA), indexOf(rowB, colB));
            full.union(indexOf(rowA, colA), indexOf(rowB, colB));
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) return;
        int currentIndex = indexOf(row, col);
        opened[currentIndex] = true;
        openedSites++;
        if (row == 1) {
            percolate.union(currentIndex, topIndex);
            full.union(currentIndex, topIndex);
        }
        if (row == n) {
            percolate.union(currentIndex, bottomIndex);
        }

        tryUnion(row, col, row-1, col);
        tryUnion(row, col, row+1, col);
        tryUnion(row, col, row, col-1);
        tryUnion(row, col, row, col+1);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return opened[indexOf(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return full.find(topIndex) == full.find(indexOf(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openedSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return percolate.find(topIndex) == percolate.find(bottomIndex);
    }
    public static void main(String[] args) {
        StdOut.println("USE PERCOLATE STATS");
    }
}
