package com.huffman;

import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author André Victor
 * @author Khadije El Zein
 */

/**
 * Classe de codigo de Huffman
 */
public class HuffmanCode {
    /**
     * Algoritmo de Huffman para construir a arvore de prefixos
     * @param map Mapa de frequencias
     * @return retorna a arvore de Prefixos
     */
    public static HuffmanArvore buildTree(Map<Object, Integer> map) {
        PriorityQueue<HuffmanArvore> trees = new PriorityQueue<HuffmanArvore>();
        for (Map.Entry<Object, Integer> entry : map.entrySet()) {
            String key = entry.getKey().toString();
            Integer frequencia = entry.getValue();
            trees.offer(new HuffmanFolha(frequencia, key));
        }
        assert trees.size() > 0;
        while (trees.size() > 1) {

            HuffmanArvore a = trees.poll();
            HuffmanArvore b = trees.poll();

            trees.offer(new HuffmanNodo(a, b));
        }
        return trees.poll();
    }

}
