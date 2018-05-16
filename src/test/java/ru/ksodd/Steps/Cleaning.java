package ru.ksodd.Steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import ru.ksodd.Helpers.ButtonsUtil;
import ru.ksodd.Pages.InitiativePage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;

public class Cleaning {
//    Этот класс не трогать не запускать не пушить и вообще забудь что он существует

    @When("^delete all initiatives$")
    public static void deleteAllInitiatives() throws IOException {
        List<SelenideElement> inits = new ArrayList<SelenideElement>();

        ElementsCollection collect = $$(By.xpath("//td[@data-kot-id='0']//div[contains(text(), 'Автор')]"));

        for (int i = 0; i < collect.size(); i++) {
            inits.add(collect.get(i));
        }

        for (int j = 0; j < collect.size(); j++) {
            sleep(400);
            inits.get(j).doubleClick();

//            $(By.xpath("//h5[text()='Загруженные файлы']")).waitWhile(Condition.visible, 3000);

            ButtonsUtil.clickSpanButton("Отказать");
            InitiativePage.fillreasonOfDenying("Fire and Blood :3");
            ButtonsUtil.clickSpanButton("Отклонить");
        }


    }


    @When("^delete all initiatives var$")
    public static void deleteAllInitiativesVar() throws Throwable {

        ElementsCollection collect = $$(By.xpath("//td[@data-kot-id='0']//div[contains(text(), 'Инициатива')]"));

        for (int i = 0; i < collect.size(); i++) {
            collect.get(i).doubleClick();
        }


        $(By.xpath("//h5[text()='Загруженные файлы']")).waitWhile(Condition.visible, 3000);

        ButtonsUtil.clickSpanButton("Отказать");
        InitiativePage.fillreasonOfDenying("Fire and Blood :3");
        ButtonsUtil.clickSpanButton("Отклонить");

        throw new PendingException();
    }


}


