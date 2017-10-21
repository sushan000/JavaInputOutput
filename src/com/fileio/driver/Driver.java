package com.fileio.driver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fileio.service.FileParsingService;

public class Driver {
	public static void main(String[] args) {
		FileParsingService fileService = new FileParsingService();

		// File f = new File("filerepo/employee.txt");
		// System.out.println(f.getAbsolutePath());
		// System.out.println(f.getName());
		
		fileService.parse("filerepo/employee.txt");
		//fileService.displayFileData();
//		if(fileService.isCheckIdPresence("t998|ewre|wer")){
//			System.out.println("true ");
//		}else
//		{
//			System.out.println("false");
//		}
		//System.out.println(fileService.isCheckIdFormat("12s3|asfddfde|asd||1234.0g"));
		//generateReport();
		fileService.validation();
		fileService.generateErrorReport();
	}
	
}
