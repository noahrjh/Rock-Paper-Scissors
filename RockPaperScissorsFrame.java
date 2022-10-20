import com.sun.javafx.scene.paint.GradientUtils;
import sun.security.jgss.krb5.ServiceCreds;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    JFrame frame;

    JPanel mainPnl;
    JPanel btnPnl;
    JPanel statsPnl;
    JPanel resultsPnl;

    JButton quitBtn;
    JButton rockBtn;
    JButton paperBtn;
    JButton scissorsBtn;

    ImageIcon rockIcon;
    ImageIcon paperIcon;
    ImageIcon scissorsIcon;

    JLabel playerWinsLbl;
    JLabel computerWinsLbl;
    JLabel tieLbl;

    JTextField playerWinsFld;
    JTextField computerWinsFld;
    JTextField tieFld;

    JTextArea resultsArea;

    JScrollPane scrollPane;

    String computerMove = "";

    public int playerWins = 0;
    public int computerWins = 0;
    public int ties = 0;
    public int gamesPlayed = 0;

    public RockPaperScissorsFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setTitle("Rock Paper Scissors Game");
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createBtnPnl();
        createStatsPnl();
        createResultsPnl();

        mainPnl.add(statsPnl, BorderLayout.NORTH);
        mainPnl.add(resultsArea, BorderLayout.CENTER);
        mainPnl.add(btnPnl, BorderLayout.SOUTH);
        add(mainPnl);
        setVisible(true);
    }

    private void createStatsPnl() {
        statsPnl = new JPanel();
        statsPnl.setLayout(new GridLayout(2, 3, 25, 25));
        statsPnl.setBorder(new TitledBorder(new EtchedBorder(), "Stats Panel"));

        playerWinsLbl = new JLabel("Player Wins", SwingConstants.CENTER);
        playerWinsLbl.setFont(new Font("Serif", Font.PLAIN, 24));

        computerWinsLbl = new JLabel("Computer Wins", SwingConstants.CENTER);
        computerWinsLbl.setFont(new Font("Serif", Font.PLAIN, 24));

        tieLbl = new JLabel("Ties", SwingConstants.CENTER);
        tieLbl.setFont(new Font("Serif", Font.PLAIN, 24));

        playerWinsFld = new JTextField("0");
        playerWinsFld.setHorizontalAlignment(JTextField.CENTER);
        playerWinsFld.setFont(new Font("Serif", Font.PLAIN, 20));

        computerWinsFld = new JTextField("0");
        computerWinsFld.setHorizontalAlignment(JTextField.CENTER);
        computerWinsFld.setFont(new Font("Serif", Font.PLAIN, 20));

        tieFld = new JTextField("0");
        tieFld.setHorizontalAlignment(JTextField.CENTER);
        tieFld.setFont(new Font("Serif", Font.PLAIN, 20));

        playerWinsFld.setEditable(false);
        computerWinsFld.setEditable(false);
        tieFld.setEditable(false);

        statsPnl.add(playerWinsLbl);
        statsPnl.add(computerWinsLbl);
        statsPnl.add(tieLbl);
        statsPnl.add(playerWinsFld);
        statsPnl.add(computerWinsFld);
        statsPnl.add(tieFld);
    }

    private void createResultsPnl() {
        resultsPnl = new JPanel();
        resultsPnl.setLayout(new GridLayout(1, 1));
        resultsPnl.setBorder(new TitledBorder(new EtchedBorder(), "Results Panel"));
        resultsPnl.setSize(600, 600);
        resultsArea = new JTextArea();
        scrollPane = new JScrollPane(resultsArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setSize(350, 500);
        resultsPnl.add(scrollPane);
    }

    private void createBtnPnl() {
        btnPnl = new JPanel();
        btnPnl.setLayout(new GridLayout());
        btnPnl.setBorder(new TitledBorder(new EtchedBorder(), "Button Panel"));

        rockIcon = new ImageIcon(new ImageIcon("src/Rock.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        paperIcon = new ImageIcon(new ImageIcon("src/Paper.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        scissorsIcon = new ImageIcon(new ImageIcon("src/Scissors.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        rockBtn = new JButton(rockIcon);
        rockBtn.addActionListener((ActionEvent e) -> {
            getMove("Rock");
        });
        paperBtn = new JButton(paperIcon);
        paperBtn.addActionListener((ActionEvent e) -> {
            getMove("Paper");
        });
        scissorsBtn = new JButton(scissorsIcon);
        scissorsBtn.addActionListener((ActionEvent e) -> {
            getMove("Scissors");
        });

        btnPnl.add(rockBtn);
        btnPnl.add(paperBtn);
        btnPnl.add(scissorsBtn);
        btnPnl.add(quitBtn);
    }

    private void getMove(String pMove) {
        Random random = new Random();
        int index = 0;
        ArrayList<String> moves = new ArrayList<>();
        moves.add("Rock");
        moves.add("Paper");
        moves.add("Scissors");

        index = random.nextInt(3);
        computerMove = moves.get(index);
        result(pMove, computerMove);
    }

    private void result(String pMove, String cMove) {
        gamesPlayed++;
        int result = 0;
        ArrayList<String> outcome = new ArrayList<>();
        outcome.add("There is a tie!");
        outcome.add("Player Wins!");
        outcome.add("Computer Wins!");

        if (pMove == "Rock" && cMove == "Rock") {
            result = 0;
        } else if (pMove == "Rock" && cMove == "Paper") {
            result = 2;
        } else if (pMove == "Rock" && cMove == "Scissors") {
            result = 1;
        } else if (pMove == "Paper" && cMove == "Paper") {
            result = 0;
        } else if (pMove == "Paper" && cMove == "Scissors") {
            result = 2;
        } else if (pMove == "Paper" && cMove == "Rock") {
            result = 1;
        } else if (pMove == "Scissors" && cMove == "Scissors") {
            result = 0;
        } else if (pMove == "Scissors" && cMove == "Rock") {
            result = 2;
        } else if (pMove == "Scissors" && cMove == "Paper") {
            result = 1;
        }

        resultsArea.append("Game # " + gamesPlayed + "\t Player Move: " + pMove + "\t Computer Move: " + cMove + "\t Result: " + outcome.get(result) + "\n");
        updateCounters(result);
    }

    private void updateCounters(int r) {
        switch (r) {
            case 0:
                ties += 1;
                tieFld.setText(Integer.toString(ties));
                break;
            case 1:
                playerWins += 1;
                playerWinsFld.setText(Integer.toString(playerWins));
                break;
            case 2:
                computerWins += 1;
                computerWinsFld.setText(Integer.toString(computerWins));
                break;
        }
    }
}
