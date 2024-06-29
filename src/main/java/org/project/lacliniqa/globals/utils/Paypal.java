package org.project.lacliniqa.globals.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

import org.json.JSONObject;

public class Paypal {
    private static String getAccessToken() {
        Properties prop = new Properties();
        String fileName = "app.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return prop.getProperty("app.access_token");
    }

    public static boolean processPayment(String creditNumber, int yearExpire, int monthExpire, int ccv, int total_price) throws IOException {
        URL url = new URL("https://api-m.sandbox.paypal.com/v2/checkout/orders");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("POST");

        httpConn.setRequestProperty("Content-Type", "application/json");
        httpConn.setRequestProperty("PayPal-Request-Id", UUID.randomUUID().toString());
        httpConn.setRequestProperty("Authorization", "Bearer " + getAccessToken());

        System.out.println(getAccessToken());

        JSONObject object = new JSONObject();
        object.put("intent", "CAPTURE");

        JSONObject purchase_units = new JSONObject();
        purchase_units.put("reference_id", UUID.randomUUID().toString());

        JSONObject amount = new JSONObject();
        amount.put("currency_code", "USD");
        amount.put("value", Integer.toString(total_price));

        purchase_units.put("amount", amount);
        object.put("purchase_units", Arrays.asList(purchase_units));

        httpConn.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
        writer.write(object.toString());
        writer.flush();
        writer.close();
        httpConn.getOutputStream().close();

        return (httpConn.getResponseCode() / 100 == 2);
    }
}
