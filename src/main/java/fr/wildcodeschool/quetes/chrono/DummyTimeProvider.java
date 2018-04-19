package fr.wildcodeschool.quetes.chrono;

import java.util.Date;

public class DummyTimeProvider implements TimeProvider {

    private Long totalTime;
    private Date date = new Date();
    private Long time = date.getTime() / 1000;
    private Boolean start = false;
    private Long delay = 0L;
    private boolean reset = false;
    private Long startTime = 0L;

    @Override
    public void startStop() {
        if (!start) {
            start = true;
            if (reset) {
                date = new Date();
                time = date.getTime() / 1000;
                reset = false;
            }
        } else {
            start = false;
        }
    }

    @Override
    public void reset() {
        if (start) {
            this.date = new Date();
            this.time = date.getTime() / 1000;
            this.delay = 0L;
            this.reset = false;
        }
        else {
            this.totalTime = 0L;
            delay = 0L;
            this.reset = true;
        }
    }

    public DummyTimeProvider(Long elapsed) {
        this.startTime = elapsed;
        this.totalTime = elapsed;
    }

    @Override
    public boolean isStarted() {
        if (start) {
            Date dateFin = new Date();
            Long timeFin = (dateFin.getTime() / 1000) + startTime; //ici startTime qui contient le nb de secondes entrés en argument
            this.totalTime = timeFin - this.time - delay;
            return true;
        }
        else {
            if (reset) {
                return true;
            } else {
                delay += 1;
                return false;
            }
        }
    }

    @Override
    public long getSecondsTotalRuntime() {
        return this.totalTime;
    }

    @Override
    public long getHoursRuntime() {
        return totalTime / 3600;
    }

    @Override
    public long getMinutesRuntime() {
        return (totalTime / 60) % 60;
    }

    @Override
    public long getSecondsRuntime() {
        return totalTime % 60;
    }
}