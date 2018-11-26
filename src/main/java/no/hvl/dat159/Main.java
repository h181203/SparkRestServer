package no.hvl.dat159;

import static spark.Spark.*;
import com.google.gson.*;

public class Main {
	
	static Temperature temp = null;
	static Heat	heat = null;
	
    public static void main(String[] args) {
    	
    	temp = new Temperature();
    	heat = new Heat();
    	
    	port(8080);
    	
    	after((req, res) -> {
    		  res.type("application/json");
    		});
    	
        get("/tempsensor/current", (req, res) -> tempToJson());
        
        put("/tempsensor/current", (req,res) -> {
        
        	Gson gson = new Gson();
        	
        	temp = gson.fromJson(req.body(), Temperature.class);
        
            return tempToJson();
        	
        });
       
        get("/heating/current", (req, res) -> heatToJson());
        
        put("/heating/current", (req,res) -> {
        	
        	Gson gson = new Gson();
        	
        	heat = gson.fromJson(req.body(), Heat.class);
        
            return heatToJson();
        });
        
        
        
    }
    
    static String tempToJson () {
    	
    	Gson gson = new Gson();
    	    
    	String jsonInString = gson.toJson(temp);
    	
    	return jsonInString;
    }
    
    static String heatToJson () {
    	
    	Gson gson = new Gson();
    	    
    	String jsonInString = gson.toJson(heat);
    	
    	return jsonInString;
    }

}
