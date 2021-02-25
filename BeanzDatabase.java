import java.sql.*;
import java.util.*;


public class BeanzDatabase{
   String mysql = "jdbc:mysql://localhost:3306/musicbeanz?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
   String username = "root";
   String password = "D0nj4..v4l4";
   Connection con;
   ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
   ArrayList<String> list2;
   int result;

   public BeanzDatabase(){
      this.mysql = mysql;
      this.username = username;
      this.password = password;  
   }

   public boolean connect(){
      try{
          //Scanner sc = new Scanner(System.in);
          //System.out.println("Enter your username: ");
          //String username = sc.nextLine();
          //System.out.println("Enter your password: ");
          //String password = sc.nextLine();
      
         //Connecting to database
         Class.forName("com.mysql.cj.jdbc.Driver");  
         con = DriverManager.getConnection(mysql,username,password);
         System.out.println("Database connected");     
         //Catching exception
      }catch(ClassNotFoundException | SQLException cnfe){
         MBException mbe = new MBException(cnfe);
      
      }
      return true; 
   }
   
   public boolean close(){
      try{
         con.close();
         System.out.println("Conection closed");
      }catch(SQLException | NullPointerException npe){
         MBException mbe = new MBException(npe);
      
      }
      return true;
   }
   
   
      public ArrayList<String> getData(String query){
      try{
         //Creating new list and executing statement
         list2 = new ArrayList<String>();

         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(query);
         ResultSetMetaData rsmd = rs.getMetaData();
         int colCount = rsmd.getColumnCount();     
         //While there is data
         while(rs.next()){
            for(int i = 1; i <= colCount; i++){
               String var = rs.getString(i);
               list2.add(var);

            }
                
         }   
         //Catching exception
      }catch(NullPointerException | SQLException sql){
         System.out.println(sql);
      }
      return list2;   
   }
   
//    public boolean  getData(String query){
//    
//       try{
//          //Creating new list and executing statement
//          list = new ArrayList<ArrayList<String>>();
//          Statement stmt = con.createStatement();
//          ResultSet rs = stmt.executeQuery(query);
//          //While there is data
//          if(rs != null){
//             result = 1;
//             for(int i = 0; i<list.size();i++){
//             System.out.println(list.get(i));
//             }
//          }
//          else
//          {
//             result = 0;
//          }
//          //Catching exception
//       }catch(NullPointerException | SQLException sql){
//          MBException mbe = new MBException(sql);
//       
//       }
//    
//       if(result==1)
//       {
//          return true;
//       }
//       else
//       {
//          return false;
//       }
//    }
   
//    public String getData(){
//    
//    }

   public boolean setData(String statement){
      try{
         connect();
         //establishing connection
         Statement stmt = con.createStatement();
         //updating
         stmt.executeUpdate(statement);
         //catching exception
      }catch(SQLException se){
         MBException mbe = new MBException(se);
      
      }
      return true;
   }
   
   public ArrayList<ArrayList<String>> getData(String query,String keyword, ArrayList<String> prepareList){
      try{
      
         prepareList.clear();
         ArrayList<String> colRow = new ArrayList();
      //          Scanner sc = new Scanner(System.in);
      //          System.out.println("Enter a search keyword:");
      //          String keyword = sc.nextLine();
         prepare(query, keyword, prepareList);
         PreparedStatement ps = prepare(query, keyword, prepareList);
         ResultSet rs = ps.executeQuery(); 
                     //calling object for retrieval of metadata  
      
         ResultSetMetaData rsmd = rs.getMetaData();
         int colCount = rsmd.getColumnCount();     
         while(rs.next()){
            for(int i = 1; i <= colCount; i++){
               String var = rs.getString(i);
               prepareList.add(var);
              
               String value = rsmd.getColumnName(i);
               //adding value to arraylist
               colRow.add(value);
            }
                
         }   
      
            //Creating a new row for column names
       
            //adding column row to top of arraylist
         
         list.add(colRow);
         list.add(prepareList);
         
         for(int i = 0; i < list.size();i++){
            System.out.println(list.get(i));
         }
      }catch(SQLException sql){
         MBException mbe = new MBException(sql);
      
      }
   
      return list;
   
   }
   
   
   
   public PreparedStatement prepare(String query, String keyword, ArrayList<String> prepareList) throws SQLException{
      prepareList = new ArrayList<>(); 
      PreparedStatement ps = null;     
      ps = con.prepareStatement(query);         
      ps.setString(1, keyword);
      return ps;
   }



}
