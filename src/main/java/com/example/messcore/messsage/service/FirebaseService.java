package com.example.messcore.messsage.service;

import com.example.messcore.hotel.dto.HotelDto;
import com.example.messcore.messsage.dto.ContentDto;
import com.example.messcore.messsage.dto.SenderDto;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.SetOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FirebaseService {

    public void updateMessageStatus(String collectionName, String status,HotelDto hotel, SenderDto sender, ContentDto content) {
        Firestore db = FirestoreClient.getFirestore();
        Map<String, Object> updates = new HashMap<>();
        updates.put("status",status);
        updates.put("hotel", hotel);
        updates.put("sender", sender);
        updates.put("content", content);

        db.collection(collectionName)
                .document(content.getId().toString())
                .set(updates, SetOptions.merge());
    }
}
