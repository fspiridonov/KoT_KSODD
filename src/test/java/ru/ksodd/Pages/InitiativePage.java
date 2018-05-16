package ru.ksodd.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import ru.ksodd.Helpers.ButtonsUtil;
import ru.ksodd.Helpers.FillUtil;
import ru.ksodd.Helpers.LoggerConsole;
import ru.ksodd.Helpers.TestHelper;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ru.ksodd.Helpers.LoggerConsole.LoggNotError;
import static ru.ksodd.Helpers.StorageString.contentOfWorks.cWork;
import static ru.ksodd.Pages.PackagePage.timeNameVar;

public class InitiativePage {


//   ================================================================== Глобальные переменные =============================================================
//   ======================================================================================================================================================


    public static List<String> listOfMyasnitskayaInitiatives = new ArrayList<String>();
    public static List<String> listOfVarvarkaInitiatives = new ArrayList<String>();


//  =================================================================== Работа с формой ===================================================================
//  =======================================================================================================================================================


    public static void fillInput(String form, String label, String val) {
//        Заполняет поле ввода (например, название улице с подписью label на форме form)
        WebElement street = $(By.xpath("//div[text()='" + form + "']/ancestor::form//div[text()='"
                + label + "']/..//input[@type='text'] | //h3[text()='" + form + "']/ancestor::form//label[text()='"
                + label + "']/..//input[@type='text']"));
        sleep(3000);
        try {
            actions().click(street).sendKeys(val).perform();
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не найдено поле");
        }
    }
    public static void selectStreet(String label, String street) {
//        Выбор улицы street из ниспадающего списка с подписью label
        $(By.xpath("//div[text()='" + label + "']/..//li[text()='" + street + "']")).click();
    }

    public static void workDescriptionInit(String form, String str) throws IOException {
//        Заполнение поля Содержание работ
        String timeNameVar = "Инициатива №" + String.valueOf(System.currentTimeMillis());
        switch (str) {
//                Два массива для инициатив каждой из улицы чтобы после проверки инициатив, если что то пойдет не так, все равно был свободный массив
            case "Мясницкая улица":
                listOfMyasnitskayaInitiatives.add(timeNameVar);
                break;
            case "Улица Варварка":
                listOfVarvarkaInitiatives.add(timeNameVar);
                break;
        }
        FillUtil.workDescription(form, timeNameVar);
    }

    public static void selectImportance(String val) {
//        Выбор значения в поле Статус
        if ($(By.xpath("//div[text()='Тип']/..//div[contains(@class, 't-patterns__combobox')]")).exists()) {
            $(By.xpath("//div[text()='Тип']/..//div[contains(@class, 't-patterns__combobox')]")).click();
        } else {
            $(By.xpath("//li[text()='" + val + "']")).click();
        }
    }

    public static void openInitiative(String init) {
//        Открытие инициативы двойным кликом
//        refresh(); //Костыль костылевич перезагрузка страницы
        sleep(5000);
        $(By.xpath("//td[@id='kot_id_0']//div[contains(text(), '" + init + "')]")).doubleClick();
        $(By.xpath("//h5[text()='Загруженные файлы']")).shouldHave();
    }


    public static void fillreasonOfDenying(String reason) {
//        Заполняю причину удаления инициативы (кнопка Отказать)
        $(By.xpath("//textarea[@name='reason']")).sendKeys(reason);
    }


    public static void editDescription() {
//        Редактирую описание инициативы
//        Создаю переменную с новым названием
        String editedInitName = "Теперь это инициатива №" + String.valueOf(System.currentTimeMillis());
//        Заменяю в массиве с описаниями инициатив старые данные отредактированной инициативы на новые
        listOfMyasnitskayaInitiatives.set(1, editedInitName);
        $(By.xpath("//h3[text()='Изменить инициативу']/ancestor::div[3]//textarea[@name='working']")).sendKeys(editedInitName);
    }


    public static void editInitStreet(String val) {
//        Редактирую улицу инициативы
        $(By.xpath("//h3[text()='Изменить инициативу']/ancestor::div//label[text()='Улица']/..//span")).click();
//        TODO посмотреть почему тормозит редактирование, создать файл с багами
        $(By.xpath("//h3[text()='Изменить инициативу']/ancestor::div//label[text()='Улица']/..//button/input")).sendKeys(val);
        $(By.xpath("//h3[text()='Изменить инициативу']/ancestor::div//li[text()='Потаповский переулок']")).click();
    }


    public static void clearListOf() {
//        Очищаю массив инициатив (чтобы новые отсчеты начинать с 0, и не запутаться в куче индексов)
        listOfVarvarkaInitiatives.clear();
    }


//   ========================================================================= Проверки ===================================================================
//   =======================================================================================================================================================


