package com.dietplanner.Controller;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MealPlanDataManager {

    private final Firestore db;

    public MealPlanDataManager() {
        this.db = FirestoreClient.getFirestore(); // Initialize Firestore client
    }

    public void saveMealPlanEntries(List<MealPlanEntry> entries) {
        // Implement the logic to save entries to Firestore
        for (MealPlanEntry entry : entries) {
            db.collection("mealPlanEntries").add(entry);
        }
        System.out.println("Entries saved to Firestore.");
    }

    public List<MealPlanEntry> loadMealPlanEntries() {
        List<MealPlanEntry> mealPlanEntries = new ArrayList<>();

        // Load entries from Firestore
        ApiFuture<QuerySnapshot> future = db.collection("mealPlanEntries").get();
        try {
            QuerySnapshot querySnapshot = future.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                MealPlanEntry entry = document.toObject(MealPlanEntry.class);
                mealPlanEntries.add(entry);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Entries loaded from Firestore.");
        return mealPlanEntries;
    }

    public void updateEntryDate(MealPlanEntry entry) {
        // Implement update logic here if needed
    }
}
