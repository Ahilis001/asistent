/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import model.Postavke;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author ahilis001
 */
public class Datoteke {
    
    public static String uploadDatoteke(String strUser, String strPass, String strMapaPolazista, String strNazivDatoteke, String strOdredista){
        String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
        String host = "files.000webhost.com";
        String user = strUser; //ahilisaplikacije
        String pass = strPass;
        String filePath = strMapaPolazista + strNazivDatoteke;
        String uploadPath = "public_html/" + strOdredista + strNazivDatoteke;
        
        String strRezultat = "";

        ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
//        System.out.println("Upload URL: " + ftpUrl);

        try {
            URL url = new URL(ftpUrl);
            URLConnection conn = url.openConnection();
            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(filePath);

            byte[] buffer = new byte[2048];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();

            strRezultat = "<p style=color:green>" + strNazivDatoteke + "</p>";
        } catch (IOException ex) {
            strRezultat = "<p style=color:red>" + strNazivDatoteke + "</p>";
        }
        
        return strRezultat;
    }
    
    /**
     * Čita sadržaj datoteke s putanje.
     * @param putanja
     * @return 
     */
    public static StringBuilder citanjeIzDatoteke(String putanja){
        
        try {
            //čitanje iz datoteke
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(putanja),"UTF-8"));
            StringBuilder builder = new StringBuilder();
            String line = null;

            while ( (line = br.readLine()) != null) {
                    builder.append(line);
                    builder.append(System.getProperty("line.separator"));
            }
            
            return builder;
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Čita sadržaj datoteke s putanje.
     * @param strPutanja
     * @param strNazivDatoteke
     * @param strOdrediste
     * @return 
     */
    public static String downloadDatoteke(String strPutanja, String strNazivDatoteke, String strOdrediste){
        
        String strRezultat = "";
        
        try  {

            //ako mapa ne postoji, kreira se
            File directory = new File(strOdrediste);
            if (!directory.exists()){
                directory.mkdir();
            }

            //ako datoteka nije prazna
            if (strNazivDatoteke.contains(".")) { 
                
                if (strPutanja.contains("http")) {

                    //kopiranje datoteka sa stranice
                    Files.copy(new URL(strPutanja + strNazivDatoteke).openStream(), Paths.get(strOdrediste + strNazivDatoteke), StandardCopyOption.REPLACE_EXISTING);
                    
                }

                else{

                    //kopiranje datoteka sa stranice
                    Files.copy(Paths.get(strPutanja + strNazivDatoteke), Paths.get(strOdrediste + strNazivDatoteke), StandardCopyOption.REPLACE_EXISTING);
                }

            }
            strRezultat = "<p style=color:green>" + strNazivDatoteke + "</p>";

        }

        catch (Exception ex) {
            strRezultat = "<p style=color:red>" + strNazivDatoteke + "</p>";
        }
        return strRezultat;
    }
    
    /**
     * Skida sve datoteke
     */
     public static String downloadSvihDatoteka(){
        String strRezultat = "";
        for (Postavke postavke : Postavke.getAlListaPostavki()) {
            if (postavke.getJtbKey().getText().contains("dat")) {
                strRezultat += Datoteke.downloadDatoteke(Postavke.dajPostavku("URLStraniceResursa") + Postavke.dajPostavku("nazivAplikacije") + "/", Postavke.dajPostavku(postavke.getJtbKey().getText()), Postavke.dajPostavku("mapaResursa"));
            }
        }
        
        return strRezultat;
    }
    
