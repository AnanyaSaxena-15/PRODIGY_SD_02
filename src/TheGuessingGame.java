import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class TheGuessingGame extends JFrame implements ActionListener {
    private JTextField guessField;
    private JButton submitButton, revealButton, playAgainButton;
    private JLabel messageLabel, attemptLabel;

    private int targetNumber;
    private int attempts;
    private Random rand;

    public TheGuessingGame() {
        setTitle("Guessing Game");
        setSize(420, 250);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245)); 


        JLabel instructionLabel = new JLabel("🎯 Guess a number between 1 and 100:");
        instructionLabel.setBounds(30, 20, 300, 20);
        add(instructionLabel);

        guessField = new JTextField();
        guessField.setBounds(30, 70, 100, 25);
        add(guessField);

        submitButton = new JButton("Check");
        submitButton.setBounds(150, 70, 100, 25);
        submitButton.setBackground(new Color(0, 120, 215));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);
        add(submitButton);

        revealButton = new JButton("Show Answer");
        revealButton.setBounds(260, 70, 120, 25);
        revealButton.setBackground(new Color(220, 53, 69));
        revealButton.setForeground(Color.WHITE);
        revealButton.addActionListener(e -> {
            messageLabel.setText("🤐 Answer was: " + targetNumber);
        });
        add(revealButton);

        messageLabel = new JLabel("");
        messageLabel.setBounds(30, 90, 350, 20);
        add(messageLabel);

        attemptLabel = new JLabel("Attempts: 0");
        attemptLabel.setBounds(30, 115, 200, 20);
        add(attemptLabel);

        playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(150, 150, 120, 30);
        playAgainButton.setBackground(new Color(40, 167, 69));
        playAgainButton.setForeground(Color.WHITE);
        playAgainButton.setVisible(false);
        playAgainButton.addActionListener(e -> resetGame());
        add(playAgainButton);

        rand = new Random();
        generateNewNumber();
    }

    private void generateNewNumber() {
        targetNumber = rand.nextInt(100) + 1;
        attempts = 0;
        attemptLabel.setText("Attempts: 0");
        messageLabel.setText("");
        guessField.setText("");
        submitButton.setEnabled(true);
        playAgainButton.setVisible(false);
    }

    private void resetGame() {
        generateNewNumber();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = guessField.getText();

        try {
            int guess = Integer.parseInt(input);
            attempts++;

            if (guess < targetNumber) {
                messageLabel.setText("Too low! Try again.");
            } else if (guess > targetNumber) {
                messageLabel.setText("Too high! Try again.");
            } else {
                messageLabel.setText("🎉 Correct! You guessed it in " + attempts + " attempts.");
                submitButton.setEnabled(false);
                playAgainButton.setVisible(true);
            }

            attemptLabel.setText("Attempts: " + attempts);
            guessField.setText("");

        } catch (NumberFormatException ex) {
            messageLabel.setText("❌ Enter a valid number.");
        }
    }

    public static void main(String[] args) {
        new TheGuessingGame().setVisible(true);
    }
}

