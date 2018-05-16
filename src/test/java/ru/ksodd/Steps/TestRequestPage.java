package ru.ksodd.Steps;


import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ru.ksodd.Helpers.ButtonsUtil;
import ru.ksodd.Helpers.TestHelper;
import ru.ksodd.Pages.InitiativePage;
import ru.ksodd.Pages.RequestPage;


import java.awt.*;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.sleep;

public class TestRequestPage {


    @When("^create new request from initiative with type \"(.*)\" and length of \"(.*)\"")
    public static void createRequestOnInit(String type, String length) throws IOException {
//          По циклу создаю по запросу на каждый тип инициативы
//        RequestPage.countStreetInitiatives(TestInitiativePage.street);
        for (int i = 0; i < 3; i++) {
            sleep(10000);
            ButtonsUtil.clickSpanButton("Новый запрос");
            sleep(2000);
            InitiativePage.fillInput("Новый запрос", "Улица", TestInitiativePage.shortStreet);
            sleep(2000);
            RequestPage.selectStreet("Улица", TestInitiativePage.street);
//            Проверяю, что все созданные нами по данной улице инициативы подгрузились после ее выбора
            sleep(2000);
            RequestPage.testInitsByStreet();
            ButtonsUtil.clickByInputValue("Выберите тип");
            sleep(2000);
            RequestPage.selectReq(type);
            RequestPage.fillDistance(length);
            RequestPage.testAdjustmentDate();
            sleep(2000);
            RequestPage.workDescriptionReq("Новый запрос");
            RequestPage.selectInitiative(InitiativePage.listOfVarvarkaInitiatives.get(i), i, type);
            RequestPage.uploadReqFiles();

            RequestPage.reqValues = new String[]{TestInitiativePage.street, length, type, InitiativePage.listOfVarvarkaInitiatives.get(i)};
            RequestPage.reqMap.put(RequestPage.listOfRequests.get(i), RequestPage.reqValues);

            sleep(2000);
            ButtonsUtil.clickSpanButton("Создать");
            sleep(2000);

        }

    }

    @Then("^test request creation$")
    public static void testReqData() throws IOException {
//    Проверяю, что запрос создан и висит на доске
        for (int i = 0; i < RequestPage.listOfRequests.size(); i++) {
            String currentReq = RequestPage.listOfRequests.get(i);
            RequestPage.testRequestExist(currentReq);
            RequestPage.openRequest(currentReq);
            RequestPage.testRequestData(currentReq);

        }

        RequestPage.reqMap.clear();
        RequestPage.listOfRequests.clear();
    }


    @When("^create request from init by drag&drop with type \"(.*)\" and length of \"(.*)\"$")
    public static void initByDragAndDrop(String type, String length) throws AWTException, IOException {
        RequestPage.countStreetInitiatives(TestInitiativePage.street); // Заношу список инициатив с данной улицей в массив

        for (int i = 0; i < 3; i++) {
            sleep(8000);
            TestHelper.scrollToBottom("0", "InitiativeShow");
            RequestPage.dragInitToRequest(InitiativePage.listOfVarvarkaInitiatives.get(i),"0","1"); // Перетаскиваю инициативу на кнопку Новый запрос
            RequestPage.testInitSelection(InitiativePage.listOfVarvarkaInitiatives.get(i));
//            Проверяю, что все созданные нами по данной улице инициативы подрузились после ее выбора
            sleep(1000);
            RequestPage.testInitsByStreet();
            sleep(3000);
            ButtonsUtil.clickByInputValue("Выберите тип");
            sleep(1000);
            RequestPage.selectReq(type);
            RequestPage.fillDistance(length);
//        ToDo проверку рассчета дат Корректировки и Согласования!!!
            RequestPage.workDescriptionReq("Новый запрос");
//        ToDo кликаем на подтянувшиеся при создании инициативы, проверяем данные
      //      RequestPage.robothelperInitSelection();
//            RequestPage.testInitSelection(InitiativePage.listOfVarvarkaInitiatives.get(i));
            sleep(1000);
            RequestPage.uploadReqFiles();

            RequestPage.reqValues = new String[]{TestInitiativePage.street, length, type, InitiativePage.listOfVarvarkaInitiatives.get(i)};
            RequestPage.reqMap.put(RequestPage.listOfRequests.get(i), RequestPage.reqValues);

            ButtonsUtil.clickSpanButton("Создать");


        }

    }
    @When("^check for negative scenarios with type \"(.*)\"$")
    //проверка на негативные сценарии
    public static void testErr(String type) throws AWTException, IOException {
        ButtonsUtil.clickSpanButton("Новый запрос");
        ButtonsUtil.clickSpanButton("Создать");
        sleep(1000);
        RequestPage.modalClickOk();
        RequestPage.testError();
        ButtonsUtil.clickByInputValue("Выберите тип");
        RequestPage.selectReq(type);
        sleep(1500);
        ButtonsUtil.clickSpanButton("Создать");
        RequestPage.modalClickOk();
        RequestPage.testError();
//        RequestPage.fillDistance("test");
        RequestPage.fillDistance("5000");
        ButtonsUtil.clickSpanButton("Создать");
        RequestPage.modalClickOk();
//        RequestPage.testError();
        RequestPage.fillWorkDescription("Проверка");
        ButtonsUtil.clickSpanButton("Создать");
        RequestPage.modalClickOk();
        ButtonsUtil.clickSpanButton("Отмена");
    }

    @When("^click on add to work$")
    public static void clickAdd() {
        RequestPage.nameTabsInsideQuery();
        RequestPage.clickHarmonizationInQuery("АМПП");
        RequestPage.clickCloseHarmInQuery();
        RequestPage.clickHarmonizationInQuery("МГТ");
        RequestPage.clickCloseHarmInQuery();
        RequestPage.clickHarmonizationInQuery("Дирекция ОДД");
        RequestPage.clickCloseHarmInQuery();
        RequestPage.clickHarmonizationInQuery("Утверждение ПОДД");
        RequestPage.clickCloseHarmInQuery();
        RequestPage.clickHarmonizationInQuery("Утверждение ПОДД");
        RequestPage.clickCloseHarmInQuery();
        RequestPage.clickCloseQuery();
        sleep(1000);


        //RequestPage.testActFalseCheck()

    }

    @When("^negative feelform on add to job$")
    public static void negativeAddJob() throws AWTException {
//    проверяем наличие подсказки обязательных полей и чекбокс отклонения.
//        RequestPage.testActCheck();
        RequestPage.testRequestCheck();
    }
    @When("^design checkbox test$")
    public static void designFilesNegativeTest() throws IOException {
        //проверяю наличие подсказок, при использовании файлов неверного формата для прекрепления в задачу войдя в чекбокс(проектирование)
        RequestPage.checkboxDesign();
        RequestPage.designFilesNegativeTest();
        ButtonsUtil.clickSpanButton("Выполнено");
//        RequestPage.existDetalFiles("Введите файл в формате .dgn");
//        RequestPage.existDetalFiles("Введите файл в формате .pdf");
        RequestPage.clickCloseHarmInQuery();
        //проверяю работу фильтра " Только "не согласованные" " для колонки задач
        RequestPage.TestfilterShowDenied();
        RequestPage.enterClickQuery();
        RequestPage.existFilterShowDenied();
        ButtonsUtil.clickSpanButton("Отмена");
        RequestPage.TestfilterShowDenied();
    }


}



