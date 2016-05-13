package com.example.android.cccportable;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Cancioneiros extends AppCompatActivity {
    public Button bt1;
    public Button bt2;
    public Button bt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancioneiros);
    }
    private void openFile(String s) {
        try {
            InputStream in = getAssets().open(s);
            File file = new File(getFilesDir(),s);
            OutputStream out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);
            copyFile(in, out);
            in.close();
            in=null;
            out.flush();
            out.close();
            out=null;
            Intent change = new Intent(Intent.ACTION_VIEW);
            change.setDataAndType(Uri.parse("file://" + getFilesDir() + "/"+s),"application/pdf");

            startActivity(change);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read=in.read(buffer))!=-1){
            out.write(buffer,0,read);
        }
    }

    public void IndiceCancioneiroActual(View view){
        TextView cx= (TextView)findViewById(R.id.cancioneiroActual);
        cx.setTextSize(16);
        cx.setText("  Hino \n Vinho do Porto\n Quando Lisboa Canta \n Vem/ Andorinha \n Sonho \n Se ao menos houvesse um dia \n Cortesia Final \n Fado da Saudade \n Vida de estudante \n Fado do estudante \n Bandolim \n Manuel e Maria \n Hombre Sincero \n Águas do Dão \n Madalena \n A rua do gato preto  \n Gaivota \n Pilinha \n Silêncio de tanta gente \n Olhos”");
        bt1=(Button)findViewById(R.id.pdf_cancioneiroActual);

        if(cx.getVisibility()==view.GONE) {
            bt1.setVisibility(View.VISIBLE);
            cx.setVisibility(View.VISIBLE);
        }else {
            cx.setVisibility(View.GONE);
            bt1.setVisibility(View.GONE);
        }
    }
    public void IndiceCancioneiroAntigo(View view){
        TextView cx= (TextView)findViewById(R.id.cancioneiroAntigo);
        cx.setTextSize(16);
        cx.setText("Preciosa\nLisboa, Rainha do Mar\nMedo do Amor\nConquistador\nPovo Que Lavas No Rio\nMedo\nGaivota\nÀ Meia Noite ao Luar.\nAdeus\nAfonso\nÀguas do Dão\nAmélia dos Olhos Doces\nApita o Comboio\nAssim mesmo é que é\nBarco Negro\nBeijinho\nCanção do Mar\nCapuchinho\nCasa Portuguesa\nDedicação\nEstudantina Portuguesa\nFado Português\nFeiticeira\nFlor Sem Tempo\nFoi Deus\nHoy Estoy Aqui\nLisboa Menina e Moça\nMadalena\nMarcha do Pião das Nicas\nMaria Faia\nMenina estás à janela\nMulher Gorda\nNem às Paredes Confesso\nO Teu Segredo\nOlhos Negros\nOndas do Douro\nPedra Filosofal\nPiel Canela\nPovo Que Lavas no Rio\nSerenata ao Luar\nTraçadinho\nVagabundo\nVerde Vinho\nVersos de Amor\nVinho do Porto\nVira do Vinho\nYo Sin Ti\n");
        bt2=(Button)findViewById(R.id.pdf_cancioneiroAntigo);

        if(cx.getVisibility()==view.GONE) {
            bt2.setVisibility(View.VISIBLE);
            cx.setVisibility(View.VISIBLE);
        }else {
            cx.setVisibility(View.GONE);
            bt2.setVisibility(View.GONE);
        }
    }

    public void Abrir1(View view) {
        openFile("cancioneiroActual.pdf");
    }

    public void Abrir2(View view) {
        openFile("cancioneiroComAntigosTemas.pdf");
    }
}
