/*
 * Copyright 2017 José A. Pacheco Ondoño - joanpaon@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.japo.java.forms;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import org.japo.java.events.CEM;
import org.japo.java.libraries.UtilesSwing;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class GUI extends JFrame {

    // Propiedades App
    public static final String PRP_LOOK_AND_FEEL = "look_and_feel";
    public static final String PRP_FAVICON = "favicon";

    // Valores por Defecto
    public static final String DEF_LOOK_AND_FEEL = UtilesSwing.LNF_NIMBUS;
    public static final String DEF_FAVICON = "img/favicon.png";

    // Referencias
    private Properties prp;
    private JLabel lblRotulo;
    private JSlider sldTalla;
    private JSpinner spnTalla;

    // Constructor
    public GUI(Properties prp) {
        // Inicialización Anterior
        initBefore(prp);

        // Creación Interfaz
        initComponents();

        // Inicializacion Posterior
        initAfter();
    }

    // Construcción del IGU
    private void initComponents() {
        // Etiqueta de prueba
        lblRotulo = new JLabel();
        lblRotulo.setFont(new Font("Georgia", Font.PLAIN, 40));
        lblRotulo.setText("Érase una vez Java");
        lblRotulo.setHorizontalAlignment(JLabel.CENTER);
        lblRotulo.setBorder(new BevelBorder(BevelBorder.LOWERED));
        lblRotulo.setOpaque(true);
        lblRotulo.setBackground(Color.WHITE);

        // Selector de talla - Deslizador
        sldTalla = new JSlider();
        sldTalla.setMinimum(0);
        sldTalla.setMaximum(100);
        sldTalla.setValue(lblRotulo.getFont().getSize());
        sldTalla.setFont(new Font("Cambria", Font.PLAIN, 20));
        sldTalla.setPaintLabels(true);
        sldTalla.setPaintTicks(true);
        sldTalla.setMinorTickSpacing(5);
        sldTalla.setMajorTickSpacing(25);
        sldTalla.addChangeListener(new CEM(this));

        // Cambiador de talla - Modelo
        SpinnerNumberModel modelo = new SpinnerNumberModel();
        modelo.setMinimum(0);
        modelo.setMaximum(100);
        modelo.setValue(lblRotulo.getFont().getSize());

        // Selector de talla - Cambiador
        spnTalla = new JSpinner(modelo);
        spnTalla.setFont(new Font("Cambria", Font.PLAIN, 20));
        spnTalla.addChangeListener(new CEM(this));

        // Panel de Control
        JPanel pnlControl = new JPanel();
        pnlControl.setLayout(new GridLayout(2, 1));
        pnlControl.setBorder(new EmptyBorder(10, 10, 0, 10));
        pnlControl.add(sldTalla);
        pnlControl.add(spnTalla);

        // Panel principal
        JPanel pnlPpal = new JPanel(new GridLayout(2, 1));
        pnlPpal.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlPpal.add(lblRotulo);
        pnlPpal.add(pnlControl);

        // Ventana principal
        setContentPane(pnlPpal);
        setTitle("Swing Manual #11");
        setResizable(false);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Inicialización Anterior    
    private void initBefore(Properties prp) {
        // Memorizar Referencia
        this.prp = prp;

        // Establecer LnF
        UtilesSwing.establecerLnF(prp.getProperty(PRP_LOOK_AND_FEEL, DEF_LOOK_AND_FEEL));
    }

    // Inicialización Posterior
    private void initAfter() {
        // Establecer Favicon
        UtilesSwing.establecerFavicon(this, prp.getProperty(PRP_FAVICON, DEF_FAVICON));
    }

    public void procesarTalla(ChangeEvent e) {
        // Valores Actuales Fuente
        String familia = lblRotulo.getFont().getFamily();
        int estilo = lblRotulo.getFont().getStyle();
        int talla = lblRotulo.getFont().getSize();

        // Talla Seleccionada
        if (e.getSource().equals(sldTalla)) {
            talla = sldTalla.getValue();
            spnTalla.setValue(talla);
        } else {
            talla = (int) spnTalla.getValue();
            sldTalla.setValue(talla);
        }

        // Generar Fuente
        Font fuente = new Font(familia, estilo, talla);

        // Asignar Fuente
        lblRotulo.setFont(fuente);
    }
}
