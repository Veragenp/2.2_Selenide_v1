package ru.netology.orderingacard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.datedelivery.DateDelivery;

import static com.codeborne.selenide.Condition.visible;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryOrderTest {
    DateDelivery dataText = new DateDelivery();



    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }


    @Test
    void shouldTestAllFields() {
        String dataDelivery = dataText.ReturnDate(5);
        $("[data-test-id=city] [placeholder='Город']").setValue("Томск");
        $("[data-test-id=date] [placeholder='Дата встречи']").
                sendKeys( Keys.CONTROL +"A",Keys.DELETE);
        $("[data-test-id=date] [placeholder='Дата встречи']").
                setValue(String.valueOf(dataDelivery));
        $("[data-test-id=name] [name='name']").setValue("Иван Юрьевич");
        $("[data-test-id=phone] [name='phone']").setValue("+79999999999");
        $("[class=checkbox__box]").click();
        $(withText("Забронировать")).click();
        $(withText("Встреча успешно забронирована на")).
                shouldBe(visible, Duration.ofSeconds(15));
        $(withText(dataDelivery)).
                shouldBe(visible, Duration.ofSeconds(15));



    }
}
