package ru.ksodd.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.eo.Se;
import org.junit.Test;
import org.omg.CORBA.MARSHAL;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.w3c.dom.events.Event;
import ru.ksodd.Helpers.*;
import ru.ksodd.Steps.TestPackagePage;


import javax.swing.text.MutableAttributeSet;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.util.*;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.awt.event.InputEvent.BUTTON1_DOWN_MASK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ru.ksodd.Helpers.LoggerConsole.Logg;
import static ru.ksodd.Helpers.LoggerConsole.LoggNotError;
import static ru.ksodd.Helpers.StorageString.contentOfWorks.cWork;

public class RequestPage {

//   ================================================================== Глобальные переменные =============================================================
//   ======================================================================================================================================================


    public static List<String> initsOnStreet = new ArrayList<String>(); //Переменная с количеством инициатив с улицей street
    public static List<SelenideElement> types = new ArrayList<SelenideElement>(); // Типы запросов
    public static String distance; //Расстояние
    public static List<String> listOfRequests = new ArrayList<String>(); //Список с названиями запросов
    public static Map<String, String[]> reqMap = new HashMap<>(); //Хашмап, у которого ключ - название запроса, а значение-инициатива
    public static String[] reqValues; //[Улица, Протяженность, Тип, Инициатива]

    public static String[] nameCheckbox = {"Принято в работу", "Натурные обследования", "Проектирование"};//Список чекбоксов
    //    public static List<SelenideElement> nameCheck = new ArrayList<SelenideElement>();
    public static List<String> nameCheck = new ArrayList<String>();
    public static String[] yyy = {};


//  =================================================================== Работа с формой ===================================================================
//  =======================================================================================================================================================


    public static void testTest() throws IOException {
        refresh();
        ElementsCollection collection = $$(By.xpath("//td[@data-lavel='1']//div[@class='c-process-card drag-drop i-bem c-process-card_js_inited']/div[@class='c-process-card__main i-bem c-process-card__main_js_inited' and text()='" + cWork + "']/../div[3]/div/ul/li/span[text()][1]"));
        WebElement doc = $(By.xpath("//td[@data-lavel='1']//div[@class='c-process-card drag-drop i-bem c-process-card_js_inited']/div[@class='c-process-card__main i-bem c-process-card__main_js_inited' and text()='" + cWork + "']"));
        sleep(1500);

        //Собирает данные на странице (Названия чек-боксов в этапе Корректировка)
        for (SelenideElement aCollection : collection) {
            String ch = aCollection.getText();
            nameCheck.add(ch);
        }

        //Цикл, который проверяет наименовании из массива NameCheckbox в NameCheck
        for (int i = 0; i < nameCheck.size(); i++) {
            yyy = nameCheck.toArray(new String[i]);
            WebElement checkBox = $(By.xpath("//div[text()='" + cWork + "']/ancestor::div[1]/div[@class='c-process-card__tasks']//span[text()='"+String.valueOf(yyy[i])+"']"));
            doc.click();
            LoggNotError("Клик по документу");
            if (yyy[i].equals(nameCheckbox[i])) {
                TestHelper.errorCrarh();
                LoggNotError("Клик по чек-боксу " + String.valueOf("\""+yyy[i]+"\"") + "");
//                checkBox.click();
            } else {
                Logg("Не смог нажать на чекбокс");

            }
        }
    }

    public static void collectRequestTypes() {
        ElementsCollection collect = $$(By.xpath("//div[text()='Тип']/ancestor::div[1]//li"));
        for (int i = 0; i < collect.size(); i++) {
            types.add(collect.get(i));
        }
    }

    public static void countStreetInitiatives(String street) {
        //        Считаю сколько инициатив с данной улицей существует на данный момент
//        Нужно для того, чтобы проверить, подгружаются ли они все после выбора улицы при создании запроса

//        Список элементов с данной улицей
        ElementsCollection collect = $$(By.xpath("//td[@id='kot_id_0']//span[text()='" + street + "']//ancestor::div[4]//div[contains(text(),'Инициатива №')]"));

        for (int i = 0; i < collect.size(); i++) {
//            Бегаю по списку и заношу каждый элемент в массив initsOnStreet
//            Копирую описание инициативы и заношу в переменную index номер символа, с которого начинается слово Инициатива
            int index = collect.get(i).getText().indexOf("Инициатива");
//            Копирую описание инициативы и с помощью substring заношу в переменную init название инициативы со слова Инициатива до последнего символа (вычисляю его с помощью длины строки)
            String init = collect.get(i).getText().substring(index, collect.get(i).getText().length());
//            Заношу все инициативы на данной улицы в массив для дальнейшей проверки
            initsOnStreet.add(init);
        }

    }

