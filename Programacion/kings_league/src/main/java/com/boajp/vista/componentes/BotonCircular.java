package com.boajp.vista.componentes;


import com.boajp.utilidades.EstilosDeVistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class BotonCircular extends JButton {

    private int dimension;
    private Dimension d;
    private Color defaultColor = EstilosDeVistas.COLOR_DE_FONDO;
    private Color hoverColor = new Color(10, 0, 0, 128);
    private Color pressedColor = new Color(10, 0, 0, 160);
    private Color disabledColor = EstilosDeVistas.COLOR_DE_FONDO;
    private int borderWidth = 1;
    private Color borderColor = Color.BLACK;

    public BotonCircular(String texto, int dimension) {
        super(texto);
        this.dimension = dimension;
        Dimension d = new Dimension(dimension, dimension);
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        int width = getWidth();
        int height = getHeight();

        ButtonModel model = getModel();
        if (model.isEnabled()) {
            if (model.isPressed()) {
                g2d.setColor(pressedColor);
            } else if (model.isRollover()) {
                g2d.setColor(hoverColor);
            } else {
                g2d.setColor(defaultColor);
            }
        } else {
            g2d.setColor(disabledColor);
        }

        Shape roundedRect = new RoundRectangle2D.Double(0, 0, dimension, dimension, dimension, dimension);
        g2d.setClip(roundedRect);
        g2d.fillRect(0, 0, width, height);

        paintText(g2d); // Custom text painting
        g2d.dispose();
    }

    protected void paintText(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        FontMetrics fontMetrics = g2d.getFontMetrics();
        Rectangle textBounds = fontMetrics.getStringBounds(getText(), g2d).getBounds();

        int x = (getWidth() - textBounds.width) / 2;
        int y = (getHeight() - textBounds.height) / 2 + fontMetrics.getAscent();

        g2d.setColor(getForeground());
        g2d.drawString(getText(), x, y);

        g2d.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderWidth));

        int x = borderWidth / 2;
        int y = borderWidth / 2;
        int width = getWidth() - borderWidth;
        int height = getHeight() - borderWidth;

        Shape roundedRect = new RoundRectangle2D.Double(x, y, width, height, dimension, dimension);
        g2d.draw(roundedRect);

        g2d.dispose();
    }

}