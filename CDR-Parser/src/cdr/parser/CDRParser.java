package cdr.parser;

import CdrModel.Cdr;
import Database.ConnectDB;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmed
 */
public class CDRParser {

    public static void getFile() {
        String dirPath = "/home/ahmed/Desktop/cdr/";
        String archivePath = "/home/ahmed/Desktop/archivecdr/";
        File cdrdir = new File(dirPath);
        File archivedir = new File(archivePath);
        String[] files = cdrdir.list();
        if (files.length == 0) {
            System.out.println("dir is Empty");

        } else {
            for (String filename : files) {
                try {
                    FileInputStream fis = new FileInputStream(dirPath + "/" + filename);
                    File cdr = new File(dirPath+ "/" + filename);
                    File arccdr = new File(archivePath+ "/" + filename);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    readFile(br);
                    Files.move(cdr.toPath(), arccdr.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("moved succes");

                } catch (Exception e) {

                }
            }

        }

    }

    public static void readFile(BufferedReader br) throws IOException, ClassNotFoundException, SQLException {

        String strline = null;
        while ((strline = br.readLine()) != null) {
            Cdr cdr = new Cdr();
            ConnectDB con = new ConnectDB();
            String[] splited = strline.split(",");
            cdr.diala = splited[0];
            cdr.dialb = splited[1];
            cdr.sid = Integer.parseInt(splited[2]);
            cdr.duration_msg_vol = Integer.parseInt(splited[3]);
            cdr.start_date = splited[4];
            cdr.start_time = splited[5];
            cdr.external_charges = splited[6];

            System.out.println(cdr.start_date);
            con.insertCdr(cdr);

        }

    }

    public static void main(String[] args) throws IOException {
        getFile();
    }

}
