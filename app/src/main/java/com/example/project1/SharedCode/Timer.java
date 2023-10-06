package com.example.project1.SharedCode;

public class Timer {

    private long StartTime;
    private long TimeDuration;
    private boolean Running;
    private boolean Finished;
public Timer(){
    StartTime = 0;
    TimeDuration = 0;
    Running = false;
    Finished = false;
}
 public Timer(long TimeDuration){
        StartTime = 0;
        this.TimeDuration = TimeDuration;
        Running = false;
        Finished = false;
    }

    public void Start(long TimeDuration){
            StartTime = System.currentTimeMillis();
            this.TimeDuration = TimeDuration;
            Running = true;
            Finished = false;
    }

    public void stop(){
        if(Running){
            Running = false;
        }

    }
    public void CheckTimer(){
            if( (System.currentTimeMillis() - StartTime) >= TimeDuration) {
                Running = false;
                Finished = true;
        }
    }

    public boolean TimerEnded(){
        if(!Finished && Running) {
            CheckTimer();
        }
    return Finished;
    }

    public long TimeRemaning(){
        if(Finished) {
            return 0;
        }else{
            return TimeDuration - (System.currentTimeMillis() - StartTime);
        }
    }

    public void resetTimer() {
        StartTime = 0;
        Running = false;
        Finished = false;
        TimeDuration = 0;

    }

}
