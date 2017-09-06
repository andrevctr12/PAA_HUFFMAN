package com.huffman;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author André Victor
 * @author Khadije El Zein
 */

/**
 * Classe de cabeçalho para os arquivos
 */
public class Cabecalho implements Serializable {
    private HashMap<String, String> hashMap;
    private int lixo = 0;
    private int altura;

    /**
     * Metodo GetHashMap
     * @return  Hashmap
     */
    public HashMap getHashMap() {
        return hashMap;
    }

    /**
     * Metodo GetLixo
     * @return lixo
     */
    public int getLixo() {
        return lixo;
    }

    /**
     * Metodo SetLixo
     * @param lixo lixo
     */
    public void setLixo(int lixo) {
        this.lixo = lixo;
    }

    /**
     * Metodo GetAltura
     * @return altura da arvore
     */
    public int getAltura() {
        return altura;
    }

    /**
     * Metodo Construtor
     * @param hashMap hashMap de frequencias
     * @param altura altura da arvore
     */
    public Cabecalho(HashMap<String, String> hashMap, int altura) {
        this.altura = altura;
        this.hashMap = hashMap;
    }

    /**
     * Metodo Construtor
     */
    public Cabecalho() {
        this.altura = 0;
        hashMap = new HashMap<>();
    }

    /**
     * Escreve o cabeçalho no arquivo
     * @param out arquivo de saida
     * @throws IOException
     */
    public void escreverCabecalho(OutputStream out) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeObject(this);
    }

    /**
     * Le cabeçalho do arquivo
     * @param in arquivo de entrada
     * @return retorna o cabeçalho lido
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Cabecalho lerCabecalho(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(in);
        return (Cabecalho) objectInputStream.readObject();

    }
}
