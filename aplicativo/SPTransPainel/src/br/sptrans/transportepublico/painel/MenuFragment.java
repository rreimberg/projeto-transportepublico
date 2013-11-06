package br.sptrans.transportepublico.painel;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rreimberg on 11/3/13.
 */
public class MenuFragment extends DialogFragment {

    boolean mDualPane;
    int mCurCheckPosition = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lateral, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setMenuListeners();
    }

    void setMenuListeners() {
        getActivity().findViewById(R.menu.viagem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getApplicationContext(), Viagem.class));
            }
        });

        getActivity().findViewById(R.menu.transito).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getApplicationContext(), Transito.class));
            }
        });

        getActivity().findViewById(R.menu.lotacao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getApplicationContext(), Lotacao.class));
            }
        });

        getActivity().findViewById(R.menu.alerta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getApplicationContext(), Alerta.class));
            }
        });
    }

    
}
