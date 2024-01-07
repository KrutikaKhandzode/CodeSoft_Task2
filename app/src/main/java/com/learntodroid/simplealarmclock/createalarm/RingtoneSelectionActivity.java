package com.learntodroid.simplealarmclock.createalarm;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.learntodroid.simplealarmclock.R;

import java.util.ArrayList;
import java.util.List;

public class RingtoneSelectionActivity extends Activity {

    private static final int RINGTONE_PICKER_REQUEST_CODE = 123;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringtone_selection);

        ListView listView = findViewById(R.id.ringtoneListView);

        // Fetch a list of available alarm sounds
        List<String> alarmSounds = fetchAlarmSounds();

        // Display the list using an ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alarmSounds);
        listView.setAdapter(adapter);

        // Set item click listener to handle selection
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Return the selected alarm sound URI to the calling activity
                String selectedRingtone = alarmSounds.get(position);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selectedRingtone", selectedRingtone);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private List<String> fetchAlarmSounds() {
        RingtoneManager ringtoneManager = new RingtoneManager(this);
        ringtoneManager.setType(RingtoneManager.TYPE_ALARM);

        Cursor alarmsCursor = ringtoneManager.getCursor();
        List<String> alarmSounds = new ArrayList<>();

        if (alarmsCursor != null && alarmsCursor.moveToFirst()) {
            do {
                String title = alarmsCursor.getString(RingtoneManager.TITLE_COLUMN_INDEX);
                alarmSounds.add(title);
            } while (alarmsCursor.moveToNext());

            alarmsCursor.close();
        }

        return alarmSounds;
    }
}
