import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "700x1200";
        Configuration.baseUrl = "https://demoqa.com";
    }
    @Test
    void fillFormTest() {
        open("/automation-practice-form");

        $("#firstName").setValue("Natalie");
        $("#lastName").setValue("Alexandrova");
        $("#userEmail").setValue("123@gmail.com");
        $("#gender-radio-2").doubleClick();
        $("#userNumber").setValue("9556664433");
//      Выбор днюхи:
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__day--016").click();

        $("#subjectsInput").click();
        $("#subjectsInput").setValue("H");
        $("#subjectsInput").pressEnter();

        $("[for=hobbies-checkbox-2]").click();
        $("#uploadPicture").uploadFile(new File("src/test/Files/test.png"));
        $("#currentAddress").setValue("SPB");
        $("#state").click();
        $("#stateCity-wrapper").$(new ByText("Uttar Pradesh")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(new ByText("Merrut")).click();
        $("#submit").click();

        // Проверки
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text("Natalie Alexandrova"),
                text("123@gmail.com"),
                text("Female"), text("9556664433"),
                text("16 September,1995"),
                text("Reading"),
                text("test.PNG"),
                text("SPB"),
                text("Uttar Pradesh Merrut")
        );
        $("#closeLargeModal").click();
    }
}

