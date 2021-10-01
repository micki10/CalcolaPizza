/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Michele
 */

import com.thoughtworks.xstream.*;
import java.io.*;
import java.nio.file.*;
// fornisce l'oggetto che contiene i parametri xml che vengono letti all'avvio dal file di configurazione
public class ParametriCalcolaPizza implements Serializable{
    
    private String colore_bottoni;
    private String font;
    private int dimensione_font;
    private String colore_background;
    private int finestra_altezza;
    private int finestra_larghezza;
    private String ip_client;
    private String ip_server;
    private int porta_server;
    private int max_ricette;
    private int default_npizze;
    private int default_peso;
    private int default_idro;
    private int default_sale;
    private int default_olio;
    private int default_ore;
    private int default_tamb;
    
    public ParametriCalcolaPizza(String cb, String f, String cf,int df, int alt, int larg, 
                            String ipc, String ips, int porta,int maxr, int np, int p, 
                            int i, int s, int o,int h,  int ta ) {
        colore_bottoni = cb;
        font = f;
        colore_background =cf;
        dimensione_font = df;
        finestra_altezza = alt;
        finestra_larghezza = larg;
        ip_client = ipc;
        ip_server = ips;
        porta_server = porta;
        max_ricette=maxr;
        default_npizze = np;
        default_peso=p;
        default_idro=i;
        default_sale=s;
        default_olio=o;
        default_ore=h;
        default_tamb=ta;
        
    }
    public ParametriCalcolaPizza(){ // parametri di default
        this("blue","Arial","white",14,1000,1000,"localhost","localhost",8080,5,4,250,60,20,40,8,20);
    }
    
    public String getColoreBottoni(){ return colore_bottoni; }
    public String getFont() { return font;}
    public String getColoreBackground() {return colore_background;}
    public int getDimensioniFont() {return dimensione_font;}
    public int getAltezza(){ return finestra_altezza; }
    public int getLarghezza() { return finestra_larghezza; }
    public String getIpClient(){ return ip_client;}
    public String getIpServer() {return ip_server;}
    public int getPortaServer() { return porta_server;}
    public int getMaxRicette() {return max_ricette;}
    public int getDefaultNPizze() {return default_npizze;}
    public int getDefaultPeso() {return default_peso;}
    public int getDefaultIdro() { return default_idro;}
    public int getDefaultSale() { return default_sale;}
    public int getDefaultOlio() { return default_olio;}
    public int getDefaultOre() { return default_ore;}
    public int getDefaultTAmb() {return default_tamb;}
    
    public ParametriCalcolaPizza LeggiParametriXML(){
        XStream xs = new XStream();
        String leggi = null;
        ParametriCalcolaPizza p;
        try {  //02
           leggi = new String(Files.readAllBytes(Paths.get("parametri.xml")));
        } catch (IOException ex) { 
                    System.out.println("errore: impossibile prelevare il file parametri.xml");
        }
        if(!ValidatoreXML.ValidaXML("parametri.xml","parametri.xsd"))
            p = new ParametriCalcolaPizza();
        else p = (ParametriCalcolaPizza)xs.fromXML(leggi);
        return p;
    }  
}
