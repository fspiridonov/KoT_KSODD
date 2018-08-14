package ru.ksodd.Steps;

import cucumber.api.java.en.When;
import ru.ksodd.Pages.MVKPage;

import java.awt.*;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class TestMVKPage {

    @When("^Заполнение значении \"(.*)\" в поле \"(.*)\"$")
    public static void inputField(String txt1, String txt) throws IOException {
        MVKPage.clickButtonAndField(txt1, txt);
    }

    @When("^ввод улицы в поле Улицы$")
    public static void inputStreet() throws IOException, InterruptedException {
        MVKPage.inputFieldStreet("1-й Белокаменный проезд");
    }

    @When("^Выбор типа в поле Тип согласования$")
    public static void inputmatchingType() throws IOException, InterruptedException {
        MVKPage.inputFieldType("МВК");
    }

    @When("^Клик на чек-бокс \"(.*)\"$")
    public static void clickCheckbox(String txt) throws IOException, InterruptedException {
        MVKPage.clickCheck(txt);

    }

    @When("^Ввод значении \"(.*)\" в поле \"(.*)\" в внутри формы задач$")
    public static void inputComment(String txt, String txt1) throws InterruptedException {
        MVKPage.inputField(txt, txt1);
    }

//    @When("^Клик  на кнопку 'Согласовано'$")
//    public static void clickButton1() {
//        MVKPage.clickButtonForm();
//    }

    @When("^Перенос карточки c этапа \"(.*)\" на этап \"(.*)\"$")
    public static void transferCardZakupki(int drag, int drop) throws AWTException, InterruptedException {
        sleep(4000);
        MVKPage.DnD(drag, drop);
    }

    @When("^Клик на кнопку 'Согласовано' на форме$")
    public static void clickSave(){
        MVKPage.clickSave();
    }

    @When("^Клик на кнопку 'Сохранить' на форме$")
    public static void clickSave1(){
        MVKPage.clickSaveNext();
    }
}