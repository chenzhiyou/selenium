package headFirstJava.Seconds;

public class GameLauncher {
    public static void main(String[] args){
        GuessGame guessGame = new GuessGame();
        System.out.println("---------------");
        guessGame.startGame();
    }
}
