package com.example.project1.SharedCode;

public class Timer {

    private long StartTime;
    private long TimeDuration;
    private boolean Running;
    private boolean Fished;
public Timer(){
    StartTime = 0;
    TimeDuration = 0;
    Running = false;
    Fished = false;
}
 public Timer(long TimeDuration){
        StartTime = 0;
        this.TimeDuration = TimeDuration;
        Running = true;
        Fished = false;
    }

    public void Start(long TimeDuration){
            StartTime = System.currentTimeMillis();
            this.TimeDuration = TimeDuration;
            Running = true;
            Fished = false;
    }

    public void stop(){
        if(Running){
            Running = false;
        }

    }
    public void CheckTimer(){
            if( (System.currentTimeMillis() - StartTime) >= TimeDuration) {
                Running = false;
                Fished = true;
        }
    }

    public boolean TimerEnded(){
        if(!Fished && Running) {
            CheckTimer();
        }
    return Fished;
    }

    public long TimeRemaning(){
        if(Fished) {
            return 0;
        }else{
            return TimeDuration - (System.currentTimeMillis() - StartTime);
        }
    }

}
