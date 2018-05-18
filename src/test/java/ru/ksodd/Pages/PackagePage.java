package ru.ksodd.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import ru.ksodd.Helpers.ButtonsUtil;
import ru.ksodd.Helpers.LoggerConsole;
import ru.ksodd.Helpers.TestHelper;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.codeborne.selenide.Condition.visible;
import static java.lang.System.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static com.codeborne.selenide.Selenide.*;
import static ru.ksodd.Helpers.LoggerConsole.Logg;
import static ru.ksodd.Helpers.LoggerConsole.LoggNotError;
import static ru.ksodd.Helpers.StorageString.contentOfWorks.cWork;
import static ru.ksodd.Helpers.TestHelper.errorCrarh;


public class PackagePage {
    public static String timeNameVar = "Документ №" + String.valueOf(System.currentTimeMillis());

    public static void lastElemTabl(String form) {
        //           $$(By.xpath("//td[@data-bem='{\"c-kanban__column\":{\"kot_id\":\"1\"}}']//div[@class='c-process-card__main i-bem c-process-card__main_js_inited']")).last().shouldHave();
        sleep(1000);
        $(By.xpath("//span[text()='" + form + "']/ancestor::li/div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-1']/ancestor::div[3]//span[@class='c-process-card__zam'][last()]")).shouldHave();
//*[text()='test']/ancestor::
        $(By.xpath("//span[text()='" + form + "']/ancestor::li/div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-1']/ancestor::div[3]//span[@class='c-process-card__zam'][last()]")).click();

        // actions().sendKeys(Keys.END).build().perform();
        //       $$(By.xpath("//td[@data-bem='{\"c-kanban__column\":{\"kot_id\":\"1\"}}']//div[@class='c-process-card__main i-bem c-process-card__main_js_inited']")).last().click();

    }

    public static void testRequestDropPackage() throws AWTException {

        //

        $$(By.xpath("//td[@data-bem='{\"c-kanban__column\":{\"kot_id\":\"1\"}}']//div[@class='c-process-card__main i-bem c-process-card__main_js_inited']")).last().click();

        Robot robot = new Robot();
        robot.setAutoDelay(500); //Задержка по умолчанию (чем больше, тем медленнее движется мышь)
        robot.mouseMove(560, 950);
        ; //Двигаем мышку     последний из видимых
//первый из видимых
        robot.mousePress(MouseEvent.BUTTON1_MASK); //Нажимаем и удерживаем мышь
        robot.mouseMove(800, 300); //Движемся к этим координатам
        robot.delay(500); // Задержка в конкретном месте (чем больше, тем медленнее движется мышь)
        robot.mouseMove(800, 450); //Двигаем мышь еще раз, тк иначе перетаскиваемый элемент не виден
        robot.mouseRelease(MouseEvent.BUTTON1_MASK);
        robot.waitForIdle(); // Ждет пока исполнится вся цепочка действий
        sleep(500);
    }

    public static void testPackageDropRequest() throws AWTException {
        Robot robot = new Robot();
        robot.setAutoDelay(500); //Задержка по умолчанию (чем больше, тем медленнее движется мышь)
        robot.mouseMove(800, 550);
        ; //Двигаем мышку
        robot.mousePress(MouseEvent.BUTTON1_MASK); //Нажимаем и удерживаем мышь
        robot.mouseMove(800, 300); //Движемся к этим координатам
        robot.delay(500); // Задержка в конкретном месте (чем больше, тем медленнее движется мышь)
        robot.mouseMove(600, 550); //Двигаем мышь еще раз, тк иначе перетаскиваемый элемент не виден
        robot.mouseRelease(MouseEvent.BUTTON1_MASK);
        robot.waitForIdle(); // Ждет пока исполнится вся цепочка действий
        sleep(500);
    }

