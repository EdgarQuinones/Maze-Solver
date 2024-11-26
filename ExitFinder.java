// Edgar

package p3; // Do not delete this

import stacksandqueues.LinkedStack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ExitFinder {

    int rows, cols;
    int startX, startY;
    char[][] cords;
    String file;
    boolean pathFound = true;

    // Complete this method; reads a maze from a file
    public ExitFinder(String mazeInputFile) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(mazeInputFile));

        file = mazeInputFile;

        rows = scan.nextInt();
        cols = scan.nextInt();

        cords = new char[rows][cols];

        scan.nextLine();


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char character = scan.next().charAt(0);
                cords[i][j] = character;

                if (character == 'R') {
                    startX = i;
                    startY = j;
                }
            }

        }
        scan.close();
    }

    // Complete this method. The main stack algorithm must be implemented inside this method
    public void findExitPathStackBased() {
        // You must 'LinkedStack' from the 'stacksandqueues' package
        // complete the remaining part
        LinkedStack<int[]> cells = new LinkedStack<>();
        int[] currentCell = {startX, startY};

        while (cords[currentCell[0]][currentCell[1]] != 'E') {
            if (cords[currentCell[0]][currentCell[1]] != 'R') {
                cords[currentCell[0]][currentCell[1]] = '.';

            }

            // UP
            if (currentCell[0] - 1 >= 0) {
                if (cords[currentCell[0] - 1][currentCell[1]] == '0' || cords[currentCell[0] - 1][currentCell[1]] == 'E') {
                    int[] temp = {currentCell[0] - 1, currentCell[1]};
                    cells.push(temp);
                }
            }

            //DOWN
            if (currentCell[0] + 1 < rows) {
                if (cords[currentCell[0] + 1][currentCell[1]] == '0' || cords[currentCell[0] + 1][currentCell[1]] == 'E') {
                    int[] temp = {currentCell[0] + 1, currentCell[1]};
                    cells.push(temp);
                }
            }

            // LEFT
            if (currentCell[1] - 1 >= 0) {
                if (cords[currentCell[0]][currentCell[1] -1 ] == '0' || cords[currentCell[0]][currentCell[1] -1 ] == 'E') {
                    int[] temp = {currentCell[0], currentCell[1] -1 };
                    cells.push(temp);
                }
            }

            //RIGHT
			if (currentCell[1] + 1 < cols) {
                if (cords[currentCell[0]][currentCell[1] + 1] == '0' || cords[currentCell[0]][currentCell[1] + 1] == 'E') {
                    int[] temp = {currentCell[0], currentCell[1] + 1};
                    cells.push(temp);
                }
            }




            if (cells.isEmpty()) {
                pathFound = false;
                break;
            } else {
                currentCell = cells.pop();
            }

        }

    }

    // Complete this method; sends the solution to an output file named 'mazeSolution'
    public void sendSolutionTo(String mazeSolution) throws IOException {
        FileWriter output = new FileWriter(mazeSolution);

        if (pathFound) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    output.write(cords[i][j] + " ");
                }
                output.write("\n");
            }
        } else {
            output.write("No path exists.");
        }


        output.close();
    }


}
