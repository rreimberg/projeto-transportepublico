package br.sptrans.transportepublico.painel;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by rreimberg on 10/31/13.
 */
public class Lotacao extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tela_lotacao);

        findViewById(R.lotacao.nivel1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizaStatus(1);
            }
        });

        findViewById(R.lotacao.nivel2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizaStatus(2);
            }
        });

        findViewById(R.lotacao.nivel3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizaStatus(3);
            }
        });

        findViewById(R.lotacao.nivel4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizaStatus(4);
            }
        });

        findViewById(R.lotacao.nivel5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizaStatus(5);
            }
        });
    }

    void atualizaStatus(int nivel) {
        ProgressBar status = (ProgressBar) findViewById(R.lotacao.status);
        status.setProgress(nivel);
    }
}