    /**
     * Čita sadržaj datoteke s putanje.
     * @param strURL
     * @return 
     */
    public static StringBuilder citanjeIzURLa(String strURL){
        
            
        
        try {
            URL url = new URL(strURL);
//            //za povlačenje preko curl-a, ne radi na MacOS-u
//            String[] command = {"curl ", "-H " ,"POST ", "http://api.openweathermap.org/data/2.5/forecast/daily?q="+gradDrzava+"&units=metric&appid=" + Pocetna.getPostavke().getProperty("meteoOMWKey")};
//            ProcessBuilder process = new ProcessBuilder(command); 
//            Process p;
//            
//            //za povlačenje preko curl-a, ne radi na MacOS-u
//            p = process.start();
//            BufferedReader br =  new BufferedReader(new InputStreamReader(p.getInputStream()));
//            StringBuilder builder = new StringBuilder();
//            String line = null;
//            
//            while ( (line = br.readLine()) != null) {
//                    builder.append(line);
//                    builder.append(System.getProperty("line.separator"));
//            }
            
            //čitanje iz URL
            BufferedReader br =  new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();
            String line = null;
            
            while ( (line = br.readLine()) != null) {
                    builder.append(line);
                    builder.append(System.getProperty("line.separator"));
            }
            
            return builder;
            
        } 
        
        catch (Exception e) {
//            e.printStackTrace();
//            MetodeTajmera.dodajULog("MetodeMisc - CitanjeIzURLa - Greška kod čitanja iz URL-a.");
        }
        return null;
    }
    
    
    /**
     * Kopira datoteku.
     * @param ulaznaDatoteka
     */
    public static void kopirajDatoteku(String ulaznaDatoteka){
        try {
            File source = new File(ulaznaDatoteka);
            File destination = new File("kopija_" + ulaznaDatoteka);
            Files.copy(source.toPath(), destination.toPath());
        } catch (IOException ex) {
//            ex.printStackTrace();
//            MetodeTajmera.dodajULog("MetodeMisc - kopirajDatoteku - Greška kod kopiranja datoteke.");
        }
    }
    
