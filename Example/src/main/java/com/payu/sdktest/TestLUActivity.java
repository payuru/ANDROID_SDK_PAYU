package com.payu.sdktest;

import android.app.Activity;
import android.os.Bundle;

import com.payu.payusdk.models.CountryCode;
import com.payu.payusdk.models.LanguageType;
import com.payu.payusdk.models.PayMethodType;
import com.payu.payusdk.models.PriceCurrency;
import com.payu.payusdk.models.lu.LUProduct;
import com.payu.payusdk.protocols.LURequestBuilder;
import com.payu.payusdk.rest.PAYURestConfig;
import com.payu.payusdk.utils.EncodeUtils;
import com.payu.payusdk.view.LUPurchaseView;

import java.util.ArrayList;
import java.util.List;

public class TestLUActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lu);
        LUPurchaseView LUPurchaseView = (LUPurchaseView) findViewById(R.id.web_view);

        List<LUProduct> productsList = new ArrayList<>();
        productsList.add(new LUProduct("name1", "code1", 1.0, 1, "19"));
        productsList.add(new LUProduct("name2", "code2", 2.0, 2, "19"));
        productsList.add(new LUProduct("name3", "code3", 3.0, 3, "19"));

        LURequestBuilder builder = new LURequestBuilder("e5|S|X~0@l10_?R4b8|1")
                .setMerchantId("ipolhtst")
                .setOrderExternalNumber("3886786")
                .setBillFirstName("Max")
                .setBillLastName("Mel")
                .setBillCountryCode(CountryCode.RU)
                .setLanguage(LanguageType.RU)
                .setDiscount(200.1)
                .setPaymentMethod(PayMethodType.CCVISAMC)
                .setOrderShipping(1200)
                .setPriceCurrency(PriceCurrency.RUB)
                .addProduct(productsList)
                .setOrderTimeout(20000)
                .setIsTestOrder(true)
                .setDebug(true);

        byte[] dostDataArray = EncodeUtils.getBytes(builder.build(), "UTF-8");
        LUPurchaseView.postUrl(PAYURestConfig.SUBMIT_ORDER_LU_URL, dostDataArray);
    }
}