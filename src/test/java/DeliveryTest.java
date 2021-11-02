import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import utils.DataGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {

    @BeforeAll
    static void setUpAll() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTest() {

        $("[data-test-id='city'] input").setValue(DataGenerator.Registration.generateInfo("ru").getCity());
        $("[type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        String today = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[type='tel']").setValue(today);
        $("[data-test-id='name'] input").setValue(DataGenerator.Registration.generateInfo("ru").getName());
        $("[data-test-id='phone'] input").setValue(DataGenerator.Registration.generateInfo("ru").getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $("[type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        String nextDay = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[type='tel']").setValue(nextDay);
        $(withText("Запланировать")).click();
        $(withText("Перепланировать")).click();
        $(withText("Успешно!")).shouldBe(appear);
        $(withText("Встреча успешно запланирована на")).shouldBe(appear);
        $(withText(nextDay)).shouldBe(appear);
    };
}