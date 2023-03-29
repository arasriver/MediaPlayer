import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MP3Player {
    private Clip clip;

    public static void main(String[] args) {
        new MP3Player().run();
    }

    public void run() {
        JFrame frame = new JFrame("Multimedia Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("No file selected.");
        topPanel.add(label);

        JPanel buttonPanel = new JPanel();
        JButton openButton = new JButton("Open File");
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    label.setText("Selected file: " + selectedFile.getName());
                    playAudio(selectedFile);
                }
            }
        });
        buttonPanel.add(openButton);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(topPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        frame.setPreferredSize(new Dimension(400, 100));
        frame.pack();
        frame.setVisible(true);
    }

    public void playAudio(File audioFile) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error playing audio: " + e.getMessage());
        }
    }
}
