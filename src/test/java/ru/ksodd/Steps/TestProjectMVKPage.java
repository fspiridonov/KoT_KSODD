package ru.ksodd.Steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ru.ksodd.Helpers.ButtonsUtil;
import ru.ksodd.Helpers.TestHelper;
import ru.ksodd.Pages.PackagePage;
import ru.ksodd.Pages.RequestPage;

import java.awt.*;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.sleep;
import static ru.ksodd.Helpers.StorageString.contentOfWorks.cWork;
import static ru.ksodd.Helpers.TestHelper.errorCrarh;

public class TestProjectMVKPage {

    @When("^войти в Проекты МВК$")

    public static void testPackegeDrop() throws AWTException, IOException {
        // проверяю, перетаскивание из запроса в пакет
        sleep(2000);
        PackagePage.mvkProject();
    }

    @When("^заполнят поле Организация$")
    public static void MvkProjectFeelOrg() throws AWTException, IOException {
        sleep(4000);
        PackagePage.mvkProjectFeelOrg();

    }

    @Then("^заполнят поле Содержание работ$")
    public void MvkProjectContentWorks() throws IOException {
        sleep(4000);
        PackagePage.mvkProjectcontentWorks();
    }


    @When("^заполнить поле Дата реализации$")
    public static void mvkProjectdaterealise() throws AWTException, IOException {
        PackagePage.mvkProjectdaterealise();
    }
    @When("^загрузить файлы Проекты МВК$")
    public static void LoadFileS() throws AWTException, IOException {
        PackagePage.LoadFiles();
    }
}