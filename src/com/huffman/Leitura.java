package com.huffman;

import java.io.File;
import java.util.HashMap;
import java.util.Stack;

/**
 * @author André Victor
 * @author Khadije El Zein
 */
public abstract class Leitura {
    protected HashMap frequencias;
    protected File fileIn;
    protected HashMap<String, String> huff = new HashMap<String, String>();

    abstract public HashMap extraiFrequenciaArquivo();

    public HashMap getFrequencias() {
        return frequencias;
    }

    public void createTableHuffman(HuffmanTree tree, Stack<Character> prefix) {
        assert tree != null;
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf) tree;
            Stack ch = (Stack) prefix.clone();
            String str = new String();
            while(!ch.isEmpty()) {
                str += ch.pop();
            }
            huff.put(leaf.value.toString(), str);

        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode) tree;
            prefix.push('0');
            createTableHuffman(node.left,prefix);
            prefix.pop();
            prefix.push('1');
            createTableHuffman(node.right, prefix);
            prefix.pop();
        }
    }

}
