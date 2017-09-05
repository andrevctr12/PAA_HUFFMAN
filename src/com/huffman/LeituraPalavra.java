package com.huffman;

import java.io.*;
import java.util.HashMap;


/**
 * @author André Victor
 * @author Khadije El Zein
 */
public class LeituraPalavra extends Leitura {
    String linha;

    public LeituraPalavra(File fileIn) {
        frequencias = new HashMap<String, Integer>();
        this.fileIn = fileIn;
    }
    @Override
    public HashMap extraiFrequenciaArquivo() {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileIn));
            linha = reader.readLine();
            int value = 1;
            while ((linha = reader.readLine()) != null) {
                for(String str : linha.split(" .,")) {
                    if (!frequencias.containsKey(str)) {
                        frequencias.put(str, 1);
                    } else {
                        value = (int) frequencias.get(str);
                        frequencias.replace(str, value + 1);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return frequencias;
    }
}
