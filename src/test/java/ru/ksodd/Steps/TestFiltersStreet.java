package ru.ksodd.Steps;

import cucumber.api.java.en.When;
import ru.ksodd.Helpers.ButtonsUtil;
import ru.ksodd.Pages.StreetFiltrePage;

import java.io.IOException;

public class TestFiltersStreet {

    @When("^перейти к форме фильтра улиц$")
    public static void enterFiltreStreet() throws IOException {
        ButtonsUtil.clickLeftIcon("Перейти к форме фильтра улиц");
    }

    @When("^открыть список в поле \"Округ\"$")
    public static void clickCountry() throws IOException {
        StreetFiltrePage.clickFieldFilter("Округ");
    }

    @When("^ввод округ в поле Округ$")
    public static void inputCountry() throws IOException {
        StreetFiltrePage.inputField("ВАО");
    }

    @When("^открыть список в поле \"Район\"$")
    public static void clickArea() throws IOException {
        StreetFiltrePage.clickFieldFilter("Район");
    }

    @When("^ввод район в поле Район$")
    public static void inputArea() throws IOException {
        StreetFiltrePage.inputField("Богородское");
    }

    @When("^открыть список в поле \"Улица\"$")
    public static void clickStreet() throws IOException {
        StreetFiltrePage.clickFieldFilter("Улица");
    }

    @When("^ввод улицы в поле Улица$")
    public static void inputStreet() throws IOException {
        StreetFiltrePage.inputField("1-й Белокаменный проезд");
    }

    @When("^вход в архив$")
    public static void clickArchive() throws IOException {
        StreetFiltrePage.clickArchive();
    }

    @When("^клик на строки работ$")
    public static void clickTabJob() throws IOException {
        StreetFiltrePage.clickTab(1);
        //todo сделать проверку наличие или отсутствие файлов
        StreetFiltrePage.clickTab(2);
        StreetFiltrePage.clickTab(3);
    }

    @When("^клик на кнопку 'Добавить'$")
    public static void clickAdd() throws IOException {
        StreetFiltrePage.clickAddPlus();
    }

    @When("^выбор типа сущпола$")
    public static void choiceType() throws IOException {
        StreetFiltrePage.choiceType("загрузка сущпола");
//        StreetFiltrePage.testNameLoadingFile();
        StreetFiltrePage.choiceType("проекты МВК");
//        StreetFiltrePage.testNameLoadingFile();
        StreetFiltrePage.choiceType("прочие ПОДД");
//        StreetFiltrePage.testNameLoadingFile();
    }

    @When("^Заполнения значении \"(.*)\" в поле \"(.*)\"$")
    public static void inputField(String string, String inputName) throws IOException {
        StreetFiltrePage.clickAndInput(string, inputName);
    }


    @When("^клик на кнопку 'Создать'$")
    public static void clickNew() throws IOException {
        StreetFiltrePage.clickAdd("Создать");
    }

}
