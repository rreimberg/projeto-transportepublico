package br.sptrans.transportepublico.painel;

<<<<<<< HEAD
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
=======
import android.app.Activity;
import android.app.DialogFragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
>>>>>>> 90ed7dff91a1cbafb9a16988e9f1d22ad0357e9a

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
                Intent i = new Intent(getActivity().getApplicationContext(), Viagem.class);
                startActivity(i);
            }
        });

        getActivity().findViewById(R.menu.transito).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplicationContext(), Transito.class);
                startActivity(i);
            }
        });

        getActivity().findViewById(R.menu.lotacao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplicationContext(), Lotacao.class);
                startActivity(i);
            }
        });

        getActivity().findViewById(R.menu.alerta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplicationContext(), Alerta.class);
                startActivity(i);
            }
        });
    }

}
