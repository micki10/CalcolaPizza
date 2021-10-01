/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Michele
 */

import java.io.*;

public class Ricetta implements Serializable{
    private String nomeRicetta;
    private int nPizze;
    private int pesoPizza;
    private int percAcqua;
    private int grSale;
    private int grOlio;
    private int OreLievitazione;
    private int tAmbiente;
    private double Farina;
    private double Acqua;
    private double Sale;
    private double Olio;
    private double Lievito;
    
    public Ricetta(String nome, int n_pizze, int peso_pizza, int perc_acqua, int gr_sale, int gr_olio, int ore_liev, int t_amb){
        nomeRicetta = nome;
        nPizze = n_pizze;
        pesoPizza = peso_pizza;
        percAcqua = perc_acqua;
        grSale = gr_sale;
        grOlio = gr_olio;
        OreLievitazione = ore_liev;
        tAmbiente = t_amb;
        Farina = 0.0;
        Acqua = 0.0;
        Sale = 0.0;
        Olio = 0.0;
        Lievito = 0.0;
    }
    
    public Ricetta(){ //costruttore di default
        this("Ricetta",10,250,60,20,0,8,22);
    }
    
    public Ricetta(Ricetta r){ // costruttore di copia
        this.nomeRicetta = r.nomeRicetta;
        this.nPizze = r.nPizze;
        this.pesoPizza = r.pesoPizza;
        this.percAcqua = r.percAcqua;
        this.grSale = r.grSale;
        this.grOlio = r.grOlio;
        this.OreLievitazione = r.OreLievitazione;
        this.tAmbiente = r.tAmbiente;
        this.Farina = r.Farina;
        this.Acqua = r.Acqua;
        this.Sale = r.Sale;
        this.Olio = r.Olio;
        this.Lievito = r.Lievito;
    }
    // getter methods
    public String getNomeRicetta() {return nomeRicetta;}
    public int getNPizze() {return nPizze;}
    public int getPesoPizza() {return pesoPizza;}
    public int getPercAcqua() {return percAcqua;}
    public int getGrSale() {return grSale;}
    public int getGrOlio() {return grOlio;}
    public int getOreLievitazione() {return OreLievitazione;}
    public int getTAmbiente() {return tAmbiente;}
    public double getFarina() {return Farina;}
    public double getAcqua() {return Acqua;}
    public double getSale() {return Sale;}
    public double getOlio() {return Olio;}
    public double getLievito() {return Lievito;}
    
    public void CalcolaRicetta(){
        final int c = 300; // fattore di calcolo
        int pesoTotale = nPizze*pesoPizza;
        int divisore = (int) (100+percAcqua+ Math.round(grSale*0.1)+ Math.round(grOlio*0.1));
        System.out.println(divisore);
        Farina = (pesoTotale/divisore)*100.0;
        System.out.println("Farina = "+ Farina);
        Acqua = (pesoTotale/divisore)*percAcqua;
        Olio = (pesoTotale/divisore)*(grOlio/10);
        Sale = (pesoTotale/divisore)*(grSale/10);
        Lievito = ((Farina*c)*(1+Sale/200)*(1+Olio/300))/(OreLievitazione*tAmbiente*Acqua);
        Lievito = Math.floor(Lievito*100)/100;
    }
    
    public void StampaRicetta(){ // funzione ridondante
       System.out.println("Nome Ricetta: "+nomeRicetta+
                            "\nNumero Pizze: "+nPizze+
                            "\nPeso per pizza: "+pesoPizza+
                            "\nPercentuale Acqua: "+percAcqua+
                            "\nPercentuale Sale[gr/kg]: "+grSale+
                            "\nPercentuale Olio[gr/kg]: "+grOlio+
                            "\nOre lievitazione: "+OreLievitazione+
                            "\nTemperatura Ambiente: "+tAmbiente+
                            "\nDati Calcolo :\n"+
                            "\ngrammi Farina: "+Farina+
                            "\ngrammi Acqua: "+Acqua+
                            "\ngrammi Sale: "+Sale+    
                            "\ngrammi Olio: "+Olio+
                            "\ngrammi Lievito: "+Lievito);           
    }
    @Override
    public String toString() {
        return ("Nome Ricetta: "+nomeRicetta+
                            "\nNumero Pizze: "+nPizze+
                            "\nPeso per pizza: "+pesoPizza+
                            "\nPercentuale Acqua: "+percAcqua+
                            "\nPercentuale Sale[gr/kg]: "+grSale+
                            "\nPercentuale Olio[gr/kg]: "+grOlio+
                            "\nOre lievitazione: "+OreLievitazione+
                            "\nTemperatura Ambiente: "+tAmbiente+
                            "\nDati Calcolo :\n"+
                            "\ngrammi Farina: "+Farina+
                            "\ngrammi Acqua: "+Acqua+
                            "\ngrammi Sale: "+Sale+    
                            "\ngrammi Olio: "+Olio+
                            "\ngrammi Lievito: "+Lievito);     
    }
    public void setNome(String s){
        this.nomeRicetta = s;
    }
    public void setFarina(double d){ this.Farina = d;}
    public void setAcqua(double d){ this.Acqua = d; }
    public void setSale(double d) { this.Sale = d; }
    public void setOlio(double d) { this.Olio = d; }
    public void setLievito(double d) { this.Lievito = d;}
}
