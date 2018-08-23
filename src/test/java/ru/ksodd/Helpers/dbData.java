package ru.ksodd.Helpers;

import java.io.*;
import java.util.Properties;

import static java.lang.Thread.sleep;
import static ru.ksodd.Helpers.StorageString.stringNumberDoc.numberDoc;
//import static ru.ksodd.Helpers.StorageString.localNumberDoc.localNumber;


public class dbData {
    public static String localNumber;

    public static void db() throws IOException, InterruptedException {
        numberDoc = String.valueOf(System.currentTimeMillis());
        Properties p = new Properties();
        OutputStream os = new FileOutputStream("Test.properties");
        p.setProperty("NumberDocument", numberDoc);
        p.setProperty("NumberDocumentKanban",numberDoc);
        p.store(os,null);
        sleep(1000);
    }

    public static void inputDB(String nameDB) throws IOException {
        FileInputStream fis;
        Properties property = new Properties();
        fis = new FileInputStream("Test.properties");
        property.load(fis);
        localNumber = property.getProperty(nameDB);
    }
}
