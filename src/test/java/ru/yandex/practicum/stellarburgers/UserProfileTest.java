package ru.yandex.practicum.stellarburgers;

import ru.yandex.practicum.stellarburgers.models.UserCreds;
import ru.yandex.practicum.stellarburgers.pageObject.LoginPage;
import ru.yandex.practicum.stellarburgers.clients.UserClient;
import ru.yandex.practicum.stellarburgers.models.UserModel;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class UserProfileTest extends CommonTest {

    private UserClient userClient;
    private UserModel userModel;
    private String bearerToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        userModel = UserModel.getRandom();
        UserCreds creds = UserCreds.from(userModel);
        userClient.registerNewUser(userModel);
        bearerToken = userClient.login(creds)
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("accessToken");
    }

    @After
    public void teardown() {
        userClient.delete(userModel.getEmail(), bearerToken);
    }


    @Test
    @DisplayName("Open user profile from main page")
    public void userProfileDisplayed() {
        final boolean profileTextDisplayed = Selenide.open(LoginPage.URL, LoginPage.class)
                .userLogin(userModel)
                .clickProfileButtonByAuthorisedUser()
                .isProfileTextDisplayed();
        assertTrue(profileTextDisplayed);
    }

    @Test
    @DisplayName("Open constructor by click on constructor link")
    public void openConstructorByClickOnConstructorLink() {
        final boolean createBurgerTextDisplayed = Selenide.open(LoginPage.URL, LoginPage.class)
                .userLogin(userModel)
                .clickProfileButtonByAuthorisedUser()
                .clickCreateBurger()
                .isCreateBurgerTextDisplayed();
        assertTrue(createBurgerTextDisplayed);
    }

    @Test
    @DisplayName("Open constructor by click on logo")
    public void openConstructorByClickOnLogo() {
        final boolean createBurgerTextDisplayed = Selenide.open(LoginPage.URL, LoginPage.class)
                .userLogin(userModel)
                .clickProfileButtonByAuthorisedUser()
                .clickCreateBurger()
                .isCreateBurgerTextDisplayed();
        assertTrue(createBurgerTextDisplayed);
    }


    @Test
    @DisplayName("Logout")
    public void successfullyLogoutUser() {
        final boolean userLoginTextDisplayed = Selenide.open(LoginPage.URL, LoginPage.class)
                .userLogin(userModel)
                .clickProfileButtonByAuthorisedUser()
                .clickLogoutButton()
                .isUserLoginTextDisplayed();
        assertTrue(userLoginTextDisplayed);
    }
}