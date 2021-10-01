
import java.io.*;
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
public class CacheUltimaRicetta implements Serializable{
    private int npizze_cache;
    private int peso_cache;
    private int idratazione_cache;
    private int sale_cache;
    private int olio_cache;
    private int ore_cache;
    private int tamb_cache;
    private Ricetta ricetta_cache;
    
    public CacheUltimaRicetta(int n, int p, int i, int s, int o, int h, int t, Ricetta r){
        npizze_cache=n;
        peso_cache=p;
        idratazione_cache=i;
        sale_cache=s;
        olio_cache=o;
        ore_cache=h;
        tamb_cache=t;
        ricetta_cache = new Ricetta(r);
    }
    
    public CacheUltimaRicetta(){
        super();
        ricetta_cache = new Ricetta();
    }
    
    public int getNpizzeCache(){return npizze_cache;}
    public int getPesoCache(){return peso_cache;}
    public int getIdratazioneCache(){return idratazione_cache;}
    public int getSaleCache(){return sale_cache;}
    public int getOlioCache(){return olio_cache;}
    public int getOreCache(){return ore_cache;}
    public int getTambCache(){return tamb_cache;}
    public Ricetta getRicettaCache(){return ricetta_cache;}
    
    public void scriviCache(String path){
        try ( FileOutputStream fout = new FileOutputStream(path);
              ObjectOutputStream oout = new ObjectOutputStream(fout);) {
          oout.writeObject(this); 
          System.out.println("cache applicativa scritta su "+path);
          oout.close();
          fout.close();
        } catch (IOException ex) {
            System.out.println("errore: impossibile conservare la cache applicativa!");
        }
    }
    
    public static CacheUltimaRicetta ripristinaCache(String path){
        CacheUltimaRicetta prelevata = null;
        try ( FileInputStream fin = new FileInputStream(path);
              ObjectInputStream oin = new ObjectInputStream(fin); ) {
              prelevata = (CacheUltimaRicetta)oin.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("errore: impossibile recuperare la cache applicativa!");
        }
        return prelevata;
    }
    
    @Override
    public String toString(){
        return "Oggetto CacheUltimaRicetta: \n"
                + "nPizze: "+npizze_cache+"\n"
                + "pesoPizze: "+peso_cache+"\n"
                + "idratazione: "+idratazione_cache+"\n"
                + "sale: "+sale_cache+"\n"
                + "olio: "+olio_cache+"\n"
                + "ore: "+ore_cache+"\n"
                + "temperatura:"+tamb_cache+"\n"
                + "Ricetta: \n"+ricetta_cache.toString();
    }
}


