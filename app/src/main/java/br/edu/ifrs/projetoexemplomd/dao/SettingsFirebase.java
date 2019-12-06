package br.edu.ifrs.projetoexemplomd.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsFirebase {
    private static DatabaseReference reference = null;

    public static DatabaseReference getNoRaiz(){
        reference = FirebaseDatabase.getInstance().getReference();
        return reference;
    }

    public static DatabaseReference getNo(String no){
        reference = FirebaseDatabase.getInstance().getReference(no);
        return reference;
    }
}
