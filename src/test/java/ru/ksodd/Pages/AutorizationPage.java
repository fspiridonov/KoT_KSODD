package ru.ksodd.Pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.ksodd.Helpers.LoggerConsole;
import ru.ksodd.Helpers.TestHelper;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.sleep;

public class AutorizationPage {

    public static void fillLogin(String login) throws FileNotFoundException, IOException, AWTException {
//        Ввожу логин login
        WebElement fieldLogin = $(By.xpath("//*[@placeholder='Имя пользователя']"));
//        Robot r = new Robot();
//        r.keyPress(KeyEvent.VK_F11);
        try {
            $(By.xpath("//*[@placeholder='Имя пользователя']")).should(Condition.visible);
            actions().moveToElement(fieldLogin).click().sendKeys(login).perform();
            LoggerConsole.LoggNotError("Логин введен в поле 'Логин'");
        } catch (Error e) {
            LoggerConsole.Logg("Поле не существует | Поле не активно");
        }
    }

    public static void fillPassword(String password) throws FileNotFoundException, IOException {
//        Ввожу пароль password
        WebElement fieldPassword = $(By.xpath("//*[@placeholder='Пароль']"));
        try {
            $(By.xpath("//*[@placeholder='Пароль']")).should(Condition.visible);
            actions().moveToElement(fieldPassword).click().sendKeys(password).perform();
            LoggerConsole.LoggNotError("Пароль введен в поле 'Пароль'");
        } catch (Error e) {
            LoggerConsole.Logg("Поле не существует | Поле не активно");
        }
    }

    public static void exit() throws IOException {
        WebElement street = $(By.xpath("//a[@href='/logout']"));
        sleep(3000);
        try {
            actions().click(street).perform();
            LoggerConsole.LoggNotError("Клик на кнопку 'Выйти'");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не произошел клик на кнопку 'Выйти' | Кнопка не активна");
        }
    }

    public static void exit1() throws IOException {
        WebElement street = $(By.xpath("//a[@href='/logout']"));
        sleep(3000);
        try {
            actions().click(street).perform();
            LoggerConsole.LoggNotError("↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ Последний шаг ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑");
        } catch (AssertionError err) {
            LoggerConsole.Logg("");
        }
    }

}

