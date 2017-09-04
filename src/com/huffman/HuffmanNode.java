package com.huffman;

/**
 * @author André Victor
 * @author Khadije El Zein
 */
public class HuffmanNode extends HuffmanTree {
    public HuffmanTree left, right;
    public HuffmanNode(HuffmanTree l, HuffmanTree r) {
        super(l.frequencia + r.frequencia);
        left = l;
        right = r;
    }
}