    public static void selectStreet(String label, String street) {
//        Выбор улицы street из ниспадающего списка с подписью label
        try {
            $(By.xpath("//label[text()='" + label + "']/..//li[text()='" + street + "']")).click();
        } catch (Error e) {
            Logg("Не выбрана улица из ниспадающего списка");
        }

//        if ($(By.xpath("//label[text()='" + label + "']/..//li[text()='" + street + "'] | //div[text()='" + label + "']/..//li[text()='" + street + "']")).exists()) {
//            $(By.xpath("//label[text()='" + label + "']/..//li[text()='" + street + "'] | //div[text()='" + label + "']/..//li[text()='" + street + "']")).click();
//        } else {
//            LoggerConsole.Logg("Не выбрана улица из ниспадающего списка");
//        }
    }


    public static void selectReq(String type) throws IOException {
//        Выбор типа запроса
        sleep(1000);
        WebElement street = $(By.xpath("//input[@value='Выберите тип']/ancestor::div[3]//li[text()='" + type + "']"));
        sleep(3000);
        try {
            actions().click(street).perform();
            LoggerConsole.LoggNotError("Выбран тип");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не выбран тип");
        }

    }

    public static void fillDistance(String length) {
//        Заполнение поля Протяженность, м
        distance = length;
        $(By.xpath("//input[@name='distance']")).sendKeys(length);
    }

    public static void fillWorkDescription(String work) {
        $(By.xpath("//textarea[@name='working']")).sendKeys(work);
    }


    public static void workDescriptionReq(String form) throws IOException {
//        Заполнение поля Содержание работ
        String timeNameVar = "Запрос №" + String.valueOf(System.currentTimeMillis());
        listOfRequests.add(timeNameVar);
        FillUtil.workDescription(form, timeNameVar);
    }

    public static void selectInitiative(String init, int i, String type) {
//        Выбираем инициативу при создании запроса
        $(By.xpath("//div[text()='" + init + "']/ancestor::div[2]//input")).click();

    }

    public static void uploadReqFiles() {
        String[] files = {"dgn.txt", "Основание.txt", "ТЗ.txt"}; //Типы статусов инициатив
        ElementsCollection collect = $$(By.xpath("//input[@type='file']"));
        for (int i = 0; i < collect.size(); i++) {
            collect.get(i).uploadFile(new File("src\\test\\repository\\Files\\" + files[i]));
        }
    }

    public static void openRequest(String init) {
//        Открытие инициативы двойным кликом
//        refresh(); //Костыль костылевич перезагрузка страницы
        sleep(1000);
        $(By.xpath("//td[@id='kot_id_1']//div[contains(text(), '" + init + "')]")).doubleClick();
        $(By.xpath("//h5[text()='Этапы планирования запроса']")).shouldHave();
    }


//   ========================================================================= Проверки ===================================================================
//   =======================================================================================================================================================


    public static void testInitsByStreet() {
//           Проверяю, что при выборе какой то улицы подгрузились все инициативы по ней
//          (заранее нужно записать все инициативы по данной улице в массив initsOnStreet методом countStreetInitiatives)
        int i = 0;
        try {
            for (; i < initsOnStreet.size(); i++) {
                assertTrue($(By.xpath("//h5[text()='Список Инициатив']/ancestor::div[2]//div[text()='" + initsOnStreet.get(i) + "']")).exists());
            }
        } catch (AssertionError err) {
            TestHelper.assertScreen();
            System.out.println(initsOnStreet.get(i) + " должна существовать, но ее нет");
            throw err;
        }
    }


    public static void testRequestExist(String req) {
//        Проверка, что запрос создан и висит на канбан доске (главная страница, второй столбец)
//        String xpath = "//td[@id='kot_id_1']//div[contains(text(), '" + req + "')]";
        try {
            sleep(1500);
            $(By.xpath("//td[@id='kot_id_1']//div[contains(text(), '" + req + "')]")).shouldHave();
//            assertTrue($(By.xpath(xpath)).isDisplayed());

        } catch (AssertionError err) {
            TestHelper.assertScreen();
            System.out.println("Ожидаемый результат: создан запрос с именем " + req);
            throw err;
        }
    }

