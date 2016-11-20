package com.rs_search_relevancy.test_search.similar_search;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetCategoryCountData {

	public String getFilePath(String testName, String locale, String env, String folder, String fileName) {
		return String.format("%s//%s//%s//%s//%s", testName, locale, env, folder, fileName);
	}

	public ArrayList<String> parseCsvAsList(String filename, String delimeter) {
		// Initialise new Array variable to store CSV information
		ArrayList<String> dataFromFile = new ArrayList<String>();
		try {

			// Scan file data using configured delimeter
			Scanner scanner = new Scanner(new FileReader("D://test_run_data//" + filename + ".csv"));
			scanner.useDelimiter(delimeter);

			// Run until data is completes
			while (scanner.hasNext()) {
				// Save next line to a string
				String dataInRow = scanner.nextLine();

				// Add array list to initiailised array list
				dataFromFile.add(dataInRow);
			}

			// Close the scanner once data is complete
			scanner.close();

		} catch (FileNotFoundException e) {

			// Print stack trace if error is found
			e.printStackTrace();
		}

		// Return data from the file

		return dataFromFile;
	}

	public List<Object[]> parseCsv(String filename, String delimiter) {
		// Initialise new Array variable to store CSV information
		List<Object[]> dataFromFile = new ArrayList<Object[]>();
		try {

			// Scan file data using parameterised delimiter
			Scanner scanner = new Scanner(
					new FileReader("D://test_run_data//" + filename + ".csv"));
			scanner.useDelimiter(delimiter);

			// Run until data is complete
			while (scanner.hasNext()) {
				// Save next line to a string
				String dataInRow = scanner.nextLine();

				// Split the String based on the configured Delimiter
				String[] dataInRowArray = dataInRow.split(delimiter);

				// Save the result as an Object array
				Object[] dataInRowObjArray = dataInRowArray;

				// Add Object array to initialised array list
				dataFromFile.add(dataInRowObjArray);
			}
			// Close the scanner once data is complete
			scanner.close();

		} catch (FileNotFoundException e) {

			// Print stack trace if error is found
			e.printStackTrace();
		}

		// Return data from the file as a list of Object arrays
		return dataFromFile;
	}
}
