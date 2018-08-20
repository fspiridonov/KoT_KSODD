package ru.ksodd.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import ru.ksodd.Helpers.LoggerConsole;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static java.lang.Thread.sleep;
import static ru.ksodd.Helpers.LoggerConsole.Logg;
import static ru.ksodd.Helpers.StorageString.stringNumberDoc.numberDoc;

public class TZPage {


    public static void inputFieldType1(String type) throws IOException, InterruptedException {
        sleep(1500);
        WebElement link = $(By.xpath("//*[@id='formMVKS']/div[4]/div/div/div/div[1]"));
        try {
            actions().click(link).sendKeys(type).sendKeys(Keys.ENTER).sendKeys(Keys.ESCAPE).perform();
            LoggerConsole.LoggNotError("Ввод в поле");
        } catch (AssertionError err) {
            Logg("Не произошел ввод в поле");
        }
    }

        public static void inputField(String txt, String txt1) throws InterruptedException {
            sleep(1500);
            WebElement comment = $(By.xpath("//*[@aria-label='" + txt1 + "']"));
            actions().click(comment).sendKeys(txt).perform();
        }

    public static void DnD(int colomn1, int colomn2) throws InterruptedException {
        WebElement drag = $(By.xpath("//div[@data-index='"+colomn1+"']/div/div/div/div[2]/div[text()[contains(.,'"+numberDoc+"')]]"));
        WebElement drop = $(By.xpath("//div[@data-index='"+colomn2+"']/div"));
        actions().dragAndDrop(drag,drop).perform();
        sleep(1500);
    }

    public static void inputStreet(String txt) throws IOException {
        WebElement link = $(By.xpath("//*[@id='formMVKS']/div[2]/div/div/div/div[1]/div[1]/input"));
        try {
            actions().click(link).sendKeys("1-й Добрынинский переулок").sendKeys(Keys.ENTER).sendKeys(Keys.ESCAPE).perform();
            LoggerConsole.LoggNotError("Ввод в поле");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не произошел ввод в поле");
        }

    }

    }
