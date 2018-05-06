package qa_nlp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JOptionPane;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

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
		
		String[][] afterPOS = null;
		int numberOfWords = 0;
		
		//JOptionPane.showMessageDialog(null, "Parsing user input...");
		
//		String[] words = {};
		
//		words = ParseInput(input);
		
		afterPOS = AssignPartsOfSpeech(input);
		
		numberOfWords = GetNumberOfTokens(input);
		
		System.out.println();
		for(int i=0;i<numberOfWords;i++){
			System.out.print("Word: " + afterPOS[i][0] + "\tPOS: " + afterPOS[i][1] + "\n");
		}
		
		String answer = db.FindAnswer(afterPOS, numberOfWords);
		
		return answer;
	}
	
//	private String[] ParseInput(String input){
//		String[] words = input.split("\\s+");
//		for(int i=0; i < words.length; i++){
//			words[i] = words[i].replaceAll("[^\\w]", "");
//		}
//		return words;
//	}
	
	private int GetNumberOfTokens(String input){
		InputStream tokenModelIn = null;
		
		try{
			String sentence = input;
	        // tokenize the sentence
	        tokenModelIn = new FileInputStream("en-token.bin");
	        TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
	        Tokenizer tokenizer = new TokenizerME(tokenModel);
	        String tokens[] = tokenizer.tokenize(sentence);
	        
	        return tokens.length;
		} catch (IOException e) {
            // Model loading failed, handle the error
            e.printStackTrace();
        }
        finally {
            if (tokenModelIn != null) {
                try {
                    tokenModelIn.close();
                }
                catch (IOException e) {
                }
            }
        }
		
		return 0; // there was an error
	}
	
	private String[][] AssignPartsOfSpeech(String input){
		
		InputStream tokenModelIn = null;
        InputStream posModelIn = null;
        String tokenWithPOS[][] = new String[30][30];
        
        try {
            String sentence = input;
            int i = 0;
            // tokenize the sentence
            tokenModelIn = new FileInputStream("en-token.bin");
            TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
            Tokenizer tokenizer = new TokenizerME(tokenModel);
            String tokens[] = tokenizer.tokenize(sentence);
 
            // Parts-Of-Speech Tagging
            // reading parts-of-speech model to a stream 
            posModelIn = new FileInputStream("en-pos-maxent.bin");
            // loading the parts-of-speech model from stream
            POSModel posModel = new POSModel(posModelIn);
            // initializing the parts-of-speech tagger with model 
            POSTaggerME posTagger = new POSTaggerME(posModel);
            // Tagger tagging the tokens
            String tags[] = posTagger.tag(tokens);
            // Getting the probabilities of the tags given to the tokens
//            double probs[] = posTagger.probs();
            
            //System.out.println("Token\t:\tTag\t:\tProbability\n---------------------------------------------");
            while(i < tokens.length){
            	for(int j=0;j<tags.length;j++){
            		tokenWithPOS[i][0] = tokens[i];
            		tokenWithPOS[i][1] = tags[j];
            		i++;
            	}
//                System.out.println(tokens[i]+"\t:\t"+tags[i]);
            }
        
        }
        catch (IOException e) {
            // Model loading failed, handle the error
            e.printStackTrace();
        }
        finally {
            if (tokenModelIn != null) {
                try {
                    tokenModelIn.close();
                }
                catch (IOException e) {
                }
            }
            if (posModelIn != null) {
                try {
                    posModelIn.close();
                }
                catch (IOException e) {
                }
            }
        }
        
        return tokenWithPOS;
	}
	
	
//	private void 
		
		
}