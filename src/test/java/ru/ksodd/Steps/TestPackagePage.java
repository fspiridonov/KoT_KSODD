package ru.ksodd.Steps;

import com.codeborne.selenide.Selenide;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import ru.ksodd.Helpers.ButtonsUtil;
import ru.ksodd.Helpers.TestHelper;
import ru.ksodd.Pages.*;

import java.awt.*;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.sleep;
import static ru.ksodd.Helpers.StorageString.contentOfWorks.cWork;
import static ru.ksodd.Helpers.TestHelper.errorCrarh;

public class TestPackagePage {

    @When("^test package drop$")

    public static void testPackegeDrop() throws AWTException {
        // проверяю, перетаскивание из запроса в пакет
        sleep(2000);
        PackagePage.lastElemTabl("Проектирование");
        PackagePage.testRequestDropPackage();
        // проверяю перетаскивание из пакета в запрос
        //      PackagePage.testInitDropPackage();


    }

    @When("^test inside create package$")
    public static void testCreatePackage() throws AWTException, IOException {
        sleep(4000);
        PackagePage.lastElemTabl("Дирекция ОДД");
        PackagePage.testPackageDropApproved();
        RequestPage.fellChecboxedRequest("Дирекция ОДД");
        ButtonsUtil.clickSpanButton("Согласовано");
        RequestPage.fellChecboxedRequest("МГТ");
        ButtonsUtil.clickSpanButton("Согласовано");
        RequestPage.fellChecboxedRequest("АМПП");
        ButtonsUtil.clickSpanButton("Согласовано");


        PackagePage.testPackageDropRequest();

        ButtonsUtil.clickSpanButton("Новый пакет");
        PackagePage.waitOpenPackage();
        ButtonsUtil.clickSpanButton("Отмена");
        RequestPage.fellChecboxedRequest("Принято в работу");
        ButtonsUtil.clickSpanButton("Согласовано");
        RequestPage.fellChecboxedRequest("Натурные обследования");
        ButtonsUtil.clickSpanButton("Согласовано");
        RequestPage.fellChecboxedRequest("Проектирование");
        PackagePage.LoaddesignFiles();
        ButtonsUtil.clickSpanButton("Выполнено");

    }
//     @When("^logout $")
//     public static void logout(){
//    AutorizationPage.exit();
//}

    @Then("^test checkbox$")
    public void testCheckbox() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


    @When("^удалить запрос$")
    public static void DeliteRequest() throws AWTException, IOException {
        PackagePage.deliteRequest(PackagePage.timeNameVar);
    }

//==================================================================================================================================
//===============================================чек-бокс 'Принято в работу'=====================================================================


    @When("^входит в чек-бокс 'Принято в работу'$")
    public static void clickCheckBoxInWork() throws AWTException, IOException {
        errorCrarh();
        sleep(3000);
        RequestPage.testTest();
        PackagePage.clickCheckBoxInWork(PackagePage.timeNameVar);
    }

    @When("^входит в отклонённый чек-бокс 'Принято в работу'$")
    public static void ClickInNotAgreedInWork() throws AWTException, IOException {
        errorCrarh();

        sleep(1500);
        PackagePage.clickInNotAgreedInWork(PackagePage.timeNameVar);
    }
//==================================================================================================================================
//===============================================чек-бокс 'Натурные обследования'=====================================================================


    @When("^входит в чек-бокс 'Натурные обследования'$")
    public static void ClickCheckBoxNature() throws AWTException, IOException {
        PackagePage.clickCheckBoxNature(PackagePage.timeNameVar);
    }

    @When("^входит в отклонённый чек-бокс 'Натурные обследования'$")
    public static void ClickInNotAgreedNature() throws AWTException {

        sleep(1500);
        PackagePage.clickInNotAgreedNature(PackagePage.timeNameVar);
        sleep(3000);

        sleep(3000);
    }
//==================================================================================================================================
//===============================================чек-бокс 'Проектирование'=====================================================================


    @When("^входит в чек-бокс 'Проектирование'$")
    public static void ClickCheckBoxDesign() throws AWTException {
        PackagePage.clickCheckBoxDesign(PackagePage.timeNameVar);
    }

//==================================================================================================================================

