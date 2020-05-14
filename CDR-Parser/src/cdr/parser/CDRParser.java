package cdr.parser;

import CdrModel.Cdr;
import Database.ConnectDB;
import java.io.*;
import java.nio.file.*;
import java.sql.SQLException;

/**
 *
 * @author ahmed
 */
public class CDRParser {

    public  void getFile() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException, InterruptedException {
        
        String path = new File(".").getCanonicalPath();
        String cdrPath = path.concat(File.separator+"cdr");
        String archivePath = path.concat(File.separator+"archivecdr");
        File cdrdir = new File(cdrPath);

        String[] files = cdrdir.list();
        while (true) {
            if (files.length == 0) {
                System.out.println("dir is Empty");

            } else {

                for (String filename : files) {
                    File cdr = new File(cdrPath + File.separator + filename);
                    File arccdr = new File(archivePath + File.separator +"archive" + filename);
                    FileInputStream fis = new FileInputStream(cdrPath + File.separator + filename);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    readFile(br);
                    br.close();
                    fis.close();             
                    Files.move(cdr.toPath(), arccdr.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("moved succes");

                }
                monitorDirectory();
            }
        }
    }

    public  void readFile(BufferedReader br) throws IOException, ClassNotFoundException, SQLException {
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
                cdr.external_charges = Double.parseDouble(splited[6]);
                con.insertCdr(cdr);
            }
        }

    }

    public  void monitorDirectory() throws IOException, InterruptedException, FileNotFoundException, ClassNotFoundException, SQLException {
        String path = new File(".").getCanonicalPath();
        String cdrPath = path.concat(File.separator + "cdr");
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

    public  void mkcdrDirs() throws IOException {
        String path = new File(".").getCanonicalPath();
        String cdrPath = path.concat(File.separator + "cdr");
        String archivePath = path.concat(File.separator + "archivecdr");
        File cdrdir = new File(cdrPath);
        File archivedir = new File(archivePath);
        if (!cdrdir.exists() || !archivedir.exists()) {
            cdrdir.mkdir();
            archivedir.mkdir();
        }
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException, InterruptedException {
        CDRParser cdrParser = new CDRParser();
        cdrParser.mkcdrDirs();
        cdrParser.monitorDirectory();
    }

}
