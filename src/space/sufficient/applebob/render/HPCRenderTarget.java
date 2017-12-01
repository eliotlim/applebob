package space.sufficient.applebob.render;

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
        cPanel.add(drawTextArea);

            //pane.setText("1uh2qo3uehqo3uehaqwourhawoefbnawefabwoefuabwoef");
            //pane.getStyledDocument().insertString(0,"1920139q934hrq3o4uhqwerhfqwe\nfuoqefhqowuefhqwouefhqwoeufhqwef\nqwheofuqhweofuqhwefouqhwefouqw23", pane.addStyle("cikor styke", null));
        f.pack();
        f.setVisible(true);
        r = new Random(1);
    }

    @Override
    public void draw(World w) {
        int we = w.getWidth();
        int hi = w.getHeight();
        int x1 = r.nextInt(we);
        int y1 = r.nextInt(hi);
//        pane.setText(Integer.toString(x1));
        System.out.println("width: "+we+",height: "+hi+",x:"+x1+",y:"+y1);
        if (w.getCell(x1,y1)==Tile.FLOOR){
            w.setCell(x1,y1,Tile.VOID);
        } else if (w.getCell(x1,y1)==Tile.VOID){
            w.setCell(x1,y1,Tile.WALL);
        } else {
            w.setCell(x1, y1, Tile.FLOOR);
        }
        StringBuilder line = new StringBuilder();
        for (int y = w.getCameraY() - mViewportHeight/2; y < w.getCameraY() + mViewportHeight/2; y++) {
            for (int x = w.getCameraX() - mViewportWidth/2; x < w.getCameraX() + mViewportWidth/2; x++) {
                line.append(w.getCell(x, y));
            }
            line.append("\n");
        }
//        pane.setText(line.toString());
        drawTextArea.setText(line.toString());
        long frameDuration = (System.currentTimeMillis() - mFpsTime);
        System.out.println("Took " + frameDuration + "ms - drawing at " + (1000L / frameDuration) );
        mFpsTime = System.currentTimeMillis();
    }

}


