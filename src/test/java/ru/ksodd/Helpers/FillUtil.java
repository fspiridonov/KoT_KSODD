package ru.ksodd.Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.sleep;

public class FillUtil {

    public static void workDescription(String form, String description) throws IOException {
//        Заполняет поле Описание работ на форме с заголовком form текстом description
        WebElement street = $(By.xpath("//div[text()='" + form + "']/ancestor::div[3]//textarea[@name='working']"));
        sleep(3000);
        try {
            actions().click(street).sendKeys(description).perform();
            LoggerConsole.LoggNotError("Заполнение поля 'Cодержания работ'");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не произошло заполнения поля 'Содержания работ'");
        }
    }

}
