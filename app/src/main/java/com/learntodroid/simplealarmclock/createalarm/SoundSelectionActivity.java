package com.learntodroid.simplealarmclock.createalarm;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.learntodroid.simplealarmclock.R;

import java.util.ArrayList;
import java.util.List;

public class SoundSelectionActivity extends AppCompatActivity {
    public static final String SELECTED_SOUND_TITLE = "selectedSoundTitle";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_selection);

        // Assuming you have a list of sound titles
        List<String> soundTitles = getSoundTitles();

        ListView soundListView = findViewById(R.id.soundListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, soundTitles);
        soundListView.setAdapter(adapter);

        soundListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected sound title
                String selectedSoundTitle = soundTitles.get(position);

                // Set the result and finish the activity
                Intent resultIntent = new Intent();
                resultIntent.putExtra(SELECTED_SOUND_TITLE, selectedSoundTitle);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private List<String> getSoundTitles() {
        // Replace this with logic to fetch sound titles
        List<String> soundTitles = new ArrayList<>();
        soundTitles.add("Sound1");
        soundTitles.add("Sound2");
        soundTitles.add("Sound3");
        // ... add more sounds as needed
        return soundTitles;
    }
}
