package com.ubs.opsit.interviews.wordfrequency;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class CountWords implements WordFrequency{
	
	private static Logger logger = Logger.getLogger(CountWords.class);
	static Map<String, Integer> wordHm = new ConcurrentHashMap<String, Integer>();
	
	public void readCommand()  {
		logger.info("CountWords.readCommand()");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));					
		String line = null;
		try {
			System.out.print("Please enter the words to count or 'Q' to quit: ");
			line = br.readLine();
			while (!line.equals("Q")) { 				
				if (line == null || line.trim().equals("") ) 
					System.out.println("Cannot enter empty string !");
				else {
			    		wordHm = countOccurrencesOfWordsWithin (line);
			    		for (Map.Entry m:wordHm.entrySet()) 
			    			System.out.println("\"" +m.getKey() + "\":\t" + m.getValue());					
				}				
				System.out.print("\n" + "Please enter the words to count or 'Q' to quit: ");
				line = br.readLine();
			}
		} catch (Exception  ex) {
			logger.error("CountWords.readCommand - Exception");
		}		
		logger.info("ReadCommand - Exit");	
	}
		
	
	public synchronized Map<String, Integer> countOccurrencesOfWordsWithin (String stringToEvaluate) {
		logger.info("CountWords.countOccurrencesOfWordsWithin()");		
		Scanner sc;
		sc = new Scanner(stringToEvaluate);
		sc.useDelimiter(" ");			
		String readWord = null;	
		Boolean match = false;
		int num = 0;
		while (sc.hasNext()) {   	 		
			readWord = sc.next().toLowerCase();   	
			logger.debug(" Loop: " + readWord);				
			for (Map.Entry m:wordHm.entrySet()) {    	
				logger.debug("  Search word: " +m.getKey() + "\t\t  Count: " + m.getValue());
				if(!match) {				
					if (readWord.equals(m.getKey())) { 
						match = true;	
						num = (Integer)m.getValue() + 1;
						wordHm.put(readWord.toLowerCase(), num);					
					}					
				}
			}
			if (!match)
				wordHm.put(readWord, 1);			
			match = false;
		}
		return wordHm;
	}
}