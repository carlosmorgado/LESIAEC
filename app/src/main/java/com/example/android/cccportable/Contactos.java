package com.example.android.cccportable;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.Scanner;
import static android.Manifest.permission.CALL_PHONE;
public class Contactos extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_contactos);
        Linhas = (LinearLayout)findViewById(R.id.contactos);
        load();
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
            pedirPermissions();
        createListener();
        dividirNomes();
        spaceAlcunhas();
//        setContentView(Linhas);
    }

    private void dividirNomes() {
        nome=new String[nomeEstudantino.length];apelido=new String[nomeEstudantino.length];alcunhas=new String[nomeEstudantino.length];
        int count= 0;int r = 0;
        int l = 0;
        for (int i = 0; i < nomeEstudantino.length; i++) {
            for (; r < nomeEstudantino[i].length() && nomeEstudantino[i].charAt(r)!='.' ; r++) {
            }
            try {


            switch (count){
                case 0 : nome[i]= nomeEstudantino[i].substring(l,r);l=++r;++count;--i;break;
                case 1 : alcunhas[i] = nomeEstudantino[i].substring(l,r);l=++r;++count;--i;break;
                default: apelido[i] = nomeEstudantino[i].substring(l,nomeEstudantino[i].length());count=0;r=0;l=0;break;
            }}catch (Exception e){
                System.out.println(i);
            }

        }
    }

    private void pedirPermissions() {
        int a1 = 10;
        String[]a=new String[]{CALL_PHONE};
        ActivityCompat.requestPermissions(this,a,a1);
    }

    private void createListener() {
        for (int i = 0; i < b.length; i++) {
            final int finalI = i;
            b[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callNumber(finalI);
                }
            });
        }
    }

    private void callNumber(int finalI) {
        Intent call= new Intent(Intent.ACTION_CALL);
        call.setData(Uri.parse("tel:"+cellPhoneNumbers[finalI]));
        if(call.resolveActivity(getPackageManager())!=null){
            startActivity(call);
        }
    }


    public Button [] b;
    public String[] nomeEstudantino;
    public String[] nome;
    public String[] alcunhas;
    public String[] apelido;
    public int [] cellPhoneNumbers;
    public LinearLayout Linhas ;

    private void load() {
        try{
            Scanner in = new Scanner(getAssets().open("Contactos.txt"));
            int size=in.nextInt();
            cellPhoneNumbers=new int [size];
            b= new Button[size];
            nomeEstudantino =new String[size];
            for(int i=0;i<size;++i){
                b[i]=new Button(this);
                LinearLayout.LayoutParams tuaMae= new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                tuaMae.setMargins(32, 16, 32,16);
                b[i].setLayoutParams(tuaMae);
                nomeEstudantino[i]=in.next();
                cellPhoneNumbers[i]=in.nextInt();
                b[i].setText(nomeEstudantino[i]);
                b[i].setBackgroundColor(Color.WHITE);
                Linhas.addView(b[i]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void search(View view) {
        EditText a = (EditText)findViewById(R.id.search);
        String nome = a.getText().toString();
        if(nome!=null&&nome.length()>0){
            if (searchByNomeDeEstudanntino(nome))return;
            if(searchByNome(nome))return;
            if(searchByAlcunha(nome))return;
            searchByApelido(nome);
        }
    }

    private boolean searchByAlcunha(String nome) {
        for (int i = 0; i < alcunhas.length; i++) {
            if(alcunhas[i].length()<nome.length())continue;
            if (alcunhas[i].substring(0,nome.length()).equalsIgnoreCase(nome)){
                ((ScrollView)findViewById(R.id.scroll)).scrollTo((int)b[i].getX(),(int)b[i].getY());
                return true;
            }
        }
        return false;
    }

    private boolean searchByNomeDeEstudanntino(String nome) {
        for (int i = 0; i < nomeEstudantino.length; i++) {
            if (nomeEstudantino[i].length()<nome.length())continue;
            if (nomeEstudantino[i].substring(0,nome.length()).equalsIgnoreCase(nome)){
                ((ScrollView)findViewById(R.id.scroll)).scrollTo((int) b[i].getX(), (int) b[i].getY());
                return true;
            }
        }
        return false;

    }

    private boolean searchByNome(String nome) {
        for (int i = 0; i < this.nome.length; i++) {
            if (this.nome[i].length()<nome.length())continue;
            if (this.nome[i].substring(0,nome.length()).equalsIgnoreCase(nome)){
                ((ScrollView)findViewById(R.id.scroll)).scrollTo((int)b[i].getX(), (int) b[i].getY());
                return true;
            }
        }
        return false;
    }

    private boolean searchByApelido(String nome) {
        for (int i = 0; i < apelido.length; i++) {
            if(apelido[i].length()<nome.length())continue;
            if (apelido[i].substring(0,nome.length()).equalsIgnoreCase(nome)){
                ((ScrollView)findViewById(R.id.scroll)).scrollTo((int) b[i].getX(), (int) b[i].getY());
                return true;
            }
        }
        return false;

    }
    private void spaceAlcunhas(){
        for (int i = 0; i <alcunhas.length ; i++) {
                for(int j=0;j<alcunhas[i].length();++j){
                    Str c = new Str(alcunhas[i]);
                    if(alcunhas[i].charAt(j)=='_'){
                        alcunhas[i]= c.change(j,' ');
                    }
                }
        }
    }
}
