package com.fileio.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Writer {
	public static List<String> errorReport = new ArrayList<>();

	public void setErrorReport(List<String> errorReport) {
		this.errorReport = errorReport;
	}

	public void writeFile3() throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter("filerepo/report.txt"));

		// for (int i = 0; i < 10; i++) {
		// pw.write("something");
		// }
		ListIterator<String> itr =errorReport.listIterator();
		while(itr.hasNext()) {
			pw.write(itr.next()+ "\n");
			
		}

		pw.close();
	}

	public void generateReport() {
		String FILENAME = "report.txt";
		// FileWriter fw;
		// try {
		// fw = new FileWriter(FILENAME, true);
		// BufferedWriter bw = new BufferedWriter(fw);
		// String content = "This is the content to write into file\n";
		// bw.write(content);
		// // bw.close();
		// System.out.println("Done");
		//
		// } catch (IOException e) {
		//
		// e.printStackTrace();
		//
		// }

		try {
			String fw = "filerepo/report.txt";
			BufferedWriter out = new BufferedWriter(new FileWriter(fw, true));
			out.write("aString");
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	
	}

}
