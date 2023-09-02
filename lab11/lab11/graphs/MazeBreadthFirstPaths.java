package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> q = new Queue<Integer>();
        marked[s] = true;
        announce();
        q.enqueue(s);
        while(!q.isEmpty()) {
            int current = q.dequeue();
            if (current == t) {
                return;
            }
            for(int near: maze.adj(current)) {
                if(!marked[near]) {
                    q.enqueue(near);
                    edgeTo[near] = current;
                    distTo[near] = distTo[current] + 1;
                    marked[near] = true;
                    announce();
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

