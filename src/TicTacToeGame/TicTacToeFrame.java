package TicTacToeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private JPanel mainPnl = new JPanel();
    public static JPanel gamePnl = new JPanel();
    private JPanel titlePnl = new JPanel();
    private JPanel cmdPnl = new JPanel();
    private JLabel titleLbl;

    private Board currentBoard = new Board();
    public static Game currentGame = new Game();

    JButton clearBtn;
    JButton quitBtn;

     static int row = -1;
     static int col = -1;

    static JButton buttonClicked;

    public TicTacToeFrame(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe!");
        add(mainPnl);

        mainPnl.setLayout(new BorderLayout());

        createTitlePanel();
        mainPnl.add(titlePnl,BorderLayout.NORTH);

        createGamePanel();
        mainPnl.add(gamePnl,BorderLayout.CENTER);

        createCmdPanel();
        mainPnl.add(cmdPnl,BorderLayout.SOUTH);


        pack();
        setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)(.75*screenSize.width),(int)(.75*screenSize.height));
        setLocationRelativeTo(null);
    }

//Panel Methods


    public void createTitlePanel(){
        titleLbl = new JLabel("Tic Tac Toe!");
        titleLbl.setFont(new Font("Impact", Font.BOLD, 48));
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titlePnl.add(titleLbl);
    }
    public void createGamePanel(){
        gamePnl = new JPanel(new GridLayout(3,3));
        currentBoard = new Board();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
               gamePnl.add(currentBoard.getBoard()[row][col]);
                currentBoard.getBoard()[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() != null) {
                            buttonClicked = (JButton) e.getSource();
                            if (buttonClicked.getText().equals(" ")) {
                                buttonClicked.setText(currentGame.getPlayer());
                                currentGame.increaseMoveCount();
                                currentBoard.gamePlayer(currentGame.getMoveCnt(),currentGame.getPlayer());
                            } else{
                                JOptionPane.showMessageDialog(gamePnl,"Whoops-a-daisy! Looks like you've summoned the ghost of tic-tac-toe past with that choice. Try again with something on this plane of existence!","Invalid Move!",JOptionPane.ERROR_MESSAGE);
                            }

                        }
                    }
                });
            }
        }
    }

    public void createCmdPanel(){
        cmdPnl.setLayout(new GridLayout(1,2));
        clearBtn = new JButton("Clear");
        quitBtn = new JButton("Quit");
        clearBtn.setFont(new Font("Impact",Font.PLAIN,20));
        quitBtn.setFont(new Font("Impact",Font.PLAIN,20));
        cmdPnl.add(clearBtn);
        cmdPnl.add(quitBtn);

        clearBtn.addActionListener(e -> {
            currentBoard.clearBoard();
        });
        quitBtn.addActionListener(e -> {
            shutdownSequence();
        });
    }

    public static void shutdownSequence(){
        JOptionPane.showMessageDialog(gamePnl, "Catch you on the flip side, where we'll battle it out again in the realm of Xs and Os. Adios, tic-tac-toe amigo!","Good Bye!",JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

}
