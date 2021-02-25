import java.sql.*;
import java.util.*;

public class Genres{

   private int genreId;
   private String genreName;
   BeanzDatabase dBase = new BeanzDatabase();
   ArrayList<ArrayList<String>> newList = null;

   public Genres(){
   }
   public Genres(int genreId, String genreName){
      this.genreId = genreId;
      this.genreName = genreName;
   }

   public void setId(int genreId){
      this.genreId = genreId;
   }
   public void setName(String genreName){
      this.genreName = genreName;
   }
   public int getId(){
      return genreId;
   }
   public String getName(){
      return genreName;
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