package java_package.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Scanner;

public class WikipediaDownloader implements Runnable {
    private String keyword;

    public WikipediaDownloader(String keyword) {
        this.keyword=keyword;
    }

    public WikipediaDownloader() {

    }

    @Override
    public void run() {
        String response="";
        String image_url="";
        if(this.keyword==null || this.keyword.length()==0) {
            return;
        }
        this.keyword=this.keyword.trim().replaceAll("[ ]+", "_");
        String wikiUrl=getWikipediaUrlForQuery(this.keyword);
        try {
            String wikipediaResponse=HttpUrlConnectionExample.sendGet(wikiUrl);
            Document document=Jsoup.parse(wikipediaResponse, "https://en.wikipedia.org/wiki/");
            Elements childElements=document.body().select(".mw-parser-output > *");
            int state=0;
            for(Element childElement: childElements) {
                if (state == 0) {
                    if (childElement.tagName().equals("table")) {
                        state = 1;
                    }
                } else if (state == 1) {
                    if (childElement.tagName().equals("p")) {
                        state = 2;
                        response = childElement.text();
                        break;
                    }
                }
            }
            try {
                image_url=document.body().select(".infobox img").get(0).attr("src");
            } catch(Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        WikiResult wikiResult=new WikiResult(this.keyword, response, image_url);
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String json=gson.toJson(wikiResult);
        System.out.println(json);
    }

    private String getWikipediaUrlForQuery(String cleanKeyword) {
        return "https://en.wikipedia.org/wiki/"+cleanKeyword;
    }

    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.print("Enter the query: ");
        String query=scan.nextLine();
        TaskManager taskManager=new TaskManager(20);
        WikipediaDownloader wikipediaDownloader = new WikipediaDownloader(query);
        taskManager.waitTillIsFreeAndAddTask(wikipediaDownloader);
    }
}