    public static void testPackageDropApproved() throws AWTException {
        Robot robot = new Robot();

        robot.setAutoDelay(500); //Задержка по умолчанию (чем больше, тем медленнее движется мышь)
        robot.mouseMove(800, 950);
        ; //Двигаем мышку
        robot.mousePress(MouseEvent.BUTTON1_MASK); //Нажимаем и удерживаем мышь

        robot.mouseMove(800, 300); //Движемся к этим координатам
        robot.delay(500); // Задержка в конкретном месте (чем больше, тем медленнее движется мышь)
        robot.mouseMove(1100, 550); //Двигаем мышь еще раз, тк иначе перетаскиваемый элемент не виден
        robot.mouseRelease(MouseEvent.BUTTON1_MASK);
        robot.waitForIdle(); // Ждет пока исполнится вся цепочка действий
        sleep(5000);
    }


    public static void waitOpenPackage() {
        $(By.xpath("//h3[text()='Создание нового пакета']")).waitUntil(visible, 10000);
//            $(By.xpath("//h3[text()='Создание нового пакета']")).sendKeys(Keys.CONTROL+"C");

    }

    public static void LoaddesignFiles() {
        String[] files = {"1111.dgn", "11110.pdf", "ТЗ.txt"}; //Типы статусов инициатив
        ElementsCollection collect = $$(By.xpath("//input[@type='file']"));
        for (int i = 0; i < collect.size(); i++) {
            collect.get(i).uploadFile(new File("src\\test\\repository\\Files\\" + files[i]));
        }
    }


//===============================================чек-бокс 'принято на работу'=======================================================================================================

    public static void clickCheckBoxInWork(String txt) throws IOException {

        sleep(3000);
        WebElement InWork = $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Принято в работу']"));
        try {
            InWork.isDisplayed();
            actions().click(InWork).perform();
            LoggNotError("Вход в внутр чек-бокса Задачи: Принято в работу");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не вошел в чекбокс Задачи: Принято в работу  /  Не увидел элемента чек-бокса Задачи: Принято в работу");
        }
    }

    public static void clickInNotAgreedInWork(String txt) throws IOException {

        try {
            $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).should(visible);
            $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).click();
            LoggNotError("Клик на кнопку 'Не согласовано'");
        } catch (Error e) {
            Logg("Не произошел клик на кнопку 'Не согласовано' / Не увидел элемент кнопки");
        }
    }

//================================================чек-бокс 'Натурные обследования'=================================================================================================


    public static void clickCheckBoxNature(String txt) throws IOException {


        sleep(3000);
        WebElement Nature = $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Натурные обследования']"));
        try {
            actions().click(Nature).perform();
            LoggNotError("Вход в внутр чек-бокса Задачи: Натурные обследования");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не вошел в чекбокс Задачи: Натурные обследования");
        }
    }

    public static void clickInNotAgreedNature(String txt) {
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).isDisplayed();
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).click();
        sleep(3000);
    }
