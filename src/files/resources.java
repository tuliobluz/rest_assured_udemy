package files;

public class resources {

    public static String placeGetDataJSON(){

        String res ="/maps/api/place/nearbysearch/json";
        return res;
    }

    public static String placePostDataJSON(){

        String res = "/maps/api/place/add/json";
        return res;
    }

    public static String placePostDataXML(){

        String res = "/maps/api/place/add/xml";
        return res;
    }

}
