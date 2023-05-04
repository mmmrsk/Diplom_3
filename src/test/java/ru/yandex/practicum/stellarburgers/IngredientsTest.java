package ru.yandex.practicum.stellarburgers;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.practicum.stellarburgers.pageObject.MainPage;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientsTest extends CommonTest {
    WebDriver driver;
    private final String expected = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";

    @Test
    @DisplayName("Checking the buns tab")
    public void checkingBunsTab() {
        final String actual = Selenide.open(MainPage.URL, MainPage.class)
                .displayAvailableFillings()
                .displayAvailableSauces()
                .displayAvailableBuns()
                .getBunsTabClassValue();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Checking the sauces tab")
    public void checkingSaucesTab() {
        final String actual = Selenide.open(MainPage.URL, MainPage.class)
                .displayAvailableFillings()
                .displayAvailableBuns()
                .displayAvailableSauces()
                .getSaucesTabClassValue();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Checking the fillings tab")
    public void checkingFillingsTab() {
        final String actual = Selenide.open(MainPage.URL, MainPage.class)
                .displayAvailableSauces()
                .displayAvailableBuns()
                .displayAvailableFillings()
                .getFillingsTabClassValue();
        assertEquals(expected, actual);
    }
}