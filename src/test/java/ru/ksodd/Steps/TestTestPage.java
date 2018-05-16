package ru.ksodd.Steps;

import com.codeborne.selenide.Selenide;
import cucumber.api.CucumberOptions;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.bytebuddy.implementation.bytecode.Throw;
import ru.ksodd.Helpers.ButtonsUtil;
import ru.ksodd.Helpers.TestHelper;
import ru.ksodd.Pages.InitiativePage;
import ru.ksodd.Pages.PackagePage;
import ru.ksodd.Pages.RequestPage;

import java.awt.*;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.sleep;
import static java.lang.Thread.sleep;

public class TestTestPage {

    public static String street = "Улица Варварка"; //Название улицы
    public static String shortStreet = "Ул"; //Два первых символа названия улицы для сортировки и ее проверки
    public static String[] importance = {"Важная"}; //Типы статусов инициатив


    @When("^Создание нового инициатива с \"(.*)\" улицы")
    public static void createNewInit(String str) throws InterruptedException, AWTException, IOException {

        street = str;
        shortStreet = street.substring(0, 2); // В переменную записываются 2 символа с 0 позиции значения переменной street
//      Создаю новые инициативы - на каждый статус по одной (бегаю по циклом по массиву)
        for (int i = 0; i < importance.length; i++) {
            Selenide.sleep(1000);
            ButtonsUtil.clickSpanButton("Новая инициатива");
            TestHelper.testOpenWindows("Новая инициатива");
            Selenide.sleep(3000);
            ButtonsUtil.clickSpanButton("Отмена");
            ButtonsUtil.clickSpanButton("Новая инициатива");
            TestHelper.testOpenWindows("Новая инициатива");
            InitiativePage.fillInput("Новая инициатива", "Улица", shortStreet);// ввести значение shortStreet перед запуском.
            InitiativePage.selectStreet("Улица", street); // ввести название улицы (street) перед запуском.
            Selenide.sleep(1000);
            InitiativePage.workDescriptionInit("Новая инициатива", str);
            Selenide.sleep(1000);
            InitiativePage.selectImportance(importance[i]);
            TestHelper.loadFile("Initiative.txt");
            ButtonsUtil.clickSpanButton("Создать");
//            Проверяю данные
            Selenide.sleep(2000); //[После удаления инициатив]
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
    @When("^Создание Запрос из Инициативы с помощью DragAndDrop с типами \"(.*)\" и длинны \"(.*)\"$")
    public static void initByDragAndDrop(String type, String length) throws AWTException, InterruptedException, IOException {
        RequestPage.countStreetInitiatives(TestInitiativePage.street); // Заношу список инициатив с данной улицей в массив

            TestHelper.scrollToBottom("0", "InitiativeShow");
            Selenide.sleep(3000);
            TestHelper.dragAndDrop("0","1");
//            RequestPage.testInitSelection(InitiativePage.listOfVarvarkaInitiatives.get(i));
//            Проверяю, что все созданные нами по данной улице инициативы подрузились после ее выбора
            Selenide.sleep(4000);
            RequestPage.testInitsByStreet();
            Selenide.sleep(4000);
            ButtonsUtil.clickByInputValue("Выберите тип");
            Selenide.sleep(4000);
            RequestPage.selectReq(type);
            RequestPage.fillDistance(length);
            Selenide.sleep(4000);
            RequestPage.workDescriptionReq("Новый запрос");
            Selenide.sleep(4000);
            RequestPage.uploadReqFiles();
            ButtonsUtil.clickSpanButton("Создать");
            Selenide.sleep(4000);
        }


    @Then("^test$")
    public static void testCheckbox1(String nameCheck) throws IOException {
        ButtonsUtil.clickSpanButton("Новый пакет");
        PackagePage.waitOpenPackage();
        ButtonsUtil.clickSpanButton("Отмена");
        RequestPage.fellChecboxedRequest("Принято в работу");
        ButtonsUtil.clickSpanButton("Согласовать");
        RequestPage.fellChecboxedRequest("Натурные обследования");
        ButtonsUtil.clickSpanButton("Согласовать");

    }

    @When("^Создание нового пакета$")
    public static void createPacket() throws IOException {
        ButtonsUtil.clickSpanButton("Новый пакет");
        PackagePage.waitOpenPackage();
        ButtonsUtil.clickSpanButton("Отмена");
        ButtonsUtil.clickSpanButton("Новый пакет");
        PackagePage.waitOpenPackage();
        ButtonsUtil.clickSpanButton("Создать");
    }


}





























