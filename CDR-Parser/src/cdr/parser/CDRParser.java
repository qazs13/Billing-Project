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
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.sql.SQLException;

/**
 *
 * @author ahmed
 */
public class CDRParser {

    public static void getFile() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException, InterruptedException {
        
        String path = new File(".").getCanonicalPath();
        String cdrPath = path.concat("/cdr");
        String archivePath = path.concat("/archivecdr");
        File cdrdir = new File(cdrPath);
        File archivedir = new File(archivePath);

        String[] files = cdrdir.list();
        while (true) {
            if (files.length == 0) {
                System.out.println("dir is Empty");

            } else {

                for (String filename : files) {
                    File cdr = new File(cdrPath + "/" + filename);
                    File arccdr = new File(archivePath + "/archive" + filename);
                    FileInputStream fis = new FileInputStream(cdrPath + "/" + filename);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    readFile(br);

                    Files.move(cdr.toPath(), arccdr.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    br.close();
                    fis.close();
                    System.out.println("moved succes");

                }
                monitorDirectory();
            }

        }
    }

    public static void readFile(BufferedReader br) throws IOException, ClassNotFoundException, SQLException {
        Cdr cdr = new Cdr();
        String strline = null;
        while ((strline = br.readLine()) != null) {

            ConnectDB con = new ConnectDB();
            String[] splited = strline.split("\\,");
            if (splited.length > 1) {
                cdr.diala = splited[0];
                cdr.dialb = splited[1];
                cdr.sid = Integer.parseInt(splited[2]);
                cdr.duration_msg_vol = Integer.parseInt(splited[3]);
                cdr.start_date = splited[4];
                cdr.start_time = splited[5];
                cdr.external_charges = splited[6];
                con.insertCdr(cdr);
            }
        }

    }

    public static void monitorDirectory() throws IOException, InterruptedException, FileNotFoundException, ClassNotFoundException, SQLException {
        String path = new File(".").getCanonicalPath();
        String cdrPath = path.concat("/cdr");
        Path cdrFolder = Paths.get(cdrPath);
        WatchService watchService = FileSystems.getDefault().newWatchService();
        cdrFolder.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

        boolean valid = true;
        do {
            WatchKey watchKey = watchService.take();

            for (WatchEvent event : watchKey.pollEvents()) {
                WatchEvent.Kind kind = event.kind();
                if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
                    String fileName = event.context().toString();
                    System.out.println("File Created:" + fileName);
                    getFile();
                }
            }
            valid = watchKey.reset();

        } while (valid);

    }

    public static void mkcdrDirs() throws IOException {
        String path = new File(".").getCanonicalPath();
        String cdrPath = path.concat("/cdr");
        String archivePath = path.concat("/archivecdr");
        File cdrdir = new File(cdrPath);
        File archivedir = new File(archivePath);
        if (!cdrdir.exists() && !archivedir.exists()) {
            cdrdir.mkdir();
            archivedir.mkdir();

        }
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException, InterruptedException {
        mkcdrDirs();
        monitorDirectory();

    }

}
