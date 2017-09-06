package com.huffman;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author André Victor
 * @author Khadije El Zein
 */
public class Cabecalho implements Serializable {
    private HashMap<String, String> hashMap;
    private int lixo = 0;
    private int altura;

    public HashMap getHashMap() {
        return hashMap;
    }

    public int getLixo() {
        return lixo;
    }

    public void setLixo(int lixo) {
        this.lixo = lixo;
    }


    public int getAltura() {
        return altura;
    }

    public Cabecalho(HashMap<String, String> hashMap, int altura) {
        this.altura = altura;
        this.hashMap = hashMap;
    }

    public Cabecalho() {
        this.altura = 0;
        hashMap = new HashMap<>();
    }
    public void escreverCabecalho(OutputStream out) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeObject(this);
    }

    public Cabecalho lerCabecalho(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(in);
        return (Cabecalho) objectInputStream.readObject();

    }
}
