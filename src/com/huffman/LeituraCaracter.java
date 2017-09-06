package com.huffman;

import java.io.*;
import java.util.HashMap;
import java.util.Stack;

import static com.huffman.HuffmanCode.buildTree;
import static com.huffman.HuffmanArvore.calcularAltura;


/**
 * @author André Victor
 * @author Khadije El Zein
 */
public class LeituraCaracter extends Leitura {
    public LeituraCaracter(File fileIn) {
        frequencias = new HashMap<Character, Integer>();
        this.fileIn = fileIn;
    }

    /**
     * Implementação do Método abstrato
     * @return Mapa de frenquencias
     */
    @Override
    public HashMap extraiFrequenciaArquivo() {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileIn));
            int r;
            int value= 1;
            while ((r = reader.read()) != -1) {
                char ch = (char) r;
                if(!frequencias.containsKey(ch)) {
                    frequencias.put(ch, 1);
                }else{
                    value = (int) frequencias.get(ch);
                    frequencias.replace(ch, value + 1);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return frequencias;
    }
    /**
     * Implementação do metodo abstrato
     * @param fileOut arquivo de saida
     */
    @Override
    public void codificarArquivo(String fileOut) {
        try{
            HuffmanArvore tree = buildTree(frequencias);
            BufferedReader reader = new BufferedReader(new FileReader(fileIn));
            FileOutputStream out = new FileOutputStream(fileOut);
            this.createTableHuffman(tree, new Stack<Character>());
            Cabecalho cabecalho = new Cabecalho(this.frequencias, calcularAltura(tree));
            cabecalho.escreverCabecalho(out);
            int r;
            String str = new String();
            while ((r = reader.read()) != -1) {
                Character ch = (char) r;
                str += huff.get(ch.toString());
                if (str.length() >= 8) {
                    for (int i = 0; i < str.length()/8; i++) {
                        String splitted = str.substring(0,8);
                        if (splitted.length() == 8) {
                            out.write((byte) Integer.parseInt(splitted, 2));
                            str = str.replace(splitted, "");
                        }
                    }
                }
            }
            if (str.length() > 0) {
                int lixo = 8 - str.length();
                for (int i = 0; i< lixo; i++) {
                    str += 0;
                }
                System.out.println(str);
                out.write((byte)Integer.parseInt(str, 2));
                cabecalho.setLixo(lixo);
                out.getChannel().position(0);
                cabecalho.escreverCabecalho(out);
            }
            reader.close();
            out.close();
            System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");
            printCode(tree, new Stack<Character>());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}