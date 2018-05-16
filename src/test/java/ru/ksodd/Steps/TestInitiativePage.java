package ru.ksodd.Steps;


import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ru.ksodd.Helpers.ButtonsUtil;
import ru.ksodd.Helpers.FillUtil;
import ru.ksodd.Helpers.TestHelper;
import ru.ksodd.Pages.InitiativePage;
import ru.ksodd.Pages.PackagePage;
import ru.ksodd.Pages.RequestPage;

import java.awt.*;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.refresh;
import static java.lang.Thread.sleep;


public class TestInitiativePage {

   public static String street = "Мясницкая улица"; //Название улицы
   public static String shortStreet = "Ул"; //Два первых символа названия улицы для сортировки и ее проверки
//   public static String[] importance = {"Важная", "МВК", "Обычная"}; //Типы статусов инициатив
    public static String[] importance = {"Важная"}; //Типы статусов инициатив





    @When("^Выбирает улицу$")
    public static void SelectStreet () throws InterruptedException {
        shortStreet = street.substring(0, 2); // В переменную записываются 2 символа с 0 позиции значения переменной street
            sleep(1500);
            InitiativePage.fillInput("Новая инициатива", "Улица", shortStreet);// ввести значение shortStreet перед запуском.
            sleep(1500);
            InitiativePage.selectStreet("Улица", street); // ввести название улицы (street) перед запуском.
    }

    @When("^Создание новой инициативы для \"(.*)\"$")
    public static void createNewInit(String str) throws InterruptedException, AWTException, IOException {
        street = str;
        shortStreet = street.substring(0, 2); // В переменную записываются 2 символа с 0 позиции значения переменной street
//      Создаю новые инициативы - на каждый статус по одной (бегаю по циклом по массиву)
        for (int i = 0; i < importance.length; i++) {

            sleep(1500);
            InitiativePage.workDescriptionInit("Новая инициатива", str);

//            TestHelper.testCloseWindowCreatInit("Новая инициатива");
//            Проверяю данные
            sleep(1500); //[После удаления инициатив]
            ButtonsUtil.clickSpanButton("Создать");
            switch (str) {
//                Два массива для инициатив каждой из улицы чтобы после проверки инициатив, если что то пойдет не так, все равно был свободный массив
                case "Мясницкая улица": //Если инициатива создается по улице Варварка, заносим в массив listOfMyasnitskayaInitiatives
                    InitiativePage.testInitiativeData(InitiativePage.listOfMyasnitskayaInitiatives.get(i), importance[i], street);
                    break;
                case "Улица Варварка": //Если инициатива создается по улице Варварка, заносим в массив listOfVarvarkaInitiatives
                    InitiativePage.testInitiativeData(InitiativePage.listOfVarvarkaInitiatives.get(i), importance[i], street);
                    break;
            }

        }
    }

    @Then("^Проверяет, что форма закрылась$")
    public static void estCloseWindow() throws InterruptedException, IOException {

        sleep(3000);
        TestHelper.testCloseWindowCreatInit("Новая инициатива");
    }



    @When("^Выбирает 'Тип' 'Обычная'$")
    public static void InitSelectTypeNormal() {
        InitiativePage.selectImportance("Обычная");
    }

    @When("^Выбирает 'Тип' 'МВК'$")
    public static void InitSelectTypeMVK () {
        InitiativePage.selectImportance("МВК");
    }
    @When("^Выбирает 'Тип' 'Важная'$")
    public static void InitSelectTypeImportance () {
        InitiativePage.selectImportance("Важная");
    }

    @When("^Клик на кнопку 'Новая инициатива'$")
    public static void NewInit () throws IOException {
        ButtonsUtil.clickSpanButton("Новая инициатива");
        TestHelper.testOpenWindows("Новая инициатива");

    }
    @When("^Прикрепляем файл$")
    public static void LoadFileInit () {
        TestHelper.loadFile("Initiative.txt");
    }


    @Then("^test new initiative creating$")
//    Проверяю, что инициатива создалась и висит на доске
    public static void testNewInit() {
        for (int i = 0; i < importance.length; i++) {
            InitiativePage.testInitiative(InitiativePage.listOfMyasnitskayaInitiatives.get(i));
            if (importance[i] == "Важная") {
                InitiativePage.testImportantSign(InitiativePage.listOfMyasnitskayaInitiatives.get(i));
            }

        }
    }


