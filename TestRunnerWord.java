package test;

import com.ubs.opsit.interviews.wordfrequency.*;
import org.apache.log4j.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunnerWord {
	private static Logger logger = Logger.getLogger(TestRunnerWord.class);

	public static void main(String[] args) {
    		logger.info("TestRunnerWord.main()");
    		Result result = JUnitCore.runClasses(WordFrequencyTest.class);
		    for (Failure failure : result.getFailures()) {
		        System.out.println(failure.toString());
		    }
		    System.out.println("Result=="+result.wasSuccessful());
	  }
}
