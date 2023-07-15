package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


/**
 * @author zhang
 */
public class PercolationStats {
    private Percolation objPercolation;
    private int T;
    private double[] result;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        result = new double[T];
        for (int i = 0; i < T; i++) {
            // Percolation objPercolation = pf.make(N);
            Percolation objPercolation = pf.make(N);
            while (!objPercolation.percolates()) {
                int randomRow = StdRandom.uniform(N);
                int randomCol = StdRandom.uniform(N);
                objPercolation.open(randomRow, randomCol);
            }
            result[i] = (double) objPercolation.numberOfOpenSites() / (N * N);
        }

    }   // perform T independent experiments on an N-by-N grid

    public double mean() {
        return StdStats.mean(result);
    }                                           // sample mean of percolation threshold
    public double stddev() {
        return StdStats.stddev(result);
    }                                         // sample standard deviation of percolation threshold
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }                                  // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }                                 // high endpoint of 95% confidence interval
}
