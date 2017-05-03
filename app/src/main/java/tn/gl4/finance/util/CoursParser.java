package tn.gl4.finance.util;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoursParser {

    private static String url = "http://www.ilboursa.com/marches/aaz.aspx" ;
    Document doc;

    public CoursParser(){
        refresh();
    }

    protected Document connect(){
        try {
            this.doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getGlobal().log(Level.SEVERE, e.getMessage()+", StackTrace: "+e.toString());
        }
        return this.doc ;
    }
    public void refresh(){
        this.doc = connect();
    }

    public Map<String, String[]> getAllCours(){

        Elements elems = doc.select("table#tabQuotes tbody tr");// table tbody:eq(1) tr") ;

        Map<String, String[]> map = new HashMap<String, String[]>(elems.size());

        for(Element e : elems){
            String[] cours = new String[8];


            String nom =  Jsoup.parse(e.childNode(1).childNode(0).outerHtml().toString()).text().toString() ;
            String ouverture =  Jsoup.parse(e.childNode(3).outerHtml().toString()).text().toString().trim() ;
            String haut =  Jsoup.parse(e.childNode(5).outerHtml().toString()).text().toString().trim() ;
            String bas =  Jsoup.parse(e.childNode(7).outerHtml().toString()).text().toString().trim() ;
            String volumeTitres =  Jsoup.parse(e.childNode(9).outerHtml().toString()).text().toString().trim() ;
            String volumeDT =  Jsoup.parse(e.childNode(11).outerHtml().toString()).text().toString().trim() ;
            String dernier =  Jsoup.parse(e.childNode(13).outerHtml().toString()).text().toString().trim() ;
            String variation =  Jsoup.parse(e.childNode(15).childNode(0).outerHtml().toString()).text().toString().trim() ;


            cours[0] = nom ;
            cours[1] = ouverture ;
            cours[2] = haut ;
            cours[3] = bas ;
            cours[4] = volumeTitres ;
            cours[5] = volumeDT ;
            cours[6] = dernier ;
            cours[7] = variation ;

            System.out.println("cours: "+ cours[0]+", "+cours[1]+", "+cours[2]+", "+cours[3]+", "+cours[4]+", "+cours[5]+", "+cours[6]+", "+cours[7]);
            map.put(nom, cours);

            //System.out.println(map.get(i).toString());
        }

        return map ;
    }
}
