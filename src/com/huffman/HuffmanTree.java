package com.huffman;

/**
 * @author André Victor
 * @author Khadije El Zein
 */
public abstract class HuffmanTree implements Comparable<HuffmanTree> {
    public int frequencia;
    private int altura;

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }



    public HuffmanTree(int freq) {
        frequencia = freq;
    }
    public int compareTo(HuffmanTree tree) {
        return frequencia - tree.frequencia;
    }
    public static int calcularAltura(HuffmanTree tree) {
        assert tree != null;
        if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode) tree;
            int he = calcularAltura(node.left);
            int hd = calcularAltura(node.right);
            if (he < hd) return hd + 1;
            else return he + 1;
        }
        return 0;
    }
    public String printCodes(HuffmanTree tree, String string) {
        assert tree != null;
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
            return leaf.value;
        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;
            if (string.substring(altura, altura + 1).equals("0")) {
                altura++;
                return this.printCodes(node.left, string);
            }
            else {
                altura++;
                return this.printCodes(node.right, string);
            }
        }
        return "";
    }
}
