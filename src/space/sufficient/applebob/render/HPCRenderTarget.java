package space.sufficient.applebob.render;

import space.sufficient.applebob.input.InputManager;
import space.sufficient.applebob.world.Tile;
import space.sufficient.applebob.world.World;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.util.Random;

public class HPCRenderTarget extends RenderTarget {

    private int mViewportHeight, mViewportWidth;
    private long mFpsTime;
    JTextArea drawTextArea;
    JSlider slider;

    public HPCRenderTarget(int width, int height) {
        this.mViewportWidth = width;
        this.mViewportHeight = height;
        JFrame f = new JFrame("HPC");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel cPanel = new JPanel();
        cPanel.setLayout(new BoxLayout(cPanel, BoxLayout.PAGE_AXIS));
        f.getContentPane().add(cPanel);

        drawTextArea = new JTextArea("",height,width);
        drawTextArea.setFont(new Font("monospaced", Font.PLAIN, 12));
        drawTextArea.setBackground(new Color(0,0,0,255));
        drawTextArea.setForeground(new Color(255,255,255,255));
        drawTextArea.setEditable(false);

        cPanel.add(drawTextArea);
        JLabel sliderLabel = new JLabel("Retro --->", JLabel.CENTER);
        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cPanel.add(sliderLabel);
        slider = new JSlider(0,155,0);
        slider.addChangeListener(e -> sliderChanged());
        cPanel.add(slider);

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

        StringBuilder line = new StringBuilder();
        for (int y = w.getCameraY() - mViewportHeight/2; y < w.getCameraY() + mViewportHeight/2; y++) {
            for (int x = w.getCameraX() - mViewportWidth / 2; x < w.getCameraX() + mViewportWidth / 2; x++) {
                line.append(w.renderCell(x, y));
            }
            line.append("\n");
        }
        drawTextArea.setText(line.toString());
        long frameDuration = (System.currentTimeMillis() - mFpsTime);
        System.out.println("Took " + frameDuration + "ms - drawing at " + (1000L / frameDuration) );
        mFpsTime = System.currentTimeMillis();
    }

    @Override
    public void setInputManager(InputManager inputManager) {
        drawTextArea.addKeyListener(inputManager);
    }

}


