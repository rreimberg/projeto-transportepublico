package br.sptrans.transportepublico.painel;

import br.sptrans.transportepublico.identificador.MenuIdentificador;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by rreimberg on 10/31/13.
 */
public class Lotacao extends BaseActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tela_lotacao);
    }
}
