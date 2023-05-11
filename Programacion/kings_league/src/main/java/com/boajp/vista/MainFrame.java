package com.boajp.vista;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() throws HeadlessException {
        setLayout(new BorderLayout());
        add(new Clasificacion().getpClasificacion(), BorderLayout.CENTER);


    }




}
