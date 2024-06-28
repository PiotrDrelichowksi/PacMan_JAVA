
public class Czasomierz {
    private boolean running;
    private int sekundy;
    private int minuty;

    public void start() {
        if (running) return;

        running = true;
        sekundy = 0;

        Thread czasomierzThread = new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(1000);
                    sekundy++;
                    if (sekundy==60){
                        minuty++;
                        sekundy=0;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        czasomierzThread.start();
    }

    public void stop() {
        running = false;
    }

    public int getMinuty() {
        return minuty;
    }

    public int getSekundy() {
        return sekundy;
    }
}
