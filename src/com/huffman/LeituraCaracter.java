package com.huffman;

import java.io.*;
import java.util.HashMap;
/**
 * @author André Victor
 * @author Khadije El Zein
 */
public class LeituraCaracter extends Leitura {
    public LeituraCaracter() {
        frequencias = new HashMap<Character, Integer>();
    }
    @Override
    public HashMap extraiFrequenciaArquivo(File arquivo) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
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

}