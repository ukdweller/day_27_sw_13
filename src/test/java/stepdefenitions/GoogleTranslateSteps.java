package stepdefenitions;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.GoogleTranslatePage;
import utilities.Config;
import utilities.Driver;

public class GoogleTranslateSteps {
	
	private WebDriver driver;
	private GoogleTranslatePage translatePage=new GoogleTranslatePage();

	@Given("^on Google Translate page$")// ^-beginning of the sentence. $-end of sentence
	public void on_Google_Translate_page() throws Throwable {
	   System.out.println("Navigating to Google Translate Page");
	   driver=Driver.getDriver();
	   driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	   //driver.get("http://translate.google.com");
	   driver.get(Config.getProperty("url"));
	   Assert.assertTrue(driver.getTitle().equals("Google Translate"));
	}

	@When("^I choose source language$")
	public void i_choose_source_language() throws Throwable {
	   System.out.println("Selecting English as a source Language");
	   translatePage.english.click();   
	}

	@And("^I choose target language$")
	public void i_choose_target_language() throws Throwable {
	   System.out.println("Selenium Zulu Language from list");
	   translatePage.translationLanIcon.click();
	   String lan=Config.getProperty("toLanguage");
	   driver.findElement(By.xpath("//div[.='"+lan+"']"));
	}

	@And("^I type a word into source field$")
	public void i_type_a_word_into_source_field() throws Throwable {
	   System.out.println("Typing a word into a source field");
	   translatePage.sourceTextField.sendKeys("Hello");
	}

	@And("^I click on translate$")
	public void i_click_on_translate() throws Throwable {
	    System.out.println("Perform click on translate button");
	    translatePage.translate.click();
	}

	@Then("^Translationis displayed$")
	public void translationis_displayed() throws Throwable {
	   System.out.println("Verifying that translation is displayed");
	   String translation=translatePage.translationTextField.getText();

	   Assert.assertFalse(translation.isEmpty());
	   
	}
}
