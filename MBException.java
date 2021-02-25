import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MBException extends Exception {

    public String errorMessage = "Unable to complete operation. Please contact the administrator.";
    private String sqlState = "Unknown_State";
    private String errorCode = "Unknown_Exception";
    private String cause = "Unknown_Cause";
    public ArrayList<String> info;
    private BufferedWriter bw;
    private FileWriter fw;
    private PrintWriter pw;
    private File file;
    private Date date;
    private Timestamp ts;

    public MBException(Exception e){
        super(e);
        errorMessage = getMessage();
        log();
    }

    public MBException(SQLException sqle){
        super(sqle);
        errorMessage = sqle.getMessage();
        log();
    }

    public MBException(Exception e, ArrayList<String> info){
        super(e);
        this.info = info;
        cause = getCause().toString();
        log();
    }

    public MBException(SQLException sqle, ArrayList<String> info){
        super(sqle);
        errorMessage = sqle.getMessage();
        sqlState = sqle.getSQLState();
        errorCode = Integer.toString(sqle.getErrorCode());
        cause = sqle.getCause().toString();
        logSQLE();
    }

    public MBException(NullPointerException npe, ArrayList<String> info){
        super(npe);
        errorMessage = npe.getMessage();
        cause = getCause().toString();
        log();
    }

    public void logSQLE(){
        String logText = null;
        date = new Date();
        ts = new Timestamp(date.getTime());
        try{
            file = new File("log.txt");
            fw =new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            if(sqlState == null){
                logText += "Message: " + errorMessage + ", ";
                System.out.println("ERROR4");
            }
            else{
                logText += sqlState;
                logText +=", ";
                logText +=errorCode;
                logText +=", ";
                logText +=errorMessage;
                System.out.println("ERROR3");
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            pw.println(logText+" ("+formatter.format(ts)+")");
            pw.flush();
            pw.close();
        }
        catch(IOException ioe){

        }
    }

    public void log(){
        String logText = null;
        date = new Date();
        ts = new Timestamp(date.getTime());
        file = new File("log.txt");
        try{
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            logText = "Message: " + errorMessage + ", Cause: " + cause;
            if(info != null){
                for (String s : info) logText += ", "+s;
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            pw.println(logText + " ("+formatter.format(ts) + ")");
            pw.flush();
            pw.close();
        }
        catch(IOException ioe){
        }
    }
}