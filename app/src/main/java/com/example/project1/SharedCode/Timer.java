package com.example.project1.SharedCode;

public class Timer {

    private long StartTime;
    private long TimeDuration;
    private boolean Running;
    private boolean Finished;

    // default constructor
    public Timer() {
        StartTime = 0;
        TimeDuration = 0;
        Running = false;
        Finished = false;
    }

    // constructor that also starts the timer
    public Timer(long TimeDuration) {
        StartTime = 0;
        this.TimeDuration = TimeDuration;
        Running = false;
        Finished = false;
    }

    // Starts timer with new duretion
    public void Start(long TimeDuration) {
        StartTime = System.currentTimeMillis();
        this.TimeDuration = TimeDuration;
        Running = true;
        Finished = false;
    }

    // Stops the timer
    public void stop() {
        if (Running) {
            Running = false;
        }

    }
 // checks if the timer is done
    private void CheckTimer() {
        if ((System.currentTimeMillis() - StartTime) >= TimeDuration) {
            Running = false;
            Finished = true;
        }
    }
// returns if the timer ended
    public boolean TimerEnded() {
        if (!Finished && Running) {
            CheckTimer();
        }
        return Finished;
    }
// returns the remaning time on the timer
    public long TimeRemaning() {
        if (Finished) {
            return 0;
        } else {
            return TimeDuration - (System.currentTimeMillis() - StartTime);
        }
    }
// to reset the timer to default sate
    public void resetTimer() {
        StartTime = 0;
        Running = false;
        Finished = false;
        TimeDuration = 0;

    }

}
