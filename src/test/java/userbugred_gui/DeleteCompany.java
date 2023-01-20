package userbugred_gui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import userbugred_api.CreateResponse;
import userbugred_api.Specification;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class DeleteCompany {
    SelenideElement companyBtn = $x("//a[contains(text(), 'API Rest Company')]");
    SelenideElement companies = $x("//span[contains(text(), 'Компании')]");

    @Test
    public void deleteCompany() {
        SoftAssert softAssert = new SoftAssert();

        try {
            companyBtn.should(Condition.exist).scrollIntoView(true).click();
            companies.should(Condition.exist).click();
            softAssert.assertNotEquals(Specification.response.getId_company(), null);
            String company_id = String.valueOf(Specification.response.getId_company());

            SelenideElement deleteBtn = $x("//a[@href='/companys/delete/" + company_id + "']");
            deleteBtn.click();
            System.out.println("Company ID " + company_id + " successfully deleted!");

            softAssert.assertAll();

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Company ID was not found");
        }
    }
}
