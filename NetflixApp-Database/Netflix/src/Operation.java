
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//Database database = new Database();
public class Operation {
    
    
    
    Connection con = null;
    Statement sta = null;
    Statement sta2 = null;
    PreparedStatement psta= null;
    
    public ArrayList<Film> FavoriTur(){
        ArrayList<Film> list=new ArrayList<Film>();
        
        String sorgu="Select * from program";
        
        try {
            sta =con.createStatement();
            ResultSet rs = sta.executeQuery(sorgu);
        
            while(rs.next()){
                int id =rs.getInt("id");
                String adi=rs.getString("adi");
                String tipi=rs.getString("tipi");
                
                StringBuilder turu=new StringBuilder();
                psta =con.prepareStatement("select * from programtur join tur on tur.id=programtur.tur_id where program_id = ?");
                psta.setInt(1, id);
                ResultSet rs2= psta.executeQuery();
                while(rs2.next()){
                    turu.append(rs2.getString("ad"));
                    turu.append(",");
                }
                
                list.add(new Film(id,adi,turu.toString(),tipi));
            }
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    //filmgetir metoduyla list oluşturdum sorguyu yazarak her dönen veriyi dönerek aktardık
    public ArrayList<Film> FilmCome(int aid){
        System.out.println(aid);
        ArrayList<Film> list=new ArrayList<Film>();
        String sorgu="Select *,group_concat(t.ad) as turu from program p JOIN programtur pt ON pt.program_id=p.id JOIN tur t ON t.id=pt.tur_id group by p.id";
        
        try {
            sta =con.createStatement();
            ResultSet rs = sta.executeQuery(sorgu);
        
            while(rs.next()){
                int id =rs.getInt("id");
                String adi=rs.getString("adi");
                String tipi=rs.getString("tipi");
                String turu=rs.getString("turu");
                
                
                list.add(new Film(id,adi,turu,tipi));
            }
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<TavsiyeFilm> TavsiyeFilmGetir(int uid){
        System.out.println(uid);
        ArrayList<TavsiyeFilm> list=new ArrayList<TavsiyeFilm>();
        String sorgu="Select * from kullanici where id="+uid;
        
        try {
            sta =con.createStatement();
            ResultSet rs = sta.executeQuery(sorgu);
        
            while(rs.next()){
                StringBuilder eklenenids=new StringBuilder();
                eklenenids.append("0,");
                int tur1 =rs.getInt("tur1");
                int tur2 =rs.getInt("tur2");
                int tur3 =rs.getInt("tur3");
                
                StringBuilder turu=new StringBuilder();
                psta =con.prepareStatement("SELECT p.*,avg(kp.puan) as puanort,pt.tur_id from kullaniciprogram kp join program p on p.id=kp.program_id join programtur pt on pt.program_id=p.id WHERE pt.tur_id=? GROUP BY kp.program_id ORDER BY puanort DESC limit 2");
                psta.setInt(1, tur1);
                ResultSet rs2= psta.executeQuery();
                while(rs2.next()){
                    int id =rs2.getInt("id");
                    eklenenids.append(id+",");
                    String adi =rs2.getString("adi");
                    String tipi =rs2.getString("tipi");
                    double puan =rs2.getDouble("puanort");
                    
                    StringBuilder turu2=new StringBuilder();
                    psta =con.prepareStatement("select * from programtur join tur on tur.id=programtur.tur_id where program_id = ?");
                    psta.setInt(1, id);
                    ResultSet rs3= psta.executeQuery();
                    while(rs3.next()){
                        turu2.append(rs3.getString("ad"));
                        turu2.append(",");
                    }
                    
                    list.add(new TavsiyeFilm(id,adi,turu2.toString(),tipi,puan));
                }
                
                turu=new StringBuilder();
                psta =con.prepareStatement("SELECT p.*,avg(kp.puan) as puanort,pt.tur_id from kullaniciprogram kp join program p on p.id=kp.program_id join programtur pt on pt.program_id=p.id WHERE pt.tur_id=? AND p.id not in ("+eklenenids.substring(0, eklenenids.length() - 1)+") GROUP BY kp.program_id ORDER BY puanort DESC limit 2");
                psta.setInt(1, tur2);
                rs2= psta.executeQuery();
                while(rs2.next()){
                    int id =rs2.getInt("id");
                    eklenenids.append(id+",");
                    String adi =rs2.getString("adi");
                    String tipi =rs2.getString("tipi");
                    double puan =rs2.getDouble("puanort");
                    
                    StringBuilder turu2=new StringBuilder();
                    psta =con.prepareStatement("select * from programtur join tur on tur.id=programtur.tur_id where program_id = ?");
                    psta.setInt(1, id);
                    ResultSet rs3= psta.executeQuery();
                    while(rs3.next()){
                        turu2.append(rs3.getString("ad"));
                        turu2.append(",");
                    }
                    
                    list.add(new TavsiyeFilm(id,adi,turu2.toString(),tipi,puan));
                }
                
                turu=new StringBuilder();
                psta =con.prepareStatement("SELECT p.*,avg(kp.puan) as puanort,pt.tur_id from kullaniciprogram kp join program p on p.id=kp.program_id join programtur pt on pt.program_id=p.id WHERE pt.tur_id=? AND p.id not in ("+eklenenids.substring(0, eklenenids.length() - 1)+") GROUP BY kp.program_id ORDER BY puanort DESC limit 2");
                psta.setInt(1, tur3);
                rs2= psta.executeQuery();
                while(rs2.next()){
                    int id =rs2.getInt("id");
                    String adi =rs2.getString("adi");
                    String tipi =rs2.getString("tipi");
                    double puan =rs2.getDouble("puanort");
                    
                    StringBuilder turu2=new StringBuilder();
                    psta =con.prepareStatement("select * from programtur join tur on tur.id=programtur.tur_id where program_id = ?");
                    psta.setInt(1, id);
                    ResultSet rs3= psta.executeQuery();
                    while(rs3.next()){
                        turu2.append(rs3.getString("ad"));
                        turu2.append(",");
                    }
                    
                    list.add(new TavsiyeFilm(id,adi,turu2.toString(),tipi,puan));
                }
                
            }
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<Film> bulfilm(String film_ad) {
    	//System.out.println(film_ad);
    	ArrayList<Film> list = new ArrayList<Film>();
    	
    	String sorgu="SELECT * FROM program where adi like '" +film_ad+"%'";
    	//System.out.println(sorgu);
        
        try {
            sta =con.createStatement();
            ResultSet rs = sta.executeQuery(sorgu);
        
            while(rs.next()){
                int id =rs.getInt("id");
                String adi=rs.getString("adi");
                String tipi=rs.getString("tipi");
                
                StringBuilder turu=new StringBuilder();
                psta =con.prepareStatement("select * from programtur join tur on tur.id=programtur.tur_id where program_id = ?");
                psta.setInt(1, id);
                ResultSet rs2= psta.executeQuery();
                while(rs2.next()){
                    turu.append(rs2.getString("ad"));
                    turu.append(",");
                }
                
                list.add(new Film(id,adi,turu.toString(),tipi));
            }
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    	
      public ArrayList<Film> bulfilm2(int tur_id) {
    	System.out.println(tur_id);
    	ArrayList<Film> list = new ArrayList<Film>();
    	
    	String sorgu="SELECT * FROM programtur JOIN program ON program.id=programtur.program_id where tur_id = "+tur_id+"";
    	
    	
    	        try {
            sta =con.createStatement();
            ResultSet rs = sta.executeQuery(sorgu);
        
            while(rs.next()){
                int id =rs.getInt("id");
                String adi=rs.getString("adi");
                String tipi=rs.getString("tipi");
                
                StringBuilder turu=new StringBuilder();
                psta =con.prepareStatement("select * from programtur join tur on tur.id=programtur.tur_id where program_id = ?");
                psta.setInt(1, id);
                ResultSet rs2= psta.executeQuery();
                while(rs2.next()){
                    turu.append(rs2.getString("ad"));
                    turu.append(",");
                }
                
                list.add(new Film(id,adi,turu.toString(),tipi));
            }
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    		
	}
    
    
    public boolean Login(String email,String password){
        //Sql sorgusu yapıyorum
        String sorgu="Select * from kullanici where email= ? and password=? "; //kurduğumuz sql bağlantısında var mı yok mu kontrolü
        try {
            PreparedStatement pstmt = con.prepareStatement(sorgu);
            pstmt.setString(1,email);
            pstmt.setString(2,password);
            
            ResultSet rs= pstmt.executeQuery();//dönecek sonucu tutmak
            
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
                         
     
    }

    public Operation() {
        
        //url-> jdbc:mysql://host:port/db_name;id;password;
        
        String url= "jdbc:sqlite:data.sqlite";
        
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(url);
            System.out.println("Veri tabanına bağlandı");        
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Driver Çalışmadı :/");
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection çalışamadı :/");
        }
                
    }
    
    public static void main(String[] args){
        Operation op =new Operation();
    }
}
