package ru.ksodd.Runners;


import com.codeborne.selenide.Configuration;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.testng.annotations.Test;
import ru.ksodd.Helpers.LoggerConsole;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

@Test
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/ru/ksodd/Features"},
        glue = "ru/ksodd/Steps",
//        tags = {"@smokeTest#4"},
//        tags = {"@smokeTest#1,@smokeTest#2,@smokeTest#3"}, //Запустить несколько фич
        tags = {"@scenario#1"},
        format = {"json:target/cucumber.json", "html:target/site/cucumber-pretty"},
        plugin = {"pretty", "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "html:target/cucumber-reports"},
        strict = true,
        monochrome = true

)

public class Scenario_1 {

    @BeforeClass

    static public void Initialization() throws IOException {
        Configuration.timeout = 20000;
        Configuration.startMaximized = true;
        System.setProperty("webdriver.chrome.driver", "src\\test\\repository\\webDriver\\chromedriver.exe");
        Configuration.browser = "chrome";
        Configuration.savePageSource = false;

//        Если нужно оставлять броузер открытым по окончании теста, по умолчанию значение false
//        Configuration.holdBrowserOpen = true;

        open("http://172.20.0.78:9494/");
        Configuration.savePageSource = false;

        LoggerConsole.LoggNotError("Отчет Сценария 1" + "\n");
    }
}