    public static void testRequestData(String currentReq) throws IOException {
//        Проверяю, что данные, введенные при создании запроса корректно сохранились
        $(By.xpath("//div[text()='Запрос']")).shouldHave();
        sleep(2000);
        try {
//            Проверяю название улицы
            assertTrue($(By.xpath("//div[text()='Запрос']/ancestor::div//label[text()='Улица']/..//div[text()='" + RequestPage.reqMap.get(currentReq)[0] + "']")).exists()); //*
//            Проверяю Протяженность
            assertTrue($(By.xpath("//div[text()='Запрос']/ancestor::div//label[text()='Протяженность']/..//div[text()='" + RequestPage.reqMap.get(currentReq)[1] + "']")).exists());
//            Проверяю название Запроса
            assertTrue($(By.xpath("//div[text()='Запрос']/ancestor::div//label[text()='Содержание работ']/..//div[text()='" + currentReq + "']")).exists()); //*
//            Проверяю Тип запроса
            assertTrue($(By.xpath("//div[text()='Запрос']/ancestor::div//label[text()='Тип']/..//div[text()='" + RequestPage.reqMap.get(currentReq)[2] + "']")).exists());
//            Проверяю Инициативу
            assertTrue($(By.xpath("//h5[text()='Список Инициатив']/ancestor::div[2]//input[@value='" + RequestPage.reqMap.get(currentReq)[3] + "']")).exists());


//            Проверяю загруженные файлы
            assertTrue($(By.xpath("//h5[text()='Загруженные файлы']/ancestor::div[2]//input[@value='dgn.txt']")).exists());
            assertTrue($(By.xpath("//h5[text()='Загруженные файлы']/ancestor::div[2]//input[@value='Основание.txt']")).exists());
            assertTrue($(By.xpath("//h5[text()='Загруженные файлы']/ancestor::div[2]//input[@value='ТЗ.txt']")).exists());


        } catch (AssertionError err) {
            TestHelper.assertScreen();
            System.out.println("Данные в запросе не верны");
            throw err;
        }
        ButtonsUtil.clickSpanButton("Отмена");

    }

    //ToDo Проверка рассчета даты корректировки
    public static void testAdjustmentDate() {
//        Проверка рассчета даты корректировки
        int plusDays, natExams = 0, proj = 0;
        plusDays = 1 + natExams + proj;
    }

    public static void dragInitToRequest(String init, String idDrag, String idDrop) throws AWTException {


//                Robot robot = new Robot();
////        robot.setAutoDelay(500); //Задержка по умолчанию (чем больше, тем медленнее движется мышь)
//        robot.mouseMove(300, 600); //Двигаем мышку
////         robot.mouseMove(200, 600); // SA
//        robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK); //Нажимаем и удерживаем мышь
//        robot.mouseMove( 550, 600); //Движемся к этим координатам
////        robot.mouseMove(400, 600); //SA
//        robot.delay(500); // Задержка в конкретном месте (чем больше, тем медленнее движется мышь)
//        robot.mouseMove(570, 600); //Двигаем мышь еще раз, тк иначе перетаскиваемый элемент не виден
////        robot.mouseMove(600, 450); // SA
//        robot.delay(1500);
//        robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
//        robot.waitForIdle(); // Ждет пока исполнится вся цепочка действий

        WebElement x1 = $(By.xpath("//td[@data-bem='{\"c-kanban__column\":{\"kot_id\":\"" + idDrag + "\"}}']/div/div[2]/div[last()]/div[2]"));
        WebElement x2 = $(By.xpath("//td[@data-bem='{\"c-kanban__column\":{\"kot_id\":\"" + idDrop + "\"}}']/div/div[2]/div[last()]/div[2]"));
        $(By.xpath("//td[@data-bem='{\"c-kanban__column\":{\"kot_id\":\"" + idDrag + "\"}}']/div/div[2]/div[last()]/div[2]")).click();
        $(By.xpath("//td[@data-bem='{\"c-kanban__column\":{\"kot_id\":\"" + idDrop + "\"}}']/div/div[2]/div[last()]/div[2]")).click();

        Point xxx = x1.findElement(By.xpath("//td[@data-bem='{\"c-kanban__column\":{\"kot_id\":\"" + idDrag + "\"}}']/div/div[2]/div[last()]/div[2]")).getLocation();
        Point xxx1 = x2.findElement(By.xpath("//td[@data-bem='{\"c-kanban__column\":{\"kot_id\":\"" + idDrag + "\"}}']/div/div[2]/div[last()]/div[2]")).getLocation();
        Robot robot = new Robot();
        robot.mouseMove(xxx.getX() + 40, xxx.getY() + 10);
        robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
        robot.setAutoDelay(1000);
        robot.mouseMove(xxx1.getX() + 30, xxx1.getY() - 1);
        robot.mouseMove(xxx1.getX() + 30, xxx1.getY() - 30);
        robot.setAutoDelay(1000);
        robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
        robot.waitForIdle();


    }

