/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import java.sql.*;

/**
 *
 * @author Michele
 */
public class OperazioniRicettario { //classe per l'interfacciamento con il db locale MySql
    String db_string;
    String db_user;
    String db_pwd;
    
    public OperazioniRicettario(String db, String user, String pwd){
        db_string = db;
        db_user = user;
        db_pwd = pwd;
    }
    
    public OperazioniRicettario(){
        this("jdbc:mysql://localhost:3306/calcolapizza_db","root","");
    }
    
    public List<Ricetta> OttieniRicette(){      //preleva dal db tutte le ricette presenti
        List<Ricetta> ricette = new ArrayList<>();
        try(
            Connection co = DriverManager.getConnection(db_string, db_user, db_pwd);
            Statement st = co.createStatement();
            )
            {
                String query = "SELECT * FROM ricette ;";
                ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    Ricetta r;
                    r = new Ricetta(rs.getString("nomeRicetta"),
                            rs.getInt("nPizze"),
                            rs.getInt("pesoPizza"),
                            rs.getInt("percAcqua"),
                            rs.getInt("grSale"),
                            rs.getInt("grOlio"),
                            rs.getInt("OreLievitazione"),
                            rs.getInt("tAmbiente"));
                    r.setFarina(rs.getDouble("Farina"));
                    r.setAcqua(rs.getDouble("Acqua"));
                    r.setSale(rs.getDouble("Sale"));
                    r.setOlio(rs.getDouble("Olio"));
                    r.setLievito(rs.getDouble("Lievito"));
                    ricette.add(r);     //aggiunge alla  List
                }
            }
            catch(SQLException sqle) {System.err.println(sqle.getMessage());}  
        return ricette;
    }
    
    public Ricetta ottieniRicetta(String nome){ //preleva una ricetta a partire da nome(primary key)
        Ricetta r = null;
        try(
            Connection co = DriverManager.getConnection(db_string, db_user, db_pwd);
            )
            {
                String query = "SELECT * FROM ricette WHERE nomeRicetta = ?;";
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1,nome);
                ResultSet rs = ps.executeQuery();
                rs.next();
                    r = new Ricetta(rs.getString("nomeRicetta"),
                                    rs.getInt("nPizze"),
                                    rs.getInt("pesoPizza"),
                                    rs.getInt("percAcqua"),
                                    rs.getInt("grSale"),
                                    rs.getInt("grOlio"),
                                    rs.getInt("OreLievitazione"),
                                    rs.getInt("tAmbiente"));
                                    r.setFarina(rs.getDouble("Farina"));
                                    r.setAcqua(rs.getDouble("Acqua"));
                                    r.setSale(rs.getDouble("Sale"));
                                    r.setOlio(rs.getDouble("Olio"));
                                    r.setLievito(rs.getDouble("Lievito"));
            }
            catch(SQLException sqle) {System.err.println(sqle.getMessage());}   
        return r;
    }
    
    public boolean InserisciRicetta(Ricetta r){ //inserisce ricetta
        try(
             Connection co =  DriverManager.getConnection(db_string,db_user,db_pwd);
             Statement st = co.createStatement();
            ) 
            {
                int executeUpdate = st.executeUpdate(("INSERT INTO ricette VALUES('"+r.getNomeRicetta()+"','"+
                                                                                 r.getNPizze()+"','"+
                                                                                 r.getPesoPizza()+"','"+
                                                                                 r.getPercAcqua()+"','"+
                                                                                 r.getGrSale()+"','"+
                                                                                 r.getGrOlio()+"','"+
                                                                                 r.getOreLievitazione()+"','"+
                                                                                 r.getTAmbiente()+"','"+
                                                                                 r.getFarina()+"','"+
                                                                                 r.getAcqua()+"','"+
                                                                                 r.getSale()+"','"+
                                                                                 r.getOlio()+"','"+
                                                                                 r.getLievito()+"');"));
            }
        catch(SQLException sqle) {System.err.println(sqle.getMessage()); return false;}
        return true;
    }
    public boolean EliminaRicetta(String nomeRicetta){ //elimina ricetta da nome (primary key)
        int righe = 0;
        try(
            Connection co = DriverManager.getConnection(db_string, db_user, db_pwd);
            ){
                String query = "DELETE FROM ricette WHERE nomeRicetta = ?";
                PreparedStatement pstmt = co.prepareStatement(query);
                pstmt.setString(1, nomeRicetta);
                righe = pstmt.executeUpdate();
            }
        catch(SQLException sqle) {System.err.println(sqle.getMessage());}  
        return (righe != 0);
    }
}