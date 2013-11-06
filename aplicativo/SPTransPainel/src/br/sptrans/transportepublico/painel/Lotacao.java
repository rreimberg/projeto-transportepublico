package br.sptrans.transportepublico.painel;

import br.sptrans.transportepublico.controle.SimplesDialogoControle;
import br.sptrans.transportepublico.identificador.LotacaoIdentificador;
import br.sptrans.transportepublico.identificador.MenuIdentificador;
import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

/**
 * Created by rreimberg on 10/31/13.
 */
public class Lotacao extends BaseActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_lotacao);
        menuSelecionado(MenuIdentificador.Lotacao);

        for (final LotacaoIdentificador item : LotacaoIdentificador.retornaTudo()) {
            ((ImageButton)findViewById(item.getControleId())).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for (LotacaoIdentificador LotacaoIdentificador : br.sptrans.transportepublico.identificador.LotacaoIdentificador.retornaTudo()) {
                        if(item.getControleId() != LotacaoIdentificador.getControleId())
                            ((ImageButton)findViewById(LotacaoIdentificador.getControleId())).setBackgroundColor(Color.GRAY);
                        else
                            ((ImageButton)findViewById(LotacaoIdentificador.getControleId())).setBackgroundColor(Color.parseColor(LotacaoIdentificador.getCorId()));
                    }

                    SimplesDialogoControle simplesDialogoControle = new SimplesDialogoControle(Lotacao.this, "Lotação", "Deseja enviar esta informaçao de Lotaçao?");
                    simplesDialogoControle.setPositiveButton("Sim", new android.content.DialogInterface.OnClickListener(
                    ) {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mensagem("Informação enviada com sucesso.");
                        }
                    });
                    simplesDialogoControle.show();
                }
            });
        }
    }
}
