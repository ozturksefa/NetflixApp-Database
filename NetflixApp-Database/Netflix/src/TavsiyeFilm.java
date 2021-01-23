
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sefaozturk
 */
public class TavsiyeFilm {
    private int id;
    private String adi;
    private String tipi;
    private String turu;
    private double puan;
    

    public TavsiyeFilm(int id, String adi, String tipi, String turu, double puan) {
        this.id = id;
        this.adi = adi;
        this.tipi = tipi;
        this.turu = turu;
        this.puan = puan;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getTipi() {
        return tipi;
    }

    public void setTipi(String tipi) {
        this.tipi = tipi;
    }

    public String getTuru() {
        return turu;
    }

    public void setTuru(String turu) {
        this.turu = turu;
    }
    
    public double getPuan() {
        return puan;
    }

    public void setPuna(double puan) {
        this.puan = puan;
    }

    
    
}
