package ru.ksodd.Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import ru.ksodd.Helpers.LoggerConsole;
import ru.ksodd.Helpers.TestHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.Thread.sleep;
import static ru.ksodd.Helpers.LoggerConsole.Logg;
import static ru.ksodd.Helpers.LoggerConsole.LoggNotError;
import static ru.ksodd.Helpers.StorageString.stringNumberDoc.numberDoc;


public class MVKPage {

    public static String[] nameFileLoad = {"Проект DGN", "Натурные обследования", "Проект PDF", "Проект DGN", "Первичный проект", "Согласовательная документация", "График проведения работ",
            "Проект DGN", "Изменения PDF", "Выписка протокола МВК", "Акт проведения авторского надзора"};
    public static List<String> nameFile1 = new ArrayList<>();
    public static String[] yyy = {};
    public static List<String> nameCheck = new ArrayList<String>();
    public static String[] zzz = {};


    public static void testNameLoadingFile() throws IOException, InterruptedException {
        ElementsCollection collection = $$(By.xpath("//div[@class='b-block i-bem b-block_js_inited']/label[text()]"));
        sleep(1500);

        //Собирает данные на странице
        for (SelenideElement aCollection : collection) {
            String ch = aCollection.getText();
            nameFile1.add(ch);
        }
        //Цикл, который проверяет наименовании из массива nameFileLoad в nameFile1
        for (int i = 0; i < nameFile1.size(); i++) {
            yyy = nameFile1.toArray(new String[i]);
            if (yyy[i].equals(nameFileLoad[i])) {
                TestHelper.errorCrarh();
            }
        }
    }


    public static void clickButton(String id) throws IOException, InterruptedException {
        WebElement but = $(By.xpath("//div[@class='kanban-column']['" + id + "']/div[3]/button/div"));
        try {
            sleep(2000);
            actions().click(but).perform();
            LoggNotError("Произошел клик на кнопку");
        } catch (Error e) {
            Logg("Не произошел клик на кнопку");
        }
    }

    public static void clickButtonAndField(String txt, String txt1) throws IOException {
//        String timeNameVar = String.valueOf(System.currentTimeMillis());
        numberDoc = String.valueOf(System.currentTimeMillis());
        WebElement but = $(By.xpath("//div/*[@aria-label='" + txt1 + "']"));
        try {
            actions().click(but).sendKeys(numberDoc).perform();
//            numberDoc = but.getText();
            LoggNotError("Произошел клик на поле " + txt1 + " и ввод значении в поле");
        } catch (Error e) {
            Logg("Не произошел клик на кнопку");
        }
    }

    //Ввод и выбор улицы
    public static void inputFieldStreet(String name) throws IOException, InterruptedException {  //вводит данные в поле
        sleep(1500);
        WebElement link = $(By.xpath("//*[@id='formMVKS']/div[2]/div/div/div/label"));
//        WebElement strlist = $(By.xpath("//a[contains(text(),'"+name+"')]"));

        try {

            actions().click(link).sendKeys("1-й Добрынинский переулок").sendKeys(Keys.ENTER).sendKeys(Keys.ESCAPE).perform();
            LoggerConsole.LoggNotError("Ввод в поле");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не произошел ввод в поле");
        }

    }

    //Выбор типа
    public static void inputFieldType(String type) throws IOException, InterruptedException {
        sleep(1500);
        WebElement link = $(By.xpath("//*[@id='formMVKS']/div[5]/div/div/div/div[1]/div[1]/input"));
        try {
            actions().click(link).sendKeys(type).sendKeys(Keys.ENTER).sendKeys(Keys.ESCAPE).perform();
            LoggerConsole.LoggNotError("Ввод в поле");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не произошел ввод в поле");
        }
    }

    public static void testStorage() throws InterruptedException {
        sleep(3000);
        $(By.xpath("//div[contains(text(),'" + numberDoc + "')]/../../div[3]/div/div[text()]")).isEnabled();
        String xpath = "//div[contains(text(),'" + numberDoc + "')]/../../div[3]/div/div[text()]";
        ElementsCollection collect = $$(By.xpath(xpath));

        for (SelenideElement aCollection : collect) {
            String ch = aCollection.getText();
            nameCheck.add(ch);
        }
    }

    public static void clickCheck(String txt) throws IOException, InterruptedException {
        sleep(4000);
        WebElement xpath = $(By.xpath("//div[text()[contains(.,'" + numberDoc + "')]]/../../div[3]/div/div/div[text()='" + txt + "']"));
        actions().click(xpath).perform();

    }

    public static void inputField(String txt, String nameField) throws InterruptedException {
        sleep(1500);
        WebElement comment = $(By.xpath("//*[@aria-label='"+nameField+"']"));
        actions().click(comment).sendKeys(txt).perform();
    }

//    public static void clickButtonForm(){
//        WebElement button = $(By.xpath("//button[@type='button'][1]/div[@class='btn__content']"));
//        actions().click(button).perform();
//    }

    public static void clickSave(){
        WebElement xpath = $(By.xpath("//button/div[@class='btn__content']"));
        actions().click(xpath).perform();
    }

    public static void clickSaveNext(){
        WebElement xpath = $(By.xpath("//button/div[@class='btn__content']"));
        actions().click(xpath).pause(1000).sendKeys(Keys.TAB).pause(1000).sendKeys(Keys.ENTER).perform();
    }

    public static void DnD(int colomn1, int colomn2) throws InterruptedException {
        WebElement drag = $(By.xpath("//div[@data-index='"+colomn1+"']/div/div/div/div[2]/div[text()[contains(.,'"+numberDoc+"')]]"));
        WebElement drop = $(By.xpath("//div[@data-index='"+colomn2+"']/div"));
        actions().dragAndDrop(drag,drop).perform();
        sleep(1500);
    }

}



