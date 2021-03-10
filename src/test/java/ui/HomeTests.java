package ui;

import api.ServiceImp;
import api.dataentities.Employees;
import api.interfaces.ICrud;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.pageObjects.*;
import ui.utils.CommonMethods;
import utils.ConfigFileReader;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class HomeTests extends BaseTest {
    HomePage homePage;
    SearchResults searchResults;
    ProductPage productPage;
    PreCartPage preCartPage;
    CartPage cartPage;
    SignInPage signInPage;
    CreateAccountPage createAccountPage;
    HelpPage helpPage;

    @DataProvider(name = "items")
    public Object[][] testData() {
        return new Object[][]{
                {"Samsung Galaxy Note 20"},
                {"Samsung Galaxy S20 FE 5G"}
        };
    }

    @Override
    public void initializeElements() {
        homePage = PageFactory.initElements(driver, HomePage.class);
        searchResults = PageFactory.initElements(driver, SearchResults.class);
        productPage = PageFactory.initElements(driver, ProductPage.class);
        preCartPage = PageFactory.initElements(driver, PreCartPage.class);
        cartPage = PageFactory.initElements(driver, CartPage.class);
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        createAccountPage = PageFactory.initElements(driver, CreateAccountPage.class);
        helpPage = PageFactory.initElements(driver, HelpPage.class);
    }

    @Test(dataProvider = "items")
    public void itShouldAddItemToTheCartAndThenDeleteIt(String product) {
        homePage.waitForAmazonLogoToBeDisplayed();
        homePage.sendKeysToSearchBox(product);
        homePage.clickSearchIcon();
        searchResults.waitForProductToBeDisplayed(product);
        double price = searchResults.getPriceOfProduct(product);
        searchResults.clickOnProduct(product);
        productPage.waitForProductTitleToBeVisible();
        Assert.assertEquals(price, productPage.getProductPrice(), "Price did not match");
        productPage.clickAddToCartButton();
        productPage.clickNoThanksButtonToDeclineCoverage();
        preCartPage.waitForAddedToCartHeaderToBeDisplayed();
        preCartPage.clickCartButton();
        Assert.assertTrue(cartPage.waitForShoppingCartHeaderToBeDisplayed());
        Assert.assertEquals(price, cartPage.getProductPrice(), "Price did not match");
        cartPage.clickProceedToCheckout();
        signInPage.waitForSignInHeaderToBeDisplayed();
        driver.navigate().back();
        Assert.assertTrue(cartPage.waitForShoppingCartHeaderToBeDisplayed());
        cartPage.clickDeleteLink();
        Assert.assertTrue(cartPage.waitForShoppingCartHeaderToBeDisplayed());
    }

    @Test
    public void loginAndSearch() {
        homePage.waitForAmazonLogoToBeDisplayed();
        homePage.hoverOverAccountsAndListsIcon();
        homePage.clickNewCustomerStartHereLink();
        createAccountPage.waitForCreateAccountHeaderToBiVisible();
        String employeeName = getEmployeeName();
        String employeeEmail = employeeName.replace(" ", ".") + "@fake.com";
        createAccountPage.sendKeysToYourNameInputField(employeeName);
        createAccountPage.sendKeysToYourEmailInputField(employeeEmail);
        createAccountPage.clickOnConditionsOfUseLink();
        helpPage.waitForConditionsOfUseHeaderToBeVisible();
        helpPage.clickAllHelpTopicsLink();
        helpPage.waitForWhatCanWeHelpHeaderToBeVisible();
        helpPage.sendKeysToSearchBar("Echo");
        helpPage.clickOnTopicLink("Echo Support");
        List<String> elements  = new ArrayList<>();
        elements.add("Getting Started");
        elements.add("Wi-Fi and Bluetooth");
        elements.add("Device Software and Hardware");
        elements.add("Troubleshooting");
        CommonMethods.validateIfListOfElementsAreDisplayed(elements, driver);
    }

    public Employees getEmployees() {
        String employeesEndPoint = new ConfigFileReader().getEmployeesEndPoint();

        //Build the request
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri(employeesEndPoint)
                .addHeader("Content-Type", "application/json")
                .build();

        ICrud api = new ServiceImp();

        // Deserialize the Response body into Employees.class
        return api.getRequest(requestSpec).getBody().as(Employees.class);
    }

    public String getEmployeeName() {
        return getEmployees().getData().get(0).getEmployee_name();
    }
}
