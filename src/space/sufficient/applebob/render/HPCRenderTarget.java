package space.sufficient.applebob.render;

import space.sufficient.applebob.input.InputManager;
import space.sufficient.applebob.world.Tile;
import space.sufficient.applebob.world.World;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.util.Random;

public class HPCRenderTarget extends RenderTarget {

    private int mViewportHeight, mViewportWidth;
    private long mFpsTime;
    JTextArea drawTextArea;
    JSlider slider;
    JLabel score1, score2;

    public HPCRenderTarget(int width, int height) {
        this.mViewportWidth = width;
        this.mViewportHeight = height;
        JFrame f = new JFrame("HPC");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));

        JPanel cPanel = new JPanel();
        cPanel.setLayout(new BoxLayout(cPanel, BoxLayout.Y_AXIS));
        f.getContentPane().add(cPanel, BorderLayout.PAGE_START);

        drawTextArea = new JTextArea("",height,width);
        drawTextArea.setFont(new Font("monospaced", Font.PLAIN, 12));
        drawTextArea.setBackground(new Color(0,0,0,255));
        drawTextArea.setForeground(new Color(255,255,255,255));
        drawTextArea.setEditable(false);

        cPanel.add(drawTextArea);


        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(1,2));
        f.getContentPane().add(scorePanel,BorderLayout.CENTER);
        for (int i =1; i<3; i++){
            JPanel tPanel = new JPanel();
            tPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            tPanel.setLayout(new BoxLayout(tPanel,BoxLayout.PAGE_AXIS));

            JLabel playerLabel = new JLabel("Player "+i, JLabel.CENTER);
            playerLabel.setPreferredSize(new Dimension(150,70));
            playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            tPanel.add(playerLabel, Component.CENTER_ALIGNMENT);
            scorePanel.add(tPanel);

            JLabel scoreLabel = new JLabel("0", JLabel.CENTER);
            scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            scoreLabel.setPreferredSize(new Dimension(150,70));
            tPanel.add(scoreLabel, Component.CENTER_ALIGNMENT);

            if (i==1) {
                score1 = scoreLabel;
            } else {
                score2 = scoreLabel;
            }
        }

        JPanel miscPanel = new JPanel();
        miscPanel.setLayout(new BoxLayout(miscPanel, BoxLayout.PAGE_AXIS));
        f.getContentPane().add(miscPanel,BorderLayout.PAGE_END);

        JLabel sliderLabel = new JLabel("Retro --->", JLabel.CENTER);
        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        miscPanel.add(sliderLabel);
        slider = new JSlider(0,155,0);
        slider.addChangeListener(e -> sliderChanged());
        miscPanel.add(slider);

        f.pack();
        f.setVisible(true);
    }

    public void sliderChanged(){
        drawTextArea.setBackground(new Color(0,0,0,255-slider.getValue()));
        drawTextArea.setForeground(new Color(255,255,255,255-slider.getValue()));
    }

    @Override
    public void draw(World w) {
        w.onTick();
        score1.setText(Integer.toString(w.score1));
        score2.setText(Integer.toString(w.score2));
        StringBuilder line = new StringBuilder();
        for (int y = w.getCameraY() - mViewportHeight/2; y < w.getCameraY() + mViewportHeight/2; y++) {
            for (int x = w.getCameraX() - mViewportWidth / 2; x < w.getCameraX() + mViewportWidth / 2; x++) {
                line.append(w.renderCell(x, y));
            }
            line.append("\n");
        }
        drawTextArea.setText(line.toString());
        long frameDuration = (System.currentTimeMillis() - mFpsTime);
        // System.out.println("Took " + frameDuration + "ms - drawing at " + (1000L / frameDuration) );
        mFpsTime = System.currentTimeMillis();
    }

    @Override
    public void setInputManager(InputManager inputManager) {
        drawTextArea.addKeyListener(inputManager);
    }

}


