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


public class PalmaresParser  {
    private static final String url = "https://data.tunisievaleurs.com/rm.aspx" ;
    private final int LIMIT = 5 ;
    private Document doc ;
    public PalmaresParser() throws IOException{
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

    private Map<Integer, Map<String,String>> getCotations(int n, int offset){
        Elements elems = doc.select(".box-apercu-mar:eq(1) table tbody:eq(1) tr") ;
        Map<Integer, Map<String,String>> map = new HashMap<Integer,Map<String,String>>(n);

        for(int i=offset; i<n+(offset) && i< elems.size();i++){
            Element e = elems.get(i);
            Map<String,String> titreMap = new HashMap<String, String>(5);


            String nom =  Jsoup.parse(e.childNode(0).outerHtml().toString()).text().toString() ;
            StringBuilder sb =  new StringBuilder(Jsoup.parse(e.childNode(1).outerHtml().toString()).text().toString().trim()) ;
            int posParentheseOuvr = sb.indexOf("(");
            int posParentheseFerm = sb.indexOf(")");

            String valeur = sb.substring(0, posParentheseOuvr).trim();
            String variation = sb.substring(posParentheseOuvr+1, posParentheseFerm).trim();

            titreMap.put("nom",nom);
            titreMap.put("valeur",valeur);
            titreMap.put("variation", variation);

            map.put(i+1-offset, titreMap);
        }
        return map ;
    }

    public Map<Integer, Map<String,String>> getTopHausses(){
        return getCotations(LIMIT, 0);
    }

    public Map<Integer, Map<String,String>> getTopBaisses(){
        return getCotations(LIMIT, 5);
    }

    public Map<Integer, Map<String,String>> getTopVolumes(){
        return getCotations(LIMIT, 10);
    }

}
