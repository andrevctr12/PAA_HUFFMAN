package com.huffman;

import java.io.*;
import java.util.HashMap;
import java.util.Stack;

import static com.huffman.HuffmanCode.buildTree;
import static com.huffman.HuffmanTree.calcularAltura;

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
            String str = new String();
            for (char bit : prefix) {
                str += bit;
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

    public static void printCode(HuffmanTree tree, Stack<Character> prefix) {
        assert tree != null;
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
            // print out character and frequency
            System.out.print(leaf.value + "\t" + leaf.frequencia + "\t");
            // print out code for this leaf, which is just the prefix
            for (char bit : prefix)
                System.out.print(bit);
            System.out.println();
        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;
            // traverse left
            prefix.push('0');
            printCode(node.left, prefix);
            prefix.pop();
            // traverse right
            prefix.push('1');
            printCode(node.right, prefix);
            prefix.pop();
        }
    }

    public void decodificarArquivo() {
        try {
            BufferedInputStream reader = new BufferedInputStream(new FileInputStream(fileIn));
            Cabecalho cabecalho = new Cabecalho();
            cabecalho = cabecalho.lerCabecalho(reader);
            frequencias = cabecalho.getHashMap();
            HuffmanTree tree = buildTree(frequencias);
            String fileOut;
            fileOut = fileIn + "Decoded.txt";
            fileOut = fileOut.replace(".bin", "");
            FileWriter filewriter = new FileWriter(fileOut);
            PrintWriter writer = new PrintWriter(filewriter);
            writer.flush();
            int r = -1;
            String str = new String();
            do {
                if (str.length() < cabecalho.getAltura()) {
                    r = (int)reader.read();
                    if (r != -1) {
                        byte r2 = (byte) r;
                        String bytes = String.format("%8s", Integer.toBinaryString(r2 & 0xFF)).replace(' ', '0');
                        str += bytes;
                    }
                    else if (str.length() > 0){
                        if (cabecalho.getLixo() > 0) {
                            String replaced = new String();
                            for (int i = 0; i < cabecalho.getLixo(); i++) {
                                replaced += 0;
                            }
                            str = str.replace(replaced, "");
                            if (str.length() > 0) {
                                tree.setAltura(0);
                                writer.print(tree.printCodes(tree, str));
                            }
                        }

                    }

                }
                else {
                    tree.setAltura(0);
                    writer.print(tree.printCodes(tree, str));
                    str = str.replaceFirst(str.substring(0, tree.getAltura()), "");
                }
            } while (r != -1);
            filewriter.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
