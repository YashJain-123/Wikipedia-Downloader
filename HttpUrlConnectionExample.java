package java_package.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlConnectionExample {
    private final static String USER_AGENT="Mozilla/5.0";

    public static String sendGet(String urlStr) throws Exception {
        StringBuilder result=new StringBuilder();
        URL url=new URL(urlStr);
        HttpURLConnection conn=(HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while((line=br.readLine())!=null) {
            result.append(line);
        }
        br.close();
        return result.toString();
    }

    public static void main(String[] args) {

    }
}
