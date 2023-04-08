/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;


public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    private MazeCell currentCell;
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        Stack <MazeCell> stack1 =  new Stack<MazeCell>();
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();

        currentCell = maze.getEndCell();

        while(maze.getStartCell() != currentCell)
        {
            stack1.push(currentCell);
            currentCell = currentCell.getParent();
        }
        stack1.push(maze.getStartCell());

        int size1 = stack1.size();
        for (int i = 0; i < size1; i++)
        {
            solution.add(stack1.pop());
        }

        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        Stack<MazeCell> stack2 = new Stack<MazeCell>();
        MazeCell current = maze.getStartCell();

        int row;
        int col;

        while(current != maze.getEndCell())
        {

            row = current.getRow();
            col = current.getCol();
            //checks north
            if(maze.isValidCell(row - 1, col))
            {
                maze.getCell(row - 1, col).setExplored(true);
                stack2.push(maze.getCell(row - 1, col));
                maze.getCell(row - 1, col).setParent(current);
            }
            //checks east
            if(maze.isValidCell(row, col + 1))
            {
                maze.getCell(row, col  + 1).setExplored(true);
                stack2.push(maze.getCell(row, col  + 1));
                maze.getCell(row, col + 1).setParent(current);
            }
            //checks south
            if(maze.isValidCell(row + 1, col))
            {
                maze.getCell(row + 1, col).setExplored(true);
                stack2.push(maze.getCell(row + 1, col));
                maze.getCell(row + 1, col).setParent(current);
            }
            //checks west
            if(maze.isValidCell(row - 1, col))
            {
                maze.getCell(row - 1, col).setExplored(true);
                stack2.push(maze.getCell(row - 1, col));
                maze.getCell(row - 1, col).setParent(current);
            }


            current = stack2.pop();


        }
        return getSolution();



    }


    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        int row;
        int col;
        Queue<MazeCell> queue1 = new LinkedList<>();
        MazeCell current = maze.getStartCell();

        while(current != maze.getEndCell())
        {
            row = current.getRow();
            col = current.getCol();
            //north
            if(maze.isValidCell(row - 1 , col))
            {
                maze.getCell(row - 1, col).setExplored(true);
                queue1.add(maze.getCell(row - 1, col));
                maze.getCell(row - 1, col).setParent(current);
            }
            //east
            if(maze.isValidCell(row, col - 1))
            {
                maze.getCell(row - 1, col).setExplored(true);
                queue1.add(maze.getCell(row, col - 1));
                maze.getCell(row, col - 1).setParent(current);
            }
            //south
            if(maze.isValidCell(row + 1, col))
            {
                maze.getCell(row - 1, col).setExplored(true);
                queue1.add(maze.getCell(row + 1, col));
                maze.getCell(row, col - 1).setParent(current);

            }
            //west
            if(maze.isValidCell(row, col + 1))
            {
                maze.getCell(row - 1, col).setExplored(true);
                queue1.add(maze.getCell(row, col + 1));
                maze.getCell(row, col + 1).setParent(current);
            }
            current = queue1.remove();

        }

        return getSolution();


    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
