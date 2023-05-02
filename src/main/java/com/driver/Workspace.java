package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        super(emailId, Integer.MAX_VALUE);
        this.calendar = new ArrayList<>();
        // The inboxCapacity is equal to the maximum value an integer can store.
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        int [][] meetings = new int[calendar.size()][2];
        for(int i=0;i<calendar.size();i++){
            int startTime = localToInt(calendar.get(i).getStartTime());
            int endTime = localToInt(calendar.get(i).getEndTime());

            meetings[i][0] = startTime;
            meetings[i][1] = endTime;
        }
        Arrays.sort(meetings, (a, b) -> a[1] - b[1]);

        int maxMeetings = 0;
        int endTime = 0;

        for (int[] meeting : meetings) {
            if (meeting[0] >= endTime) {
                maxMeetings++;
                endTime = meeting[1];
            }
        }

        return maxMeetings;
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am

    }

    public int localToInt(LocalTime localTime){
        int result = localTime.get(ChronoField.MINUTE_OF_DAY);
        return result;
    }
}