    @When("^проверка открытия выпадающего списка в поле 'Улица'$")
    public static void StreetOpenListExists() throws AWTException, IOException {
        errorCrarh();
        TestHelper.testOpenWindowReq("Новый запрос");
        RequestPage.deleteFieldStreet();
        RequestPage.StreetOpenListexists();
    }

    @When("^выбор улицы из выпадающего списка$")
    public static void FeelStreet() throws AWTException, IOException, InterruptedException {
        TestHelper.RandomString();
        sleep(1500);
//        RequestPage.fillStreet("е")
//        RequestPage.selectStreet("Улица", "1-й Электрозаводский переулок");
        RequestPage.selectRandomStreet();
    }

    @When("^выбор типа$")
    public static void SelectType() throws AWTException, IOException {
        ButtonsUtil.clickByInputValue("Выберите тип");
        RequestPage.selectReq("Актуализация (инициатива ЦОДД)");
    }

    @When("^выбор типа 'Новое проектирование'$")
    public static void SelectTypenewproject() throws AWTException, IOException {
        ButtonsUtil.clickByInputValue("Выберите тип");
        RequestPage.selectReq("Новое проектирование");
    }

    @When("^выбор типа 'ПОДДы МВК'$")
    public static void SelectTypePODDMVK() throws AWTException, IOException {
        ButtonsUtil.clickByInputValue("Выберите тип");
        RequestPage.selectReq("ПОДДы МВК");
    }

    @Then("^проверяет, что появились характерные Подд чек-боксы$")
    public static void ExistCheckBoxPODD() {
        PackagePage.ExistcheckPODD();
    }

    @When("^заполняет поле 'Протяженность, м'$")
    public static void FillDistance() throws AWTException, IOException {
        PackagePage.fillDistance();
    }

    @When("^заполняет поле 'Содержание работ'$")
    public static void FillContent() throws AWTException, IOException {
        PackagePage.fillContent();
//         PackagePage.fillCause(PackagePage.timeNameVar);
    }

    @When("^заполняет поле 'Причина' 'при создании запроса, с улицей на которую уже есть запросы'$")
    public static void FillCause() throws AWTException, IOException {
        PackagePage.fillCause(PackagePage.timeNameVar);
    }

    @When("^кликаем на кнопку 'Создать'$")
    public static void ButtonCreate() throws AWTException, IOException {
        sleep(2000);
        ButtonsUtil.clickSpanButton("Создать");
        sleep(2000);
//        TestHelper.testCloseWindowCreatReq("Новый запрос");
    }

    @When("^создание нового запроса с помощью клика на кнопку 'Новый запрос'$")
    public static void CreateNewReq() throws AWTException, IOException {
        errorCrarh();
        ButtonsUtil.clickSpanButton("Новый запрос");
    }

    @When("^перенос инициативы в запрос$")
    public static void Dnd3Test() throws AWTException {
        errorCrarh();
        sleep(3000);
        TestHelper.dragAndDrop("0", "1");
    }

    @When("^создание нового запроса$")
    public static void Creatrequest3Test() throws AWTException, IOException, InterruptedException {
        String timeNameVar = "Документ №" + String.valueOf(System.currentTimeMillis());
        sleep(6000);
        RequestPage.deleteFieldStreet();
        RequestPage.StreetOpenListexists();
        TestHelper.RandomString();
        sleep(1500);
        RequestPage.selectRandomStreet();
        ButtonsUtil.clickByInputValue("Выберите тип");
        RequestPage.selectReq("Актуализация (инициатива ЦОДД)");
//        RequestPage.errorCrarh();
        PackagePage.fillDistance();
        PackagePage.fillContent();
        sleep(2000);
        ButtonsUtil.clickSpanButton("Создать");
        sleep(2000);
        TestHelper.testCloseWindowCreatReq("Новый запрос");

    }

    @When("^проверка работы всех чекбоксов запроса 'проверка кнопок и истории комментариев внутри чекбоксов запроса'$")
    public static void CheckBoxes3Test() throws AWTException, IOException {
        String timeNameVar = "Документ №" + String.valueOf(System.currentTimeMillis());
        sleep(6000);
        PackagePage.checkBox4Test(PackagePage.timeNameVar); //повторяет fillContent 4 сценария
    }

