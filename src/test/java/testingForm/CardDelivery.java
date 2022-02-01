package testingForm;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDelivery {


    private int daysDiff = 4;

    private String GetOrderDate(int daysDiff) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return dateFormat.format(LocalDateTime.now().plusDays(daysDiff));
    }

    @Test
    public void shouldCreditCardDeliveryOrder() {

        open("http://localhost:9999");

        $("[data-test-id=\"city\"] input").setValue("Элиста");
        $("[data-test-id=\"date\"] input").doubleClick();
        $("[data-test-id=\"date\"] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] input").setValue(GetOrderDate(daysDiff));
        $("[data-test-id=\"name\"] input").setValue("Горяева Амуланг Юрьевна");
        $("[data-test-id=\"phone\"] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $(withText("Забронировать")).click();

        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Успешно!"));
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно забронирована на " + GetOrderDate(daysDiff)));

    }
}
