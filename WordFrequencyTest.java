package com.ubs.opsit.interviews.wordfrequency;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;


public class WordFrequencyTest {
	private static Logger logger = Logger.getLogger(WordFrequencyTest.class);
	private CountWords cw = new CountWords();
	Map<String, Integer> wordHm = new ConcurrentHashMap<String, Integer>();
	
	public static void main(String[] args)  {
		logger.info("WordFrequencyTest.main()");
		CountWords cw = new CountWords();	
		cw.readCommand();		
	}
	
	@Before 
	public void init() {
		cw.wordHm = new ConcurrentHashMap<String, Integer>();
		wordHm = new ConcurrentHashMap<String, Integer>();
	}
	
    @Test
    public void testcase_1() throws IOException {
    	logger.info("WordFrequencyTest.testcase_1()");
		
		wordHm.put("the", 2);
		wordHm.put("man", 1);
		wordHm.put("in", 1);
		wordHm.put("moon", 1);
    	Assert.assertEquals(wordHm, cw.countOccurrencesOfWordsWithin("the man in the moon"));
    	
		wordHm.put("the", 4);
		wordHm.put("man", 2);
		wordHm.put("in", 1);
		wordHm.put("on", 1);
		wordHm.put("moon", 2);
	   	Assert.assertEquals(wordHm, cw.countOccurrencesOfWordsWithin("the man on the moon"));
    }  	
	
    @Test
    public void testcase_2() throws IOException {
    	logger.info("WordFrequencyTest.testcase_2()");
    	Assert.assertEquals(wordHm, cw.countOccurrencesOfWordsWithin(""));
    }
    
    @Test
    public void testcase_3() throws IOException {
    	logger.info("WordFrequencyTest.testcase_3()");
		
		wordHm.put("dog", 2);
		wordHm.put("cat", 1);	
    	Assert.assertEquals(wordHm, cw.countOccurrencesOfWordsWithin("dog cat dog"));
    	
		wordHm.put("cat", 2);	
		wordHm.put("dog", 3);
		wordHm.put("bird", 1);
	   	Assert.assertEquals(wordHm, cw.countOccurrencesOfWordsWithin("BIRD CAT DOG"));
    }  
    
    @Test
    public void testcase_4() throws IOException {
    	logger.info("WordFrequencyTest.testcase_4()");
		
		wordHm.put("star", 1);
		wordHm.put("moon", 1);	
		wordHm.put("sun", 1);	
    	Assert.assertEquals(wordHm, cw.countOccurrencesOfWordsWithin("moOn sUn STaR"));
    	
		wordHm.put("moon", 3);	
		wordHm.put("sun", 1);
		wordHm.put("star", 2);
	   	Assert.assertEquals(wordHm, cw.countOccurrencesOfWordsWithin("stAr MOON moON"));
    }     
    
}
