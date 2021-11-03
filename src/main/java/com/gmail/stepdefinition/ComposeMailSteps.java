package com.gmail.stepdefinition;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ComposeMailSteps {

    WebDriver driver;

    @Given("I open gmail page in browser")
    public void openGmailPage(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ramak\\Downloads\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://mail.google.com");

        driver.manage().window().maximize();

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

    @When("^I click on Compose button$")
    public void iClickOnComposeButton() {
        WebElement composeButton = driver.findElement(By.xpath("//div[@class = 'z0']"));
        WebDriverWait waitForCompose = new WebDriverWait(driver,10);
        waitForCompose.until(ExpectedConditions.visibilityOf(composeButton));
        composeButton.click();
    }

    @Then("^New message window is opened$")
    public void newMessageWindowIsOpened() {
        WebElement dialogWindow = driver.findElement(By.xpath("//div[@class='aYF']/span"));
        waitForElementVisibility(dialogWindow);
        Assert.assertEquals("New Message title does not match","New Message",dialogWindow.getText());
    }


    @When("^I enter the recipient email address \"([^\"]*)\"$")
    public void iEnterTheRecipientEmailAddress(String receiptEmailAddr) throws Throwable {
        WebElement toEmail =  driver.findElement(By.xpath("//textarea[@name='to']"));
        waitForElementVisibility(toEmail);
        toEmail.click();
//        toEmail.sendKeys(Keys.BACK_SPACE);
        toEmail.sendKeys(receiptEmailAddr);
    }

    private void waitForElementVisibility(WebElement element){
        WebDriverWait waitForElemVisibility = new WebDriverWait(driver, 10);
        waitForElemVisibility.until(ExpectedConditions.visibilityOf(element));
    }

    private boolean isElementDisplayed(WebElement element){
        WebDriverWait waitForElemVisibility = new WebDriverWait(driver, 10);
        waitForElemVisibility.until(ExpectedConditions.textToBePresentInElement(element,"Message sent"));
        return true;
    }

    @And("^i enter the subject \"([^\"]*)\"$")
    public void iEnterTheSubject(String subTxt) throws Throwable {
        WebElement subject = driver.findElement(By.xpath(("//input[@name='subjectbox']")));
        waitForElementVisibility(subject);
        subject.sendKeys(subTxt);
        Thread.sleep(3000);
    }

    @And("^I enter the body \"([^\"]*)\"$")
    public void iEnterTheBody(String bodyText) throws Throwable {
        WebElement body = driver.findElement(By.xpath(("//table//td//div[@class='Am Al editable LW-avf tS-tW']")));
        waitForElementVisibility(body);
        body.sendKeys(bodyText);
        Thread.sleep(3000);
    }

    @When("^I click on Send button$")
    public void iClickOnSendButton() throws InterruptedException {
        WebElement sendBtn = driver.findElement(By.xpath(("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")));
        waitForElementVisibility(sendBtn);
        sendBtn.click();
        Thread.sleep(1000);
    }

    @Then("^mail should be sent successfully$")
    public void mailShouldBeSentSuccessfully() throws InterruptedException {
        WebElement message = driver. findElement(By.xpath(("//span[@class='aT']/span[text()='Message sent.']")));
        waitForElementVisibility(message);
        Assert.assertEquals("Confirmation message does not match",true, isElementDisplayed(message));
        Thread.sleep(3000);
    }

}
