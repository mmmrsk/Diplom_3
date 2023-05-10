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
import static org.junit.Assert.assertTrue;


public class NewUserRegistrationTest extends CommonTest {

    private UserClient userClient;
    private UserCreds creds;
    private UserModel userModel;
    private boolean afterToBeLaunched;

    @Before
    public void setUp() {
        afterToBeLaunched = true;
        userClient = new UserClient();
        userModel = UserModel.getRandom();
        creds = UserCreds.from(userModel);
    }

    @After
    public void teardown() {
        if (!afterToBeLaunched) {
            return;
        }
        String bearerToken = userClient.login(creds)
                .then().log().all()
                .extract()
                .path("accessToken");
        userClient.delete(userModel.getEmail(), bearerToken);
    }

    // я очень извиняюсь, но здесь у меня тест проходит и я не знаю как мне понять что не так(
    @Test
    @DisplayName("User registration with incorrect pass")
    public void registrationUserWithIncorrectPass() {
        userModel.setPassword("five");
        final boolean incorrectPasswordWarningDisplayed = Selenide.open(MainPage.URL, MainPage.class)
                .clickLoginButton()
                .clickRegisterButton()
                .registerNewUserWithIncorrectPass(userModel)
                .isIncorrectPassDisplayed();
        assertTrue(incorrectPasswordWarningDisplayed);
        afterToBeLaunched = false;
    }
    @Test
    @DisplayName("Successful user registration")
    public void successfulUserRegistrationTest() {
        // Создание пользователя для регистрации
        UserModel userModel = UserModel.getRandom();

        final boolean userRegistration = Selenide.open(MainPage.URL, MainPage.class)
                .clickLoginButton()
                .clickRegisterButton()
                .registerNewUser(userModel)
                .isUserLoginTextDisplayed();

        // Проверка успешной регистрации
        assertTrue(userRegistration);
        afterToBeLaunched = false;
    }
}