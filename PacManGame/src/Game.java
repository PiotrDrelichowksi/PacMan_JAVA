import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class Game extends JFrame {
    JPanel panel;
    JPanel panel1;
    JPanel rozgrywka;
    Button mainMenu;
    Czasomierz czas;
    JLabel czasLabel;
    JLabel punkty;
    JLabel zycia;
    boolean czyDziala;
    PacMan pac;
    ArrayList<ArrayList<JLabel>> labelki;
    String mapPath;
    DuchiPac duchiPac;
    ArrayList<Duch> listaDuchow;
    private int poprzedniaWyskoksc;
    private int poprzedniaSzerokosc;
    int szrokoscIkonki;
    int wysokoscIkonki;
    MapLoader mapLoader;



    public Game(String mapPath){
        super("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mapPath=mapPath;
        mapLoader = new MapLoader();
        mapLoader.loadMap(mapPath);
        czyDziala=true;
        labelki=mapLoader.getMapLabels();
        setSize(mapLoader.getSzerokosc()*35, mapLoader.getWysokosc()*35+100);
        poprzedniaSzerokosc=mapLoader.getSzerokosc()*35;
        poprzedniaWyskoksc=mapLoader.getWysokosc()*35+100;
        panel=new JPanel(new BorderLayout());
        rozgrywka=new JPanel(new GridLayout(mapLoader.getWysokosc(),mapLoader.getSzerokosc()));
        panel1=new JPanel(new GridLayout(1,3));
        mainMenu=new Button("Main Menu");
        mainMenu.setFont(new Font(Font.DIALOG,Font.BOLD,15));
        czasLabel=new JLabel();
        punkty=new JLabel();
        zycia=new JLabel();
        Thread czasowy=new Thread(()->{
            czas=new Czasomierz();
            czas.start();
            while (czyDziala){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
                czasLabel.setText("Minuty: "+czas.getMinuty()+"    Sekundy: "+czas.getSekundy());
            }
        });
        czasowy.start();
        czasLabel.setForeground(new Color(251,243,54));
        czasLabel.setFont(new Font(Font.DIALOG,3,22));
        punkty.setForeground(new Color(251,243,54));
        punkty.setFont(new Font(Font.DIALOG,3,22));
        zycia.setForeground(new Color(251,243,54));
        zycia.setFont(new Font(Font.DIALOG,3,22));
        pac=mapLoader.getPac();
        listaDuchow=mapLoader.getListaDuchow();
        duchiPac=new DuchiPac(pac,listaDuchow);
        duchiPac.startGame();


        addKeyListener(pac);
        Thread pubktowy=new Thread(()->{
            while (czyDziala){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                punkty.setText("Punkty: "+pac.getPoints());
                zycia.setText("Zycia: "+pac.getZycie());
            }


        });
        pubktowy.start();

        Thread czyPacZyje=new Thread(()->{
            while (czyDziala){
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(pac.getZycie()<=0){
                    SwingUtilities.invokeLater(()->new ScoreSaver(pac.getPoints()));
                    czyDziala=false;
                    duchiPac.setCzyDziala(false);
                    this.dispose();
                    duchiPac.setCzyDziala(false);

                }
            }


        });
        czyPacZyje.start();

        for (int y = 0; y < labelki.size(); y++) {
            for (int x = 0; x < labelki.get(y).size(); x++) {
                if (mapLoader.getsciany()[y][x]==-1) {
                    labelki.get(y).get(x).setIcon(new ImageIcon("zdjecia/bloki.png"));
                }
                rozgrywka.add(labelki.get(y).get(x));
            }
        }

        Thread resize=new Thread(()->{
            while (czyDziala){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                szrokoscIkonki = getWidth()/ mapLoader.getSzerokosc();
                wysokoscIkonki = getHeight()/ mapLoader.getWysokosc();
                    skalowanie(szrokoscIkonki,wysokoscIkonki);
            }


        });
        resize.start();


        Thread pubktyNaMapie=new Thread(()->{
            while (czyDziala) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                int liczeniePubktow = 0;
                for (int i = 0; i < mapLoader.getWysokosc(); i++) {
                    for (int j = 0; j < mapLoader.getSzerokosc(); j++) {
                        if (mapLoader.getsciany()[i][j] == 1 || mapLoader.getsciany()[i][j] == 2) {
                            liczeniePubktow++;
                        }
                    }
                }
                if (liczeniePubktow == 0) {
                    mapLoader.updateMap();
                }
            }

        });
        pubktyNaMapie.start();
        /*addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e){
                skalowanie(getWidth()/ mapLoader.getSzerokosc(),getHeight()/ mapLoader.getWysokosc());
            }
        });
        pubktyNaMapie.start();*/

        rozgrywka.setBackground(Color.BLACK);
        panel1.add(czasLabel);
        panel1.add(punkty);
        panel1.add(zycia);
        panel.add(panel1,BorderLayout.PAGE_START);
        panel.add(mainMenu,BorderLayout.PAGE_END);
        panel1.setBackground(new Color(130,38,38));

        add(panel,BorderLayout.PAGE_START);
        add(rozgrywka,BorderLayout.CENTER);

        mainMenu.addActionListener(e -> {
            SwingUtilities.invokeLater(()->new ScoreSaver(pac.getPoints()));
            czyDziala=false;
            duchiPac.setCzyDziala(false);
            this.dispose();
            duchiPac.setCzyDziala(false);
        });

        setVisible(true);
        setLocationRelativeTo(null);
        setFocusable(true);
        requestFocusInWindow();



    }
    public void skalowanie(int szrokoscIkonki, int wysokoscIkonki) {
        if (poprzedniaSzerokosc != getWidth() || poprzedniaWyskoksc != getHeight()) {
            for (int y = 0; y < labelki.size(); y++) {
                for (int x = 0; x < labelki.get(y).size(); x++) {
                    if (mapLoader.getsciany()[y][x] == -1) {
                        labelki.get(y).get(x).setIcon(new ImageIcon((new ImageIcon("zdjecia/bloki.png")).getImage().getScaledInstance(szrokoscIkonki, wysokoscIkonki, Image.SCALE_DEFAULT)));
                    } else if (mapLoader.getsciany()[y][x] == 1) {
                        labelki.get(y).get(x).setIcon(new ImageIcon((new ImageIcon("zdjecia/point.png")).getImage().getScaledInstance(szrokoscIkonki, wysokoscIkonki, Image.SCALE_DEFAULT)));
                    } else if (mapLoader.getsciany()[y][x] == 2) {
                        labelki.get(y).get(x).setIcon(new ImageIcon((new ImageIcon("zdjecia/superPoint.png")).getImage().getScaledInstance(szrokoscIkonki, wysokoscIkonki, Image.SCALE_DEFAULT)));
                    } else if (mapLoader.getsciany()[y][x] == 3) {
                        labelki.get(y).get(x).setIcon(new ImageIcon((new ImageIcon("zdjecia/supermoce/alkohol.png")).getImage().getScaledInstance(szrokoscIkonki, wysokoscIkonki, Image.SCALE_DEFAULT)));
                    }else if (mapLoader.getsciany()[y][x] == 4) {
                        labelki.get(y).get(x).setIcon(new ImageIcon((new ImageIcon("zdjecia/supermoce/speed.png")).getImage().getScaledInstance(szrokoscIkonki, wysokoscIkonki, Image.SCALE_DEFAULT)));
                    }else if (mapLoader.getsciany()[y][x] == 5) {
                        labelki.get(y).get(x).setIcon(new ImageIcon((new ImageIcon("zdjecia/supermoce/mrozenie.png")).getImage().getScaledInstance(szrokoscIkonki, wysokoscIkonki, Image.SCALE_DEFAULT)));
                    }else if (mapLoader.getsciany()[y][x] == 6) {
                        labelki.get(y).get(x).setIcon(new ImageIcon((new ImageIcon("zdjecia/supermoce/podwojenie.png")).getImage().getScaledInstance(szrokoscIkonki, wysokoscIkonki, Image.SCALE_DEFAULT)));
                    }else if (mapLoader.getsciany()[y][x] == 6) {
                        labelki.get(y).get(x).setIcon(new ImageIcon((new ImageIcon("zdjecia/supermoce/zyciePlus.png")).getImage().getScaledInstance(szrokoscIkonki, wysokoscIkonki, Image.SCALE_DEFAULT)));
                    }
                }
            }
            pac.rezisePac(szrokoscIkonki, wysokoscIkonki);
            for (Duch d : listaDuchow) {
                d.reziseDuch(szrokoscIkonki, wysokoscIkonki);
            }
            poprzedniaWyskoksc = getHeight();
            poprzedniaSzerokosc = getWidth();
       }
    }



    public ArrayList<ArrayList<JLabel>> getLabelki() {
        return labelki;
    }
}
