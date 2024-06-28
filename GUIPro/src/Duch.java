import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Duch {
    PacMan pac;
    public boolean isKillable;
    private ArrayList<ArrayList<JLabel>> map;
    private int duchX;
    private int duchY;
    private Icon duchIcon;
    private int[][] sciany;
    private int nextX;
    private int nextY;
    private int oldX;
    private int oldY;
    private ImageIcon[] duchR;
    private ImageIcon[] duchL;
    private ImageIcon[] duchG;
    private ImageIcon[] duchD;
    private int spawnX;
    private int spawnY;
    Kierunek kierunek;
    private int licznik;
    private ImageIcon punktIcon;
    private ImageIcon superPunktIcon;
    private ImageIcon piwko;
    private ImageIcon speed;
    private ImageIcon zamrozenie;
    private ImageIcon podwojenie;
    private ImageIcon zyciePlus;
    private ImageIcon duchF;
    private boolean czyDziala;
    public Duch(ArrayList<ArrayList<JLabel>> map,int[][]wallMap,int duchX,int duchY,PacMan pac) {
        this.map = map;
        this.isKillable=false;
        this.sciany = wallMap;
        this.duchX = duchX;
        this.duchY = duchY;
        spawnX=duchX;
        spawnY=duchY;
        oldX=duchX;
        oldY=duchY;
        this.licznik=0;
        this.duchIcon = new ImageIcon("zdjecia/duchR/duchR1.png");
        this.nextX = duchX;
        this.nextY = duchY;
        kierunek= Kierunek.PRAWO;
        duchR=new ImageIcon[]{new ImageIcon("zdjecia/duchR/duchR1.png"),new ImageIcon("zdjecia/duchR/duchR2.png"),new ImageIcon("zdjecia/duchR/duchR3.png")};
        duchL=new ImageIcon[]{new ImageIcon("zdjecia/duchL/duchL1.png"),new ImageIcon("zdjecia/duchL/duchL2.png"),new ImageIcon("zdjecia/duchL/duchL3.png")};
        duchG=new ImageIcon[]{new ImageIcon("zdjecia/duchG/duchG1.png"),new ImageIcon("zdjecia/duchG/duchG2.png"),new ImageIcon("zdjecia/duchG/duchG3.png")};
        duchD=new ImageIcon[]{new ImageIcon("zdjecia/duchD/duchD1.png"),new ImageIcon("zdjecia/duchD/duchD2.png"),new ImageIcon("zdjecia/duchD/duchD3.png")};
        piwko=new ImageIcon("zdjecia/supermoce/alkohol.png");
        speed=new ImageIcon("zdjecia/supermoce/speed.png");
        zamrozenie=new ImageIcon("zdjecia/supermoce/mrozenie.png");
        podwojenie=new ImageIcon("zdjecia/supermoce/podwojenie.png");
        zyciePlus = new ImageIcon("zdjecia/supermoce/zyciePlus.png");
        duchF=new ImageIcon("zdjecia/duchR/duchF.png");
        this.pac=pac;
        this.punktIcon=new ImageIcon("zdjecia/point.png");
        this.superPunktIcon=new ImageIcon("zdjecia/superPoint.png");
        this.czyDziala=true;

    }

    public void moveDuch() {
        synchronized (this) {
            Random rand = new Random();
            if (pac.getSupermoc() != 5) {
                int losowyKierunek = rand.nextInt(4);
                switch (losowyKierunek) {
                    case 0:
                        nextY = duchY - 1;
                        nextX = duchX;
                        kierunek = Kierunek.GORA;

                        break;
                    case 1:
                        nextY = duchY + 1;
                        nextX = duchX;
                        kierunek = Kierunek.DOL;

                        break;
                    case 2:
                        nextY = duchY;
                        nextX = duchX - 1;
                        kierunek = Kierunek.LEWO;

                        break;
                    case 3:
                        nextY = duchY;
                        nextX = duchX + 1;
                        kierunek = Kierunek.PRAWO;

                        break;
                }

                if (sciany[nextY][nextX] != -1) {
                    if (sciany[nextY][nextX] == 1) {
                        sciany[nextY][nextX] = 1;

                    }
                    if (sciany[nextY][nextX] == 2) {
                        sciany[nextY][nextX] = 2;
                    }
                    if (sciany[duchY][duchX] == 1) {
                        map.get(duchY).get(duchX).setIcon(punktIcon);
                    }
                    if (sciany[duchY][duchX] == 2) {
                        map.get(duchY).get(duchX).setIcon(superPunktIcon);
                    }
                    if (sciany[nextY][nextX] == 3) {
                        sciany[nextY][nextX] = 3;
                    }
                    if (sciany[duchY][duchX] == 3) {
                        map.get(duchY).get(duchX).setIcon(piwko);
                    }
                    if (sciany[duchY][duchX] == 4) {
                        sciany[duchY][duchX] = 4;
                    }
                    if (sciany[duchY][duchX] == 4) {
                        map.get(duchY).get(duchX).setIcon(speed);
                    }
                    if (sciany[duchY][duchX] == 5) {
                        sciany[duchY][duchX] = 5;
                    }
                    if (sciany[duchY][duchX] == 5) {
                        map.get(duchY).get(duchX).setIcon(zamrozenie);
                    }
                    if (sciany[nextY][nextX] == 6) {
                        sciany[nextY][nextX] = 6;
                    }
                    if (sciany[duchY][duchX] == 6) {
                        map.get(duchY).get(duchX).setIcon(podwojenie);
                    }
                    if (sciany[nextY][nextX] == 7) {
                        sciany[nextY][nextX] = 7;
                    }
                    if (sciany[duchY][duchX] == 7) {
                        map.get(duchY).get(duchX).setIcon(zyciePlus);
                    }
                    oldX=duchX;
                    oldY=duchY;

                    duchX = nextX;
                    duchY = nextY;

                }

            }
        }
    }
    public void duchAni(){
        synchronized (this) {
            if ((nextX != oldX || nextY != oldY) && sciany[oldY][oldX] == 0) {
                map.get(oldY).get(oldX).setIcon(null);
            }
            if (pac.getSupermoc() != 5) {

                licznik++;
                if (licznik == 2)
                    licznik = 0;
                if (kierunek == Kierunek.PRAWO) {
                    map.get(duchY).get(duchX).setIcon(duchR[licznik]);
                } else if (kierunek == Kierunek.LEWO) {
                    map.get(duchY).get(duchX).setIcon(duchL[licznik]);
                } else if (kierunek == Kierunek.GORA) {
                    map.get(duchY).get(duchX).setIcon(duchG[licznik]);
                } else if (kierunek == Kierunek.DOL) {
                    map.get(duchY).get(duchX).setIcon(duchD[licznik]);
                }
            }else {
                map.get(duchY).get(duchX).setIcon(duchF);
            }
        }
    }
    public void reziseDuch(int szer,int wys){
        duchR= new ImageIcon[]{new ImageIcon(new ImageIcon("zdjecia/duchR/duchR1.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/duchR/duchR2.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/duchR/duchr3.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))};
        duchL= new ImageIcon[]{new ImageIcon(new ImageIcon("zdjecia/duchL/duchL1.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/duchL/duchL2.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/duchL/duchL3.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))};
        duchG= new ImageIcon[]{new ImageIcon(new ImageIcon("zdjecia/duchG/duchG1.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/duchG/duchG2.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/duchG/duchG3.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))};
        duchD= new ImageIcon[]{new ImageIcon(new ImageIcon("zdjecia/duchD/duchD1.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/duchD/duchD2.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/duchD/duchD3.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))};
        punktIcon.setImage((new ImageIcon("zdjecia/point.png").getImage().getScaledInstance(szer,wys,Image.SCALE_DEFAULT)));
        superPunktIcon.setImage((new ImageIcon("zdjecia/superPoint.png").getImage().getScaledInstance(szer,wys,Image.SCALE_DEFAULT)));
        piwko.setImage((new ImageIcon("zdjecia/supermoce/alkohol.png").getImage().getScaledInstance(szer,wys,Image.SCALE_DEFAULT)));
        zamrozenie.setImage((new ImageIcon("zdjecia/supermoce/mrozenie.png").getImage().getScaledInstance(szer,wys,Image.SCALE_DEFAULT)));
        speed.setImage((new ImageIcon("zdjecia/supermoce/speed.png").getImage().getScaledInstance(szer,wys,Image.SCALE_DEFAULT)));
        podwojenie.setImage((new ImageIcon("zdjecia/supermoce/podwojenie.png").getImage().getScaledInstance(szer,wys,Image.SCALE_DEFAULT)));
        duchF.setImage((new ImageIcon("zdjecia/duchR/duchF.png").getImage().getScaledInstance(szer,wys,Image.SCALE_DEFAULT)));
        zyciePlus.setImage((new ImageIcon("zdjecia/supermoce/zyciePlus.png").getImage().getScaledInstance(szer,wys,Image.SCALE_DEFAULT)));
    }
    public void wroc() {
        map.get(duchY).get(duchX).setIcon(null);
        duchX = spawnX;
        duchY = spawnY;
        nextX = spawnX;
        nextY = spawnY;
        map.get(duchY).get(duchX).setIcon(duchR[licznik]);
    }
    public void supermocLosowanie(){

        Thread supermoce = new Thread(() -> {
            Random rand = new Random();
            Random losowanieSupermmocy= new Random();
            while (czyDziala) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int czySupermoac = rand.nextInt(4);
                int ktoraSupermoc= losowanieSupermmocy.nextInt(5);
                if (sciany[nextY][nextX] != -1&&sciany[nextY][nextX] != 1&&sciany[nextY][nextX] != 2) {
                    if (czySupermoac == 0) {
                        if (ktoraSupermoc==0) {
                            sciany[nextY][nextX]=3;
                            map.get(nextY).get(nextX).setIcon(piwko);
                        } else if (ktoraSupermoc==1) {
                            sciany[nextY][nextX]=4;
                            map.get(nextY).get(nextX).setIcon(speed);
                        }else if (ktoraSupermoc==2) {
                            sciany[nextY][nextX]=5;
                            map.get(nextY).get(nextX).setIcon(zamrozenie);
                        }
                        else if (ktoraSupermoc==3) {
                            sciany[nextY][nextX]=6;
                            map.get(nextY).get(nextX).setIcon(podwojenie);
                        }else if (ktoraSupermoc==4) {
                            sciany[nextY][nextX]=7;
                            map.get(nextY).get(nextX).setIcon(zyciePlus);
                        }
                    }
                }
            }
        });
        supermoce.start();
    }
    public void kolizjaDuch() {
        if (duchX == pac.getPacmanX() && duchY == pac.getPacmanY()) {
            pac.reduceLife();
        }
    }
    public int getDuchX() {
        return duchX;
    }

    public int getDuchY() {
        return duchY;
    }

    public int getNextX() {
        return nextX;
    }

    public int getNextY() {
        return nextY;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

}
