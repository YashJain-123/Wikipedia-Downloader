package java_package.utility;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;

public class CityInformationExample {
    private String keyword;

    public CityInformationExample(String keyword) {
        this.keyword=keyword;
    }

    public CityInformationExample() {

    }

    public static void main(String[] args) {

    }

    public List<String> getCities() {
        List<String> list=new ArrayList<String>();
        String url="http://ontheworldmap.com/all/cities/";
        try {
            String siteResponse=HttpUrlConnectionExample.sendGet(url);
            Document document=Jsoup.parse(siteResponse, url);
            Elements elements= document.body().select(".clr li");
            for(Element element: elements) {
                String response=element.text();
                if(response.compareTo("Zurich")==0) {
                    list.add(response);
                    break;
                }
                list.add(response);
            }
        } catch(Exception e) {

        }
        return list;
    }
}
