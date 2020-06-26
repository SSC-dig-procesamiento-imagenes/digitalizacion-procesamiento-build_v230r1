package mx.com.teclo.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.parser.JSONParser;

public class Methods {

	URL oracle;
	JSONParser parser = new JSONParser();
	
	public String getJsonString(String serviceUrl) {
        String json=null;
        try {         
            oracle = new URL(serviceUrl); // URL to Parse
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));           
            json = in.readLine();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("json: "+json);
        return json;
    }
}
