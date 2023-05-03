package ru.yandex.practicum.stellarburgers.pageObject;

import io.qameta.allure.Step;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.practicum.stellarburgers.models.UserModel;

import static com.codeborne.selenide.Condition.visible;

public class LoginPage {

    final public static String URL = MainPage.URL + "login";

    // Локатор кнопки Восстановить пароль
    @FindBy(how = How.XPATH,using = ("//a[text()='Восстановить пароль']"))
    private SelenideElement forgotPassword;

    // Локатор кнопки Зарегистрироваться
    @FindBy(how = How.XPATH,using = ("//a[text()='Зарегистрироваться']"))
    private SelenideElement registerButton;

    // Локатор кнопки Войти
    @FindBy(how = How.XPATH,using = ("//button[text()='Войти']"))
    private SelenideElement loginButton;

    // Локатор поля Email
    @FindBy(how = How.XPATH,using =("//label[@class='input__placeholder text noselect text_type_main-default'][text()" +
            "='Email']/parent::div/input"))
    private SelenideElement emailField;

    // Локатор поля Пароль
    @FindBy(how = How.XPATH,using =("//label[@class='input__placeholder text noselect text_type_main-default'][text()" +
            "='Пароль']/parent::div/input"))
    private SelenideElement passwordField;

    // Локатор надписи Вход
    @FindBy(how = How.XPATH,using = ("//h2[text()='Вход']"))
    private SelenideElement userLoginText;

    // Метод клика по кнопке Зарегистрироваться
    @Step("Click on register button")
    public RegistrationPage clickRegisterButton() {
        registerButton.shouldBe(visible).click();
        return Selenide.page(RegistrationPage.class);
    }

    // Метод клика по кнопке Восстановить пароль
    @Step("Click on forgot password")
    public ForgotPasswordPage clickForgotPasswordButton() {
        forgotPassword.click();
        return Selenide.page(ForgotPasswordPage.class);
    }
    // Метод заполнения поля Email
    @Step("Input email")
    public LoginPage inputEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }
    // Метод заполнения поля Пароль
    @Step("Input password")
    public LoginPage inputPassword(String password) {
        passwordField.sendKeys(password);
        return this;    }

    // Метод для авторизации пользователя
    @Step("Login")
    public MainPage userLogin(UserModel user) {
        inputEmail(user.getEmail());
        inputPassword(user.getPassword());
        loginButton.click();
        return Selenide.page(MainPage.class);
    }
    // етод для отображения надписи входа
    @Step("User login text is loaded")
    public boolean isUserLoginTextDisplayed() {
        return userLoginText.shouldBe(visible).isDisplayed();
    }
}