//================================================чек-бокс 'Проектирование'=================================================================================================


    public static void clickCheckBoxDesign(String txt) {
        sleep(3000);
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Проектирование']")).isDisplayed();
        TestHelper.printReport("//div[text()='\" + txt + \"']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Проектирование']",
                "Не смог войти в чекбокс 'Проектирование' ");
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Проектирование']")).click();
    }


    public static void checkBox4Test(String txt) throws IOException {
        String checkName1 = "//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Принято в работу']";
        String checkName2 = "//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Натурные обследования']";
        String comment = "//textarea[@name='reason']";
        sleep(5000);
        $(By.xpath(checkName1)).isDisplayed();

        TestHelper.printReport("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Принято в работу']",
                "Не смог войти в чекбокс 'принято на работу' ");
        sleep(5000);
        $(By.xpath(checkName1)).click();
        ButtonsUtil.clickSpanButton("Согласовано");
        errorCrarh();
        refresh();
//        $(By.xpath(checkName2)).isDisplayed();
        sleep(5000);
        $(By.xpath(checkName2)).click();
        TestHelper.printReport(checkName2,
                "Не смог войти в чекбокс 'Натурные обследования");
        $(By.xpath(comment)).isDisplayed();
        $(By.xpath(comment)).sendKeys("Не согласовано");

        $(By.xpath("//span[text()='Не согласовано']")).click();
        $(By.xpath(checkName2)).isDisplayed();
        refresh();
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).isDisplayed();
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).click();
        TestHelper.printReport("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']",
                "Не смог войти в чекбокс 'Натурные обследования' после его отклонения ");
        $(By.xpath("//td[text()='Не согласовано']")).isDisplayed();
        TestHelper.printReport("//div[text()='Не согласовано']",
                "Не смог увидеть комментарий введённый при отклонении внутри чекбокса 'натуральные обследования'");
        ButtonsUtil.clickSpanButton("Передал/Отправил");
        refresh();
    }

    public static void checkBox5Test(String txt) throws IOException {

        String checkName1 = "//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Принято в работу']";
        String checkName2 = "//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Натурные обследования']";

        $(By.xpath(checkName2)).isDisplayed();
        $(By.xpath(checkName2)).click();

        $(By.xpath("//h3[text()='Задача: Натурные обследования']")).isDisplayed();
        TestHelper.printReport("//div[text()='Задача: Натурные обследования']",
                "Не вошел в чекбокс 'Натурные обследования' ");
        ButtonsUtil.clickSpanButton("Согласовано");
        refresh();

    }

    public static void OpencheckBox5Test(String txt) throws IOException {
        String checkName3 = "//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Проектирование']";
        $(By.xpath("//div[text()='" + txt + "']")).shouldHave();
        $(By.xpath("//div[text()='" + txt + "']")).doubleClick();



    }

    public static void checkFiles() {
        $(By.xpath("//input[@value='1111.dgn']")).shouldHave();
        TestHelper.printReport("//input[@value='1111.dgn']",
                "Не обнаружены прикреплённые файлы при входе внутрь 'запроса'  ");
        $(By.xpath("//input[@value='11110.pdf']")).shouldHave();
        TestHelper.printReport("//input[@value='11110.pdf']",
                "Не обнаружены прикреплённые файлы при входе внутрь 'запроса' ");
    }

    public static void checkBox8Test(String txt) throws IOException {


        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='АМПП']")).isDisplayed();
        TestHelper.printReport("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='АМПП']",
                "Не смог войти в чекбокс 'АМПП' ");
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='АМПП']")).click();
        $(By.xpath("//div[@class='b-textarea-field']/textarea[@placeholder='Комментарий']")).isDisplayed();
        TestHelper.printReport("//span[text()='Не согласовано']",
                "Не увидел кнопку 'Отклонить' внутри чекбокса 'АМПП' ");
        $(By.xpath("//span[text()='Не согласовано']")).click();
        refresh();
        $(By.xpath("//textarea[@placeholder='Необходимо ввести комментарий!']")).isDisplayed();
        TestHelper.printReport("//textarea[@placeholder='Необходимо ввести комментарий!']",
                "Не увидел подсказку 'Необходимо ввести комментарий!' при отклонении без комментария внутри чекбокса 'АМПП' ");
        $(By.xpath("//textarea[@placeholder='Необходимо ввести комментарий!']")).sendKeys("Не согласовано");
        $(By.xpath("//span[text()='Не согласовано']")).click();
        refresh();
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='АМПП']")).isDisplayed();
        TestHelper.printReport("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']",
                "Не смог войти в чекбокс 'АМПП' после его отклонения");
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).click();
        ButtonsUtil.clickSpanButton("Отмена");
        refresh();
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).isDisplayed();
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).click();
        $(By.xpath("//td[text()='ты не пройдёшь']")).isDisplayed();
        ButtonsUtil.clickSpanButton("Повторно отправил");
        refresh();
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='АМПП']")).isDisplayed();
        TestHelper.printReport("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='АМПП']",
                "Не вошел в чекбокс 'АМПП' ");
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='АМПП']")).click();
        ButtonsUtil.clickSpanButton("Согласовано");
        refresh();
    }

