package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class payLoad {

    public static String getPostDataJSON(){
        String b = "{"+
                "\"location\": {"+
                "\"lat\": -33.8669710,"+
                "\"lng\": 151.1958750"+
                "},"+
                "\"accuracy\": 50,"+
                "\"name\": \"Google Shoes!\","+
                "\"phone_number\": \"(02) 9374 4000\","+
                "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
                "\"types\": [\"shoe_store\"],"+
                "\"website\": \"http://www.google.com.au/\","+
                "\"language\": \"en-AU\""+
                "}";

        return b;
    }

    public static String GenerateStringFromResource(String path) throws IOException{

        return new String(Files.readAllBytes(Paths.get(path)));

    }

}
