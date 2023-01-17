package com.example.mychatapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mychatapp.Models.MessageData;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class nav_messageCreate extends Fragment {
    Button CreateMessage;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    TextInputEditText MessageName,MessageDesc;

    FirebaseFirestore firebaseFirestore;

    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_nav_message_create,container,false);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        firebaseFirestore =FirebaseFirestore.getInstance();

        CreateMessage=rootView.findViewById(R.id.message_create);
        MessageName=rootView.findViewById(R.id.messagename);
        MessageDesc=rootView.findViewById(R.id.messagedesc);
        recyclerView=rootView.findViewById(R.id.messages);

        CreateMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(MessageName.getText().toString()) || TextUtils.isEmpty(MessageDesc.getText().toString())){
                    Tools.showMessage("Lütfen mesaj adı ve mesajı giriniz");
                }
                String userid=firebaseUser.getUid();

                MessageData messageData=new MessageData(userid,MessageName.getText().toString(),MessageDesc.getText().toString());

                firebaseFirestore.collection("message")
                        .add(messageData)
                        .addOnSuccessListener(documentReference -> {
                            Tools.showMessage("Mesaj Oluşturuldu");
                        });
            }
        });



        return rootView;
    }
}