    public static void testInitiativeDataEdit(String init, String state, String street) {
//            Проверяю, что при редактировании не затираются старые данные (до того как сам не изменишь)
        String xpath = "//td[@id='kot_id_0']//div[contains(text(), '" + init + "')]";
        try {
//            Улица
            assertTrue($(By.xpath("//h3[text()='Изменить инициативу']/ancestor::div[3]//..//input[@value='" + street + "']")).exists());
//            Проверяю название Инициативы
            assertTrue($(By.xpath("//h3[text()='Изменить инициативу']/ancestor::div[3]//..//textarea[text()='" + init + "']")).exists());
//            Проверяю Статус
            assertTrue($(By.xpath("//div[text()='Инициатива']/ancestor::div//label[text()='Статус']/..//label[text()='" + state + "']")).exists());
        } catch (AssertionError err) {
            TestHelper.assertScreen();
            System.out.println("При нажатии редактировать инициативу, открывается форма без введенных ранее данных");
            throw err;
        }
    }

    public static void testInitiativeDel(String init) {
//        Проверяю, что инициативы удалились
        String xpath = "//td[@id='kot_id_0']//div[contains(text(), '" + init + "')]";
        try {
            sleep(300);
            assertFalse($(By.xpath(xpath)).exists());
        } catch (AssertionError err) {
            TestHelper.assertScreen();
            System.out.println("Ожидаемый результат: инициатива с именем \"" + init + "\" не существует");
            throw err;
        }
    }

    public static void testInitiative(String init) {
//        Проверка, что инициатива создалась и висит на канбан доске (главная страница, первый столбец)
        String xpath = "//td[@id='kot_id_0']//div[contains(text(), '" + init + "')]";
        try {
            sleep(3000);
            assertTrue($(By.xpath(xpath)).exists());
        } catch (AssertionError err) {
            TestHelper.assertScreen();
            System.out.println("Ожидаемый результат: создана инициатива с именем " + init);
            throw err;
        }
    }

    public static void testImportantSign(String init) {
//        Проверка, что инициатива со статусом Важное имеет иконку восклицательного знака
        try {
            sleep(300);
            assertTrue($(By.xpath("//td[@id='kot_id_0']//div[contains(text(), '" + init + "')]/..//div[contains(@class, 'c-process-card__type_type-2')]")).exists());
        } catch (AssertionError err) {
            TestHelper.assertScreen();
            System.out.println("Ожидаемый результат: у инициативы с названием " + init + "стоит значок с восклицательным знаком (статус Важное)");
            throw err;
        }
    }

