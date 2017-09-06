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
public class LeituraPalavra extends Leitura {
    String linha;

    public LeituraPalavra(File fileIn) {
        frequencias = new HashMap<String, Integer>();
        this.fileIn = fileIn;
    }
    @Override
    public HashMap extraiFrequenciaArquivo() {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileIn));
            int value = 1;
            int value_linha = 0;
            frequencias.put("\n", value_linha);
            while ((linha = reader.readLine()) != null) {
                for(String str : linha.split("(?!^)\\b")) {
                    if (!frequencias.containsKey(str)) {
                        frequencias.put(str, 1);
                    } else {
                        value = (int) frequencias.get(str);
                        frequencias.replace(str, value + 1);
                    }
                }
                frequencias.replace("\n", value_linha++);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return frequencias;
    }

    public void codificarArquivo(String fileOut) {
        try{
            HuffmanTree tree = buildTree(frequencias);
            BufferedReader reader = new BufferedReader(new FileReader(fileIn));
            FileOutputStream out = new FileOutputStream(fileOut);
            this.createTableHuffman(tree, new Stack<Character>());
            Cabecalho cabecalho = new Cabecalho(this.frequencias, calcularAltura(tree));
            cabecalho.escreverCabecalho(out);
            int r;
            String str = new String();
            while ((linha = reader.readLine()) != null) {
                for(String palavra : linha.split("(?!^)\\b")) {
                    str += huff.get(palavra);
                    if (str.length() >= 8) {
                        for (int i = 0; i < str.length()/8; i++) {
                            String splitted = str.substring(0,8);
                            if (splitted.length() == 8) {
                                out.write((byte) Integer.parseInt(splitted, 2));
                                str = str.replace(splitted, "");
                            }
                        }
                    }
                }
                str += huff.get("\n");
                if (str.length() >= 8) {
                    for (int i = 0; i < str.length()/8; i++) {
                        String splitted = str.substring(0,8);
                        if (splitted.length() == 8) {
                            out.write((byte) Integer.parseInt(splitted, 2));
                            str = str.replace(splitted, "");
                        }
                    }
                }
            }
            if (str.length() > 0) {
                str = str.replace(huff.get("\n"), "");
                int lixo = 8 - str.length();
                for (int i = 0; i< lixo; i++) {
                    str += 0;
                }
                System.out.println(str);
                out.write((byte)Integer.parseInt(str, 2));
                cabecalho.setLixo(lixo);
                out.getChannel().position(0);
                cabecalho.escreverCabecalho(out);
            }
            reader.close();
            out.close();
            System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");
            printCode(tree, new Stack<Character>());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void decodificarArquivo() {
//        try {
//            BufferedInputStream reader = new BufferedInputStream(new FileInputStream(fileIn));
//            Cabecalho cabecalho = new Cabecalho();
//            cabecalho = cabecalho.lerCabecalho(reader);
//            frequencias = cabecalho.getHashMap();
//            HuffmanTree tree = buildTree(frequencias);
//            String fileOut;
//            fileOut = fileIn + "Decoded.txt";
//            fileOut = fileOut.replace(".bin", "");
//            FileWriter filewriter = new FileWriter(fileOut);
//            PrintWriter writer = new PrintWriter(filewriter);
//            writer.flush();
//            int r = -1;
//            String str = new String();
//            do {
//                if (str.length() < cabecalho.getAltura()) {
//                    r = (int)reader.read();
//                    if (r != -1) {
//                        byte r2 = (byte) r;
//                        String bytes = String.format("%8s", Integer.toBinaryString(r2 & 0xFF)).replace(' ', '0');
//                        str += bytes;
//                    }
//                    else if (str.length() > 0){
//                        str = str.replace(str.substring(7 - cabecalho.getLixo(), 8), "");
//                        tree.setAltura(0);
//                        writer.print(tree.printCodes(tree,str));
//                    }
//
//                }
//                else {
//                    tree.setAltura(0);
//                    writer.print(tree.printCodes(tree, str));
//                    str = str.replaceFirst(str.substring(0, tree.getAltura()), "");
//                }
//            } while (r != -1);
//            filewriter.close();
//            reader.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}
