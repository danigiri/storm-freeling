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


package cat.tv3.eng.rec.fl.analyzed.sentence;


import java.util.ArrayList;


/**
 * A Object that manage the output of freeling.
 *
 * This Class supports the following outputs from freeling:
 * 		- TOKEN
 * 		- SPLITTED
 * 		- MORFO
 * 		- TAGGED 
 * 
 * @author jaume jané
 */


public class FlAnalyzedSentence {
	private ArrayList<String> originalTokens;
	private ArrayList<ArrayList<String>> morfologicTokens;
	private ArrayList<ArrayList<String>> poSTaggedTokens;
	private ArrayList<ArrayList<Double>> probsMorfologicTokens;
	private static int TOKEN_ORIGINAL = 0;
	private static int TOKEN_MORFOLOGIC = 1;
	private static int TOKEN_TAGGED = 2;
	private static int TOKEN_PROBS = 0;
	
	
	
	public FlAnalyzedSentence(String outputFreeling){
		originalTokens = new ArrayList<String>();
		morfologicTokens = new ArrayList<ArrayList<String>>();
		poSTaggedTokens = new ArrayList<ArrayList<String>>();
		probsMorfologicTokens = new ArrayList<ArrayList<Double>>();
		
		String[] lines = outputFreeling.split("\n");
		for(int i=0; i<lines.length;++i){				
			String[] tokens = lines[i].split(" ");
			ArrayList<String> line_MorfologicTokens = new ArrayList<String>();
			ArrayList<String> line_PoSTaggedTokens = new ArrayList<String>();
			ArrayList<Double> line_ProbsMorfologicTokens = new ArrayList<Double>();
			for(int j=0; j<tokens.length; ++j){				
				if(j==TOKEN_ORIGINAL) originalTokens.add(tokens[j]);
				else if(j%3==TOKEN_MORFOLOGIC) line_MorfologicTokens.add(tokens[j]);
				else if(j%3==TOKEN_TAGGED) line_PoSTaggedTokens.add(tokens[j]);
				else if(j%3==TOKEN_PROBS) line_ProbsMorfologicTokens.add(Double.parseDouble(tokens[j]));
			}
			morfologicTokens.add(line_MorfologicTokens);
			poSTaggedTokens.add(line_PoSTaggedTokens);
			probsMorfologicTokens.add(line_ProbsMorfologicTokens);

		}
	}
	
	


	public ArrayList<String> getOriginalTokens() {
		return originalTokens;
	}




	public void setOriginalTokens(ArrayList<String> originalTokens) {
		this.originalTokens = originalTokens;
	}




	public ArrayList<ArrayList<String>> getMorfologicTokens() {
		return morfologicTokens;
	}




	public void setMorfologicTokens(ArrayList<ArrayList<String>> morfologicTokens) {
		this.morfologicTokens = morfologicTokens;
	}




	public ArrayList<ArrayList<String>> getPoSTaggedTokens() {
		return poSTaggedTokens;
	}




	public void setPoSTaggedTokens(ArrayList<ArrayList<String>> poSTaggedTokens) {
		this.poSTaggedTokens = poSTaggedTokens;
	}




	public ArrayList<ArrayList<Double>> getProbsMorfologicTokens() {
		return probsMorfologicTokens;
	}




	public void setProbsMorfologicTokens(
			ArrayList<ArrayList<Double>> probsMorfologicTokens) {
		this.probsMorfologicTokens = probsMorfologicTokens;
	}




	public String getOriginalText(){
		try{
			String result = "";
			for(int i=0; i<originalTokens.size();++i){
				if(i==0) result=originalTokens.get(i);
				else{
					result+=" "+ originalTokens.get(i);
				}
			}		
			return result;
		}catch (IndexOutOfBoundsException e ) {
			return "Not";
				
      	}
	}
	
	public String getMorfologicText(){
		try{
			String result = "";
			for(int i=0; i<morfologicTokens.size();++i){
				if(i==0) result=morfologicTokens.get(i).get(0);			
				else{
					result+=" "+morfologicTokens.get(i).get(0);
				}
			}	
			return result;
		}catch (IndexOutOfBoundsException e ) {
			return "Not";
				
      	}
		
	}
	
	public String getPoSTaggedText(){
		try{
			String result = "";
			for(int i=0; i<poSTaggedTokens.size();++i){
				if(i==0) result=poSTaggedTokens.get(i).get(0);
				else{
					result+=" "+ poSTaggedTokens.get(i).get(0);
				}
			}		
			return result;
		}catch (IndexOutOfBoundsException e ) {
			return "Not";
				
      	}
		
	}
	
	public String getProbsMorfologicText(){
		try{
			String result = "";
			for(int i=0; i<probsMorfologicTokens.size();++i){
				if(i==0) result=probsMorfologicTokens.get(i).get(0).toString();
				else{
					result+=" "+ probsMorfologicTokens.get(i).get(0).toString();
				}
			}		
			return result;
		}catch (IndexOutOfBoundsException e ) {
			return "Not";
				
      	}
	}
	
}
