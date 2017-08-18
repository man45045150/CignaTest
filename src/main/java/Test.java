
import java.io.*;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws Exception {
        String dataPath = System.getProperty("user.dir") + "\\src\\main\\java\\data.txt";
        ArrayList<String> games = importData(dataPath);
        for (int i = 0;i<games.size();i++){
            printOutputGame(games.get(i));
        }
        //printOutputGame(games.get(2));
    }
    private static ArrayList<String> importData(String dataPath){
        ArrayList<String> games = new ArrayList<String>();
        File file = new File(dataPath);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            try {
                while((st=br.readLine()) != null){
                    games.add(st);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error read file");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error open file");
        }
        return games;
    }


    static  String[] states = {"0","15","30","40","WIN"};
    private static void printOutputGame(String game){
        int aScorePoint = 0,bScorePoint = 0;

        boolean isMatchPoint = false;
        String[] winners = game.split(" ");
        //display title game
        System.out.println(winners[0]);
        for(int i = 1 ;i< winners.length;i++){
            if(winners[i].equals("A"))
                aScorePoint++;
            else
                bScorePoint++;

            if (!isMatchPoint & aScorePoint==(states.length-2) & aScorePoint == bScorePoint){
                isMatchPoint = true;
                System.out.println(winners[i]+":"+states[aScorePoint]);
                System.out.println("DUCE");
                continue;
            }

            publishScore(winners[i],aScorePoint,bScorePoint,isMatchPoint);
        }
    }
    private static void publishScore(String winner,int aScore,int bScore,boolean isMatchPoint ){
        int winnerScore = winner.equals("A")?aScore : bScore;
        int loserScore = winner.equals("A")?bScore:aScore;
        if(!isMatchPoint){
            System.out.println(winner+":"+states[winnerScore]);
        }else{
            if(winnerScore - loserScore == 1)
                System.out.println(winner+":ADV");
            else if(winnerScore == loserScore){
                System.out.println(winner+":ADV");
                System.out.println("DUCE");
            }else
                System.out.println(winner+":WIN");

        }

    }
}
