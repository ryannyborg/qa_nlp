package qa_nlp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Objects;

import org.json.*;


public class Database {

	public String FindAnswer(String[][] afterPOS, int numberOfWords, String dbUsername, String dbPassword){
		
		SemanticNetwork sn = new SemanticNetwork();
		
		String league = "mlb"; // default to baseball for right now
		String season_name = "current"; // current is default because we only want live season data right now
		String date = getCurrentDate();
		
		sn = LoadSemanticNetwork(sn);
		ConvertToQuery(afterPOS, numberOfWords, sn);
		SaveSemanticNetwork(sn);
		String apiResponse = FetchFromAPI(dbUsername, dbPassword, league, season_name, date);
		String answer = CreateAnswer(apiResponse);
		
		System.out.print(answer);
		return answer;
	}
	
	public void ResetSemanticNetwork(){
		SemanticNetwork sn = new SemanticNetwork();
		
		CreateSemanticNetwork(sn);
		
		SaveSemanticNetwork(sn);
	}
	
	private SemanticNetwork CreateSemanticNetwork(SemanticNetwork sn){
		
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
		
		return sn;
		
//		System.out.print(sn.edges.contains(o));
		
//		System.out.print(sn.edges.toString());
//		System.out.println();
//		System.out.print(sn.nodes.toString());
//		System.out.println();
	}
	