//===============================================чек-бокс 'Утверждение ПОДД'========================================================

    public static void ClickCheckBoxYPODD(String txt) {
        sleep(3000);
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Утверждение ПОДД']")).isDisplayed();
        TestHelper.printReport("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Утверждение ПОДД']",
                "Не вошел в чекбокс 'Утверждение ПОДД' ");
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Утверждение ПОДД']")).click();
    }

    public static void ClickInNotAgreedYPODD(String txt) {
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).isDisplayed();
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).click();

        sleep(3000);
    }

//===============================================чек-бокс 'АМПП'========================================================

    public static void ClickCheckBoxAMPP(String txt) {
        sleep(3000);
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='АМПП']")).isDisplayed();
        TestHelper.printReport("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='АМПП']",
                "Не вошел в чекбокс 'АМПП' ");
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='АМПП']")).click();
    }

    public static void ClickInNotAgreedAMPP(String txt) {
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).isDisplayed();
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).click();

        sleep(3000);
    }
//===============================================чек-бокс 'МГТ'========================================================

    public static void ClickCheckBoxMGT(String txt) {
        sleep(3000);
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='МГТ']")).isDisplayed();
        TestHelper.printReport("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='МГТ']",
                "Не вошел в чекбокс 'МГТ' ");
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='МГТ']")).click();
    }

    public static void ClickInNotAgreedMGT(String txt) {
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='МГТ']")).isDisplayed();
        TestHelper.printReport("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']",
                "Не вошел в чекбокс 'МГТ' после его отклонения");
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).click();

        sleep(3000);
    }
//===============================================чек-бокс 'Дирекция ОДД'========================================================

    public static void ClickCheckBoxODD(String txt) throws IOException {
        sleep(3000);
        WebElement BoxODD = $(By.xpath("//div[text()='" + txt + "']/../div[4]/div/ul/li[1]/span[text()='Дирекция ОДД']"));
        sleep(1500);
        try {
            $(By.xpath("//div[text()='" + txt + "']/../div[4]/div/ul/li[1]/span[text()='Дирекция ОДД']")).should(visible);
            LoggNotError("Чекбокс 'Дирекция ОДД' присутствует");
            actions().click(BoxODD).perform();
            $(By.xpath("//*[@class='b-block modal-dialog i-bem b-block_js_inited']/div/div/div[text()='Задача: Дирекция ОДД']")).should(visible);
            LoggNotError("Открылась форма 'Задача: Дирекция ОДД' при клике на чек-бокс 'Дирекция ОДД'");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не вошел в чекбокс 'Дирекция ОДД'");
        }
     }
//   //div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Дирекция ОДД']
    public static void ClickInNotAgreedODD(String txt) {
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).isDisplayed();
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).click();


        sleep(3000);
    }

    //===============================================чек-бокс 'Эксплуатация'========================================================

    public static void checkBox10Test(String txt) throws IOException {


        $(By.xpath("//div[@class='b-textarea-field']/textarea[@placeholder='Комментарий']")).isDisplayed();
        TestHelper.printReport("//span[text()='Не согласовано']",
                "Не произошел клик га кнопку 'Отклонить' внутри чекбокса 'Эксплуатация'");
        $(By.xpath("//span[text()='Не согласовано']")).click();
        sleep(3000);
        $(By.xpath("//textarea[@placeholder='Необходимо ввести комментарий!']")).isDisplayed();
        TestHelper.printReport("//textarea[@placeholder='Необходимо ввести комментарий!']",
                "Не увидел подсказку 'Необходимо ввести комментарий!' при отклонении без комментария внутри чекбокса 'Эксплуатация' ");
        $(By.xpath("//textarea[@placeholder='Необходимо ввести комментарий!']")).sendKeys("отказано");
        $(By.xpath("//span[text()='Не согласовано']")).click();

        refresh();
        sleep(3000);

        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).isDisplayed();
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).click();
        $(By.xpath("//td[text()='отказано']")).isDisplayed();
        ButtonsUtil.clickSpanButton("Повторно отправил");
        refresh();
        sleep(3000);

        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Эксплуатация']")).isDisplayed();
        TestHelper.printReport("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Эксплуатация']",
                "Не вошел в чекбокс 'Эксплуатация' ");
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Эксплуатация']")).click();
        ButtonsUtil.clickSpanButton("Согласовано");
        refresh();
    }
