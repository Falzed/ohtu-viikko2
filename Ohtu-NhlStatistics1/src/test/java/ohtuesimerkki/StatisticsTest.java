package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {

//    Statistics stats;
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };
    Statistics stats = new Statistics(readerStub);

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    public boolean samaPelaaja(Player player1, Player player2) {
        if (player1.getGoals() != player2.getGoals()
                || player1.getAssists() != player2.getAssists()
                || !player1.getTeam().equals(player2.getTeam())
                || !player1.getName().equals(player2.getName())
                || player1.getPoints() != player2.getPoints()) {
            return false;
        } else {
            return true;
        }
    }

    @Test
    public void loytaaPelaajan() {
        Player semenko = new Player("Semenko", "EDM", 4, 12);
        assertEquals(samaPelaaja(stats.search("Semenko"), semenko), true);
    }

    @Test
    public void eiLoydaPelaajaa() {
        assertEquals(stats.search("null"), null);
    }

    @Test
    public void loytaaJoukkueenPelaajat() {
//        ArrayList players = new ArrayList<Player>();
//        players.add(new Player("Semenko", "EDM", 4, 12));
//        players.add(new Player("Kurri", "EDM", 37, 53));
//        players.add(new Player("Gretzky", "EDM", 35, 89));
//
//        assertEquals(samaPelaaja(stats.team("EDM").get(0), (Player) players.get(0)), true);
//        assertEquals(samaPelaaja(stats.team("EDM").get(1), (Player) players.get(0)), true);
//        assertEquals(samaPelaaja(stats.team("EDM").get(2), (Player) players.get(0)), true);

        assertEquals(samaPelaaja(stats.team("EDM").get(0), new Player("Semenko", "EDM", 4, 12)), true);
        assertEquals(samaPelaaja(stats.team("EDM").get(1), new Player("Kurri", "EDM", 37, 53)), true);
        assertEquals(samaPelaaja(stats.team("EDM").get(2), new Player("Gretzky", "EDM", 35, 89)), true);
    }

    @Test
    public void topScorersJarjestys() {
        List<Player> list;
        list = stats.topScorers(4);
        
        assertEquals(samaPelaaja(list.get(0), new Player("Gretzky", "EDM", 35, 89)), true);
        assertEquals(samaPelaaja(list.get(1), new Player("Lemieux", "PIT", 45, 54)), true);
        assertEquals(samaPelaaja(list.get(2), new Player("Yzerman", "DET", 42, 56)), true);
        assertEquals(samaPelaaja(list.get(3), new Player("Kurri", "EDM", 37, 53)), true);
        assertEquals(samaPelaaja(list.get(4), new Player("Semenko", "EDM", 4, 12)), true);
    }
}
