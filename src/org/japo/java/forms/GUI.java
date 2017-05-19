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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import org.japo.java.events.CEM;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class GUI extends JFrame {
    // Tamaño de la ventana
    public static final int VENTANA_ANC = 600;
    public static final int VENTANA_ALT = 300;

    // Fuente etiqueta - Parámetros
    public static final String FNT_FAM_LBL = "Georgia";
    public static final int FNT_EST_LBL = Font.PLAIN;
    private int fntTamLBL = 40;
    
    // Fuente Controles - Parámetros
    public static final String FNT_FAM_CTR = "Calibri";
    public static final int FNT_EST_CTR = Font.BOLD;
    public static final int FNT_TAM_CTR = 20;
    
    // Parámetros JSlider
    public static final int SIZE_MIN = 0;
    public static final int SIZE_MAX = 100;
    public static final int PASO_MEN = 5;
    public static final int PASO_MAY = 25;
    
    // Texto de prueba
    public static final String TEXTO = "Érase una vez Java";
    
    // Componentes del GUI
    private JLabel lblPrueba;
    private JSlider sldTalla;
    private JSpinner spnTalla;
    
    public GUI() {
        // Inicialización PREVIA
        beforeInit();

        // Creación del interfaz
        initComponents();

        // Inicialización POSTERIOR
        afterInit();
    }

    // Construcción del IGU
    private void initComponents() {
        // Fuentes
        Font fntLBL = new Font(FNT_FAM_LBL, FNT_EST_LBL, fntTamLBL);
        Font fntCTR = new Font(FNT_FAM_CTR, FNT_EST_CTR, FNT_TAM_CTR);
        
        // Bordes
        Border brdPNL = new EmptyBorder(10, 10, 10, 10);
        Border brdLBL = new BevelBorder(BevelBorder.LOWERED);
        
        // Color - Etiqueta
        Color c = new Color(184, 244, 244);
        
        // Gestor de Eventos de Cambio
        CEM cem = new CEM(this);
        
        // Etiqueta de prueba
        lblPrueba = new JLabel(TEXTO);
        lblPrueba.setFont(fntLBL);
        lblPrueba.setOpaque(true);
        lblPrueba.setBackground(c);
        lblPrueba.setBorder(brdLBL);
        lblPrueba.setHorizontalAlignment(JLabel.CENTER);
        
        // Panel de Control
        JPanel pnlControl = new JPanel();
        pnlControl.setLayout(new GridLayout(2, 1));
        
        // Selector de talla - Deslizador
        sldTalla = new JSlider();
        sldTalla.setMinimum(SIZE_MIN);
        sldTalla.setMaximum(SIZE_MAX);
        sldTalla.setValue(fntTamLBL);
        sldTalla.setFont(fntCTR);
        sldTalla.setPaintLabels(true);
        sldTalla.setPaintTicks(true);
        sldTalla.setMinorTickSpacing(PASO_MEN);
        sldTalla.setMajorTickSpacing(PASO_MAY);
        sldTalla.addChangeListener(cem);
        pnlControl.add(sldTalla);
        
        // Cambiador de talla - Modelo
        SpinnerNumberModel modelo = new SpinnerNumberModel();
        modelo.setMinimum(SIZE_MIN);
        modelo.setMaximum(SIZE_MAX);
        modelo.setValue(fntTamLBL);
        
        // Selector de talla - Cambiador
        spnTalla = new JSpinner(modelo);
        spnTalla.setFont(fntCTR);
        spnTalla.addChangeListener(cem);
        pnlControl.add(spnTalla);
        
        // Panel Principal
        JPanel pnlPpal = new JPanel();
        pnlPpal.setLayout(new GridLayout(2, 1));
        pnlPpal.setBorder(brdPNL);
        pnlPpal.add(lblPrueba);
        pnlPpal.add(pnlControl);
        
        
        // Ventana principal
        setTitle("Selección Talla");
        setContentPane(pnlPpal);
        setSize(VENTANA_ANC, VENTANA_ALT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }

    // Inicialización antes del IGU
    private void beforeInit() {

    }

    // Inicialización después del IGU
    private void afterInit() {

    }

    public void procesarTalla(ChangeEvent e) {
        // Causante del evento
        if (e.getSource().equals(sldTalla)) {
            fntTamLBL = sldTalla.getValue();
            spnTalla.setValue(fntTamLBL);
        } else {
            fntTamLBL = (int) spnTalla.getValue();
            sldTalla.setValue(fntTamLBL);
        }
        
        // Generar Fuente
        Font f = new Font(FNT_FAM_LBL, FNT_EST_LBL, fntTamLBL);
        
        // Asignar Fuente
        lblPrueba.setFont(f);
    }
}
