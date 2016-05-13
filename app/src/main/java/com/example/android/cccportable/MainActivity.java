package com.example.android.cccportable;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.android.cccportable.Contactos;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Conceitos(View view){
        TextView cx= (TextView)findViewById(R.id.conceitos);
        cx.setTextSize(16);
        cx.setText(" -O que acontece dentro do grupo de caloiros, fica no grupo de caloiros \n -Ou és Estudantino & veterano ou Estudantino & Caloiro. \n -SOMOS CALOIROS devemos remar todos para o mesmo lado \n -Diferenças existem em todo o lado mas se forem entre caloiros devem ficar neste grupo ou seja “ não tem de ser todos amigos, só têm de se ajudar e respeitar enquanto caloiros.”");
        if(cx.getVisibility()==view.GONE)
            cx.setVisibility(View.VISIBLE);
        else cx.setVisibility(View.GONE);
    }
    public void Isel(View view){
        TextView cx= (TextView)findViewById(R.id.isel);
        cx.setTextSize(16);
        cx.setText("A EAISEL é uma instituição que engloba todos os cursos, logo se estás a pouco tempo no ISEL, participa nas praxes do ISEL e tudo mais, contudo se, se sobrepuser com eventos da EAISEL tens que atender ao chamamento e cagar nas praxes do ISEL. O estatuto da Estudantina é superior ao de Veterano no ISEL.");
        if(cx.getVisibility()==view.GONE)
            cx.setVisibility(View.VISIBLE);
        else cx.setVisibility(View.GONE);
    }
    public void Hierarquia(View view){
        TextView cx= (TextView)findViewById(R.id.hierarquia);
        cx.setTextSize(16);
        cx.setText("-Fundadores \n-Magíster’s & Direção \n-Veteranos \n-Chefe & SubChefe \n-Caloiros mais velhos");
        if(cx.getVisibility()==view.GONE)
            cx.setVisibility(View.VISIBLE);
        else cx.setVisibility(View.GONE);
    }
    public void Ensaios(View view){
        TextView cx= (TextView)findViewById(R.id.ensaios);
        cx.setTextSize(16);
        cx.setText("-Ensaio de caloiros \n      .Afinar instrumentos \n"+
                "      .Aquecimento de vozes \n" +
                "      .Revisão de reportório antigo \n-Respeitar o Ensaiador \n-Não Falar, se ninguém (ensaiador / veterano) não te pediu para o fazeres. \n-Não tocar instrumentos quando está a ser explicado algo, a menos que seja para afinar \n-Nunca responder a quaisquer tipo de provocações \n" +
                "-Sorrir \n" +
                "-Curtir");
        if(cx.getVisibility()==view.GONE)
            cx.setVisibility(View.VISIBLE);
        else cx.setVisibility(View.GONE);
    }
    public void Traje(View view){
        TextView cx= (TextView)findViewById(R.id.traje);
        cx.setTextSize(16);
        cx.setText("-Todo o traje pode e deve ser lavado, e se necessário, sim… inclusive a capa, não há nada que o proíba. \n-TODAS as etiquetas do traje têm de ser removidas. \n-Numero de pins só pode ser impar \n-Relógio só de bolso e só veteranos \n-Sim as meias são pretas e apenas pretas \n-Caloiros, colete completamente abotoado ( o que nos distingue dos veteranos por não abotoarem o ultimo botão) \n-Óculos de Sol/mão no bolso não fazem parte do traje \n-A batina só é removida se solicitada, ou se a capa tiver ao serviço da estudantina, caso contrario, considera-te mal trajado. ( A mesa tens de ter a batina, a menos que te digam ) \n" +
                "-A credencial é para atar ao cinto e enfiar no bolso das calças, Não pode andar a mostra. \n-Preservativo sempre no bolso ESQUERDO do colete.");
        if(cx.getVisibility()==view.GONE)
            cx.setVisibility(View.VISIBLE);
        else cx.setVisibility(View.GONE);
    }
    public void Mesa(View view) {
        TextView cx = (TextView) findViewById(R.id.mesa);
        cx.setTextSize(16);
        cx.setText("-Servir todos os veteranos \n-Ninguém se senta, sem ordem. \n-Não se brinca com a comida \n-Escolher o que gostas ,nada com molhos, fruta e pão");
        if (cx.getVisibility() == view.GONE)
            cx.setVisibility(View.VISIBLE);
        else cx.setVisibility(View.GONE);
    }
    public void Autocarro(View view){
        TextView cx= (TextView)findViewById(R.id.autocarro);
        cx.setTextSize(16);
        cx.setText("  O autocarro é aquele momento perfeito para atacar qualquer caloiro. Não podes fugir, por mais esperto que sejas. Ri sempre, canta sempre, curte sempre. Tenta marcar o teu traje de forma subtil, porque vais acabar sempre despido a meio da viagem e terás de encontrar o teu no meio de um monte de trajes.");
        if(cx.getVisibility()==view.GONE)
            cx.setVisibility(View.VISIBLE);
        else cx.setVisibility(View.GONE);
    }


    public void ChangeToContacts(View view){

        startActivity(new Intent(view.getContext(), Contactos.class));

    }
    public void ChangeToCancioneiros(View view){
        startActivity(new Intent(view.getContext(),Cancioneiros.class));
    }
    public void ccc(View view) {
        openFile("ccc.pdf");
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read=in.read(buffer))!=-1){
            out.write(buffer,0,read);
        }
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
            change.setDataAndType(Uri.parse("file://" + getFilesDir() + "/" + s),"application/pdf");

            startActivity(change);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
