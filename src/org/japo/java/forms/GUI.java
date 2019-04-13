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
public final class GUI extends JFrame {

    // Propiedades App
    public static final String PRP_FAVICON_RESOURCE = "favicon_resource";
    public static final String PRP_FONT_BANNER_RESOURCE = "font_banner_resource";
    public static final String PRP_FONT_INTERFACE_RESOURCE = "font_interface_resource";
    public static final String PRP_FORM_HEIGHT = "form_height";
    public static final String PRP_FORM_TITLE = "form_title";
    public static final String PRP_FORM_WIDTH = "form_width";
    public static final String PRP_LOOK_AND_FEEL_PROFILE = "look_and_feel_profile";

    // Valores por Defecto
    public static final String DEF_FAVICON_RESOURCE = "img/favicon.png";
    public static final String DEF_FONT_BANNER_SYSTEM_NAME = UtilesSwing.FONT_LUCIDA_BRIGHT_NAME;
    public static final String DEF_FONT_BANNER_FALLBACK_NAME = "Serif";
    public static final String DEF_FONT_INTERFACE_SYSTEM_NAME = UtilesSwing.FONT_LUCIDA_SANS_NAME;
    public static final String DEF_FONT_INTERFACE_FALLBACK_NAME = "Dialog";
    public static final int DEF_FORM_HEIGHT = 300;
    public static final String DEF_FORM_TITLE = "Swing Manual #10";
    public static final int DEF_FORM_WIDTH = 500;
    public static final String DEF_LOOK_AND_FEEL_PROFILE = UtilesSwing.LNF_WINDOWS_PROFILE;

    // Referencias
    private Properties prp;

    // Componentes
    private JLabel lblRotulo;
    private JSlider sldTalla;
    private JSpinner spnTalla;
    private JPanel pnlControl;
    private JPanel pnlPpal;

    // Fuentes
    private Font fntRotulo;
    private Font fntInterfaz;

    // Constructor
    public GUI(Properties prp) {
        // Conectar Referencia
        this.prp = prp;

        // Inicialización Anterior
        initBefore();

        // Creación Interfaz
        initComponents();

        // Inicializacion Posterior
        initAfter();
    }

    // Construcción del IGU
    private void initComponents() {
        // Fuentes
        fntRotulo = UtilesSwing.generarFuenteRecurso(
                prp.getProperty(PRP_FONT_BANNER_RESOURCE),
                DEF_FONT_BANNER_SYSTEM_NAME,
                DEF_FONT_BANNER_FALLBACK_NAME);
        fntInterfaz = UtilesSwing.generarFuenteRecurso(
                prp.getProperty(PRP_FONT_INTERFACE_RESOURCE),
                DEF_FONT_INTERFACE_SYSTEM_NAME,
                DEF_FONT_INTERFACE_FALLBACK_NAME);

        // Etiqueta de prueba
        lblRotulo = new JLabel();
        lblRotulo.setBackground(Color.WHITE);
        lblRotulo.setBorder(new BevelBorder(BevelBorder.LOWERED));
        lblRotulo.setFont(fntRotulo.deriveFont(Font.PLAIN, 40));
        lblRotulo.setHorizontalAlignment(JLabel.CENTER);
        lblRotulo.setOpaque(true);
        lblRotulo.setText("Érase una vez Java");

        // Selector de talla - Deslizador
        sldTalla = new JSlider();
        sldTalla.setFont(fntInterfaz.deriveFont(Font.PLAIN, 20));
        sldTalla.setMinimum(0);
        sldTalla.setMaximum(100);
        sldTalla.setMinorTickSpacing(5);
        sldTalla.setMajorTickSpacing(25);
        sldTalla.setPaintLabels(true);
        sldTalla.setPaintTicks(true);
        sldTalla.setValue(lblRotulo.getFont().getSize());

        // Cambiador de talla - Modelo
        SpinnerNumberModel modelo = new SpinnerNumberModel();
        modelo.setMinimum(0);
        modelo.setMaximum(100);
        modelo.setValue(lblRotulo.getFont().getSize());

        // Selector de talla - Cambiador
        spnTalla = new JSpinner(modelo);
        spnTalla.setFont(fntInterfaz.deriveFont(Font.PLAIN, 20));

        // Panel de Control
        pnlControl = new JPanel();
        pnlControl.add(sldTalla);
        pnlControl.add(spnTalla);
        pnlControl.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlControl.setLayout(new GridLayout(2, 1));

        // Panel principal
        pnlPpal = new JPanel(new GridLayout(2, 1));
        pnlPpal.add(lblRotulo);
        pnlPpal.add(pnlControl);
        pnlPpal.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Ventana principal
        setContentPane(pnlPpal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        try {
            int height = Integer.parseInt(prp.getProperty(PRP_FORM_HEIGHT));
            int width = Integer.parseInt(prp.getProperty(PRP_FORM_WIDTH));
            setSize(width, height);
        } catch (NumberFormatException e) {
            setSize(DEF_FORM_WIDTH, DEF_FORM_HEIGHT);
        }
        setTitle(prp.getProperty(PRP_FORM_TITLE, DEF_FORM_TITLE));
    }

    // Inicialización Anterior    
    private void initBefore() {
        // Establecer LnF
        UtilesSwing.establecerLnFProfile(prp.getProperty(
                PRP_LOOK_AND_FEEL_PROFILE, DEF_LOOK_AND_FEEL_PROFILE));
    }

    // Inicialización Posterior
    private void initAfter() {
        // Establecer Favicon
        UtilesSwing.establecerFavicon(this, prp.getProperty(
                PRP_FAVICON_RESOURCE, DEF_FAVICON_RESOURCE));

        // Registrar Festores de Eventos
        sldTalla.addChangeListener(new CEM(this));
        spnTalla.addChangeListener(new CEM(this));
    }

    // Respuesta al Cambio de Talla
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
