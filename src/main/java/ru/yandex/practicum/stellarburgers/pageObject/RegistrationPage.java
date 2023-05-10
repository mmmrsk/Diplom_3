package ru.yandex.practicum.stellarburgers.pageObject;

import io.qameta.allure.Step;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.practicum.stellarburgers.models.UserModel;

import static com.codeborne.selenide.Condition.visible;

public class RegistrationPage {

    final public static String URL = MainPage.URL + "register";

    // локатор поля для ввода имени
    @FindBy(how = How.XPATH,using =("//label[text()='Имя']//following-sibling::input"))
    private SelenideElement nameField;

    // локатор поля для ввода email
    @FindBy(how = How.XPATH,using =("//label[text()='Email']//following-sibling::input"))
    private SelenideElement emailField;

    // локатор поля для ввода пароля
    @FindBy(how = How.XPATH,using =("//input[@type='password']"))
    private SelenideElement passwordField;

    // локатор кнопки зарегистрироваться
    @FindBy(how = How.XPATH,using = ("//button[text()='Зарегистрироваться']"))
    private SelenideElement registrationButton;

    // локатор текстового элемента Некорректный пароль
    @FindBy(how = How.XPATH,using = ("//p[text()='Некорректный пароль']"))
    private SelenideElement incorrectPasswordWarning;

    // локатор кнопки Войти
    @FindBy(how = How.XPATH,using = ("//a[text()='Войти']"))
    private SelenideElement loginButton;

    // метод заполнения поля ввода имени
    @Step("Input Name")
    public RegistrationPage inputName(String name) {
        nameField.sendKeys(name);
        return this;
    }

    // метод заполнения поля ввода email
    @Step("Input email")
    public RegistrationPage inputEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    // метод заполнения поля ввода пароля
    @Step("Input password")
    public RegistrationPage inputPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    // Метод клика по кнопке <Зарегистрироваться>
    @Step("Click on registration button")
    public LoginPage clickRegistrationButton() {
        registrationButton.click();
        return Selenide.page(LoginPage.class);
    }

    // метод регистрации нового пользователя

    @Step("Register")
    public LoginPage registerNewUser(UserModel user) {
        inputName(user.getName());
        inputEmail(user.getEmail());
        inputPassword(user.getPassword());
        clickRegistrationButton();
        return Selenide.page(LoginPage.class);
    }

    // метод регистрации нового пользователя с неверным паролем
    @Step("Register with incorrect data")
    public RegistrationPage registerNewUserWithIncorrectPass(UserModel user) {
        inputName(user.getName());
        inputEmail(user.getEmail());
        inputPassword(user.getPassword());
        clickRegistrationButton();
        return this;
    }

    // метод проверки видимости элемента Некорректный пароль
    public boolean isIncorrectPassDisplayed() {
        return incorrectPasswordWarning.shouldBe(visible).isDisplayed();
    }

    // Метод клика по ссылке Войти
    @Step("Click on login")
    public LoginPage clickLoginLink() {
        loginButton.click();
        return Selenide.page(LoginPage.class);
    }
}
