package qa_nlp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class Database {

	public String FindAnswer(String[][] afterPOS, int numberOfWords){
		
		//ConvertToQuery(afterPOS, numberOfWords);
		
		CreateSemanticNetwork();
		
		String apiResponse = FetchFromAPI();
		
		String answer = CreateAnswer(apiResponse);
		
		System.out.print(answer);
		
		
		return answer;
	}
	
	private void CreateSemanticNetwork(){
		SemanticNetwork sn = new SemanticNetwork();
		
		// add all nodes for major league baseball (MLB) teams
		sn.addNode("MLB");
		sn.addNode("Arizona Diamondbacks");
		sn.addNode("Atlanta Braves");
		sn.addNode("Baltimore Orioles");
		sn.addNode("Boston Red Sox");
		sn.addNode("Chicago Cubs");
		sn.addNode("Chicago White Sox");
		sn.addNode("Cincinnati Reds");
		sn.addNode("Cleveland Indians");
		sn.addNode("Colorado Rockies");
		sn.addNode("Detroit Tigers");
		sn.addNode("Miami Marlins");
		sn.addNode("Houston Astros");
		sn.addNode("Kansas City Royals");
		sn.addNode("Los Angeles Angels of Anaheim");
		sn.addNode("Los Angeles Dodgers");
		sn.addNode("Milwaukee Brewers");
		sn.addNode("Minnesota Twins");
		sn.addNode("New York Mets");
		sn.addNode("New York Yankees");
		sn.addNode("Oakland Athletics");
		sn.addNode("Philadelphia Phillies");
		sn.addNode("Pittsburgh Pirates");
		sn.addNode("St. Louis Cardinals");
		sn.addNode("San Diego Padres");
		sn.addNode("San Francisco Giants");
		sn.addNode("Seattle Mariners");
		sn.addNode("Tampa Bay Rays");
		sn.addNode("Texas Rangers");
		sn.addNode("Toronto Blue Jays");
		sn.addNode("Washington Nationals");
		
		sn.addEdge("MLB", "Arizona Diamondbacks");
		sn.addEdge("MLB", "Atlanta Braves");
		sn.addEdge("MLB", "Baltimore Orioles");
		sn.addEdge("MLB", "Boston Red Sox");
		sn.addEdge("MLB", "Chicago Cubs");
		sn.addEdge("MLB", "Chicago White Sox");
		sn.addEdge("MLB", "Cincinnati Reds");
		sn.addEdge("MLB", "Cleveland Indians");
		sn.addEdge("MLB", "Colorado Rockies");
		sn.addEdge("MLB", "Detroit Tigers");
		sn.addEdge("MLB", "Miami Marlins");
		sn.addEdge("MLB", "Houston Astros");
		sn.addEdge("MLB", "Kansas City Royals");
		sn.addEdge("MLB", "Los Angeles Angels of Anaheim");
		sn.addEdge("MLB", "Los Angeles Dodgers");
		sn.addEdge("MLB", "Milwaukee Brewers");
		sn.addEdge("MLB", "Minnesota Twins");
		sn.addEdge("MLB", "New York Mets");
		sn.addEdge("MLB", "New York Yankees");
		sn.addEdge("MLB", "Oakland Athletics");
		sn.addEdge("MLB", "Philadelphia Phillies");
		sn.addEdge("MLB", "Pittsburgh Pirates");
		sn.addEdge("MLB", "St. Louis Cardinals");
		sn.addEdge("MLB", "San Diego Padres");
		sn.addEdge("MLB", "San Francisco Giants");
		sn.addEdge("MLB", "Seattle Mariners");
		sn.addEdge("MLB", "Tampa Bay Rays");
		sn.addEdge("MLB", "Texas Rangers");
		sn.addEdge("MLB", "Toronto Blue Jays");
		sn.addEdge("MLB", "Washington Nationals");
		
		System.out.print(sn.getNeighbors("Arizona Diamondbacks").copy().toString());
		
//		System.out.print(sn.edges.toString());
//		System.out.println();
//		System.out.print(sn.nodes.toString());
//		System.out.println();
	}
	
	private void ConvertToQuery(String[][] afterPOS, int numberOfWords){
		
		String[] keywords = {};
		
		for(int i=0; i < numberOfWords; i++){
			
			if(afterPOS[i][1] == "WP" || afterPOS[i][1] == "WRB"){
				// checks for the type of question
				switch(afterPOS[i][0]){
					case "Who":
						// looking for a person/team
						break;
					case "What":
						// looking for a score; ex: What was the score of the Diamondbacks game?
						break;
					case "Where":
						// return a location, could be an arena or stadium
						break;
					case "Why":
						// not answerable, usually involves analysis
						break;
					case "When":
						// looking for a time/date something happened on
						break;
					case "How":
						// usually in the form of "how many" looking for a quantity
						break;
				}
			} else if(afterPOS[i][1] == "NN" || afterPOS[i][1] == "NNS" || afterPOS[i][1] == "NNP"
						|| afterPOS[i][1] == "NNP"){
				// checks for important nouns
				
				
			}
			
			
		}
		
		
	}
	
	private String FetchFromAPI(){
		try {
			
			// url needs to be created based on what the user's question is
			
            URL url = new URL ("https://api.mysportsfeeds.com/v1.2/pull/nhl/2018-playoff/scoreboard.json?fordate=20180418");
            
            String username = "rnyborg";
            String password = "ece466qa";
            
            String stringToEncode = username + ":" + password;
            
            // need to enter username and password based off of the MySportsFeeds account
            String encoding = Base64.getEncoder().encodeToString(stringToEncode.getBytes());
            
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in = 
                new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            return line;
        } catch(Exception e) {
        	String error = "Could not fetch sports data. Please check your Username and Password or Internet connection.";
        	return error;
            //System.out.print("Could not fetch sports data. Please check your Internet connection.");
            
        }
	}
	
	private String CreateAnswer(String apiResponse){
		
		return apiResponse;
	}
	
}
