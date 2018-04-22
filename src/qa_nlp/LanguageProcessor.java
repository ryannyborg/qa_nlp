package qa_nlp;

import javax.swing.JOptionPane;

public class LanguageProcessor {
	
	public String GetUserInput (String input){
//		System.out.print(input + " working...");
//		System.out.println();
		Database db = new Database();
		
		JOptionPane.showMessageDialog(null, "Parsing user input...");
		
		ParseInput(input);
		
		db.CreateSQLStatement();
		
		return "Not yet implemented";
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
