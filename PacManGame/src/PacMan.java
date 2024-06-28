import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class PacMan implements KeyListener {
    private ArrayList<ArrayList<JLabel>> map;
    private int pacmanX;
    private int pacmanY;
    private int spawnX;
    private int spawnY;
    private Icon pacmanIcon;
    private int[][] sciany;
    int points=0;
    private int nextX;
    private int nextY;
    private int oldX;
    private int oldY;
    private ImageIcon[] pacR;
    private ImageIcon[] pacL;
    private ImageIcon[] pacG;
    private ImageIcon[] pacD;
    private int licznik;
    private int zycie;
    Kierunek kierunek;
    private int supermoc;
    private int pacSpeed;
    private int mnoznikPunktow;
    public PacMan(ArrayList<ArrayList<JLabel>> map,int[][]wallMap,int x,int y) {
        this.map = map;
        this.sciany = wallMap;
        this.pacmanX = x;
        this.pacmanY = y;
        this.spawnX=x;
        this.spawnY=y;
        this.oldX=x;
        this.oldY=y;
        this.licznik=0;
        this.zycie=3;
        this.pacmanIcon = new ImageIcon("zdjecia/pacR1.png");
        this.map.get(pacmanY).get(pacmanX).setIcon(pacmanIcon);
        this.nextX = pacmanX;
        this.nextY = pacmanY;
        supermoc=0;
        pacSpeed=250;
        mnoznikPunktow=1;
        kierunek = Kierunek.PRAWO;
        pacR=new ImageIcon[]{new ImageIcon("zdjecia/pacR/pacR5.png"),new ImageIcon("zdjecia/pacR/pacR4.png"),new ImageIcon("zdjecia/pacR/pacR3.png")
                ,new ImageIcon("zdjecia/pacR/pacR2.png"), new ImageIcon("zdjecia/pacR/pacR1.png"),new ImageIcon("zdjecia/pacR/pacR2.png")
                ,new ImageIcon("zdjecia/pacR/pacR3.png"),new ImageIcon("zdjecia/pacR/pacR4.png")};
        pacL=new ImageIcon[]{new ImageIcon("zdjecia/pacL/pacL5.png"),new ImageIcon("zdjecia/pacL/pacL4.png"),new ImageIcon("zdjecia/pacL/pacL3.png")
                ,new ImageIcon("zdjecia/pacL/pacL2.png"), new ImageIcon("zdjecia/pacL/pacL1.png"),new ImageIcon("zdjecia/pacL/pacL2.png")
                ,new ImageIcon("zdjecia/pacL/pacL3.png"),new ImageIcon("zdjecia/pacL/pacL4.png")};
        pacG=new ImageIcon[]{new ImageIcon("zdjecia/pacG/pacG5.png"),new ImageIcon("zdjecia/pacG/pacG4.png"),new ImageIcon("zdjecia/pacG/pacG3.png")
                ,new ImageIcon("zdjecia/pacG/pacG2.png"), new ImageIcon("zdjecia/pacG/pacG1.png"),new ImageIcon("zdjecia/pacG/pacG2.png")
                ,new ImageIcon("zdjecia/pacG/pacG3.png"),new ImageIcon("zdjecia/pacG/pacG4.png")};
        pacD=new ImageIcon[]{new ImageIcon("zdjecia/pacD/pacD5.png"),new ImageIcon("zdjecia/pacD/pacD4.png"),new ImageIcon("zdjecia/pacD/pacD3.png")
                ,new ImageIcon("zdjecia/pacD/pacD2.png"), new ImageIcon("zdjecia/pacD/pacD1.png"),new ImageIcon("zdjecia/pacD/pacD2.png")
                ,new ImageIcon("zdjecia/pacD/pacD3.png"),new ImageIcon("zdjecia/pacD/pacD4.png")};

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (supermoc!=3) {
            switch (e.getKeyChar()) {
                case 'w':
                    nextY = pacmanY - 1;
                    nextX = pacmanX;
                    kierunek = Kierunek.GORA;
                    break;
                case 's':
                    nextY = pacmanY + 1;
                    nextX = pacmanX;
                    kierunek = Kierunek.DOL;
                    break;
                case 'a':
                    nextY = pacmanY;
                    nextX = pacmanX - 1;
                    kierunek = Kierunek.LEWO;
                    break;
                case 'd':
                    nextY = pacmanY;
                    nextX = pacmanX + 1;
                    kierunek = Kierunek.PRAWO;
                    break;
            }
        }else {
                switch (e.getKeyChar()) {
                    case 's':
                        nextY = pacmanY - 1;
                        nextX = pacmanX;
                        kierunek = Kierunek.GORA;
                        break;
                    case 'w':
                        nextY = pacmanY + 1;
                        nextX = pacmanX;
                        kierunek = Kierunek.DOL;
                        break;
                    case 'd':
                        nextY = pacmanY;
                        nextX = pacmanX - 1;
                        kierunek = Kierunek.LEWO;
                        break;
                    case 'a':
                        nextY = pacmanY;
                        nextX = pacmanX + 1;
                        kierunek = Kierunek.PRAWO;
                        break;

            }
        }
    }
    public void movePacman() {
        synchronized (this) {
            if (sciany[nextY][nextX] != -1) {
                if (sciany[nextY][nextX] == 1) {
                    points+=1*mnoznikPunktow;
                    sciany[nextY][nextX] = 0;
                } else if (sciany[nextY][nextX] == 2) {
                    points += 10*mnoznikPunktow;
                    sciany[nextY][nextX] = 0;
                } else if (sciany[nextY][nextX] == 3) {
                    supermoc = 3;
                    sciany[nextY][nextX] = 0;
                    Thread timer = new Thread(() -> {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        supermoc = 0;
                    });
                    timer.start();
                }else if (sciany[nextY][nextX] == 4) {
                    supermoc = 4;
                    sciany[nextY][nextX] = 0;
                    pacSpeed=100;
                    Thread timer = new Thread(() -> {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        supermoc = 0;
                        pacSpeed=250;
                    });
                    timer.start();
                }else if (sciany[nextY][nextX] == 5) {
                    supermoc = 5;
                    sciany[nextY][nextX] = 0;
                    Thread timer = new Thread(() -> {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        supermoc = 0;
                    });
                    timer.start();
                }else if (sciany[nextY][nextX] == 6) {
                    supermoc = 6;
                    sciany[nextY][nextX] = 0;
                    mnoznikPunktow=2;
                    Thread timer = new Thread(() -> {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        supermoc = 0;
                        mnoznikPunktow=1;
                    });
                    timer.start();
                }else if (sciany[nextY][nextX] == 7) {
                    zycie++;
                    sciany[nextY][nextX] = 0;
                }

                oldX=pacmanX;
                oldY=pacmanY;

                pacmanX = nextX;
                pacmanY = nextY;
            }
        }
    }
    public void pacAnimation(){
        synchronized (this) {
            if (nextX != oldX || nextY != oldY) {
                map.get(oldY).get(oldX).setIcon(null);
            }
            licznik++;
            if (licznik == 8) {
                licznik = 0;
            }
            if (kierunek == Kierunek.PRAWO) {
                map.get(pacmanY).get(pacmanX).setIcon(pacR[licznik]);
            } else if (kierunek == Kierunek.LEWO) {
                map.get(pacmanY).get(pacmanX).setIcon(pacL[licznik]);
            } else if (kierunek == Kierunek.GORA) {
                map.get(pacmanY).get(pacmanX).setIcon(pacG[licznik]);
            } else if (kierunek == Kierunek.DOL) {
                map.get(pacmanY).get(pacmanX).setIcon(pacD[licznik]);
            }
        }
    }


    public void reduceLife() {
            map.get(pacmanY).get(pacmanX).setIcon(null);
            zycie--;
            wroc();
    }

    public void kolizaPac(ArrayList<Duch> duchy) {
        for (Duch duch : duchy) {
            if (pacmanX == duch.getDuchX() && pacmanY == duch.getDuchY()) {
                reduceLife();
            }
        }
    }
    public void wroc() {
        pacmanX = spawnX;
        pacmanY = spawnY;
        nextX = spawnX;
        nextY = spawnY;
        map.get(pacmanY).get(pacmanX).setIcon(pacR[licznik]);
    }
    public void rezisePac(int szer,int wys){
        pacR= new ImageIcon[]{new ImageIcon(new ImageIcon("zdjecia/pacR/pacR5.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacR/pacR4.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacR/pacR3.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacR/pacR2.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacR/pacR1.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacR/pacR2.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacR/pacR3.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacR/pacR4.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))};
        pacL= new ImageIcon[]{new ImageIcon(new ImageIcon("zdjecia/pacL/pacL5.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacL/pacL4.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacL/pacL3.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacL/pacL2.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacL/pacL1.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacL/pacL2.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacL/pacL3.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacL/pacL4.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))};
        pacG= new ImageIcon[]{new ImageIcon(new ImageIcon("zdjecia/pacG/pacG5.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacG/pacG4.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacG/pacG3.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacG/pacG2.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacG/pacG1.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacG/pacG2.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacG/pacG3.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacG/pacG4.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))};
        pacD= new ImageIcon[]{new ImageIcon(new ImageIcon("zdjecia/pacD/pacD5.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacD/pacD4.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                , new ImageIcon(new ImageIcon("zdjecia/pacD/pacD3.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                , new ImageIcon(new ImageIcon("zdjecia/pacD/pacD2.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                , new ImageIcon(new ImageIcon("zdjecia/pacD/pacD1.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacD/pacD2.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacD/pacD3.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))
                ,new ImageIcon(new ImageIcon("zdjecia/pacD/pacD4.png").getImage().getScaledInstance(szer, wys, Image.SCALE_DEFAULT))};
    }




    @Override
    public void keyReleased(KeyEvent e) {

    }

    public int getPoints() {
        return points;
    }

    public int getZycie(){
        return zycie;
    }
    public int getPacmanX(){
        return pacmanX;
    }

    public int getPacmanY() {
        return pacmanY;
    }

    public int getPacSpeed() {
        return pacSpeed;
    }

    public int getSupermoc() {
        return supermoc;
    }
}