    public static void modalClickOk() throws AWTException {
        //Нажимает на кнопку Ок в модальном окне с помощью заданной координаты
        Robot robot = new Robot();
        robot.setAutoDelay(500);
        robot.mouseMove(1110, 180);
        robot.mousePress(BUTTON1_DOWN_MASK);
        robot.mouseRelease(BUTTON1_DOWN_MASK);
        robot.waitForIdle(); // Ждет пока исполнится вся цепочка действий
    }

    public static void testInitSelection(String init) {
        String xpath = "//div[@data-object='initiative']/div[contains(.,'" + init + "')]";
        try {
            $(By.xpath(xpath)).shouldHave();
        } catch (AssertionError err) {
            TestHelper.assertScreen();
            System.out.println("Ожидаемый xpath: " + xpath);
            throw err;
        }
    }

    public static void robothelperInitSelection() throws AWTException {
        Robot robot = new Robot();
        robot.mouseMove(1315, 550);
        robot.mousePress(MouseEvent.BUTTON1_MASK); //Нажимаем и удерживаем мышь
        robot.mouseRelease(MouseEvent.BUTTON1_MASK);
        robot.mousePress(MouseEvent.BUTTON1_MASK); //Нажимаем и удерживаем мышь
        robot.mousePress(MouseEvent.BUTTON1_MASK); //Нажимаем и удерживаем мышь
        robot.mouseRelease(MouseEvent.BUTTON1_MASK);
        actions().sendKeys(Keys.END).build().perform();
        sleep(5000);
        robot.waitForIdle(); // Ждет пока исполнится вся цепочка действий
    }

    public static void testRequestCheck() throws AWTException {
        // проверяю, при отклонении задачи(принято на работу), есть подсказка, что комментарий обязательное поле при отклонении.
    }

