package qa_nlp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class Database {

	public void CreateSQLStatement(){
		
		FetchFromAPI();
		
	}
	
	private void FetchFromAPI(){
		try {
			
			// url needs to be created based on what the user's question is
			
            URL url = new URL ("https://api.mysportsfeeds.com/v1.2/pull/nhl/2018-playoff/scoreboard.json?fordate=20180418");
            String encoding = Base64.getEncoder().encodeToString("{username}:{password}".getBytes());
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
        } catch(Exception e) {
            e.printStackTrace();
        }
	}
	
	private String CreateAnswer(){
		
		return "Not yet implemented";
	}
	
}
