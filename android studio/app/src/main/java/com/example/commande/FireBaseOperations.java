package com.example.commande;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import android.view.LayoutInflater;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class FireBaseOperations {
    public static FirebaseAuth connect() {
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword("hazembarka7@gmail.com", "02587410000");

        return mAuth;
    }


    public void disconnect(FirebaseAuth mAuth) {
        mAuth = null;
    }


    public static void sendData(String[] list) {
        FirebaseAuth mAuth = connect();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        String listString = "";

        for (String s : list) {
            listString += s + "/";
        }

        myRef.child("Users").push().setValue(listString);
    }




}