    @When("^согласование последнего чекбокса$")
    public static void Lostcheck3Test() throws IOException {
        String timeNameVar = "Документ №" + String.valueOf(System.currentTimeMillis());
        sleep(3000);
        PackagePage.checkBox5Test(PackagePage.timeNameVar);
    }

    @When("^загружает файлы$")
    public static void LoadFiles() {
        PackagePage.LoaddesignFiles();
    }

    @When("^входит внутрь тестового запроса$")
    public static void Files3Test() throws IOException {

        sleep(3000);
        PackagePage.OpencheckBox5Test(PackagePage.timeNameVar);  //повторяет fillContent 4 сценария
        sleep(4000);
    }

    @When("^проверяет наличие файлов$")
    public static void FilesCheck() {

        sleep(3000);
        PackagePage.checkFiles();  //повторяет fillContent 4 сценария
        sleep(4000);
    }

    @When("^Создание нового пакета с помощью кнопки 'Создать пакет'$")
    public static void CreatPackage() throws AWTException, IOException {
        ButtonsUtil.clickSpanButton("Новый пакет");
        ButtonsUtil.clickSpanButton("Создать");
    }

    @When("^проверка работы первых трёх чекбоксов этапа 'пакеты' при перенесённом запросе$")
    public static void checkbox8Test() throws AWTException, IOException {
        sleep(3000);
        PackagePage.checkBox8Test(cWork);  //повторяет fillContent 4 сценария
    }

    //     @When("^перенос тестового запроса в пакеты$")
//     public static void dragAndDrop$8Test() throws AWTException {
//        sleep(5000);
//         TestHelper.dragAndDrop("1","2");
//    }
//===============================================6.1=================================================================================
//==================(создание тесового документа через кнопку 'создать запрос' в этапе реализация)========================================================
    @When("^создание тесового документа через кнопку 'создать запрос' в этапе реализация$")
    public static void NewRealReq() throws AWTException, IOException {

//        ButtonsUtil.clickByInputValue("Выберите тип");
//        RequestPage.selectReq("Актуализация (инициатива ЦОДД)");
//        RequestPage.errorCrarh();

        PackagePage.fillContent();
        sleep(2000);
//        PackagePage.fillCause(PackagePage.timeNameVar);

    }

    @When("^клик на кнопку 'Создать запрос'$")
    public static void FillStreetNewRealReq() throws AWTException, IOException {
        ButtonsUtil.NewRealRequest();
        sleep(1500);
        TestHelper.testOpenWindowReq("Новый запрос");
    }

    @When("^ввод значения в поле улицы$")
    public static void ButtonNewRealReq() throws AWTException, IOException, InterruptedException {
        RequestPage.deleteFieldStreet();
        TestHelper.RandomString();
        sleep(1500);
        RequestPage.selectRandomStreet();
    }

    @When("^ввод значения в поле протяжённости$")
    public static void FillDistanceNewRealReq() throws AWTException, IOException {
        PackagePage.fillDistance();
        sleep(1500);
    }

    @When("^ввод значения в поле содержание работ$")
    public static void FillContentNewRealReq() throws AWTException, IOException {
        PackagePage.fillContent();
        sleep(1500);
    }

    @When("^Клик  на кнопку \"(.*)\"$")
    public static void CreatClickNewReq(String button) throws AWTException, IOException, InterruptedException {
        ButtonsUtil.clickSpanButton(button);
    }

    @When("^клик на кнопку 'Изменить'$")
    public static void Clickchange() throws AWTException, IOException {
        ButtonsUtil.clickSpanButton("Изменить");
        sleep(1500);
    }

    @When("^перенос тестового запроса в этап пакеты$")
    public static void DragDrop() throws AWTException, IOException, InterruptedException {
        TestHelper.dragAndDrop("1","2");
        TestHelper.nameDocument();

    }

    @When("^перенос тесового документа из этапа согласования в этап утверждённые$")
    public static void Drd9Test() throws AWTException, IOException, InterruptedException {
        TestHelper.dragAndDrop("2", "3");
        sleep(1500);
        TestHelper.nameDocument();
    }

