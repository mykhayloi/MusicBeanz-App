import java.sql.*;
import java.util.*;

public class UpcomingEvents{

   private int eventInfoId, eventId;
   String eventName;
   BeanzDatabase dBase = new BeanzDatabase();
   ArrayList<ArrayList<String>> newList = null;

   public UpcomingEvents(){
   }
   public UpcomingEvents(int eventInfoId,int cityId,String eventName){
      this.eventInfoId = eventInfoId;
      this.eventName = eventName;
      this.eventId = eventId;
   }

   public void setId(int eventId){
      this.eventId = eventId;
   }
   public int getId(){
      return eventInfoId;
   }

   public ArrayList<ArrayList<String>> fetchP(String query, String keyword, ArrayList<String> prepareList){
      try{
           //calling new getData
         dBase.getData(query,keyword, prepareList);
           // Catching exception
      }catch(NullPointerException npe){
                            MBException mbe = new MBException(npe);

      }
      return newList;
   } 


}