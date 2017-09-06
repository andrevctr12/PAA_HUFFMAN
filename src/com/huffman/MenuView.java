package com.huffman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Stack;

import static com.huffman.HuffmanCode.buildTree;
import static com.huffman.Leitura.printCode;

/**
 * @author André Victor
 * @author Khadije El Zein
 */
public class MenuView extends JFrame {
    private JPanel mainPanel;
    private JButton codificarArquivoPorCaracterButton;
    private JButton codificarArquivoPorPalavraButton;
    private JButton decodificarArquivoPorCaracterButton;
    private JButton decodificarArquivoPorPalavraButton;

    public MenuView() {
        setTitle("MENUZINHU");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();

        codificarArquivoPorCaracterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codificarArquivoPorCaracterButtonActionPerformed(e);
            }
        });

        codificarArquivoPorPalavraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codificarArquivoPorPalavraButtonActionPerformed(e);
            }
        });

        decodificarArquivoPorCaracterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decodificarArquivoPorCaracterButtonActionPerformed(e);
            }
        });

        decodificarArquivoPorPalavraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decodificarArquivoPorPalavraButtonActionPerformed(e);
            }
        });
    }

    private void codificarArquivoPorCaracterButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setDialogTitle("Escolha o arquivo");
            if (chooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG) {
                File fileSelected = chooser.getSelectedFile();
                LeituraCaracter leituraCaracter = new LeituraCaracter(fileSelected);
                HashMap hashMapFreq = leituraCaracter.extraiFrequenciaArquivo();
                leituraCaracter.codificarArquivo("algumacoisa.bin");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void codificarArquivoPorPalavraButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setDialogTitle("Escolha o arquivo");
            if (chooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG) {
                File fileSelected = chooser.getSelectedFile();
                LeituraPalavra leituraPalavra = new LeituraPalavra(fileSelected);
                HashMap hashMap = leituraPalavra.extraiFrequenciaArquivo();
                leituraPalavra.codificarArquivo("algumacoisaPalavra.bin");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void decodificarArquivoPorCaracterButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setDialogTitle("Escolha o arquivo");
            if (chooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG) {
                File fileSelected = chooser.getSelectedFile();
                LeituraCaracter leituraCaracter = new LeituraCaracter(fileSelected);
                leituraCaracter.decodificarArquivo();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void decodificarArquivoPorPalavraButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setDialogTitle("Escolha o arquivo");
            if (chooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG) {
                File fileSelected = chooser.getSelectedFile();
                LeituraPalavra leituraPalavra = new LeituraPalavra(fileSelected);
                leituraPalavra.decodificarArquivo();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuView frame = new MenuView();
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });
    }
}