    @When("^заполнение всех чекбоксов этапа утверждённых$")
    public static void checkbox9Test() throws AWTException, IOException {

        PackagePage.checkBox9Test("1242144");
    }

    @When("^перенос тесового документа из этапа утверждённых в этап реализации$")
    public static void Drd10Test() throws AWTException, IOException, InterruptedException {
        TestHelper.dragAndDrop("3", "4");
        TestHelper.nameDocument();
    }

    @When("^заполнение всех чекбоксов этапа реализации$")
    public static void checkbox10Test() throws AWTException, IOException {
        PackagePage.checkBox10Test(cWork);
    }

//    @When("^заполнение всех чекбоксов этапа реализации при созданном запросе$")
//    public static void checkbox11Test() throws AWTException {
//
//        PackagePage.checkBox11Test(PackagePage.timeNameVar);
//    }

//===============================================чек-бокс 'Эксплуатация ЦОДД'========================================================

    @When("^входит в чек-бокс 'Эксплуатация ЦОДД'$")
    public static void ClickCheckBoxOperationDCOD() throws AWTException {
        sleep(3000);
        PackagePage.ClickCheckBoxOperationDCOD(PackagePage.timeNameVar);
    }

    @When("^кликает на кнопку 'Не согласовано'$")
    public static void NotAgreed() throws AWTException, IOException {
        errorCrarh();
        PackagePage.NotAgreed();
        sleep(1500);
    }

    @When("^кликает на кнопку 'Отмена'$")
    public static void ClickCancel() throws AWTException, IOException {
        errorCrarh();
        ButtonsUtil.clickSpanButton("Отмена");
    }

    @When("^проверяет, что появилась в поле причины отказа появилась подсказка 'Необходимо ввести комментарий!'$")
    public static void NotAgreedExistTips() throws AWTException {
        errorCrarh();
        PackagePage.ExistTips();
    }

    @When("^вводит в поле причины отказа значение$")
    public static void FeelNotAgreedCause() throws AWTException, IOException {
        errorCrarh();
        PackagePage.FeelNotAgreedCause();
    }

    @When("^кликает на кнопку 'Не согласовано '$")
    public static void ClickNotAgreedDCOD() throws AWTException, IOException {
        errorCrarh();
        ButtonsUtil.clickSpanButton("Не согласовано");
    }

    @When("^входит в отклонённый чек-бокс 'Эксплуатация ЦОДД'$")
    public static void ClickInNotAgreedDCOD() throws AWTException {
        sleep(1500);
        errorCrarh();
        PackagePage.ClickInNotAgreedDCODD(PackagePage.timeNameVar);
    }

    @When("^проверят комментарий введённый при отклонении$")
    public static void ExistComment() throws AWTException {
        errorCrarh();
        PackagePage.Existcomment();
    }

    @When("^кликает на кнопку 'Повторно отправил'$")
    public static void ReAgreed() throws AWTException, IOException {
        errorCrarh();
        ButtonsUtil.clickSpanButton("Повторно отправил");

    }

    @When("^кликает на кнопку 'Передал/Отправил'$")
    public static void ReAgreed1() throws AWTException, IOException {
        errorCrarh();
        ButtonsUtil.clickSpanButton("Передал/Отправил");

    }

    @When("^кликает на кнопку 'Согласовано'$")
    public static void Agreed() throws AWTException, IOException {
        errorCrarh();
        ButtonsUtil.clickSpanButton("Согласовано");

    }
//===============================================чек-бокс 'Авторский надзор'========================================================

    @When("^входит в чек-бокс 'Авторский надзор'$")
    public static void ClickCheckBoxOperationAN() throws AWTException {
        errorCrarh();
        PackagePage.ClickCheckBoxOperationAn(cWork);
    }

    @When("^входит в отклонённый чек-бокс 'Авторский надзор'$")
    public static void ClickInNotAgreedAN() throws AWTException {
        errorCrarh();
        sleep(1500);
        PackagePage.ClickInNotAgreedAn(cWork);
    }

