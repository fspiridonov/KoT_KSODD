package ru.ksodd.Helpers;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import ru.ksodd.Helpers.StorageString.stringLogg;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.getWebDriverLogs;
import static com.codeborne.selenide.Selenide.sleep;
import static ru.ksodd.Helpers.StorageString.contentError.error;
import static ru.ksodd.Helpers.StorageString.contentError.setError;
import static ru.ksodd.Helpers.StorageString.stringLogg.*;


public class LoggerConsole {

    public static String nameFile = String.valueOf(System.currentTimeMillis());

    public static void Logg(String logg) {
        sleep(1500);
        try {
            WebElement errorText = $(By.xpath("//div[@class='messenger error']"));
            error = errorText.getText();
        } catch (Error e) {

        } finally {

            Logg = logg;
            getWebDriverLogs(
                    "\n" + "============================================================" + "\n"
                            + logg +
                            "\n" + "____________________________________________________________" + "\n"
                            + error +
                            "\n" + "============================================================" + "\n",
                    Level.WARNING);
        }
    }




    public static void LoggNotError(String nLogg) throws IOException {
        String test = String.valueOf(error);
        String logg = String.valueOf(Logg);
        String test1 = "null";
        String Logg1 = "null";

        String Logging = "Отчеты\\" + "Отчет №" + nameFile + ".txt";
        FileWriter writer = new FileWriter(Logging, true);
        try {
            if (logg.equals(Logg1)) {
                writer.write("\n" + "Шаг:  " + nLogg + "\n");
                writer.flush();
            } else if(test.equals(test1)){
                writer.write("\n" + "Шаг:  " + nLogg + "\n" + "Причина: " + Logg + "\n");
                writer.flush();
            } else {
                writer.write("\n" + "Шаг:  " + nLogg + "\n" + "Строка ошибки: " + error + "\n" + "Причина: " + Logg + "\n");
                writer.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

