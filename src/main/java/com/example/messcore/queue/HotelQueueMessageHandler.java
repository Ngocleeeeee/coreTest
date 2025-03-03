package com.example.messcore.queue;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
    public class HotelQueueMessageHandler {

    public void handleMessage(String message) {
        try {
            JSONObject jsonObject = new JSONObject(message);
            String hotelId = jsonObject.optString("hotelId", "unknown");
            String data = jsonObject.optString("data", "");

            System.out.println("Received message for hotel " + hotelId + ": " + data);
        } catch (Exception e) {
            System.err.println("Error parsing message: " + message);
            e.printStackTrace();
        }
    }

}
