package restassured.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class PierwszyTest {
    private Helper helper = new Helper();
//    private WebDriver driver = helper.initializeWebDriver();
    private WebDriver driver;

//    @Test(priority = 1)
    public void createTable() {
        String boardName = "Nazwa tablicy";
        helper.logIn("norbert.bokwa@gmail.com", "Kiep1234");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#header > div.header-user > a.header-btn.js-open-add-menu > span")));

        //mniej aktywne tablice
        List<WebElement> button = driver.findElements(By.cssSelector("#content > div > div.js-boards-page > div > div > div.boards-page-board-section.mod-no-sidebar > a > span"));
        if (!button.isEmpty()) {
            button.get(0).click();
        }
        helper.createNewBoard(boardName);
        helper.waitForPageLoaded();
        int numberOfBoards = driver.findElements(By.className("board-tile-details-name")).size();
        List<WebElement> listOfBoards = driver.findElements(By.className("board-tile-details-name"));
        boolean isStringInList = false;
        for (int i = 0; i < numberOfBoards; i++) {
            if (listOfBoards.get(i).getAttribute("title").equals(boardName)) isStringInList = true;
        }
        Assert.assertTrue(isStringInList,"Utworzono nową tablice");
        helper.deleteBoards(boardName);
        helper.deleteBoards("Nowa tablica1");
    }

//    @Test(priority = 2)
    public void testDwa() {
        String boardName = "Nazwa tablicy";
        //mniej aktywne tablice
        List<WebElement> button = driver.findElements(By.cssSelector("#content > div > div.js-boards-page > div > div > div.boards-page-board-section.mod-no-sidebar > a > span"));
        if (!button.isEmpty()) {
            button.get(0).click();
        }
        helper.createNewBoard(boardName);
        helper.waitForPageLoaded();
        int numberOfBoards = driver.findElements(By.className("board-tile-details-name")).size();
        List<WebElement> listOfBoards = driver.findElements(By.className("board-tile-details-name"));
        boolean isStringInList = false;
        for (int i = 0; i < numberOfBoards; i++) {
            if (listOfBoards.get(i).getAttribute("title").equals(boardName)) isStringInList = true;
        }
        Assert.assertTrue(isStringInList,"Utworzono nową tablice");
        helper.deleteBoards(boardName);
    }

//    @AfterSuite
    public void tearDown() {
        driver.quit();
    }

////    @Test(priority = 3)
//    public void makeSureThatGoogleIsUp() {
//        System.out.println("witam");
//        RestAssured.given().when().get("http://www.google.com").then().statusCode(200);
//    }
//
////    @Test(priority = 3)
//    public void basicPingTest() {
//        System.out.println("no czesc");
//        RestAssured.given().when().get("/garage").then().statusCode(200);
//    }
}

