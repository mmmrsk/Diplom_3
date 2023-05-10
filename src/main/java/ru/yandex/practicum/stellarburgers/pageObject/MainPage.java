package ru.yandex.practicum.stellarburgers.pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.visible;

public class MainPage {

    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    // Локатор кнопки Войти
    @FindBy(how = How.XPATH,using = ("//button[text()='Войти в аккаунт']"))
    private SelenideElement loginButton;

    // Локатор кнопки Оформить заказ
    @FindBy(how = How.XPATH,using = ("//button[text()='Оформить заказ']"))
    private SelenideElement orderButton;

    // Локатор для кнопки Личный кабинет
    @FindBy(how = How.XPATH,using = ("//p[text()='Личный Кабинет']"))
    private SelenideElement personalAccountButton;

    // Локатор для надписи Соберите бургер
    @FindBy(how = How.XPATH,using = ("//h1[text()='Соберите бургер']"))
    private SelenideElement createBurgerText;

    // Локатор для указателя Булки в Конструкторе
    @FindBy(how = How.XPATH,using = ("//span[text()='Булки']//parent::div"))
    private SelenideElement bunsTab;

    // Локатор для указателя Соусы в Конструкторе
    @FindBy(how = How.XPATH,using = ("//span[text()='Соусы']//parent::div"))
    private SelenideElement saucesTab;

    // Локатор для указателя Начинки в Конструкторе
    @FindBy(how = How.XPATH,using = ("//span[text()='Начинки']//parent::div"))
    private SelenideElement fillingsTab;

    // Локаторы для скролла
    @FindBy(how = How.CLASS_NAME,using = "BurgerIngredients_ingredients__list__2A-mT")
    private SelenideElement ingredientsScrollDown;

    // Метод клика по кнопке Войти в аккаунт
    @Step("Click on login button")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return Selenide.page(LoginPage.class);
    }

    // Метод клика по кнопке Оформить заказ
    @Step("Clicking on Order button")
    public LoginPage clickOrderButton() {
        orderButton.click();
        return Selenide.page(LoginPage.class);
    }
    @Step("Order is loaded")
    public boolean isOrderButtonDisplayed() {
        return orderButton.shouldBe(visible).isDisplayed();
    }

    // Метод клика по кнопке Личный кабинет
    @Step("Click on profile button")
    public LoginPage clickProfileButton() {
        personalAccountButton.click();
        return Selenide.page(LoginPage.class);
    }
    // Метод клика по кнопке Личный кабинет авторизованным пользователем
    @Step("Click on profile button by authorised user")
    public ProfilePage clickProfileButtonByAuthorisedUser() {
        personalAccountButton.click();
        return Selenide.page(ProfilePage.class);
    }
    @Step("Text for burger is loaded")
    public boolean isCreateBurgerTextDisplayed() {
        return createBurgerText.shouldBe(visible).isDisplayed();
    }

    // Метод клика по указателю Булки
    @Step("Buns is loaded")
    public MainPage displayAvailableBuns() {
        bunsTab.click();
        Selenide.sleep(3000);
        return this;
    }

    // Метод клика по указателю Соусы
    @Step("Sauces is loaded")
    public MainPage displayAvailableSauces() {
        saucesTab.click();
        Selenide.sleep(3000);
        return this;
    }
    // Метод клика по указателю Начинки
    @Step("Fillings is loaded")
    public MainPage displayAvailableFillings() {
        fillingsTab.click();
        Selenide.sleep(3000);
        return this;
    }

    public String getBunsTabClassValue() {
        return bunsTab.getAttribute("class");
    }

    public String getSaucesTabClassValue() {
        return saucesTab.getAttribute("class");
    }

    public String getFillingsTabClassValue() {
        return fillingsTab.getAttribute("class");
    }
}
