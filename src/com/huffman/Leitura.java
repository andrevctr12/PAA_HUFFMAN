package com.huffman;

import java.io.*;
import java.util.HashMap;
import java.util.Stack;

import static com.huffman.HuffmanCode.buildTree;

/**
 * @author André Victor
 * @author Khadije El Zein
 */

/**
 * Classe abstrata de Leitura
 */
public abstract class Leitura {
    protected HashMap frequencias;
    protected File fileIn;
    protected HashMap<String, String> huff = new HashMap<String, String>();

    /**
     * Metodo Abstrato que extrai frequencias do arquivo texto
     * @return retorna mapa de frequencias
     */
    abstract public HashMap extraiFrequenciaArquivo();

    /**
     * Metodo abstrato de codificação de arquivo
     * @param fileOut nome do arquivo de saida
     */
    abstract public void codificarArquivo(String fileOut);

    public HashMap getFrequencias() {
        return frequencias;
    }

    /**
     * Cria a tabela com os prefixos de huffman
     * @param tree arvore de prefixos
     * @param prefix pilha de prefixos
     */
    public void createTableHuffman(HuffmanArvore tree, Stack<Character> prefix) {
        assert tree != null;
        if (tree instanceof HuffmanFolha) {
            HuffmanFolha leaf = (HuffmanFolha) tree;
            String str = new String();
            for (char bit : prefix) {
                str += bit;
            }
            huff.put(leaf.value.toString(), str);

        } else if (tree instanceof HuffmanNodo) {
            HuffmanNodo node = (HuffmanNodo) tree;
            prefix.push('0');
            createTableHuffman(node.left,prefix);
            prefix.pop();
            prefix.push('1');
            createTableHuffman(node.right, prefix);
            prefix.pop();
        }
    }

    /**
     * Imprime os codigos
     * @param tree
     * @param prefix
     */
    public static void printCode(HuffmanArvore tree, Stack<Character> prefix) {
        assert tree != null;
        if (tree instanceof HuffmanFolha) {
            HuffmanFolha leaf = (HuffmanFolha)tree;
            // print out character and frequency
            System.out.print(leaf.value + "\t" + leaf.frequencia + "\t");
            // print out code for this leaf, which is just the prefix
            for (char bit : prefix)
                System.out.print(bit);
            System.out.println();
        } else if (tree instanceof HuffmanNodo) {
            HuffmanNodo node = (HuffmanNodo)tree;
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

    /**
     * Metodo Para decodificar um arquivo codificado, tanto com palavra, quanto com character
     */
    public void decodificarArquivo() {
        try {
            BufferedInputStream reader = new BufferedInputStream(new FileInputStream(fileIn));
            Cabecalho cabecalho = new Cabecalho();
            cabecalho = cabecalho.lerCabecalho(reader);
            frequencias = cabecalho.getHashMap();
            HuffmanArvore tree = buildTree(frequencias);
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
