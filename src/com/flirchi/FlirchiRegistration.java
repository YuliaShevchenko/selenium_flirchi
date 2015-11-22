package com.flirchi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlirchiRegistration {

    private static final By MALE = By.xpath(".//*[@class='inter-slide f m']/div[2]/div[2]");
    private static final By FEMALE = By.xpath(".//*[@class='inter-slide f m']/div[2]/div[1]");

    private static final By RIGHT_BTN_HAIR = By.xpath(".//*[@id='slide-2' and contains(@class,'visible')]/div[2]/div[2]");
    private static final By RIGHT_BTN_EYES = By.xpath(".//*[@id='slide-3' and contains(@class,'visible')]/div[2]/div[2]");
    private static final By RIGHT_BTN_BODY = By.xpath(".//*[@id='slide-4' and contains(@class,'visible')]/div[2]/div[2]");

    private static final By NAME_FIELD = By.xpath(".//*[@id='slide-6' and contains(@class,'visible')]//*[@id='form-signup-name']");
    private static final By EMAIL_FIELD = By.xpath(".//*[@id='slide-6' and contains(@class,'visible')]//*[@id='form-signup-email']");
    private static final By GENDER_FIELD = By.xpath(".//*[@id='slide-6' and contains(@class,'visible')]//*[@id='field-gender-select']");
    private static final By AGE_FIELD = By.xpath(".//*[@id='slide-6' and contains(@class,'visible')]//*[@id='field-age-select']");

    private static final By SUBMIT_BTN = By.xpath(".//*[@id='slide-6' and contains(@class,'visible')]//button[@type='submit']");

    private WebDriver driver;

    public FlirchiRegistration(WebDriver driver) {
        this.driver = driver;
        driver.get("https://flirchi.com/sign/inter?fr=1&p=1232");
    }

    public void selectLookingGender(Gender userGender) {
        WebElement button;
        if (userGender == Gender.MALE) {
            button = driver.findElement(FEMALE);
        } else {
            button = driver.findElement(MALE);
        }
        button.click();
    }

    public void selectHairColor() {
        WebElement button = driver.findElement(RIGHT_BTN_HAIR);
        button.click();
    }

    public void selectEyeColor() {
        WebElement button = driver.findElement(RIGHT_BTN_EYES);
        button.click();
    }

    public void selectBody() {
        WebElement button = driver.findElement(RIGHT_BTN_BODY);
        button.click();
    }

    public void registration(String name, String email, Gender gender, int age) {
        WebElement nameField = driver.findElement(NAME_FIELD);
        nameField.sendKeys(name);

        WebElement emailField = driver.findElement(EMAIL_FIELD);
        emailField.sendKeys(email);

        WebElement genderField = driver.findElement(GENDER_FIELD);
        Select genderDropDown = new Select(genderField);
        genderDropDown.selectByValue(gender == Gender.MALE ? "m" : "f");

        WebElement ageField = driver.findElement(AGE_FIELD);
        Select ageDropDown = new Select(ageField);
        ageDropDown.selectByValue(String.valueOf(age));

        WebElement submitButton = driver.findElement(SUBMIT_BTN);
        submitButton.click();
    }

    public void waitRegistrationForm() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_BTN));
    }

    public void waitForNewProfilePageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlContains("https://flirchi.com/u"));
    }

}
