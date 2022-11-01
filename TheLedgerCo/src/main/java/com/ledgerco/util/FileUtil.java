package com.ledgerco.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {
	private static final String FILE_EXT_TXT= "TXT";
	
	public static List<String> getNoOFLines(String fileName) {
		List<String> listOfLines = new ArrayList<>();
		
		if (isValidFile(fileName)) {
			try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileName))) {
				listOfLines = bufferedReader.lines().collect(Collectors.toList());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Error, System accepts only text input file!");
			System.exit(1);
		}
		
		return listOfLines;
	}
	
	private static boolean isValidFile(String fileName) {
		fileName = fileName.toUpperCase();
		return fileName.endsWith(FILE_EXT_TXT);
	}

}
