package com.example.messcore.messsage.service;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FirebaseService {

    public void updateMessageStatus(String collectionName,String messageId, String status) {
        Firestore db = FirestoreClient.getFirestore();
        Map<String, Object> updates = new HashMap<>();
        updates.put(messageId, status);
        db.collection(collectionName).document(String.valueOf(messageId)).set(updates);
    }
}
