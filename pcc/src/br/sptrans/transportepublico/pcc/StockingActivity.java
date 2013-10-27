package br.sptrans.transportepublico.pcc;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by rreimberg on 10/26/13.
 */
public class StockingActivity extends BaseActivity {

    private ImageView _current_level;
    private ArrayList _levels = new ArrayList<ImageView>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocking);
        defaultNavigation();

        _levels.add(0, findViewById(R.stocking.level_1));
        _levels.add(1, findViewById(R.stocking.level_2));
        _levels.add(2, findViewById(R.stocking.level_3));
        _levels.add(3, findViewById(R.stocking.level_4));
        _levels.add(4, findViewById(R.stocking.level_5));

        SeekBar level = (SeekBar) findViewById(R.stocking.level);
        _current_level = (ImageView) _levels.get(level.getProgress());
        _current_level.setVisibility(View.VISIBLE);

        level.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                SeekBar level = (SeekBar) StockingActivity.this.findViewById(R.stocking.level);
                StockingActivity.this.updateStocking(level.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                TextView last_update = (TextView) StockingActivity.this.findViewById(R.stocking.last_update);
                last_update.setText(currentDateTimeString);
            }
        });
    }

    public void updateStocking(Integer level) {
        _current_level.setVisibility(View.INVISIBLE);
        _current_level = (ImageView) _levels.get(level);
        _current_level.setVisibility(View.VISIBLE);
    }
}