    //==================================================================================================================================
//===============================================чек-бокс 'ГБУ АД'========================================================
    @When("^входит в чек-бокс 'ГБУ АД'$")
    public static void ClickCheckBoxOperationGBU() throws AWTException, IOException {
        errorCrarh();
        PackagePage.ClickCheckBoxGBU(cWork);
    }

    @When("^входит в отклонённый чек-бокс 'ГБУ АД'$")
    public static void ClickInNotAgreedGBU() throws AWTException, IOException {
        errorCrarh();
        sleep(1500);
        PackagePage.ClickInNotAgreedGBU(cWork);
    }
//==================================================================================================================================
//===============================================чек-бокс 'Эксплуатация'========================================================

    @When("^входит в чек-бокс 'Эксплуатация'$")
    public static void ClickCheckBoxExploitation() throws AWTException {
        errorCrarh();
        PackagePage.ClickCheckBoxExploitation(cWork);
    }

    @When("^входит в отклонённый чек-бокс 'Эксплуатация'$")
    public static void ClickInNotAgreedExploitation() throws AWTException {
        errorCrarh();
        sleep(1500);
        PackagePage.ClickInNotAgreedExploitation(cWork);
    }


//==================================================================================================================================
//===============================================чек-бокс 'Начало реализации'========================================================


    @When("^входит в чек-бокс 'Начало реализации'$")
    public static void ClickCheckBoxStartReal() throws AWTException, IOException {
        errorCrarh();
        PackagePage.ClickCheckBoxStartReal(cWork);

    }

    @When("^входит в отклонённый чек-бокс 'Начало реализации'$")
    public static void ClickInNotAgreedStartReal() throws AWTException {
        errorCrarh();
        sleep(1500);
        PackagePage.ClickInNotAgreedStartReal(cWork);
    }

    //==================================================================================================================================
//===============================================чек-бокс 'Дирекция ОДД'========================================================


    @When("^входит в чек-бокс 'Дирекция ОДД'$")
    public static void ClickCheckBoxODD() throws AWTException, IOException {
        errorCrarh();
        PackagePage.ClickCheckBoxODD(cWork);
    }

    @When("^входит в чек-бокс 'Дирекция ОДД после клика на кнопку Создать пакет'$")
    public static void ClickCheckBoxODD1() throws AWTException, IOException {
        errorCrarh();
        ButtonsUtil.clickSpanButton("Создать");
    }

    @When("^входит в отклонённый чек-бокс 'Дирекция ОДД'$")
    public static void ClickInNotAgreedODD() throws AWTException {
        errorCrarh();
        sleep(1500);
        PackagePage.ClickInNotAgreedODD(cWork);
    }
//==================================================================================================================================
//===============================================чек-бокс 'МГТ'=====================================================================


    @When("^входит в чек-бокс 'МГТ'$")
    public static void ClickCheckBoxMGT() throws AWTException {
        PackagePage.ClickCheckBoxMGT(cWork);
    }

    @When("^входит в отклонённый чек-бокс 'МГТ'$")
    public static void ClickInNotAgreedMGT() throws AWTException {

        sleep(1500);
        PackagePage.ClickInNotAgreedMGT(cWork);
    }
//==================================================================================================================================
//===============================================чек-бокс 'АМПП'=====================================================================


    @When("^входит в чек-бокс 'АМПП'$")
    public static void ClickCheckBoxAMPP() throws AWTException {
        PackagePage.ClickCheckBoxAMPP(cWork);
    }

    @When("^входит в отклонённый чек-бокс 'АМПП'$")
    public static void ClickInNotAgreedAMPP() throws AWTException {

        sleep(1500);
        PackagePage.ClickInNotAgreedAMPP(cWork);
    }
//==================================================================================================================================
//===============================================чек-бокс 'Утверждение ПОДД'=====================================================================


    @When("^входит в чек-бокс 'Утверждение ПОДД'$")
    public static void ClickCheckBoxYPODD() throws AWTException {
        PackagePage.ClickCheckBoxYPODD(cWork);
    }

    @When("^входит в отклонённый чек-бокс 'Утверждение ПОДД'$")
    public static void ClickInNotAgreedYPODD() throws AWTException {

        sleep(1500);
        PackagePage.ClickInNotAgreedYPODD(cWork);
    }
}