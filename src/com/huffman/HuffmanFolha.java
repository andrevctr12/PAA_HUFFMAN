package com.huffman;

/**
 * @author André Victor
 * @author Khadije El Zein
 */

/**
 * Classe referente a Arvore de prefixos (folha da arvore)
 */
public class HuffmanFolha extends HuffmanArvore {
    public String value;

    /**
     * Metodo Construtor
     * @param freq frequencia do Objeto
     * @param val valor do Objeto
     */
    public HuffmanFolha(int freq, String val) {
        super(freq);
        value = val;
    }
}
