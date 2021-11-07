import com.github.javafaker.Faker;
import lombok.var;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import utils.DataGenerator;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTest() {
        var user = DataGenerator.Registration.generateInfo("ru");
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[type='tel']").setValue(user.getToday());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $("[data-test-id=success-notification]").shouldHave(text("Встреча успешно запланирована на " + user.getToday()));
        $("[type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[type='tel']").setValue(user.getNextDay());
        $(withText("Запланировать")).click();
        $(withText("Перепланировать")).click();
        $(withText("Успешно!")).shouldBe(appear);
        $("[data-test-id=success-notification]").shouldHave(text("Встреча успешно запланирована на " + user.getNextDay()));
    };
}