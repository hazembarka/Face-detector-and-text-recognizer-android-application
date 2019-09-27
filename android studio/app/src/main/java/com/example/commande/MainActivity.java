package com.example.commande;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button b=(Button) findViewById(R.id.btnSubmit);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Spinner spinner = (Spinner) findViewById(R.id.spinner1);
                EditText nom = (EditText) findViewById(R.id.nom);
                EditText tel = (EditText) findViewById(R.id.tel);
                EditText adress = (EditText) findViewById(R.id.adress);
                EditText date = (EditText) findViewById(R.id.dateliv);

                String commande[] = {nom.getText().toString(),
                        spinner.getSelectedItem().toString(),
                        tel.getText().toString(),
                        adress.getText().toString(),
                        date.getText().toString()};

                Context context = getApplicationContext();
                CharSequence message = "Commande envoyée !";
                int duration = Toast.LENGTH_SHORT;
                FireBaseOperations.sendData(commande);

                FirebaseAuth mAuth = FireBaseOperations.connect();
                final ArrayList<String> list = new ArrayList<String>();
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                Query query = rootRef.child("Users");
                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            list.add(ds.getValue(String.class));
                            TextView t = (TextView) findViewById(R.id.textView);
                            t.setText(ds.getValue(String.class));

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d(TAG, databaseError.getMessage());
                    }
                };
                query.addListenerForSingleValueEvent(valueEventListener);









            Toast toast = Toast.makeText(context,message, duration);
                toast.show();

            }
        });
    }
}
