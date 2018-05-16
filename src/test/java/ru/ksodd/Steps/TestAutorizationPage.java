package ru.ksodd.Steps;

import cucumber.api.java.ca.I;
import cucumber.api.java.en.When;
import ru.ksodd.Helpers.ButtonsUtil;
import ru.ksodd.Helpers.TestHelper;
import ru.ksodd.Pages.AutorizationPage;
import ru.ksodd.Pages.RequestPage;

import java.awt.*;
import java.io.IOException;

public class TestAutorizationPage {

    @When("^autorization with login \"(.*)\" and password \"(.*)\"$")
    public static void login(String log, String pas) throws IOException, AWTException {
        AutorizationPage.fillLogin(log);
        AutorizationPage.fillPassword(pas);
        ButtonsUtil.clickSpanButton("Войти");
    }

    @When("^вводим логин \"(.*)\"$")
    public static void loginL(String log) throws IOException, AWTException {
        AutorizationPage.fillLogin(log);
    }

    @When("^вводим пароль \"(.*)\"$")
    public static void loginP(String pas) throws IOException {
        AutorizationPage.fillPassword(pas);
    }

    @When("^клик на кнопку 'Войти'$")
    public static void enter() throws IOException {
        ButtonsUtil.clickSpanButton("Войти");
    }

    @When("^logout$")
    public static void logOut() throws IOException {
        AutorizationPage.exit();
    }

    @When("выход из системы")
    public static void stop() throws IOException {
        AutorizationPage.exit1();
    }
}
