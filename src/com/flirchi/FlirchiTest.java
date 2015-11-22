package com.flirchi;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Random;

public class FlirchiTest {

    @Test
    public void successTest_Female() {
        String email = getRandomEmail();
        boolean isRegistrationSuccess = testRegistration("Jessika", email, Gender.FEMALE, 18);
        Assert.assertTrue(isRegistrationSuccess);
    }

    @Test
    public void successTest_Male() {
        String email = getRandomEmail();
        boolean isRegistrationSuccess = testRegistration("John", email, Gender.MALE, 70);
        Assert.assertTrue(isRegistrationSuccess);
    }

    @Test
    public void negativeTest_alreadyUsedEmail() {
        boolean isRegistrationSuccess = testRegistration("John", "alreadyUsedEmail@mail.ru", Gender.FEMALE, 22);
        Assert.assertFalse(isRegistrationSuccess);
    }

    @Test
    public void negativeTest_emptyName() {
        String randomEmail = getRandomEmail();
        boolean isRegistrationSuccess = testRegistration("", randomEmail, Gender.FEMALE, 22);
        Assert.assertFalse(isRegistrationSuccess);
    }

    private boolean testRegistration(String name, String email, Gender gender, int age) {
        WebDriver driver = new FirefoxDriver();

        FlirchiRegistration steps = new FlirchiRegistration(driver);
        steps.selectLookingGender(gender);
        steps.selectHairColor();
        steps.selectEyeColor();
        steps.selectBody();
        steps.waitRegistrationForm();
        steps.registration(name, email, gender, age);

        try {
            steps.waitForNewProfilePageLoaded();
            driver.close();
            return true;
        } catch (Exception e) {
            //do nothing
        }

        driver.close();
        return false;
    }

    private String getRandomEmail() {
        Random random = new Random();
        int randomNumber = random.nextInt();
        return "myEmail" + randomNumber + "@mail.ru";
    }
}



