package be.hetnest.hetnestproject;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class StepDefinitionsTest {
    WebDriver driver;


    public void pre_given() {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Given("^I am on the page where I can enter my login and password$")
    public void i_am_on_the_page_where_I_can_enter_my_login_and_password() throws Throwable {
        pre_given();
        driver.navigate().to("http://localhost:8080/login");
    }

    @When("^I enter \"([^\"]*)\" in the ([^\"]*) field$")
    public void i_enter_in_the_username_field(String enteredText, String fieldName) throws Throwable {
        driver.findElement(By.name(fieldName)).sendKeys(enteredText);

    }

    @When("^I enter \"([^\"]*)\" in the password passwordfield$")
    public void i_enter_in_the_password_field(String enteredText) throws Throwable {
        driver.findElement(By.name("password")).sendKeys(enteredText);
    }

    @When("^I press on the Login button$")
    public void i_press_on_the_Login_button() throws Throwable {
        driver.findElement(By.name("login")).click();
    }

    @Then("^I should be on the home screen$")
    public void i_should_be_on_the_home_screen() throws Throwable {
        Assert.assertTrue(driver.getCurrentUrl().contentEquals("http://localhost:8080/"));
    }

    @Given("^I am on the home screen logged in$")
    public void i_am_on_the_home_screen_logged_in() throws Throwable {
        Assert.assertTrue(driver.getCurrentUrl().contentEquals("http://localhost:8080/"));
    }

    @When("^I press on the bekijk aanbiedingen button$")
    public void i_press_on_the_bekijk_aanbiedingen_button() throws Throwable {
        driver.findElement(By.name("aanbiedingen")).click();
    }

    @Then("^I am directed to the aanbiedingen screen$")
    public void i_am_directed_to_the_aanbiedingen_screen() throws Throwable {
        Assert.assertTrue(driver.getCurrentUrl().contentEquals("http://localhost:8080/aanbiedingen.html"));
    }

    @Given("^I am on the aanbiedingen screen$")
    public void i_am_on_the_aanbiedingen_screen() throws Throwable {
        Assert.assertTrue(driver.getCurrentUrl().contentEquals("http://localhost:8080/aanbiedingen.html"));
    }

    @When("^I press on the nieuwe aanbieding link$")
    public void i_press_on_the_nieuwe_aanbieding_link() throws Throwable {
        driver.findElement(By.name("nieuweAanbieding")).click();
    }

    @Then("^I am directed to the page where I can introduce a new aanbieding$")
    public void i_am_directed_to_the_page_where_I_can_introduce_a_new_aanbieding() throws Throwable {
        Assert.assertTrue(driver.getCurrentUrl().contentEquals("http://localhost:8080/nieuweAanbieding.html"));
    }
    @Given("^I am on the page where I can introduce a new aanbieding$")
    public void i_am_on_the_page_where_I_can_introduce_a_new_aanbieding() throws Throwable {
        Assert.assertTrue(driver.getCurrentUrl().contentEquals("http://localhost:8080/nieuweAanbieding.html"));
    }

    @When("^I press on the Save button$")
    public void i_press_on_the_Save_button() throws Throwable {
        driver.findElement(By.name("submit")).click();
    }

    class LabelData {
        String label;
        String data;
    }

    @Then("^I should see the following on the screen$")
    public void i_should_see_the_following_on_the_screen(List<LabelData> checklist) throws Throwable {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .textToBePresentInElementLocated(By.tagName("body"), "Details van aanbieding"));

        String bodyText = driver.findElement(By.tagName("body")).getText();
        for (LabelData ld: checklist){
            String text2bFound = ld.label + " " + ld.data;
            Assert.assertTrue("Did not find this text: "+text2bFound+"\n",bodyText.contains(text2bFound));
        }
    }

    @When("^I click the the ([^\"]*) link$")
    public void i_click_the_the_Lijst_van_alle_aanbiedingen_link(String linkTekst) throws Throwable {
        driver.findElement(By.id("lijstAllAanbiedingen")).click();

    }

    @Then("^I should see a list containing \"([^\"]*)\"$")
    public void i_should_see_a_list_containing(String text2bFound) throws Throwable {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .textToBePresentInElementLocated(By.tagName("body"), text2bFound));
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Did not find this text: "+text2bFound+"\n",bodyText.contains(text2bFound));
    }

    @Then("^I close the browser$")
    public void i_close_the_browser() throws Throwable {
       driver.quit();
    }

}