    //      Robot robot = new Robot();
    public static void testError() {
        String xpath = "//body/div[text()='Не все обязательные поля заполнены!']";
        try {
            //Проверка при неверного заполнения заявки
            $(By.xpath(xpath)).waitUntil(Condition.visible, 3000);
            assertTrue($(By.xpath(xpath)).exists());
        } catch (AssertionError err) {
            TestHelper.assertScreen();
            System.out.println("Ожидаемый xpath: " + xpath);
            throw err;
        }

        $(By.xpath("//span[text()='Отклонить']")).click();
        assertTrue($(By.xpath("//div[@class='b-textarea-field']/textarea[@placeholder='Необходимо ввести комментарий!']")).exists());
        //проверяю чекбокс отклонения снаружи задачи
        $(By.xpath("//div[@class='b-textarea-field']/textarea[@placeholder='Необходимо ввести комментарий!']")).sendKeys("комментарий");
        $(By.xpath("//span[text()='Отклонить']")).click();

        // проверяю чек бокс внутри задачи
        RequestPage.testActCheck1();
        assertTrue($(By.xpath("//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).exists());
        //       robot.waitForIdle(); // Ждет пока исполнится вся цепочка действий
        $(By.xpath("//span[text()='Отмена']")).click();
    }


    //    public static void testActCheck() {
//        $(By.xpath("//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-0']/ancestor::li/span[text()='Принято в работу']")).waitWhile(Condition.visible, 1000);
//           }
    public static void testActCheck1() {

        String xpath = "//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']/ancestor::li/span[text()='Принято в работу']";

        try {
//        выбираю запрос c крестиком внутри чекбокса "Принято в работу"
            assertTrue($(By.xpath(xpath)).exists());
            $(By.xpath(xpath)).click();
        } catch (AssertionError err) {
            TestHelper.assertScreen();
            System.out.println("Ожидаемый xpath: " + xpath);
            throw err;
        }
    }

    public static void TestfilterShowDenied() {
        //ставим галку на верхний чекбокс корректировка
        $(By.xpath("//div[text()='Корректировка']/ancestor ::div[1]//span[text()='Только \"не согласованные\"']")).shouldHave();
        $(By.xpath("//div[text()='Корректировка']/ancestor ::div[1]//span[text()='Только \"не согласованные\"']")).click();
    }

    public static void nameTabsInsideQuery() {
        //проверка верхних вкладок внутри созданного запроса
        //       String xpath = "//div[@id='tabBarRequestShow']/descendant::div[text()='" + txt + "']";
        sleep(1000);
        RequestPage.enterClickQuery();
        sleep(1000);
        $(By.xpath("//div[@id='tabBarRequestShow']")).waitUntil(Condition.visible, 1000);
        sleep(1000);
        $(By.xpath("//div[@id='tabBarRequestShow']/descendant::div[text()='Доп параметры']")).waitUntil(Condition.visible, 1000);
        $(By.xpath("//div[@id='tabBarRequestShow']/descendant::div[text()='Досье']")).waitUntil(Condition.visible, 1000);
        $(By.xpath("//div[@id='tabBarRequestShow']/descendant::div[text()='Запрос']")).waitUntil(Condition.visible, 1000);
        $(By.xpath("//div[@id='tabBarRequestShow']/descendant::div[text()='Доп параметры']")).click();
        assertTrue($(By.xpath("//*[@id='main_content_request_show']//div[text()='Исходящий номер']")).exists());
        $(By.xpath("//div[@id='tabBarRequestShow']/descendant::div[text()='Досье']")).click();
        assertTrue($(By.xpath("//*[@id='main_content_request_show']//div[text()='Комментарий']")).exists());
        $(By.xpath("//div[@id='tabBarRequestShow']/descendant::div[text()='Запрос']")).click();
        assertTrue($(By.xpath("//*[@id='main_content_request_show']//h5[text()='Этапы планирования запроса']")).exists());
    }

    public static void clickHarmonizationInQuery(String vol) {
        //кликаем на параметры согласования внутри заявки.
        $(By.xpath("//span[text()='" + vol + "']")).click();
        sleep(1000); //TODO
        //  vol = АМПП, МГТ,  Дирекция ОДД, Утверждение ПОДД - параметры согласования
    }

    public static void clickCloseHarmInQuery() {
        // закрываем окно согласования внутри запроса
        $(By.xpath("//button[@class='b-block fa fa-times close i-bem b-block_js_inited']")).shouldHave();
        $(By.xpath("//button[@class='b-block fa fa-times close i-bem b-block_js_inited']")).click();
    }

    public static void clickCloseQuery() {
        // закрываем запрос
        sleep(1500);
        $(By.xpath("//button[@class='b-button-call close i-bem b-button-call_js_inited']")).click();
    }

    public static void enterClickQuery() {
        //  кликаем на первый  элемент списка запросов.
        $(By.xpath("//span[@class='b-checkbox-task__name i-bem b-checkbox-task__name_js_inited']/ancestor::td[@class='c-kanban__column stage i-bem c-kanban__column_js_inited']//div[@class='c-process-card__main i-bem c-process-card__main_js_inited']")).doubleClick();
    }

    public static void checkboxDesign() {
// входим в задачу чекбокс(проектирование)
        $(By.xpath("//span[@class='b-checkbox-task__name i-bem b-checkbox-task__name_js_inited']/ancestor::li[@class='b-checkbox-task i-bem b-checkbox-task_js_inited b-checkbox-task_state-none']")).click();
    }

    public static void designFilesNegativeTest() {
        // неверный формат.
        String[] files = {"initiative.txt", "dgn.txt", "ТЗ.txt"}; //Типы статусов инициатив
        ElementsCollection collect = $$(By.xpath("//input[@type='file']"));
        for (int i = 0; i < collect.size(); i++) {
            collect.get(i).uploadFile(new File("src\\test\\repository\\Files\\" + files[i]));
        }
    }

    public static void existFilterShowDenied() {
        assertTrue($(By.xpath("//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-2']")).exists());
//проверяю что внутри задачи стоит чекбокс несогласия
    }

    public static void
    existDetalFiles(String form) {
        $(By.xpath("//div[text()='" + form + "']")).waitUntil(Condition.visible, 1000);
    }

    public static void fellChecboxedRequest(String form) {
        // заполняем все чекбоксы самой нижней задачи.
        $(By.xpath("//div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-0']/ancestor::li/span[text()= '" + form + "']")).shouldHave();
        $$(By.xpath("//span[text()='" + form + "']/ancestor::li[@class='b-checkbox-task i-bem b-checkbox-task_js_inited b-checkbox-task_state-none']/div[@class='b-checkbox-task__checkbox i-bem b-checkbox-task__checkbox_js_inited b-checkbox-task__checkbox_state-0']")).last().click();
    }

    public static void fillOtvest(String otvestvenn) {
        $(By.xpath("//label[text()='Отвественный']/..//input[@class='b-combobox__input b-input form-control checkInput i-bem b-combobox__input_js_inited']")).click();
        $(By.xpath("//label[text()='Отвественный']/..//input[@class='b-combobox__input b-input form-control checkInput i-bem b-combobox__input_js_inited']")).sendKeys(otvestvenn);
        $(By.xpath("//li[@class='b-combobox__item i-bem b-combobox__item_js_inited name']")).click();
    }


    public static void selectDropdownStreet(String street) {
        $(By.xpath("//li[@class='b-combobox__item c-request-initiative-select i-bem c-request-initiative-select_js_inited' and text()='" + street + "']")).click();
    }

    public static void deleteFieldStreet() throws IOException {

        WebElement street = $(By.xpath("//label[text()='Улица']/..//input[@class='b-combobox__input b-input form-control checkInput i-bem b-combobox__input_js_inited']"));
        sleep(3000);
        try {
            $(By.xpath("//label[text()='Улица']/..//input[@class='b-combobox__input b-input form-control checkInput i-bem b-combobox__input_js_inited']")).click();
            actions().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
            actions().sendKeys(Keys.DELETE).build().perform();

            LoggerConsole.LoggNotError("Удалены данные из поле 'Улица'");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не удалились данные из поля 'Улица' | Не увидел элемент поля 'Улица'");
        }
    }

    public static void fillStreet(String street2) throws IOException {

        WebElement street = $(By.xpath("//label[text()='Улица']/..//input[@class='b-combobox__input b-input form-control checkInput i-bem b-combobox__input_js_inited']"));
        sleep(1500);
        try {
            actions().click(street).sendKeys(street2).perform();
            LoggerConsole.LoggNotError("Заполнил поле 'Улица'");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не заполнил значениями в поле 'Улица'");
        }
    }


    // проверяем что после второго символа
    public static void StreetOpenListexists() throws IOException {
        RequestPage.fillStreet("М");
        RequestPage.testNotOpenWindowReq();
        RequestPage.fillStreet("я");
        sleep(1500);
        RequestPage.testOpenWindowReq();
        RequestPage.deleteFieldStreet();
    }

    public static void testOpenWindowReq() throws IOException {
        if ($(By.xpath("//label[text()='Улица']/ancestor::div/div/div/ul[@class='b-combobox__list dropdown-menu i-bem b-combobox__list_js_inited']")).exists()) {
            $(By.xpath("//label[text()='Улица']/ancestor::div/div/div/ul[@class='b-combobox__list dropdown-menu i-bem b-combobox__list_js_inited']")).waitUntil(visible, 10000);
            LoggNotError("Cписок улицы открылся после второго символа");
        } else {
            Logg("Cписок улицы не открылся после второго символа");
        }
    }

    public static void testNotOpenWindowReq() {
        if ($(By.xpath("//label[text()='Улица']/ancestor::div/div/div/ul[@class='b-combobox__list dropdown-menu i-bem b-combobox__list_js_inited']")).exists()) {
            $(By.xpath("//label[text()='Улица']/ancestor::div/div/div/ul[@class='b-combobox__list dropdown-menu i-bem b-combobox__list_js_inited']")).waitUntil(not(visible), 10000);
        } else {
            Logg(" список улицы открылся после первого символа");
        }
    }

    public static void selectRandomStreet() throws IOException {
//        Выбор первой улицы street из ниспадающего списка
        try {
//            $(By.xpath("//label[text()='" + label + "']/..//li[text()='" + street + "']")).click();
            $(By.xpath("//ul[@class='b-combobox__list dropdown-menu i-bem b-combobox__list_js_inited']/li[@class='b-combobox__item c-request-initiative-select i-bem c-request-initiative-select_js_inited'][1]")).click();
            LoggNotError("Выбрана улица из ниспадающего списка");
        } catch (Error e) {
            Logg("Не выбрана улица из ниспадающего списка");
        }

    }



}





