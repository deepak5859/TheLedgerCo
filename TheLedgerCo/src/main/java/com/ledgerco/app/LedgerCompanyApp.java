package com.ledgerco.app;

import java.util.List;

import com.ledgerco.service.LedgerService;
import com.ledgerco.util.FileUtil;

public class LedgerCompanyApp {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		LedgerService ledgerService = new LedgerService();
				
		if(args.length < 1) {
			System.out.println("Error, usage: java ClassName InputFile");
			System.exit(1);
		}
		
		List<String> listOfLines = FileUtil.getNoOFLines(args[0]);
		
		if(listOfLines != null && listOfLines.size() > 0) {
			List<String> processedOutputList = ledgerService.processInputFile(listOfLines);
			processedOutputList.stream().forEach(System.out :: println);
		} else {
			System.out.println("Error, Input File is empty!");
			System.exit(1);
		}
	}

}
