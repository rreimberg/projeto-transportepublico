package br.sptrans.transportepublico.painel;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by rreimberg on 10/31/13.
 */
public class Transito extends BaseActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_transito);
    }

    @Override
    public void onBackPressed() {
        mensagem("Não é possível sair da aplicação.");
    }
}