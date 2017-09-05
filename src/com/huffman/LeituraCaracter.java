package com.huffman;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import static com.huffman.HuffmanCode.printCodes;


/**
 * @author André Victor
 * @author Khadije El Zein
 */
public class LeituraCaracter extends Leitura {
    public LeituraCaracter(File fileIn) {
        frequencias = new HashMap<Character, Integer>();
        this.fileIn = fileIn;
    }
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

    public void codificarArquivo(String fileOut, HuffmanTree arvore){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileIn));
            FileOutputStream out = new FileOutputStream(fileOut);
            this.createTableHuffman(arvore, new Stack<Character>());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(huff);
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
            reader.close();
            out.close();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}