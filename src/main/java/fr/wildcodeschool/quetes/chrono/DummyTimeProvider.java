package fr.wildcodeschool.quetes.chrono;

import java.time.Duration;
import java.time.LocalDateTime;

public class DummyTimeProvider implements TimeProvider {
    private Long totalTime;
    private LocalDateTime startTime = LocalDateTime.now();
    private Boolean start = false;
    private boolean reset = false;
    private Long delay = 0L;

    @Override
    public void startStop() {
        if (!start) {
            start = true;
            if (reset) {
                startTime = LocalDateTime.now();
                reset = false;
            }
        } else {
            start = false;
        }
    }

    @Override
    public void reset() {
        this.delay = 0L;
        this.totalTime = 0L;
        if (start) {
            this.startTime = LocalDateTime.now();
            this.reset = false;
        }
        else {
            this.reset = true;
        }
    }

    public DummyTimeProvider(Long elapsed) {
        this.delay = elapsed;
        this.totalTime = elapsed;
    }

    @Override
    public boolean isStarted() {
        if (start) {
            Duration gap = Duration.between(startTime,LocalDateTime.now().plusSeconds(delay));
            this.totalTime = gap.toSeconds();
            return true;
        }
        else {
            if (reset) {
                return true;
            } else {
                startTime = startTime.plusSeconds(1);
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
