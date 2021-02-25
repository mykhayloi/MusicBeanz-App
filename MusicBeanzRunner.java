import java.sql.*;
import java.util.*;

public class MusicBeanzRunner{

public static void main(String[]args){
BeanzDatabase db = new BeanzDatabase();
Artists artists = new Artists();
ArrayList preparedList = new ArrayList();
      db.connect();
      db.getData("select artistName from artists where artistName = ?", preparedList);
      db.close();
}

}