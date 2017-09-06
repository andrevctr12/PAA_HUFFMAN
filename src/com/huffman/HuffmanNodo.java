package com.huffman;

/**
 * @author André Victor
 * @author Khadije El Zein
 */

/**
 * Classe referente a Arvore de prefixos (Nodo da arvore)
 */
public class HuffmanNodo extends HuffmanArvore {
    public HuffmanArvore left, right;

    /**
     * Metodo Construtor
     * @param l nó esquedo
     * @param r nó direito
     */
    public HuffmanNodo(HuffmanArvore l, HuffmanArvore r) {
        super(l.frequencia + r.frequencia);
        left = l;
        right = r;
    }
}