//===============================================чек-бокс 'ГБУ АД'========================================================

    public static void ClickCheckBoxGBU(String txt) throws IOException {

        try {
            $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='ГБУ АД']")).should(visible);
            $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='ГБУ АД']")).click();
            LoggNotError("Вошел в чекбокс 'ГБУ АД'");
        } catch (Error e) {
            Logg("Не вошел в чекбокс 'ГБУ АД'");
        }
    }

    public static void ClickInNotAgreedGBU(String txt) throws IOException {

        try {
            $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).should(visible);
            $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).click();
            LoggNotError("Клик на отрицательный чекбокc 'ГБУ АД'");
        } catch (Error e) {
            Logg("Не вошел в отрицательный чекбокc 'ГБУ АД'");
        }

        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).isDisplayed();
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).click();
        sleep(3000);
    }

    //===============================================чек-бокс 'Эксплуатация'========================================================
    public static void ClickCheckBoxExploitation(String txt) {
        sleep(3000);
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Эксплуатация']")).isDisplayed();
        TestHelper.printReport("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Эксплуатация']",
                "Не вошел в чекбокс 'Эксплуатация' ");
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Эксплуатация']")).click();
    }

    public static void ClickInNotAgreedExploitation(String txt) {
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).isDisplayed();
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).click();
        sleep(3000);
    }



    public static void deliteRequest(String txt) throws IOException {
        sleep(1000);
        $(By.xpath("//div[text()='" + txt + "']")).doubleClick();
        $(By.xpath("//button[text()='Удалить запрос']")).isDisplayed();
        $(By.xpath("//button[text()='Удалить запрос']")).click();
        $(By.xpath("//textarea[@placeholder='Укажите причину удаления...']")).isDisplayed();
        $(By.xpath("//textarea[@placeholder='Укажите причину удаления...']")).sendKeys("Ты был мне как брат!");
        ButtonsUtil.clickSpanButton("Удалить");
        try {
            $(By.xpath("//div[not(text()='" + txt + "')]/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Принято в работу']")).isDisplayed();
            sleep(300);
            assertFalse($(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Принято в работу']")).exists());
        } catch (AssertionError err) {
            TestHelper.assertScreen();
            System.out.println("Тестовая заявка с комментарием " + txt + " не удалена");
            throw err;
        }

    }

    public static void fillContent() throws IOException {
        $(By.xpath("//div[@class='b-block panel-heading i-bem b-block_js_inited']/h3[text()='Новый запрос']")).shouldHave();
        sleep(2000);
        WebElement content = $(By.xpath("//textarea[@class='b-textarea-field__name form-control']"));
        sleep(3000);
        $(By.xpath("//textarea[@class='b-textarea-field__name form-control']")).click();
        try {
            actions().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
            actions().sendKeys(Keys.DELETE).build().perform();
            actions().click(content).sendKeys(timeNameVar).perform();
            cWork = timeNameVar;
            LoggNotError("Заполнение значения в поле 'Содержания работ'");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не заполнил поле содержания работ");
        }
    }


    public static void fillDistance() throws IOException {
        WebElement street = $(By.name("distance"));
        sleep(3000);
        try {
            actions().click(street).sendKeys("1234").perform();
            LoggNotError("Заполнение значения в поле 'Дистанция'");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не заполнил значения в поле 'Дистанция'");
        }

    }


    public static void fillresponsible() throws IOException {
        WebElement openList = $(By.xpath("//label[text()='Отвественный']/ancestor::div[1]/div[@class='t-patterns__combobox i-bem t-patterns__combobox_js_inited']"));
        sleep(3000);
        WebElement clickofListElem = $(By.xpath("//li[text()='ttt1']"));
        try {
            actions().click(openList).perform();
            actions().click(clickofListElem).perform();
            LoggNotError("Заполнение и выбор значения в поле 'Ответственный'");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не заполнилось значения / не был выбран в поле 'Ответственный'");
        }
    }


    public static void checkBox9Test(String txt) throws IOException {

        String checkpackage1 = "//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Начало реализации']";

        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Начало реализации']")).isDisplayed();
        TestHelper.printReport("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Начало реализации']",
                "Не вошел в чекбокс 'Начало реализации' ");
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Начало реализации']")).click();
        sleep(3000);
        TestHelper.printReport("//textarea[@name='reason']",
                "Не вошел внутрь чек-бокса 'Начало реализации'");

        $(By.xpath("//span[text()='Не согласовано']")).click();
        $(By.xpath("//textarea[@placeholder='Необходимо ввести комментарий!']")).isDisplayed();
        TestHelper.printReport("//textarea[@placeholder='Необходимо ввести комментарий!']",
                "Не увидел подсказку 'Необходимо ввести комментарий!' при отклонении без комментария внутри чекбокса 'Начало реализации' ");
        $(By.xpath("//textarea[@placeholder='Необходимо ввести комментарий!']")).isDisplayed();
        $(By.xpath("//textarea[@placeholder='Необходимо ввести комментарий!']")).sendKeys("отказ");
        $(By.xpath("//span[text()='Не согласовано']")).click();

        refresh();
        sleep(3000);

        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).isDisplayed();
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).click();
        $(By.xpath("//div[text()='отказ']")).isDisplayed();
        TestHelper.printReport("//div[text()='отказ']",
                "Не увидел комментарий введённый при отклонении");
        ButtonsUtil.clickSpanButton("Повторно отправил");
        refresh();
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Начало реализации']")).isDisplayed();
        TestHelper.printReport("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Начало реализации']",
                "Не вошел в чекбокс 'Начало реализации' ");
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Начало реализации']")).click();
        ButtonsUtil.clickSpanButton("Согласовано");
        refresh();
    }