    /**
     * Kopira datoteku.
     * @param ulaznaDatoteka
     * @param izlaznaDatoteka
     */
    public static void kopirajDatoteku(String ulaznaDatoteka, String izlaznaDatoteka){
        try {
            Files.copy(new File(ulaznaDatoteka).toPath(), new File(izlaznaDatoteka).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
//            ex.printStackTrace();
        }
    }  
     /**
     * Upisuje proslijedeni sadržaj u datoteku s putanje.
     * @param nazivDatoteke
     * @param sadrzaj
     */
    public static void upisUDatoteku(String nazivDatoteke, String sadrzaj){
        
        try {

            FileOutputStream outputStream = new FileOutputStream(nazivDatoteke);
            byte[] strToBytes = sadrzaj.getBytes();
            outputStream.write(strToBytes);

            outputStream.close();
        } catch (IOException e) {
//            e.printStackTrace();
//            MetodeTajmera.dodajULog("MetodeMisc - upisUDatoteku - Greška kod upisa u datoteku.");
        }
    }
    
    /**
     * Ako nije povučen niti jedan dogadjaj u datoteku dogadjaja, 
     * postavlja da je nova datoteka dogadjaja kopija stare.
     * @param ulaznaDatoteka
     */
    public static void brisiKopijuDatoteke(String ulaznaDatoteka){
        
        File source = new File(ulaznaDatoteka);
        File destination = new File("kopija_" + ulaznaDatoteka);
        
        //ako ne postoji original ili je prazan
        //briše se stari original, kreira novi i u njega upisuje sadrzaj kopije
        //na kraju se briše kopija
        if (!source.exists() || source.length() == 0) {
            source.delete();
            kopirajDatoteku("kopija_" + ulaznaDatoteka, ulaznaDatoteka);
            destination.delete();
        } 
        
        //ako je povućen novi original
        else {
            destination.delete();
        }
    }
    
    /**
     * download svih datoteka mape koja je na url-u
     * @param strURL
     * @param strOdrediste
     * @return 
     */
    public static String downloadSvihDatotekaMapeSURLa(String strURL, String strOdrediste){
        
        if(strURL.contains("http")){
            try {

               Document doc = Jsoup.connect(strURL).get();
               Elements links = doc.getElementsByTag("a");
               for (Element link : links) {
                   try {

                       //download i zapis datoteke na odrediste
                       Datoteke.downloadDatoteke(strURL, link.text(), strOdrediste);
                   } catch (Exception e) {
                       return "<p style=color:red>" + strOdrediste + "</p>";
                   }
               }
           } catch (IOException ex) {
               return "<p style=color:red>" + strOdrediste + "</p>";

//               ex.printStackTrace();
           }
               return "<p style=color:green>" + strOdrediste + "</p>";

           }
        
        else{
            
            try {

                String[] pathnames;

                File f = new File(strURL);

                // Populates the array with names of files and directories
                pathnames = f.list();
                for (String path : pathnames) {
                    
                    try {

                        //download i zapis datoteke na odrediste
                        Datoteke.downloadDatoteke(strURL, path, strOdrediste);
                    } catch (Exception e) {
                        return "<p style=color:red>" + strOdrediste + "</p>";
                    }
                }
           } catch (Exception ex) {
               return "<p style=color:red>" + strOdrediste + "</p>";

//               ex.printStackTrace();
           }
               return "<p style=color:green>" + strOdrediste + "</p>";

        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static String test1(String strUser, String strPass, String strMapaResursi, String strNazivDatoteke){
        String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
        String host = "files.000webhost.com";
        String user = strUser; //ahilisaplikacije
        String pass = strPass;
        String filePath = strMapaResursi + strNazivDatoteke;
        String uploadPath = "public_html/asistent/"+ strNazivDatoteke;
        
        String strRezultat = "";

//        ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
//        System.out.println("Upload URL: " + ftpUrl);

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

        FTPClient client = new FTPClient();
        FileInputStream fis = null;

        try {
            client.connect(host);
            client.login(user, strPass);

            //
            // Create an InputStream of the file to be uploaded
            //
            String filename = filePath;
            fis = new FileInputStream(filename);

            //
            // Store file to server
            //
            client.storeFile(uploadPath, fis);
            client.logout();
            strRezultat = "<p style=color:green>" + strNazivDatoteke + "</p>";
        } catch (IOException ex) {
            strRezultat = "<p style=color:red>" + strNazivDatoteke + "</p>";
        
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return strRezultat;
    }
    
    public static String test2(String strUser, String strPass, String strMapaResursi, String strNazivDatoteke){
        String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
        String host = "files.000webhost.com";
        String user = strUser; //ahilisaplikacije
        String pass = strPass;
        String filePath = strMapaResursi + strNazivDatoteke;
        String uploadPath = "public_html/asistent/"+ strNazivDatoteke;
        
        int port = 21;
        
        String strRezultat = "";

        FTPClient ftpClient = new FTPClient();
        try {

            ftpClient.connect(host, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File(filePath);

            String firstRemoteFile = uploadPath;
            InputStream inputStream = new FileInputStream(firstLocalFile);

            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                strRezultat = "<p style=color:green>" + strNazivDatoteke + "</p>";
            }
            
            else{
                strRezultat = "<p style=color:red>" + strNazivDatoteke + "</p>";
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        return strRezultat;
    }
    
    public static String test3(String strUser, String strPass, String strMapaResursi, String strNazivDatoteke){
        String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
        String host = "files.000webhost.com";
        String user = strUser; //ahilisaplikacije
        String pass = strPass;
        String filePath = strMapaResursi + strNazivDatoteke;
        String uploadPath = "public_html/asistent/"+ strNazivDatoteke;
        
        int port = 21;
        
        String strRezultat = "";
        
        FTPClient ftpClient = new FTPClient();
        try {

            ftpClient.connect(host, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            
            // APPROACH #2: uploads second file using an OutputStream
            File secondLocalFile = new File(filePath);
            String secondRemoteFile = uploadPath;
            InputStream inputStream = new FileInputStream(secondLocalFile);

            OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
            byte[] bytesIn = new byte[4096];
            int read = 0;

            while ((read = inputStream.read(bytesIn)) != -1) {
                outputStream.write(bytesIn, 0, read);
            }
            inputStream.close();
            outputStream.close();

            boolean completed = ftpClient.completePendingCommand();
            if (completed) {
                strRezultat = "<p style=color:green>" + strNazivDatoteke + "</p>";
            }
            
            else{
                strRezultat = "<p style=color:red>" + strNazivDatoteke + "</p>";
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return strRezultat;
    }
}
