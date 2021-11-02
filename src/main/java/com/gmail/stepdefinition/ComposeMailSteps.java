package com.gmail.stepdefinition;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ComposeMailSteps {
    WebDriver driver;

    @Given("I open gmail page in browser")
    public void openGmailPage(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ramak\\Downloads\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://mail.google.com");

        driver.manage().window().maximize();
//        driver.manage().deleteAllCookies();

        //Implicit Wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @When("^I have provide mail id \"([^\"]*)\"$")
    public void iHaveProvideMailId(String emailAddr) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //input[@type='email']
        WebElement emailTxtField = driver.findElement(By.xpath("//input[@type='email']"));
        emailTxtField.clear();
        emailTxtField.sendKeys(emailAddr);
        WebElement nextBtn = driver.findElement(By.xpath("//span[@class='VfPpkd-vQzf8d' and text()='Next']"));
        nextBtn.click();
        Thread.sleep(5000);
    }

    @And("^I provide the password \"([^\"]*)\"$")
    public void iProvideThePassword(String pwd) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        WebElement pwdTxtField = driver.findElement(By.xpath("//input[@type='password']"));
        pwdTxtField.clear();
        pwdTxtField.sendKeys(pwd);
        WebElement nextBtn = driver.findElement(By.xpath("//span[@class='VfPpkd-vQzf8d' and text()='Next']"));
        nextBtn.click();
        Thread.sleep(10000);
    }

    @When("^I click on the Next button$")
    public void iClickOnTheNextButton() {

    }

    @Then("^user is able to see inbox$")
    public void userIsAbleToSeeInbox() {
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