    public static void testInitiativeData(String init, String state, String street) throws IOException {
//        Проверяю, что данные, введенные при создании инициативы корректно сохранились
//        refresh(); // Костыль костылевич перезагрузка страницы
        sleep(3000);
        openInitiative(init);
        $(By.xpath("//div[text()='Инициатива']")).shouldHave();
        try {
//            Проверяю название улицы
            sleep(1500);
            $(By.xpath("//div[text()='Инициатива']/ancestor::div//label[text()='Улица']/..//label[text()='" + street + "']")).shouldHave();
//            assertTrue($(By.xpath("//div[text()='Инициатива']/ancestor::div//label[text()='Улица']/..//label[text()='" + street + "']")).exists());
//            Проверяю название Инициативы
            $(By.xpath("//div[text()='Инициатива']/ancestor::div//label[text()='Содержание работ']/..//label[text()='" + init + "']")).shouldHave();
            assertTrue($(By.xpath("//div[text()='Инициатива']/ancestor::div//label[text()='Содержание работ']/..//label[text()='" + init + "']")).exists());
//            Проверяю Статус
            $(By.xpath("//div[text()='Инициатива']/ancestor::div//label[text()='Статус']/..//label[text()='" + state + "']")).shouldHave();
            assertTrue($(By.xpath("//div[text()='Инициатива']/ancestor::div//label[text()='Статус']/..//label[text()='" + state + "']")).exists());
//            Проверяю загруженный файл
            $(By.xpath("//div[text()='Инициатива']/ancestor::div//h5[text()='Загруженные файлы']/ancestor::form//input[@value='Initiative.txt']")).shouldHave();
            assertTrue($(By.xpath("//div[text()='Инициатива']/ancestor::div//h5[text()='Загруженные файлы']/ancestor::form//input[@value='Initiative.txt']")).exists());

            if (street == "Потаповский переулок") {
                assertTrue($(By.xpath("//div[text()='Инициатива']/ancestor::div//h5[text()='Загруженные файлы']/ancestor::form//input[@value='Initiative_Edit.txt']")).exists());
            }

        } catch (AssertionError err) {
            TestHelper.assertScreen();
            System.out.println("Данные в инициативе не верны");
            throw err;
        }
        ButtonsUtil.clickSpanButton("Отмена");
    }
    public static void workContentErr(){
        //проверяю наличие подсказки при неверном введении данных
        String xpath = "//div[text()='Не все обязательные поля заполнены!']";
        $(By.xpath("//div[text()='Не все обязательные поля заполнены!']")).waitUntil(Condition.visible, 3000);
        try {
            $(By.xpath(xpath)).waitUntil(Condition.visible, 3000);
            } catch (AssertionError err) {
        TestHelper.assertScreen();
        System.out.println("Данные в запросе не верны");
        throw err;
    }
//        ButtonsUtil.clickSpanButton("Отмена");
    }
    public static void filterTest() {
        String xpath = "//span[text()='Улица Варварка']";
        $(By.xpath("//div[@id='place_content']/descendant::span[1]")).click();
        $(By.xpath("//li[text()='ЦАО']")).click();
        $(By.xpath("//button[@class='btn btn-default']/descendant::input")).sendKeys("Улица Варварка");
        $(By.xpath("//li[text()='Улица Варварка']")).click();
        $(By.xpath("//span[text()='Улица Варварка']")).waitUntil(Condition.visible, 3000);
        try {
            $(By.xpath(xpath)).waitUntil(Condition.visible, 3000);
            assertTrue($(By.xpath(xpath)).exists());

        } catch (AssertionError err) {
            TestHelper.assertScreen();
            System.out.println("Фильтр не работает");
            throw err;
        }
    }
    public static void modalClickOk() throws AWTException {
        //Нажимает на кнопку Ок в модальном окне с помощью заданной координаты
        Robot robot = new Robot();
        robot.setAutoDelay(500);
        robot.mouseMove( 1000, 180);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.waitForIdle(); // Ждет пока исполнится вся цепочка действий
    }
    public static void waitForNegative(String text){
        // ожидание загрузки окна создания новой инициативы
        $(By.xpath("//button//span[text()='" + text + "']")).waitUntil(Condition.visible, 3000);
    }
    public static void workContent(){
        $(By.xpath("//textarea[@name='working']")).sendKeys("teZt");
    }
    public static void workContentСlean(){
        $(By.xpath("//textarea[@name='working']")).clear();
    }
public static void inInitiativeLast() throws IOException {
    WebElement xpath = $(By.xpath("//td[@data-lavel='0']//div[@class='c-process-card drag-drop i-bem c-process-card_js_inited'][last()]/div[@class='c-process-card__main i-bem c-process-card__main_js_inited']"));
    try {
        actions().doubleClick(xpath).perform();
        LoggerConsole.LoggNotError("зашёл в последнюю из списка инициативу ");
    } catch (AssertionError err) {
        LoggerConsole.Logg("Не зашёл в последнюю из списка инициативу ");
    }
}


    public static void inInitiativeRedactor() throws IOException {
        WebElement xpath = $(By.xpath("//span[@class='b-button-call__glyphicon glyphicon glyphicon-pencil']"));
        try {
            actions().click(xpath).perform();
            LoggerConsole.LoggNotError("зашёл в последнюю из списка инициативу ");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не зашёл в последнюю из списка инициативу ");
        }

    }
    public static void InitiativeRedactorStreet(String strt) throws IOException {
        WebElement xpath = $(By.xpath("//li[text()='" + strt + "']"));

        sleep(3000);
        try {
            $(By.xpath("//div[@class='b-combobox-field i-bem b-combobox-field_js_inited']/label[text()='Улица']/ancestor::div[1]//div[@class='b-combobox i-bem b-combobox_js_inited']")).click();
            actions().sendKeys("мя").perform();
            actions().click(xpath).perform();

            LoggerConsole.LoggNotError("ввёл данные из поле 'Улица'");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не ввел данные поле 'Улица' | Не увидел элемент поля 'Улица'");
        }
    }

    public static void fillContentRedactorInit() throws IOException {

        sleep(2000);
        WebElement content = $(By.xpath("//textarea[@class='b-textarea-field__name form-control i-bem b-textarea-field__name_js_inited']"));
        sleep(3000);

        try {
            actions().click(content).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
            actions().sendKeys(Keys.DELETE).build().perform();
            actions().click(content).sendKeys(timeNameVar).perform();
            cWork = timeNameVar;
            LoggNotError("Заполнения значения в поле 'Содержания работ'");
        } catch (AssertionError err) {
            LoggerConsole.Logg(" не заполнил поле содержания работ");
        }
    }

    public static void InitiativeRedactorType(String sts) throws IOException {
        WebElement xpath = $(By.xpath("//li[text()='" + sts + "']"));

        sleep(3000);
        try {
            $(By.xpath("//div[@class='b-combobox-field i-bem b-combobox-field_js_inited']/label[text()='Статус']/ancestor::div[1]//div[@class='b-combobox i-bem b-combobox_js_inited']")).click();
            actions().click(xpath).perform();

            LoggerConsole.LoggNotError("ввёл данные из поле 'Статус'");
        } catch (AssertionError err) {
            LoggerConsole.Logg("Не ввел данные поле 'Статус' | Не увидел элемент поля 'Статус'");
        }
    }

}

