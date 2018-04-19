package fr.wildcodeschool.quetes.chrono;

public class Startup {

    public static void main(String... args) throws InterruptedException {
        Boolean maximise = false;
        Long elapsed = 0L;

        if(args.length >= 1)
            try {
            maximise = Boolean.parseBoolean(args[0]);
        }
            catch (Exception e) {
            e.printStackTrace();
        }

        if(args.length >= 2)
            try {
            elapsed = Long.parseLong(args[1]);
        }
            catch (Exception e) {
            e.printStackTrace();
        }

        TimeProvider tp = new DummyTimeProvider(elapsed);

        new Chrono(tp,maximise).roll();
    }
}
