package TicTacToeGame;

public class Game {
    private String player;
    private int moveCnt;


    public Game() {
        player = "X";
        moveCnt = 0;
    }

    public void increaseMoveCount(){
        moveCnt += 1;
    }


    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getMoveCnt() {
        return moveCnt;
    }

    public void setMoveCnt(int moveCnt) {
        this.moveCnt = moveCnt;
    }
}
