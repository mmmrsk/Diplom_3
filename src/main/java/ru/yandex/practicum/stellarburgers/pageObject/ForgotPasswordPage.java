package ru.yandex.practicum.stellarburgers.pageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPage {

    // Метод перехода на страницу восстановления пароля
    final public static String URL = MainPage.URL + "forgot-password";

    // локатор кнопки Войти
    @FindBy(how = How.XPATH,using = ("//a[text()='Войти']"))
    private SelenideElement loginLink;

    // Метод клика по кнопке Войти
    @Step("Click Authorization Link on restore password page")
    public LoginPage clickLoginLink() {
        loginLink.click();
        return Selenide.page(LoginPage.class);
    }
}
