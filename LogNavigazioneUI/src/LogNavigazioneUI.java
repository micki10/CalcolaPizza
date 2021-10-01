import java.io.*;
import java.net.*;
import java.nio.file.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Michele
 */

public class LogNavigazioneUI {
    private int porta;
    
    public LogNavigazioneUI(int p){
          porta = p;
    }
    public LogNavigazioneUI(){
        this(8080);
    }
    public String RiceviLog(){
        String ricevuto = null;
        System.out.println("Server log in ascolto sulla porta "+porta);
        try ( ServerSocket servs = new ServerSocket(porta);
          Socket sd = servs.accept(); 
          DataInputStream din = new DataInputStream(sd.getInputStream());
        ){ 
            ricevuto = din.readUTF();
            System.out.println(ricevuto);
            try{ 
                Files.write(Paths.get("log.xml"), ricevuto.getBytes()); 
            } catch (Exception e) { System.out.println("Errore generico");}
        }   
        catch (Exception e) { System.err.println(e.getMessage());}
        return ricevuto;        
    }
    public void ScriviLog(String entry){
        if(ValidatoreXML.ValidaXML("log.xml", "log.xsd")){
            try(FileWriter fw = new FileWriter("log.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                out.println(entry);
            } catch (IOException e) {
                  System.err.println("Errore apertura file");
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int firstArg = 8080;
        if (args.length > 0) {
            try {
                firstArg = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " must be an integer.");
                System.exit(1);
            }
        }
        
        LogNavigazioneUI server = new LogNavigazioneUI(firstArg);
        
        while(true){
            server.ScriviLog(server.RiceviLog());
        }
    }
    
}
