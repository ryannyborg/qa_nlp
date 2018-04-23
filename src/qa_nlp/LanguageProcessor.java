package qa_nlp;

import javax.swing.JOptionPane;

public class LanguageProcessor {
	
	public String ProcessQuestion (String input){
		String answer = "";
//		System.out.print(input + " working...");
//		System.out.println();
		String apiAnswer = GetUserInput(input);
		
		if(apiAnswer == ""){
			answer = "Sorry, I can't find an answer to that one. Can you try asking in a different way?";
		} else {
			answer = apiAnswer;
		}
		
		
		return answer;
	}
	
	private String GetUserInput(String input){
		Database db = new Database();
		
		//JOptionPane.showMessageDialog(null, "Parsing user input...");
		
		ParseInput(input);
		
		String answer = db.FindAnswer();
		
		return answer;
	}
	
	private void ParseInput(String input){
		String[] words = input.split("\\s+");
		for(int i=0; i < words.length; i++){
			words[i] = words[i].replaceAll("[^\\w]", "");
		}
		
//		for(int i=0; i < words.length; i++){
//			System.out.print(words[i]);
//			System.out.println("");
//		}
		
		// assign each word a probable part of speech
		
	}
	
	
	
	

}
