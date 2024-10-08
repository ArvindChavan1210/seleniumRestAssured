package eCommerce;

import commons.baseClass;
import eCommercePageFactory.cartPage;
import eCommercePageFactory.orderConfirmationPage;
import eCommercePageFactory.productsPage;
import eCommercePageFactory.purchasePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.utilities.YamlReader;

public class eCommercePurchseSingleProduct extends baseClass {

    @Test
    public void getSingleProduct() {
        eCommerceLandingPage.goTO();
        productsPage productsPage = eCommerceLandingPage.
                validateErrorMessage(YamlReader.readYamlData("username"),
                        YamlReader.readYamlData("password"));
        productsPage.get_Single_Product(YamlReader.readYamlData("product"));
        productsPage.getProductDetails();
        cartPage cartPage = productsPage.clickOnCartPage();
        purchasePage purchasePage=cartPage.getCartData();
        Assert.assertEquals(cartPage.item_in_Cart,
                productsPage.product_Selected, "Different Product Available in cart");
        Assert.assertEquals(cartPage.price_of_item_in_Cart,
                productsPage.price_of_product_selected, "Price Mismatch please check the product");
        purchasePage.paymentDetails();
        purchasePage.shipmentDetails();
        orderConfirmationPage orderConfirmationPage=purchasePage.placeOrder();
        orderConfirmationPage.orderConfirmation();
        orderConfirmationPage.orderList();
        orderConfirmationPage.orderReceipt();
        Assert.assertEquals(orderConfirmationPage.order_id_On_Summary_pag,orderConfirmationPage.order_id_on_final_page,"Order ID Mismatch Please Check");
    }

}
