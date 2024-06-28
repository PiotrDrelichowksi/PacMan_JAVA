import java.io.Serializable;

public class Player implements Serializable, Comparable<Player>{
    private String name;
    private int score;
    public Player(String name,int score){
        this.name=name;
        this.score=score;
    }
    @Override
    public String toString() {
        return name+" "+score;
    }
    @Override
    public int compareTo(Player player){
        return Integer.compare(player.score, this.score);
    }
}

