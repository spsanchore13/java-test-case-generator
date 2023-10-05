package com.masai;

import java.io.*;
import java.util.*;
import java.util.List;

public class RandomTestCaseGenerator {

    public static void main(String[] args) {
        Random rand = new Random();

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of testcases you want to generate...");

        int tc = sc.nextInt();
        sc.close();
        int numberOfTestCases = rand.nextInt(tc) + 1; // Random value for the number of test cases (1 to tc)

        try {
            for (int t = 1; t <= numberOfTestCases; t++) {
                int n = rand.nextInt(100000) + 1; // Random value for n (1 to 100)

                // Generate intervals for the new problem
                List<List<Integer>> intervals = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    int start = rand.nextInt(100000) + 1; // Random start point (1 to 10^5)
                    int end = rand.nextInt(100000 - start + 1) + start; // Random end point within constraints
                    intervals.add(Arrays.asList(start, end));
                }

                // Write input to the input file (t.in)
                BufferedWriter inputWriter = new BufferedWriter(new FileWriter(t + ".in"));
                inputWriter.write(n + "\n");

                for (List<Integer> interval : intervals) {
                    inputWriter.write(interval.get(0) + " " + interval.get(1) + "\n");
                }
                inputWriter.close();

                // Solve the problem and write the output to the output file (t.out)
                int numberOfPoints = numberOfPoints(intervals);
                BufferedWriter outputWriter = new BufferedWriter(new FileWriter(t + ".out"));
                outputWriter.write(String.valueOf(numberOfPoints));
                outputWriter.newLine();
                outputWriter.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Function to count the number of points in intervals
    public static int numberOfPoints(List<List<Integer>> intervals) {
        Set<Integer> points = new HashSet<>();

        for (List<Integer> interval : intervals) {
            int start = interval.get(0);
            int end = interval.get(1);
            for (int i = start; i <= end; i++) {
                points.add(i);
            }
        }

        return points.size();
    }
}
