package ru.yandex.practicum.stellarburgers;

import ru.yandex.practicum.stellarburgers.models.UserCreds;
import ru.yandex.practicum.stellarburgers.pageObject.MainPage;
import ru.yandex.practicum.stellarburgers.clients.UserClient;
import ru.yandex.practicum.stellarburgers.models.UserModel;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.stellarburgers.pageObject.RegistrationPage;
import ru.yandex.practicum.stellarburgers.pageObject.ForgotPasswordPage;

import static org.junit.Assert.assertTrue;


public class UserSignInTest extends CommonTest {

    private UserClient userClient;
    private UserModel user;
    private String bearerToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserModel.getRandom();
        UserCreds creds = UserCreds.from(user);
        userClient.registerNewUser(user);
        bearerToken = userClient.login(creds)
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("accessToken");
    }

    @After
    public void teardown() {

        userClient.delete(user.getEmail(), bearerToken);
    }

    @Test
    @DisplayName("User login from the main page")
    public void userLoginOnTheMainPage() {
        final boolean orderButtonDisplayed = Selenide.open(MainPage.URL, MainPage.class)
                .clickLoginButton()
                .userLogin(user)
                .isOrderButtonDisplayed();

        assertTrue(orderButtonDisplayed);
    }

    @Test
    @DisplayName("User login via profile link from the main page")
    public void userLoginByProfileLink() {
        final boolean orderButtonDisplayed = Selenide.open(MainPage.URL, MainPage.class)
                .clickProfileButton()
                .userLogin(user)
                .isOrderButtonDisplayed();

        assertTrue(orderButtonDisplayed);
    }


    @Test
    @DisplayName("User login from the register page")
    public void userLoginOnTheRegisterPage() {
        final boolean orderButtonDisplayed = Selenide.open(RegistrationPage.URL, RegistrationPage.class)
                .clickLoginLink()
                .userLogin(user)
                .isOrderButtonDisplayed();

        assertTrue(orderButtonDisplayed);
    }

    @Test
    @DisplayName("User login from the restore password page")
    public void userLoginOnTheRestorePasswordPage() {
        final boolean orderButtonDisplayed = Selenide.open(ForgotPasswordPage.URL, ForgotPasswordPage.class)
                .clickLoginLink()
                .userLogin(user)
                .isOrderButtonDisplayed();

        assertTrue(orderButtonDisplayed);
    }
}