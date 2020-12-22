package com.qa.tests;

import com.qa.base.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductDetailsPage;
import com.qa.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductTests extends BaseTest {

    @Test
    public void validateProductOnProductsPage() {
        SoftAssert sa = new SoftAssert();
        ProductsPage productsPage = new LoginPage().login(loginUsers.getJSONObject("validUser").getString("username")
                , loginUsers.getJSONObject("validUser").getString("password"));
        sa.assertEquals(productsPage.getSLBTitle(), strings.get("products_page_slb_title"));
        sa.assertEquals(productsPage.getSLBPrice(), strings.get("products_page_slb_price"));
        sa.assertAll();
    }

    @Test
    public void validateProductOnProductDetailsPage() {
        ProductsPage productsPage = new LoginPage().login(loginUsers.getJSONObject("validUser").getString("username")
                , loginUsers.getJSONObject("validUser").getString("password"));
        ProductDetailsPage productDetailsPage = productsPage.pressSLBTitle();
        Assert.assertEquals(productDetailsPage.getSLBTitle(), strings.get("product_details_page_slb_title"));
    }
}
