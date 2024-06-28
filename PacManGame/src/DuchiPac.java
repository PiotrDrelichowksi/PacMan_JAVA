import java.util.ArrayList;

public class DuchiPac {
    private PacMan pac;
    private ArrayList<Duch> listaDuchow;
    private boolean czyDziala;
    private final Object lock = new Object();
    public  DuchiPac(PacMan pac,ArrayList<Duch> listaDuchow){
        this.pac=pac;
        this.listaDuchow=listaDuchow;
        czyDziala=true;
    }
    public void startGame() {
        Thread duchThread = new Thread(() -> {
            while (czyDziala) {
                synchronized (lock){
                    for (Duch duch : listaDuchow) {
                        duch.moveDuch();
                        duch.kolizjaDuch();
                    }
                }
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        duchThread.start();
        Thread pacThread = new Thread(() -> {
            while (czyDziala) {
                synchronized (lock){
                pac.movePacman();
                pac.kolizaPac(listaDuchow);
            }
                try {
                    Thread.sleep(pac.getPacSpeed());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        pacThread.start();

        Thread aniThread = new Thread(() -> {
            while (czyDziala) {
                    pac.pacAnimation();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

        });
        aniThread.start();
            Thread duchAniThread = new Thread(() -> {
                while (czyDziala) {
                    for (Duch duch : listaDuchow) {
                        duch.duchAni();
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            });
            duchAniThread.start();


        for (Duch duch : listaDuchow) {
            duch.supermocLosowanie();
        }
    }


    public void setCzyDziala(boolean czyDziala) {
        this.czyDziala = czyDziala;
    }

}
