package ru.ksodd.Steps;

import cucumber.api.java.en.When;
import ru.ksodd.Helpers.TestHelper;
import ru.ksodd.Helpers.dbData;
import ru.ksodd.Pages.TZPage;

import java.awt.*;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class TestTZPage {


    @When("^войти в ТЗ$")
    public static void testPackegeDrop() throws AWTException, IOException, InterruptedException {
        // проверяю, перетаскивание из запроса в пакет
        dbData.db();
        sleep(2000);
        TestHelper.clickIcon("Перейти к ТЗ");
    }

    @When("^Выбор типа в поле Тип$")
    public static void unputField() throws IOException, InterruptedException {
        TZPage.inputFieldType1("ТЗ ");
    }

    @When("^Загрузка файла в форму$")
    public static void loadFile() throws IOException {
        TZPage.loadFile("dgn.txt");
    }

    @When("^Ввод значении \"(.*)\" в поле \"(.*)\" в внутри формы задач1$")
    public static void inputField(String txt, String txt1) throws InterruptedException {
        TZPage.inputField(txt, txt1);
    }

    @When("^Перенос карточки c этапа \"(.*)\" на этап \"(.*)\"1$")
    public static void transferCardTZ(int drag, int drop) throws AWTException, InterruptedException {
        sleep(4000);
        TZPage.DnD(drag, drop);
    }

    @When("^ввод улицы в поле Улицы1$")
    public static void inputStreet() throws IOException {
        TZPage.inputStreet("1-й Белокаменный проезд");
    }

    @When("^Проверка создания карточки ТЗ$")
    public static void testAddCardTZ() throws IOException {
        TZPage.testAddCardTZ();
    }

}
