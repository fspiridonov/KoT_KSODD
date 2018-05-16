package ru.ksodd.Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import ru.ksodd.Helpers.LoggerConsole;
import ru.ksodd.Helpers.TestHelper;
import sun.awt.windows.WEmbeddedFrame;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static ru.ksodd.Helpers.LoggerConsole.Logg;
import static ru.ksodd.Helpers.LoggerConsole.LoggNotError;


public class StreetFiltrePage {

    public static String[] nameFileLoad = {"Проект DGN", "Натурные обследования", "Проект PDF", "Проект DGN", "Первичный проект", "Согласовательная документация", "График проведения работ",
            "Проект DGN", "Изменения PDF", "Выписка протокола МВК", "Акт проведения авторского надзора"};
    public static List<String> nameFile1 = new ArrayList<>();
    public static String[] yyy = {};


    public static void clickFieldFilter(String name) throws IOException {  //Кликает на поля
        WebElement field = $(By.xpath("//div[@class='dropdown-toggle clearfix']/input[@placeholder='" + name + "']"));
        sleep(1500);
        try {
            actions().click(field).perform();
            LoggerConsole.LoggNotError("Клик на поле " + name + "");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не произошел клик на поле " + name + "");
        }
    }


    public static void inputField(String name) throws IOException {  //вводит данные в поле
        sleep(1500);
        try {
            actions().sendKeys("" + name + "").sendKeys(Keys.ENTER).perform();
            LoggerConsole.LoggNotError("Ввод в поле");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не произошел ввод в поле");
        }

    }


    public static void clickArchive() throws IOException {  //кликает на первый найденный архив по параметрам
        WebElement arch = $(By.xpath("//div[@class='storage-body']/div[@class='storage-body__row'][1]"));
        sleep(1500);
        try {
            actions().doubleClick(arch).perform();
            LoggerConsole.LoggNotError("Клик на архив");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не произошел клик по поле");
        }
    }

    public static void clickTab(int num) throws IOException {
        WebElement tab = $(By.xpath("//div[@class='tabs-list__header']//div[@class='tabs-list__header-item'][" + num + "]"));
        sleep(1500);
        try {
            actions().click(tab).perform();
            LoggerConsole.LoggNotError("Клик на колонку");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не произошел клик на колонку");
        }
    }

    public static void clickAddPlus() throws IOException {
        WebElement add = $(By.cssSelector("#app > div.search > div.search__footer > svg"));
        sleep(1500);
        try {
            actions().click(add).perform();
            LoggerConsole.LoggNotError("Клик на колонку");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не произошел клик на колонку");
        }

    }

    public static void choiceType(String numId) throws IOException {
        WebElement type = $(By.xpath("//button[@class='btn btn-default dropdown-toggle']/*[@id='input']")); //клик на само поле Тип
        WebElement typeList = $(By.xpath("//ul[@class='b-combobox__list dropdown-menu i-bem b-combobox__list_js_inited']/li[@data-id='" + numId + "']")); //выбор из списка
        try {
            actions().click(type).perform();
            LoggerConsole.LoggNotError("Клик на поле тип");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не произошел клик на поле тип");
        }
        try {
            actions().click(typeList).perform();
            LoggerConsole.LoggNotError("Выбор из списка");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не произошел клик на поле тип");
        }
    }


    public static void testNameLoadingFile() throws IOException {
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


    public static void clickAndInput(String name) throws IOException {
        WebElement nameInput = $(By.xpath("//div/label[text()='" + name + "']/../*[@name]"));
        switch (name) {
            case "Организация":
                sleep(1500);
                try {
                    actions().click(nameInput).perform();
                    LoggerConsole.LoggNotError("Клик на поле Организация");
                } catch (AssertionError err) {
                    LoggerConsole.Logg("Не произошел клик на поле Организация");
                }
                try {
                    actions().sendKeys("Спарта").perform();
                    LoggerConsole.LoggNotError("Ввод в поле Организация");
                } catch (AssertionError err) {
                    LoggerConsole.Logg("Не произошел ввод в поле Организация");
                }
                break;
            case "Содержание работ":
                sleep(1500);
                try {
                    actions().click(nameInput).perform();
                    LoggerConsole.LoggNotError("Клик на поле Содержание работ");
                } catch (AssertionError err) {
                    LoggerConsole.Logg("Не произошел клик на поле Содержание работ");
                }
                try {
                    actions().sendKeys("Обучаем будущех перцев для битвы c Персидской армией").perform();
                    LoggerConsole.LoggNotError("Ввод в поле Содержание работ");
                } catch (AssertionError err) {
                    LoggerConsole.Logg("Не произошел ввод в поле Содержание работ");
                }
                break;
            case "Протокол":
                sleep(1500);
                try {
                    actions().click(nameInput).perform();
                    LoggerConsole.LoggNotError("Клик на поле Протокол");
                } catch (AssertionError err) {
                    LoggerConsole.Logg("Не произошел клик на поле Протокол");
                }
                try {
                    actions().sendKeys("1234567890").perform();
                    LoggerConsole.LoggNotError("Ввод в поле Протокол");
                } catch (AssertionError err) {
                    LoggerConsole.Logg("Не произошел ввод в поле Протокол");
                }
                break;
        }
    }


    public static void selectDate(String date) throws IOException {
        WebElement tab = $(By.xpath("//label[text()='" + date + "']/../input[@name='target_date']"));
        WebElement tap = $(By.xpath("//div[@id='ui-datepicker-div']/table/tbody/tr[5]/td"));
        sleep(1500);
        try {
            actions().click(tab).perform();
            LoggerConsole.LoggNotError("Клик на поле календарь");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не произошел клик на поле календарь");
        }
        try {
            actions().click(tap).perform();
            LoggerConsole.LoggNotError("Клик на число в дате");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не произошел клик на число в дате");
        }
    }


    public static void clickAdd(String nameBut) throws IOException {
        WebElement button = $(By.xpath("//button/span[text()='" + nameBut + "']"));
        sleep(1500);
        try {
            actions().click(button).perform();
            LoggerConsole.LoggNotError("Клик на кнопку Создать");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не произошел клик кнопку Создать");
        }
    }
}