//===============================================чек-бокс 'Начало реализации'========================================================

    public static void ClickCheckBoxStartReal(String txt) throws IOException {

        WebElement StRe = $(By.xpath("//div[text()='" + txt + "']//ancestor::div[1]/div[@class='c-process-card__tasks']//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-0']"));
        try {
            sleep(1000);
            $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-0']")).should(visible);
            actions().click(StRe).click(StRe).perform();
            sleep(1500);
            LoggNotError("Клик на чек-бокс 'Начало реализации'");
            $(By.xpath("//*[@class='b-block modal-dialog i-bem b-block_js_inited']/div/div/div[text()='Задача: Начало реализации']")).should(visible);
            LoggNotError("Вошел внутр чек-бокса 'Задача: Начало реализации'");
        } catch (Error e) {
            Logg("Не вошел в чек-бок 'Начало реализации'");
        }

    }


    public static void ClickInNotAgreedStartReal(String txt) {
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).isDisplayed();
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).click();
    }
//==================================================================================================================================

    public static void fillCause(String txt) throws IOException {
        WebElement cause = $(By.xpath("//label[text()='Причина']/ancestor::div[@class='b-textarea-field']/textarea[@class='b-textarea-field__name form-control']"));
        sleep(3000);
            try {
                $(By.xpath("//label[text()='Причина']/ancestor::div[@class='b-textarea-field']/textarea[@class='b-textarea-field__name form-control']")).should(visible);
                actions().click(cause).sendKeys(txt).perform();
                cWork = txt;
                LoggNotError("Заполнение значения в поле 'Причина'");
            } catch (AssertionError err) {
        }
    }



