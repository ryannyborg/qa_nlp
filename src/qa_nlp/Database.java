package qa_nlp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class Database {

	public String FindAnswer(){
		
		String apiResponse = FetchFromAPI();
		
		String answer = CreateAnswer(apiResponse);
		
		System.out.print(answer);
		
		
		return answer;
	}
	
	private String FetchFromAPI(){
		try {
			
			// url needs to be created based on what the user's question is
			
            URL url = new URL ("https://api.mysportsfeeds.com/v1.2/pull/nhl/2018-playoff/scoreboard.json?fordate=20180418");
            
            String username = "";
            String password = "";
            
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
