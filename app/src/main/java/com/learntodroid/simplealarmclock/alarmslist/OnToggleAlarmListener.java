package com.learntodroid.simplealarmclock.alarmslist;

import com.learntodroid.simplealarmclock.data.Alarm;

public interface OnToggleAlarmListener {
    void onToggle(Alarm alarm);
    void onDelete(Alarm alarm);

    void onLongClick(Alarm alarm);
}