//===============================================чек-бокс 'Эксплуатация ЦОДД'========================================================

    public static void ClickCheckBoxOperationDCOD(String txt) {
        sleep(3000);
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Эксплуатация ЦОДД']")).isDisplayed();
        TestHelper.printReport("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Эксплуатация ЦОДД']",
                "Не вошел в чекбокс 'Эксплуатация ЦОДД' ");
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Эксплуатация ЦОДД']")).click();
        sleep(3000);
    }

    public static void NotAgreed() throws IOException {
        try {
            $(By.xpath("//span[text()='Не согласовано']")).should(visible);
            $(By.xpath("//span[text()='Не согласовано']")).click();
            LoggNotError("Клик на кнопку 'Не согласованно'");
        } catch (Error e) {
            Logg("Не нажал на кнопку 'Не согласованно' / Не увидел кнопку 'Не согласовано'");
        }
    }

    public static void ExistTips() {
        $(By.xpath("//textarea[@placeholder='Необходимо ввести комментарий!']")).isDisplayed();
        TestHelper.printReport("//textarea[@placeholder='Необходимо ввести комментарий!']",
                "Не увидел подсказку 'Необходимо ввести комментарий!' при отклонении без комментария внутри чекбокса");
        sleep(3000);
    }

    public static void FeelNotAgreedCause() throws IOException {
        WebElement NotAgreedCause = $(By.xpath("//textarea[@id='textareaRequestTaskShow']"));
        sleep(3000);
        try {
            $(By.xpath("//textarea[@id='textareaRequestTaskShow']")).should(visible);
            actions().click(NotAgreedCause).sendKeys("Да пребудет с тобой Сила").perform();
            sleep(2000);
            LoggNotError("Заполнение значения в поле 'Причина'");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не заполнено значениями в поле 'Причина'");
        }
    }

    public static void ClickInNotAgreedDCODD(String txt) {
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).isDisplayed();
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).click();
        sleep(3000);

    }

    public static void Existcomment() {
        $(By.xpath("//div[text()='Не согласовано']")).isDisplayed();
        TestHelper.printReport("//div[text()='Не согласовано']",
                "Не увидел комментарий введённый при отклонении");
    }

