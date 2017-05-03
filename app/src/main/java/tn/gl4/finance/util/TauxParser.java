package tn.gl4.finance.util;


import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class TauxParser {

    private static String url = "http://www.ilboursa.com/marches/convertisseur_devises.aspx/getRateCurrency" ;

    Devise from ;
    Devise to ;
    public TauxParser() {
        // par defaut entre dinar et euro
        from = Devise.EUR ;
        to   = Devise.TND ;
        Log.e("info","set up default taux parser");
    }

    public TauxParser(Devise from, Devise to){
        this.from = from ;
        this.to   = to ;
        Log.e("info","set up taux parser to currencies "+from +" => "+to);
    }



    private String sendJsonRequest(String json) throws ProtocolException, IOException{
        String jsonResponse = "";
        URL obj;
        try {
            obj = new URL(url);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(json);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();

            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + json);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println("Resultat serveur : "+response.toString());
            jsonResponse = response.toString() ;

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonResponse ;
    }

    private String buildJsonRequest(Devise from, Devise to){
        return "{'c1':'"+from+"','c2':'"+to+"'}" ;
    }

    public float getTaux()throws IOException,ProtocolException{
        return getTaux(this.from, this.to);
    }

    public float getTaux(Devise from, Devise to) throws IOException,ProtocolException{
        String request = buildJsonRequest(from, to) ;
        String response = sendJsonRequest(request);

        String taux = response.substring(6,response.indexOf("\"",6)).replace(",", ".");
        float thetaux = Float.parseFloat(taux);
        //System.out.println("sent : "+request+" , got back : "+response+" taux = "+taux+" / "+thetaux);

        this.taux = thetaux ;
        return thetaux ;
    }
    float taux ;
    public String toString(){
        return "from '"+url+"', taux entre "+from+" et "+to+ " est de "+taux;
    }

}
