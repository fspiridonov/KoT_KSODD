package ru.ksodd.Helpers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import cucumber.api.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;
import static ru.ksodd.Helpers.LoggerConsole.Logg;
import static ru.ksodd.Helpers.LoggerConsole.LoggNotError;


public class ButtonsUtil {

    public static void clickSpanButton(String text) throws FileNotFoundException, IOException {
        sleep(1500);
        try {
            $(By.xpath("//button//span[text()='" + text + "'] | //button//div[text()='" + text + "']")).should(Condition.visible);
            $(By.xpath("//button//span[text()='" + text + "'] | //button//div[text()='" + text + "']")).click();
            LoggNotError("Произошел клик на кнопку '" + text + "'");
        } catch (Error e) {
            Logg("Не произошел клик на кнопку '" + text + "'");
        }
    }

    public static void NewRealRequest() throws FileNotFoundException, IOException {
        sleep(1500);
        if (($(By.xpath("//span[text()='Создать запрос']")).exists())) {
            $(By.xpath("//span[text()='Создать запрос']")).click();
            LoggNotError("Произошел клик на кнопку 'Создать запрос' на этапе реализации");
        } else {
            Logg("Не произошел клик на кнопку 'Создать запрос' на этапе реализации");
        }
        sleep(1500);

    }
    //span[text()='Создать запрос']

    public static void clickEditInitiative() throws FileNotFoundException, IOException {
//        Кликает кнопку изменить инициативу
        if ($(By.xpath("//span[contains(@class, 'pencil')]/ancestor::button")).exists()) {
            $(By.xpath("//span[contains(@class, 'pencil')]/ancestor::button")).click();
            LoggNotError("Произошел клик на кнопку 'Изменить'");
        } else {
            Logg("Не произошел клик кнопки 'Изменить'");
        }
        $(By.xpath("//h3[text()='Изменить инициативу']")).waitUntil(Condition.visible, 3000);
    }

    public static void clickByInputValue(String val) throws IOException {
//        Кликает кнопку, текст который в value инпута
        sleep(1500);
        WebElement button = $(By.xpath("//button//input[@value='" + val + "']"));
        sleep(1500);
        try {
            actions().click(button).perform();
            LoggerConsole.LoggNotError("Произошел клик на поле " + val + "");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не произошел клик на поле " + val + "");
        }
    }

    public static void clickLeftIcon(String nameIcon) throws IOException {
        WebElement button = $(By.xpath("//a[@class='btn' and @title='" + nameIcon + "']"));
        sleep(1500);
        try {
            actions().click(button).perform();
            LoggerConsole.LoggNotError("Произошел клик на поле " + nameIcon + "");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не произошел клик на поле " + nameIcon + "");
        }
    }
}