	private void ConvertToQuery(String[][] afterPOS, int numberOfWords, SemanticNetwork sn){
		
		//String[] keywords = {};
		
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
						|| afterPOS[i][1] == "NNPS"){
				// checks for important nouns
				switch(afterPOS[i][0]){
					case "score":
						// selects the scoreboard API
						break;
				}
				
			} else if(afterPOS[i][1] == "NNP"){
				// check the semantic network for this proper noun
				//sn.nodes.
				
				
			}
			
			
		}
		
		
	}
	
	private SemanticNetwork LoadSemanticNetwork(SemanticNetwork sn){
		// The name of the file to open.
        String nodeFile = "SemanticNetworkNodes.txt";
        String edgeFile = "SemanticNetworkEdges.txt";
        
        String fnode = "";
        String tnode = "";
        
        char[] lineInChars = {};

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader nodeFileReader = new FileReader(nodeFile);
            FileReader edgeFileReader = new FileReader(edgeFile);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReaderNode = new BufferedReader(nodeFileReader);
            BufferedReader bufferedReaderEdge = new BufferedReader(edgeFileReader);
            
            int indexEndfnode = 0;
            int indexStarttnode = 0;

            while((line = bufferedReaderNode.readLine()) != null) {
                sn.addNode(line);
            }   
            
            while((line = bufferedReaderEdge.readLine()) != null){
            	lineInChars = line.toCharArray();
            	for(int i=0; i < lineInChars.length; i++){
            		if(lineInChars[i] == '<'){
            			// get the index of that char
            			indexEndfnode = i-1;
            		} else if(lineInChars[i] == '>'){
            			indexStarttnode = i+1;
            		}
            	}
            	
            	for(int i=0; i < lineInChars.length; i++){
            		while(i < indexEndfnode){
            			fnode += String.valueOf(lineInChars[i]);
            			i++;
            		}
            		while(i > indexStarttnode){
            			tnode += String.valueOf(lineInChars[i]);
            			i++;
            			if(i == lineInChars.length){
            				break;
            			}
            		}
            	}
            	// add this edge
        		sn.addEdge(fnode, tnode);
        		fnode = "";
        		tnode = "";
            }

            // Always close files.
            bufferedReaderNode.close();
            bufferedReaderEdge.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to load Semantic Network.");                
        }
        catch(IOException ex) {
            System.out.println("Unable to load Semantic Network.");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        
        return sn;
	}
	
	private void SaveSemanticNetwork(SemanticNetwork sn){
		// saves all nodes in semantic network to text file
		PrintStream nodesFile = null;
		PrintStream edgesFile = null;
		
		try {
			nodesFile = new PrintStream(new FileOutputStream("SemanticNetworkNodes.txt"));
			edgesFile = new PrintStream(new FileOutputStream("SemanticNetworkEdges.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("Could not save semantic network.");
		}
		
		Object[] nodes = sn.nodes.toArray();
		Object[] edges = sn.edges.toArray();
		String nodesToTextFile = "";
		String edgesToTextFile = "";
		
		for(int i=0; i < nodes.length; i++){
			nodesToTextFile += nodes[i].toString();
			nodesToTextFile += System.lineSeparator();
		}
		
		for(int i=0; i < edges.length; i++){
			edgesToTextFile += edges[i].toString();
			edgesToTextFile += System.lineSeparator();
		}

		nodesFile.print(nodesToTextFile);
		edgesFile.print(edgesToTextFile);
		
		System.setOut(nodesFile);
		System.setOut(edgesFile);
		
		nodesFile.close();
		edgesFile.close();
	}
	
	private String FetchFromAPI(String dbUsername, String dbPassword, String league, String season_name, String date){
		try {
			
			// url needs to be created based on what the user's question is
			
			/* Sample HTTPS request */
			// https://api.mysportsfeeds.com/v1.2/pull/mlb/{season-name}/scoreboard.{format}?fordate={for-date}
			// {season-name} can be defaulted to current 
			// {format} 
			
//			String urlString = "https://api.mysportsfeeds.com/v1.2/pull/mlb/2018-regular/scoreboard.json?fordate=20180403";
			String urlString = "https://api.mysportsfeeds.com/v1.2/pull/" + league + "/" + season_name + "/scoreboard.json?fordate=" + date;
			
            URL url = new URL(urlString);
            
            System.out.print(urlString);
            
            String username = dbUsername;
            String password = dbPassword;
            
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
            String outputOfAPI = "";
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                outputOfAPI += line;
            }
            return outputOfAPI;
        } catch(Exception e) {
        	String error = "Could not fetch sports data. Please check your Username and Password or Internet connection.";
        	return error;
            //System.out.print("Could not fetch sports data. Please check your Internet connection.");
            
        }
	}
	
	private String CreateAnswer(String apiResponse){
		
		// this function takes json response and turns it into a natural language response
		
		JSONObject obj = new JSONObject(apiResponse);
		
		String answer = "";
		
		String awayTeam = "";
		String homeTeam = "";
		
		String teamLookingFor = "Arizona";
		int gameFoundIndex = -1;
		
		JSONArray gameArray = obj.getJSONObject("scoreboard").getJSONArray("gameScore");
		
		// stays like this unless team was found
		answer = "Sorry, the team you specified isn't playing today.";
		
		for(int i=0; i < gameArray.length(); i++){
			awayTeam = gameArray.getJSONObject(i).getJSONObject("game").getJSONObject("awayTeam").getString("City");
			homeTeam = gameArray.getJSONObject(i).getJSONObject("game").getJSONObject("homeTeam").getString("City");
			if(Objects.equals(awayTeam, teamLookingFor) || Objects.equals(homeTeam, teamLookingFor)){
				gameFoundIndex = i;
				
				answer = "";
				
				String awayCity = gameArray.getJSONObject(gameFoundIndex).getJSONObject("game").getJSONObject("awayTeam").getString("City");
				String awayName = gameArray.getJSONObject(gameFoundIndex).getJSONObject("game").getJSONObject("awayTeam").getString("Name");
				String homeCity = gameArray.getJSONObject(gameFoundIndex).getJSONObject("game").getJSONObject("homeTeam").getString("City");
				String homeName = gameArray.getJSONObject(gameFoundIndex).getJSONObject("game").getJSONObject("homeTeam").getString("Name");
				
				answer += "The score of the ";
				answer += awayCity + " " + awayName + " vs. " + homeCity + " " + homeName + " game was ";
				
				String awayScore = gameArray.getJSONObject(gameFoundIndex).getString("awayScore");
				String homeScore = gameArray.getJSONObject(gameFoundIndex).getString("homeScore");
				
				if(Integer.parseInt(awayScore) < Integer.parseInt(homeScore)){
					answer += homeScore + "-" + awayScore + " in favor of the " + homeName + ".";
				} else {
					answer += awayScore + "-" + homeScore + " in favor of the " + awayName + ".";
				}
				break;
			}
			
		}

		return answer;
	}
	
	private String getCurrentDate(){ 
		LocalDateTime now = LocalDateTime.now(); 
		String formattedDate = "";
		
		// date format should be YYYYMMDD
		
		formattedDate += Integer.toString(now.getYear());
		
		if(now.getMonthValue() < 10){
			formattedDate += "0" + Integer.toString(now.getMonthValue());
		} else{
			formattedDate += Integer.toString(now.getMonthValue());
		}
		
		if(now.getDayOfMonth() < 10){
			formattedDate += "0" + Integer.toString(now.getDayOfMonth());
		} else{
			formattedDate += Integer.toString(now.getDayOfMonth());
		}
		
		
		System.out.println(formattedDate); 
		return formattedDate;
	}
	
	private void SelectLeague(){
		// for future implementation to access databases from other leagues (nhl, mlb, nfl, and nba)
		
	}
	
}