//===============================================чек-бокс 'Авторский надзор'========================================================

    public static void ClickCheckBoxOperationAn(String txt) {

        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Авторский надзор']")).isDisplayed();
        TestHelper.printReport("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Авторский надзор']",
                "Не вошел в чекбокс 'Авторский надзор' ");
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Авторский надзор']")).click();
    }


    public static void ClickInNotAgreedAn(String txt) {
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='Авторский надзор']")).isDisplayed();
        TestHelper.printReport("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']",
                "Не вошел в чекбокс 'Авторский надзор' после его отклонения");
        $(By.xpath("//div[text()='" + txt + "']/ancestor::div[1]//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).click();
    }


//==================================================================================================================================

    public static void ExistcheckPODD() {
        $(By.xpath("//span[text()='Авторский надзор']/ancestor::ul[1]//span[text()='Дирекция ОДД']")).isDisplayed();
        TestHelper.printReport("//span[text()='Авторский надзор']/ancestor::ul[1]//span[text()='Дирекция ОДД']",
                "Не увидел характерные чек-боксы 'Дирекция ОДД' ");

        $(By.xpath("//span[text()='Дирекция ОДД']/ancestor::ul[1]//span[text()='Авторский надзор']")).isDisplayed();
        TestHelper.printReport("//span[text()='Дирекция ОДД']/ancestor::ul[1]//span[text()='Авторский надзор']",
                "Не увидел характерные чек-боксы 'Авторский надзор'");
    }

//======================================перейти к форме фильтра улиц================================================================
public static void openstreetfilter() throws IOException {
    WebElement openxpath = $(By.xpath("//a[@title='Перейти к форме фильтра улиц']"));
    sleep(3000);
    try {
        actions().click(openxpath).perform();
        LoggNotError("Перешёл к форме фильтра улиц");
    } catch (AssertionError err) {
        LoggerConsole.Logg("Не перешёл к форме фильтра улиц");
    }
}
    //==================================================================================================================================
    //======================================открыть список в поле "Округ"================================================================
    public static void opencountylist() throws IOException {
        WebElement openxpath = $(By.xpath("//input[@placeholder='Округ']"));
        sleep(3000);
        try {
            actions().click(openxpath).perform();
            LoggNotError("Открыл выпадающий список в поле 'Округ'");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не открыл выпадающий список в поле 'Округ'");
        }
    }
//==================================================================================================================================
//======================================выбрать округ из списка=====================================================================
public static void getcounty() throws IOException {
    WebElement xpath = $(By.xpath("//input[@placeholder='Округ']"));
    sleep(3000);
    try {
        actions().click(xpath).perform();
        LoggNotError("Выбрал округ из списка");
    } catch (AssertionError err) {
        LoggerConsole.Logg("Не выбрал округ из списка");
    }
}
//==================================================================================================================================
//======================================войти в Проекты МВК=====================================================================
public static void mvkProject () throws IOException {
    WebElement xpath = $(By.xpath("//button[@class='b-button-call btn btn-primary i-bem b-button-call_js_inited']/span[text()='Новый проект']"));
    sleep(3000);
    try {
        actions().click(xpath).perform();
        LoggNotError("вошёл в форму создания документа Проекты МВК");
    } catch (AssertionError err) {
        LoggerConsole.Logg("не вошёл в форму создания документа Проекты МВК");
    }
}
//==================================================================================================================================
//======================================заполнить поле Организация=====================================================================
public static void mvkProjectFeelOrg () throws IOException {
    WebElement xpath = $(By.xpath("//input[@class='b-input-field__string b-input']"));
    sleep(3000);
    try {
        actions().click(xpath).sendKeys(PackagePage.timeNameVar).perform();
        LoggNotError("Заполнил поле Организация");
    } catch (AssertionError err) {
        LoggerConsole.Logg("не заполнил поле Организация");
    }
}
//==================================================================================================================================
//======================================заполнить поле Содержание работ=====================================================================
public static void mvkProjectcontentWorks () throws IOException {
    WebElement xpath = $(By.xpath("//textarea[@class='b-textarea-field__name c-input-check form-control i-bem b-textarea-field__name_js_inited c-input-check_js_inited']"));
    sleep(3000);
    try {
        actions().click(xpath).sendKeys(PackagePage.timeNameVar).perform();
        LoggNotError("Заполнил поле содержание работ");
    } catch (AssertionError err) {
        LoggerConsole.Logg("не заполнил поле содержание работ");
    }
}
//==================================================================================================================================
//======================================заполнить поле Дата реализации=====================================================================
public static void mvkProjectdaterealise () throws IOException {
    WebElement xpath = $(By.xpath("//input[@class='b-date-field__date form-control pdate i-bem hasDatepicker b-date-field__date_js_inited']"));
    WebElement xpath1 = $(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[6]/td[1]/a"));

    sleep(3000);
    try {
        actions().click(xpath).perform();
        actions().click(xpath1).perform();
        LoggNotError("Заполнил поле Дата реализации");
    } catch (AssertionError err) {
        LoggerConsole.Logg("не заполнил поле Дата реализации");
    }
}
//==================================================================================================================================
//======================================загрузить файлы Проекты МВК=====================================================================

    public static void LoadFiles() {
    String[] files = {"1111.dgn", "11110.pdf", "ТЗ.txt", "ТЗ.txt", "1111.dgn", "11110.pdf", "ТЗ.txt", "ТЗ.txt", "11110.pdf"}; //Типы статусов инициатив
    ElementsCollection collect = $$(By.xpath("//input[@type='file']"));
    for (int i = 0; i < collect.size(); i++) {
        collect.get(i).uploadFile(new File("src\\test\\repository\\Files\\" + files[i]));
    }
}

}