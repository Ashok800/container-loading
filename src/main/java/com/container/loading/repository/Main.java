package com.container.loading.repository;

import java.util.*;
public class Main
{
    public static void main(String[] args) {

        // Define the dimensions of the container
        int containerLength = 10;
        int containerWidth = 8;
        int containerHeight = 6;

        // Create a list of boxes with their dimensions
        int[][] boxes = {{2, 3, 1}, {4, 2, 3}, {1, 5, 2}, {3, 4, 3}, {2, 2, 2},{2,3,4}};

        // Sort the boxes based on their height in descending order
        Arrays.sort(boxes, (a, b) -> Integer.compare(b[2], a[2]));


        // Initialize the container space as a 2D array
        int[][] containerSpace = new int[containerLength][containerWidth];

        // Place the boxes in the container
        for (int i = 0; i < boxes.length; i++) {
            int[] box = boxes[i];

            // Find the first available spot that can accommodate the box
            int row = -1;
            int col = -1;
            for (int j = 0; j <= containerLength - box[0]; j++) {
                for (int k = 0; k <= containerWidth - box[1]; k++) {
                    if (isSpaceAvailable(containerSpace, j, k, box)) {
                        row = j;
                        col = k;
                        break;
                    }
                }
                if (row != -1 && col != -1) {
                    break;
                }
            }

            // If a suitable spot is found, place the box there
            if (row != -1 && col != -1) {
                placeBox(containerSpace, row, col, box);
                System.out.println("Box " + (i + 1) + " placed at (" + row + "," + col + ")");
            } else {
                System.out.println("No available space for Box " + (i + 1));
            }
        }
    }

    private static boolean isSpaceAvailable(int[][] containerSpace, int row, int col, int[] box) {
        // Check if the space is empty and large enough to accommodate the box
        for (int i = row; i < row + box[0]; i++) {
            for (int j = col; j < col + box[1]; j++) {
                if (i >= containerSpace.length || j >= containerSpace[0].length || containerSpace[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void placeBox(int[][] containerSpace, int row, int col, int[] box) {
        // Mark the space as occupied by the box
        for (int i = row; i < row + box[0]; i++) {
            for (int j = col; j < col + box[1]; j++) {
                containerSpace[i][j] = box[2];
            }
        }
    }
}

