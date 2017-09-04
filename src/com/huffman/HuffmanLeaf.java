package com.huffman;

/**
 * @author André Victor
 * @author Khadije El Zein
 */
public class HuffmanLeaf extends HuffmanTree {
    public String value;
    public HuffmanLeaf(int freq, String val) {
        super(freq);
        value = val;
    }
}
