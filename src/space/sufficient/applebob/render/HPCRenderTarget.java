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
    Random r;
    JTextPane pane;

    public HPCRenderTarget(int width, int height) {
        this.mViewportWidth = width;
        this.mViewportHeight = height;
        JFrame f = new JFrame("HPC");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //f.setPreferredSize(new Dimension(1280,800));
//        JPanel cPanel = new JPanel(new BorderLayout());
        JPanel cPanel = new JPanel();
        f.getContentPane().add(cPanel);
        drawTextArea = new JTextArea("",height,width);

//        pane = new JTextPane();
//        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 14);
//        pane.setFont(font);
//        cPanel.add(pane, BorderLayout.CENTER);

        drawTextArea.setFont(new Font("monospaced", Font.PLAIN, 12));
        drawTextArea.setEditable(false);
        cPanel.add(drawTextArea);

            //pane.setText("1uh2qo3uehqo3uehaqwourhawoefbnawefabwoefuabwoef");
            //pane.getStyledDocument().insertString(0,"1920139q934hrq3o4uhqwerhfqwe\nfuoqefhqowuefhqwouefhqwoeufhqwef\nqwheofuqhweofuqhwefouqhwefouqw23", pane.addStyle("cikor styke", null));
        f.pack();
        f.setVisible(true);
        r = new Random(1);
    }

    @Override
    public void draw(World w) {
        w.onTick();

        StringBuilder line = new StringBuilder();
        for (int y = w.getCameraY() - mViewportHeight/2; y < w.getCameraY() + mViewportHeight/2; y++) {
            for (int x = w.getCameraX() - mViewportWidth/2; x < w.getCameraX() + mViewportWidth/2; x++) {
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


