package com.huffman;

/**
 * @author André Victor
 * @author Khadije El Zein
 */

/**
 * Metodo que cria a arvore de prefixos
 */
public abstract class HuffmanArvore implements Comparable<HuffmanArvore> {
    public int frequencia;
    private int altura;

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }



    public HuffmanArvore(int freq) {
        frequencia = freq;
    }
    public int compareTo(HuffmanArvore tree) {
        return frequencia - tree.frequencia;
    }
    public static int calcularAltura(HuffmanArvore tree) {
        assert tree != null;
        if (tree instanceof HuffmanNodo) {
            HuffmanNodo node = (HuffmanNodo) tree;
            int he = calcularAltura(node.left);
            int hd = calcularAltura(node.right);
            if (he < hd) return hd + 1;
            else return he + 1;
        }
        return 0;
    }
    public String printCodes(HuffmanArvore tree, String string) {
        assert tree != null;
        if (tree instanceof HuffmanFolha) {
            HuffmanFolha leaf = (HuffmanFolha)tree;
            return leaf.value;
        } else if (tree instanceof HuffmanNodo) {
            HuffmanNodo node = (HuffmanNodo)tree;
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
