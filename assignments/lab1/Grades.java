package lab1;

/*
 * Author: Inderpreet Dhillon
 * Teacher: Leonard Manzara
 * Tutorial: MW 11:00 - 11:50
 * Purpose: This program has the ability to get a users grades for a class and provide
 * 				 statistics for that class like: average, grade distribution and overall letter grade
 */
import java.util.Scanner;

public class Grades {

	public static void main(String[] args) {
		// Scanner object for keyboard input
		Scanner keyb = new Scanner(System.in);

		// Get course name and store it
		System.out.print("Enter your course name: ");
		String courseName = keyb.nextLine();

		System.out.println("Enter your grades for " + courseName
				+ " as numbers (-1 to quit)");

		// integers to keep track of stats
		int gradeValue = 0, totalGrade = 0, numOfGrades = 0;

		// Valid values will always be larger or smaller than these values
		int maxGrade = -1, minGrade = 101;

		// Bonus, counters to keep track of number of letter grades received
		int aCount = 0, aMinusCount = 0, bPlusCount = 0, bCount = 0, bMinusCount = 0, cPlusCount = 0, cCount = 0, cMinusCount = 0, dPlusCount = 0, dCount = 0, fCount = 0;

		// Loop until the entered value is negative
		while (gradeValue >= 0) {
			// Prompt user for grade
			System.out.print("Grade " + (numOfGrades + 1) + ": ");

			// Get input as integer
			gradeValue = keyb.nextInt();

			// Grades between 0 and 100 are permitted
			if (gradeValue >= 0 && gradeValue <= 100) {
				// Add to total and increment number of grades
				totalGrade += gradeValue;
				numOfGrades++;

				// Find minimum and maximum grades
				maxGrade = ((gradeValue > maxGrade) ? gradeValue : maxGrade);
				minGrade = ((gradeValue < minGrade) ? gradeValue : minGrade);

				// For bonus, find letter grade and increment counter
				if (gradeValue >= 90) {
					aCount++;
				} else if (gradeValue >= 85) {
					aMinusCount++;
				} else if (gradeValue >= 80) {
					bPlusCount++;
				} else if (gradeValue >= 75) {
					bCount++;
				} else if (gradeValue >= 70) {
					bMinusCount++;
				} else if (gradeValue >= 65) {
					cPlusCount++;
				} else if (gradeValue >= 60) {
					cCount++;
				} else if (gradeValue >= 55) {
					cMinusCount++;
				} else if (gradeValue >= 50) {
					dPlusCount++;
				} else if (gradeValue >= 45) {
					dCount++;
				} else {
					fCount++;
				}
			} else if (gradeValue > 100) {
				// Grades over 100 are not valid
				System.out.println("Invalid grade!");
			}
		}

		// Close the scanner, we don't need it any longer
		keyb.close();

		// Only do calculations and show stats when grades have been entered
		if (numOfGrades == 0) {
			System.out.println("No grades entered");
		} else {

			// Calculate average
			double average = (double) totalGrade / numOfGrades;

			// Bonus, calculate letter grade for class based off average
			String letterGrade;
			if (average >= 90.0) {
				letterGrade = "A";
			} else if (average >= 85.0) {
				letterGrade = "A-";
			} else if (average >= 80.0) {
				letterGrade = "B+";
			} else if (average >= 75.0) {
				letterGrade = "B";
			} else if (average >= 70.0) {
				letterGrade = "B-";
			} else if (average >= 65.0) {
				letterGrade = "C+";
			} else if (average >= 60.0) {
				letterGrade = "C";
			} else if (average >= 55.0) {
				letterGrade = "C-";
			} else if (average >= 50.0) {
				letterGrade = "D+";
			} else if (average >= 45.0) {
				letterGrade = "D";
			} else {
				letterGrade = "F";
			}

			// Output statistics
			System.out.println("\nGrades for " + courseName);
			System.out.println("Total: " + numOfGrades);
			System.out.println("Sum: " + totalGrade);
			System.out.printf("Minimum: %d\n", minGrade);
			System.out.printf("Maximum: %d\n", maxGrade);
			System.out.printf("Average: %.2f\n", average);

			// Bonus marks, letter grade and distribution
			System.out.println("Letter Grade: " + letterGrade);
			System.out.println("\nGrade Distribution for " + courseName);
			System.out.println("A: " + aCount);
			System.out.println("A-: " + aMinusCount);
			System.out.println("B+: " + bPlusCount);
			System.out.println("B: " + bCount);
			System.out.println("B-: " + bMinusCount);
			System.out.println("C+: " + cPlusCount);
			System.out.println("C: " + cCount);
			System.out.println("C-: " + cMinusCount);
			System.out.println("D+: " + dPlusCount);
			System.out.println("D: " + dCount);
			System.out.println("F: " + fCount);
		}
	}
}
