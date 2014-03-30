/**
Copyright 2014 Jaume Jané 

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package cat.tv3.eng.rec.analyzed.fl.sentence.test;


import java.io.InputStream;
import java.util.Scanner;

import cat.tv3.eng.rec.analyzed.fl.sentence.AnalyzedFLSentence;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;


public class AnalyzedFLSentenceTest {
	
	private final static String FREELING_OUTPUT = "freeling_out.txt";
	private static String outputFreeling;
	private static AnalyzedFLSentence analizedFreelingSentence;
	
	@BeforeClass
	public static void prepareFile(){
		outputFreeling = "";
		InputStream is = AnalyzedFLSentence.class.getClassLoader().getResourceAsStream(FREELING_OUTPUT);
		outputFreeling = new Scanner(is,"UTF-8").useDelimiter("\\A").next();
		
		analizedFreelingSentence = new AnalyzedFLSentence(outputFreeling);		
		
	}
	
	@Test
	public void testOriginalText() throws InterruptedException {				
		assertEquals("A test method is a definitive procedure that produces a test result", analizedFreelingSentence.getOriginalText());	
	}
	
	@Test
	public void  testMorfologicText() throws InterruptedException {			
		assertEquals("1 test method be 1 definitive procedure that produce 1 test result", analizedFreelingSentence.getMorfologicText());	
	}
	
	@Test
	public void  testPoSTaggedText() throws InterruptedException {			
		assertEquals("Z NN NN VBZ Z JJ NN WDT VBZ Z NN NN", analizedFreelingSentence.getPoSTaggedText());	
	}
	
	@Test
	public void  testgetProbsMorfologicText() throws InterruptedException {			
		assertEquals("0.99998 0.918129 1.0 1.0 0.99998 1.0 1.0 0.244662 1.0 0.99998 0.918129 0.843311", analizedFreelingSentence.getProbsMorfologicText());	
	}
	
	


	
	
}
