package com.fileio.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import com.fileio.domain.Employee;

public class FileParsingService {
	// File fileResource;
	FileReader inputFile;

	List<String> reportError = new ArrayList<>();

	public List<Employee> parse(String file) {
		// fileResource= new File(file);
		try {
			inputFile = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// System.out.println(fileResource.getName());

		return null;
	}

	public List<String> readLinesFromFile() {
		List<String> linesFromFile = new ArrayList<>();
		try {

			String line;
			BufferedReader br = new BufferedReader(inputFile);

			while ((line = br.readLine()) != null) {
				linesFromFile.add(line);
			}

			return linesFromFile;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void displayFileData() {
		for (String check : readLinesFromFile()) {
			System.out.println(check);
		}
	}

	public void validation() {
		List<String> dataFromFile = readLinesFromFile();

		ListIterator<String> itr = dataFromFile.listIterator();
		while (itr.hasNext()) {
			int rowNo = itr.nextIndex();
			String lineToCheck = itr.next();
			// if (isCheckIdPresence(lineToCheck, rowNo) && isCheckIdFormat(lineToCheck,
			// rowNo+1)
			// && isCheckFirstNamePresence(lineToCheck, rowNo+1)
			// && isCheckFirstNameCharacterLength(lineToCheck, rowNo+1) &&
			// isCheckFirstNameFormat(lineToCheck, rowNo+1)
			// && isCheckLastNamePresence(lineToCheck, rowNo+1) &&
			// isCheckLastNameCharacterLength(lineToCheck, rowNo+1)
			// && isCheckLastNameFormat(lineToCheck, rowNo+1) && isCheckAge(lineToCheck,
			// rowNo+1)
			// && isCheckAgeFormat(lineToCheck, rowNo+1) || isCheckSalary(lineToCheck,
			// rowNo+1)
			// && isCheckSalaryFormat(lineToCheck, rowNo+1)) {
			// //System.out.println("No errors ");
			// }

			isCheckIdPresence(lineToCheck, rowNo);
			isCheckIdFormat(lineToCheck, rowNo + 1);
			isCheckFirstNamePresence(lineToCheck, rowNo + 1);
			isCheckFirstNameCharacterLength(lineToCheck, rowNo + 1);
			isCheckFirstNameFormat(lineToCheck, rowNo + 1);
			isCheckLastNamePresence(lineToCheck, rowNo + 1);
			isCheckLastNameCharacterLength(lineToCheck, rowNo + 1);
			isCheckLastNameFormat(lineToCheck, rowNo + 1);
			isCheckAge(lineToCheck, rowNo + 1);
			isCheckAgeFormat(lineToCheck, rowNo + 1);
			isCheckSalary(lineToCheck, rowNo + 1);
			isCheckSalaryFormat(lineToCheck, rowNo + 1);

		}

		printErrorReport();
		generateErrorReport();

	}

	public void generateErrorReport() {
		Writer writeReport = new Writer();
		writeReport.setErrorReport(reportError);
		// writeReport.generateReport();
		try {
			writeReport.writeFile3();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void printErrorReport() {
		for (String error : reportError) {
			System.out.println(error);
		}
	}

	public boolean isCheckSpecialCharacter(String line, int rowNo) {
		// String line ="ag|b|c|d";
		List<String> listString = new ArrayList<>();
		String[] lineArray = line.split("\\|");
		System.out.println(lineArray.length);
		for (int i = 0; i < lineArray.length; i++) {
			System.out.println(lineArray[i]);
		}

		// System.out.println(Arrays.toString(lineArray));
		listString = Arrays.asList(lineArray);
		if ((listString.get(1)).isEmpty()) {
			System.out.println("buls eye");
		}
		// System.out.println(listString.get(1).toCharArray());
		return false;
	}

	public boolean isCheckIdPresence(String line, int rowNo) {

		if (line.substring(0, 1).matches("[0-9]")) {
			return true;
		} else {
			reportError.add("Row  " + rowNo + " Id is mandatory");
			return false;
		}
	}

	private List<String> splitIt(String line) {
		String[] lineArray = line.split("\\|");
		List<String> listString = new ArrayList<>();
		listString = Arrays.asList(lineArray);
		return listString;
	}

	public boolean isCheckIdFormat(String line, int rowNo) {
		List<String> listString = splitIt(line);
		String id = listString.get(0);
		if (isParsableAsInteger(id)) {
			return true;
		} else {
			reportError.add("Row  " + rowNo + " Id format is wrong");
			return false;
		}
	}

	public boolean isCheckFirstNamePresence(String line, int rowNo) {
		List<String> listString = splitIt(line);
		String checkFirstName = listString.get(1);
		if (checkFirstName.isEmpty()) {

			reportError.add("Row  " + rowNo + " First name  is mandatory");
			return false;
		}
		return true;
	}

	public boolean isCheckFirstNameCharacterLength(String line, int rowNo) {
		List<String> listString = splitIt(line);
		String checkFirstName = listString.get(1);
		if (checkFirstName.length() < 9) {
			return true;
		}
		reportError.add("Row  " + rowNo + " First name  character is less than 8 ");
		return false;
	}

	public boolean isCheckFirstNameFormat(String line, int rowNo) {
		List<String> listString = splitIt(line);
		String checkFirstName = listString.get(1);
		if (checkFirstName.substring(0, 1).matches("[a-zA-Z]")) {
			return true;
		}
		reportError.add("Row  " + rowNo + " First name format is not correct");
		return false;
	}

	public boolean isCheckLastNamePresence(String line, int rowNo) {
		List<String> listString = splitIt(line);
		String checkLastName = listString.get(2);
		if (checkLastName.isEmpty()) {
			reportError.add("Row  " + rowNo + " Last name  is mandatory");
			return false;
		}
		return true;
	}

	public boolean isCheckLastNameCharacterLength(String line, int rowNo) {
		List<String> listString = splitIt(line);
		String checkLastName = listString.get(2);
		if (checkLastName.length() < 9) {
			return true;
		}
		reportError.add("Row  " + rowNo + " Last name  character is less than 8 ");
		return false;
	}

	public boolean isCheckLastNameFormat(String line, int rowNo) {
		List<String> listString = splitIt(line);
		String checkLastName = listString.get(2);
		if (checkLastName.substring(0, 1).matches("[a-zA-Z]")) {
			return true;
		}

		reportError.add("Row  " + rowNo + " First name format is not correct");
		return false;
	}

	public boolean isCheckAge(String line, int rowNo) {
		List<String> listString = splitIt(line);
		String age = listString.get(3);
		if (age.isEmpty()) {
			reportError.add("Row  " + rowNo + " Age  is mandatory");
			return false;
		} else {
			return true;
		}
	}

	public boolean isCheckAgeFormat(String line, int rowNo) {
		List<String> listString = splitIt(line);
		String age = listString.get(3);
		if (isParsableAsInteger(age)) {
			return true;
		} else {
			reportError.add("Row  " + rowNo + " Age format is wrong");
			return false;
		}

	}

	public boolean isCheckSalary(String line, int rowNo) {
		List<String> listString = splitIt(line);
		String salary = listString.get(4);
		return isParsableAsInteger(salary);
	}

	public boolean isCheckSalaryFormat(String line, int rowNo) {

		List<String> listString = splitIt(line);
		String salary = listString.get(4);
		// Integer.parseInt(salary);
		if (isParsableAsInteger(salary) || isParsableAsDouble(salary)) {
			return true;
		}
		reportError.add("Row  " + rowNo + " Salary  format is wrong");

		return false;

	}

	private static boolean isParsableAsDouble(final String s) {
		try {
			// Double.parseDouble(s);
			Double.valueOf(s);
			return true;
		} catch (NumberFormatException numberFormatException) {
			return false;
		}
	}

	private static boolean isParsableAsInteger(final String s) {
		try {
			// Integer.parseInt(s);
			Integer.valueOf(s);
			return true;
		} catch (NumberFormatException numberFormatException) {
			return false;
		}
	}

}
