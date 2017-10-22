package com.fileio.testcase;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fileio.service.FileParsingService;

public class TestFileParsingService {
	FileParsingService fileParsing;
	
	@Before
	public void beforeTest() {
		fileParsing = new FileParsingService();
	}
	
	@Test
	public void testIsCheckIdPresence() {
		assertTrue(fileParsing.isCheckIdPresence("123|sushan|maharjan|25|15000.0", 1));
		
	}
	@Test
	public void testIsCheckIdFormat() {
		assertFalse(fileParsing.isCheckIdFormat("f123|sushan|maharjan|25|15000.0",2));
	}
	
	@Test
	public void testIsCheckFirstName() {
		assertTrue(fileParsing.isCheckFirstNamePresence("123|sushan|maharjan|25|15000.0", 1));
		assertFalse(fileParsing.isCheckFirstNameFormat("123|2sushan|maharjan|25|15000.0", 1));
		assertTrue(fileParsing.isCheckFirstNameCharacterLength("123|sushan", 1));
		
	}
	
	@Test
	public void testIsCheckLastName() {
		assertTrue(fileParsing.isCheckLastNamePresence("123|sushan|maharjan|25|15000.0", 1));
		assertFalse(fileParsing.isCheckLastNameFormat("123|2sushan|2maharjan|25|15000.0", 1));
		assertTrue(fileParsing.isCheckLastNameCharacterLength("123|sushan|maharjan", 1));
		
	}
	@Test
	public void testAge() {
		assertTrue(fileParsing.isCheckAge("123|sushan|maharjan|25|15000.0", 1));
		assertFalse(fileParsing.isCheckAge("123|sushan|maharjan||15000.0", 1));
		assertTrue(fileParsing.isCheckAgeFormat("123|sushan|maharjan|25|15000.0", 1));
		assertFalse(fileParsing.isCheckAgeFormat("123|sushan|maharjan|g25|15000.0", 1));
	}
	@Test
	public void testSalary() {
		assertTrue(fileParsing.isCheckSalaryFormat("123|sushan|maharjan|25|15000.0", 1));
		assertFalse(fileParsing.isCheckSalaryFormat("123|sushan|maharjan|25|1asdf5000.0", 1));
		
		
	}
	
}
