package net.thucydides.showcase.cucumber.pages;

import com.google.common.base.Function;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.ThucydidesSystemProperty;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HomePage extends PageObject {


    public void navigateTo(String urlpath)
    {
        EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();

        String baseUrl = variables.getProperty(ThucydidesSystemProperty.WEBDRIVER_BASE_URL);
        getDriver().manage().window().maximize();

        this.getDriver().navigate().to(baseUrl+"/"+urlpath);
    }


}
