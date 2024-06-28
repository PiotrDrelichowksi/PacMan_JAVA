import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MapLoader extends JPanel {
        private ArrayList<ArrayList<JLabel>> mapLabels;
        Duch duch;
        PacMan pac;
        int[][] mapascian;
        BufferedImage image;
        ArrayList<Duch> listaDuchow=new ArrayList<>();

    public MapLoader() {

        this.mapLabels=new ArrayList<>();
        }

        public void loadMap(String filePath) {
            try {
                image = ImageIO.read(new File(filePath));
               mapascian=new int[image.getHeight()][image.getWidth()];
                for (int i = 0; i < image.getHeight(); i++) {
                    ArrayList<JLabel> temp = new ArrayList<>();
                    for (int j = 0; j < image.getWidth(); j++) {
                        JLabel p = new JLabel();
                        temp.add(p);
                    }
                    mapLabels.add(temp);
                }
                //legenda:
                //wszystkie supermoce działają przez 10 sekund
                //-1=sciana
                //1=punkt
                //2=superPunkt
                //3-alkohol supermoc(odwroceinie sterownia)
                //4-speed supermoc
                //5-zamrozenie supermoc
                //6-punkty x2
                //7-zycie++

                for (int y = 0; y < image.getHeight(); y++) {
                    for (int x = 0; x < image.getWidth(); x++) {
                        int pixelColor = image.getRGB(x, y);
                        JLabel label = mapLabels.get(y).get(x);

                        if (pixelColor==-16777216) {
                            mapascian[y][x]=-1;
                            label.setIcon(new ImageIcon("zdjecia/bloki.png"));
                        } else if (pixelColor==-65536) {
                            label.setIcon(new ImageIcon("zdjecia/superPoint.png"));
                            mapascian[y][x]=2;
                        }else if (pixelColor==-1) {
                            pac=new PacMan(mapLabels,mapascian,x,y);
                        } else if (pixelColor==-16711936) {
                            duch=new Duch(mapLabels,mapascian,x,y,pac);
                            listaDuchow.add(duch);
                        }else if(pixelColor==-16776961){
                            label.setIcon(new ImageIcon("zdjecia/point.png"));
                            mapascian[y][x]=1;
                        }else {
                            mapascian[y][x]=0;
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


    }
    public void updateMap() {
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int pixelColor = image.getRGB(x, y);

                    JLabel label = mapLabels.get(y).get(x);

                    if (pixelColor == -16777216) {
                        mapascian[y][x] = -1;
                        label.setIcon(new ImageIcon("zdjecia/bloki.png"));
                    } else if (pixelColor == -65536) {
                        label.setIcon(new ImageIcon("zdjecia/superPoint.png"));
                        mapascian[y][x] = 2;
                    }else if(pixelColor==-16776961){
                        label.setIcon(new ImageIcon("zdjecia/point.png"));
                        mapascian[y][x]=1;
                    }else {
                        mapascian[y][x]=0;
                    }
                        pac.wroc();
                    for (Duch d:listaDuchow) {
                        d.wroc();
                    }
                }
            }

    }


    public int[][]getsciany(){
        return mapascian;
    }

    public PacMan getPac() {
        return pac;
    }

    public ArrayList<Duch> getListaDuchow() {
        return listaDuchow;
    }
    public int getWysokosc(){
        return image.getHeight();
    }
    public int getSzerokosc(){
        return image.getWidth();
    }

    public ArrayList<ArrayList<JLabel>> getMapLabels() {
        return mapLabels;
    }
}
