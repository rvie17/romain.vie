package com.chillyfacts.com;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL; 
import org.json.JSONObject;
import java.io.File;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;

//import org.json.JSONObject;
public class Main {
	public static void main(String[] args) {
     try {
     File f = new File("test.txt");
    int i=1;
         double sueil =0.3;
  
      while(i==1){
          
         float BTCUSDT= Main.call_me_bid("https://www.okex.com/api/spot/v3/instruments/BTC-USDT/ticker");
         float OKBUSDT= Main.call_me_bid("https://www.okex.com/api/spot/v3/instruments/OKB-USDT/ticker");
         float WXTOKB= Main.call_me_bid("https://www.okex.com/api/spot/v3/instruments/WXT-OKB/ticker");
         float wxt_btc_bid=Main.call_me_bid("https://www.okex.com/api/spot/v3/instruments/WXT-BTC/ticker");
         float wxt_btc_ask=Main.call_me_ask("https://www.okex.com/api/spot/v3/instruments/WXT-BTC/ticker");
         float WXTUSDK=Main.call_me_bid("https://www.okex.com/api/spot/v3/instruments/WXT-USDK/ticker");
         float WXTUSDTBID=Main.call_me_bid("https://www.okex.com/api/spot/v3/instruments/WXT-USDT/ticker");
         float WXTUSDTASK=Main.call_me_ask("https://www.okex.com/api/spot/v3/instruments/WXT-USDT/ticker");
         //LinkedList<String> list=new LinkedList<String>();
         //list=;
         
//Quantité de chaque coté 
float wxtusdtAskqty=Main.call_me_depth("https://www.okex.com/api/spot/v3/instruments/WXT-USDT/book?size=5","asks");
float wxtusdtBidqty=Main.call_me_depth("https://www.okex.com/api/spot/v3/instruments/WXT-USDT/book?size=5","bids");
float wxtbtcAskqty=Main.call_me_depth("https://www.okex.com/api/spot/v3/instruments/WXT-BTC/book?size=5","asks");
float wxtbtcBidqty=Main.call_me_depth("https://www.okex.com/api/spot/v3/instruments/WXT-BTC/book?size=5","bids");
// test valeur System.out.println(wxtusdtAskqty+" "+wxtusdtBidqty+" "+wxtbtcAskqty+" "+wxtbtcBidqty);
float wxt_btc_usdt_ask=BTCUSDT*wxt_btc_ask;
float wxt_btc_usdt_bid=BTCUSDT*wxt_btc_bid;
float wxtokbusdt=OKBUSDT*WXTOKB;
float spreadbid=(wxt_btc_usdt_bid-WXTUSDTASK)*100/(WXTUSDTASK); // doit etre en neg pour benef 
float spreadask=(wxt_btc_usdt_ask-WXTUSDTBID)*100/(WXTUSDTBID); //doit être en pos 
//float spread2=(wxtokbusdt-WXTUSDT)*100/(WXTUSDT);  

//System.out.println(ask);

if (spreadbid > sueil || spreadask < -sueil){
  
System.out.println(wxt_btc_ask);
System.out.println(wxt_btc_bid); 
System.out.println(wxt_btc_usdt_bid);
System.out.println(WXTUSDTASK);
System.out.println(wxt_btc_usdt_ask);
System.out.println(WXTUSDTBID);
System.out.println(wxtusdtAskqty+" "+wxtusdtBidqty+" "+wxtbtcAskqty+" "+wxtbtcBidqty);
System.out.println(new Date());

Date date = new Date();
    String strDateFormat = "hh:mm:ss a";
    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
    String formattedDate= dateFormat.format(date);
    System.out.println(formattedDate);
    
System.out.println(spreadbid);
System.out.println(spreadask);
System.out.println("//");
   File b = new File("/Users/romain/Desktop/test2.txt");
   String s1=Float.toString(spreadbid); 
   String s2=Float.toString(spreadask);
    String askwbtc=Float.toString(wxtbtcAskqty); 
    String bidwbtc=Float.toString(wxtbtcBidqty);
    String askwusdt=Float.toString(wxtusdtAskqty); 
    String bidwusdt=Float.toString(wxtusdtBidqty);
      String retour=("\n");
      String comma =(";");
     
    write(s1, b);
    write(comma,b);
    write(s2, b);
    write(comma,b);

//test pour savoir quelle qty affiché 
if (spreadbid > sueil){
       write(bidwbtc+"  "+askwusdt,b);   

      write(comma,b);
}
if(spreadask < -sueil){
    write(askwbtc+"  "+bidwusdt,b);
     write(comma,b);
}
   write(formattedDate,b);
    write(retour,b); 
}
//System.out.println(spread2);
    }




        
        } 
     catch (Exception e) {
         e.printStackTrace();
         
       }
     }
	   
public static float call_me_bid(String url) throws Exception {
      
     URL obj = new URL(url);
     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
     // optional default is GET
     con.setRequestMethod("GET");
     //add request header
     int responseCode = con.getResponseCode();
     //System.out.println("\nSending 'GET' request to URL : " + url);
     BufferedReader in = new BufferedReader(
             new InputStreamReader(con.getInputStream()));
     String inputLine;
     StringBuffer response = new StringBuffer();
     while ((inputLine = in.readLine()) != null) {
     	response.append(inputLine);
     }
     in.close();
     //print in String
     //System.out.println(response.toString());
     //Read JSON response and print
     JSONObject myResponse = new JSONObject(response.toString());
     //System.out.println("result after Reading JSON Response");
            float wxtbtc=myResponse.getFloat("bid");
     //System.out.println(wxtbtc);
     return wxtbtc;
   }

public static float call_me_ask(String url) throws Exception {
      
     URL obj = new URL(url);
     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
     // optional default is GET
     con.setRequestMethod("GET");
     //add request header
     int responseCode = con.getResponseCode();
     //System.out.println("\nSending 'GET' request to URL : " + url);
     BufferedReader in = new BufferedReader(
             new InputStreamReader(con.getInputStream()));
     String inputLine;
     StringBuffer response = new StringBuffer();
     while ((inputLine = in.readLine()) != null) {
     	response.append(inputLine);
     }
     in.close();
     //print in String
     //System.out.println(response.toString());
     //Read JSON response and print
     JSONObject myResponse = new JSONObject(response.toString());
     //System.out.println("result after Reading JSON Response");
            float wxtbtc=myResponse.getFloat("ask");
     //System.out.println(wxtbtc);
     return wxtbtc;
   }

public static float call_me_depth(String url,String Side) throws Exception {
      
     URL obj = new URL(url);
     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
     // optional default is GET
     con.setRequestMethod("GET");
     //add request header
     int responseCode = con.getResponseCode();
     //System.out.println("\nSending 'GET' request to URL : " + url);
     BufferedReader in = new BufferedReader(
             new InputStreamReader(con.getInputStream()));
     String inputLine;
     StringBuffer response = new StringBuffer();
     while ((inputLine = in.readLine()) != null) {
     	response.append(inputLine);
     }
     in.close();
     //print in String
     //System.out.println(response.toString());
     //Read JSON response and print
     //System.out.println("result after Reading JSON Response");
     JSONObject myResponse = new JSONObject(response.toString()); 
 JSONArray Asks = myResponse.getJSONArray(Side);
     JSONArray prix = Asks.getJSONArray(0);
     //System.out.println(prix.toString());     
     Float qty =prix.getFloat(1);       
     return qty;
   }
//Problem pour récupérer order depth

public static void write(String s, File f) throws IOException { 
    FileWriter fw  = new FileWriter(f, true);
 fw.write(s); 
 fw.close(); 
} 
}