    @When("^delete \"(.*)\" initiative$")
    public static void rejectInitiative(int index) throws IOException {
        InitiativePage.openInitiative(InitiativePage.listOfMyasnitskayaInitiatives.get(index));
        ButtonsUtil.clickSpanButton("Отказать");
        InitiativePage.fillreasonOfDenying("Don't panic!");
        ButtonsUtil.clickSpanButton("Отклонить");
    }

    @Then("^test \"(.*)\" initiative deletion$")
    public static void testInitDel(int init) {
        InitiativePage.testInitiativeDel(InitiativePage.listOfMyasnitskayaInitiatives.get(init));
    }

    @When("^edit initiative$")
    public static void editInit() throws IOException {
        InitiativePage.openInitiative(InitiativePage.listOfMyasnitskayaInitiatives.get(1));
        ButtonsUtil.clickEditInitiative();
        InitiativePage.testInitiativeDataEdit(InitiativePage.listOfMyasnitskayaInitiatives.get(1), importance[1], street);
        InitiativePage.editInitStreet("Потаповский");
        InitiativePage.editDescription();
        InitiativePage.selectImportance("Важная");
        ButtonsUtil.clickSpanButton("Изменить");
        TestHelper.loadFile("Initiative_Edit.txt");
        ButtonsUtil.clickSpanButton("Отмена");

    }

    @Then("^test initiative edition$")
    public static void testEditedInit() throws IOException {
        InitiativePage.testInitiative(InitiativePage.listOfMyasnitskayaInitiatives.get(1));
        InitiativePage.testInitiativeData(InitiativePage.listOfMyasnitskayaInitiatives.get(1), importance[0], "Потаповский переулок");

    }

    @When("^clear array of initiatives$")
    public static void clearLiOfInits() {
        InitiativePage.clearListOf();
    }

    @When("^try to create with an invalid value untrue$")
    public static void createNegativeInit() throws AWTException, IOException {
        // оба обязательных поля пусты поля
        ButtonsUtil.clickSpanButton("Новая инициатива");
        InitiativePage.waitForNegative("Создать");
        ButtonsUtil.clickSpanButton("Создать");
        InitiativePage.modalClickOk();
        InitiativePage.workContentErr();
        // улица пуста
        InitiativePage.workContent();
        ButtonsUtil.clickSpanButton("Создать");
        InitiativePage.modalClickOk();
        InitiativePage.workContentErr();
        InitiativePage.workContentСlean();

        InitiativePage.fillInput("Новая инициатива", "Улица", "1-й Сыромятнический переулок");
        InitiativePage.selectStreet("Улица", "1-й Сыромятнический переулок");
        ButtonsUtil.clickSpanButton("Создать");
        InitiativePage.modalClickOk();
        InitiativePage.workContentErr();
    }

    @When("^filter test$")
    public static void filtertest(){
        InitiativePage.filterTest();

    }

    @When("^check whether the button$")
    public static void checkButton() {
//        InitiativePage.checkButton();
    }

    @When("^выбор ответственного$")
    public static void responsible() throws IOException {
        PackagePage.fillresponsible();
    }
    @When("^входит в последнюю инициативу$")
    public static void InInitiativeLast() throws IOException {
        InitiativePage.inInitiativeLast();
    }
    @When("^клик на редактирование инициативы$")
    public static void InInitiativeRedactor() throws IOException {
        InitiativePage.inInitiativeRedactor();
    }
@When("^редактор поля улицы \"(.*)\"$")
    public static void StreetRedactorInit(String str) throws Throwable {
    InitiativePage.InitiativeRedactorStreet(str);
}

@When("^редактирование содержания работ$")
public static void ContentRedactorInit() throws IOException{
    InitiativePage.fillContentRedactorInit();
}

    @When("^редактирование статуса \"(.*)\"$")
    public static void InitiativeRedactorStatus(String type) throws Throwable{
        InitiativePage.InitiativeRedactorType(type);
    }
    }

