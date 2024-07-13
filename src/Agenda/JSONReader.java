package Agenda;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import com.google.gson.Gson;

public class JSONReader {
    public static void main(String[] args) {
        try {
        
            Reader rd = Files.newBufferedReader(Paths.get("src/Agenda/contacts.json"));
            Gson gson = new Gson();
            Map<?,?> userMap = gson.fromJson(rd, Map.class);
            System.out.println(userMap.get("name"));
    
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }
}
