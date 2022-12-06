package com.utility;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class import_csv {
	
	static List<String[]> allData;
	public Object[][] readcsv(String file)
	{
		Object[][] data = null;
	try {
		// Create an object of file reader
		// class with CSV file as a parameter.
		FileReader filereader = new FileReader(file);

		// create csvReader object and skip first Line
		CSVReader csvReader = new CSVReaderBuilder(filereader)
				.withSkipLines(1)
				.build();
		allData = csvReader.readAll();
		List e= new ArrayList();
		// print Data
		int u=0;

		data = new Object[allData.size()][1];
		System.out.println(allData.size());
		for (String[] row : allData) {
			for (String cell : row) {
				System.out.print(cell + "\t");
				e.add(cell);
			}
			data[u][0]=new ArrayList<String>(e);
			System.out.println();
			u++;
			e.clear();
		}
		return data;

	}

	catch (Exception e) {
		e.printStackTrace();
		return null;
	}

	//System.out.println(data[1][0]);
	}
}
