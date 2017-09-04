package com.huffman;

/**
 * @author André Victor
 * @author Khadije El Zein
 */
public abstract class HuffmanTree implements Comparable<HuffmanTree> {
    public int frequencia;
    public HuffmanTree(int freq) {
        frequencia = freq;
    }
    public int compareTo(HuffmanTree tree) {
        return frequencia - tree.frequencia;
    }
}
