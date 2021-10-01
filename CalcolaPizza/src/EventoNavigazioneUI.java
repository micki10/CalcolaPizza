
import com.thoughtworks.xstream.*;
import java.io.*;
import java.net.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Michele
 */

public class EventoNavigazioneUI {
    private String nomeApplicazione;
    String ipClient;
    String DataOra;
    TipoEvento tipo;
    
    public EventoNavigazioneUI(String nome, String ip, String tempo, TipoEvento te){
        nomeApplicazione = nome;
        ipClient = ip;
        DataOra = tempo;
        tipo = te;
    }
    
    public String toString(){
        return ("Nome Applicazione: "+nomeApplicazione+"\n"
                + "IP Client: "+ipClient+"\n"
                + "Timestamp: "+DataOra+"\n"
                + "Evento: "+tipo);
    }
    
    public void InviaLog(String ipServer, int portaServer){// invia una istanza di log al server 
        XStream xs = new XStream();
        String x = xs.toXML(this);
        System.out.println(x);
    try ( DataOutputStream dout =
            new DataOutputStream( (new Socket(ipServer,portaServer) ).getOutputStream())
        ) { dout.writeUTF(x);} catch (Exception e) {System.err.println(e.getMessage());}   
    }
}