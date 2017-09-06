package com.huffman;

import org.omg.CORBA.INTERNAL;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @author André Victor
 * @author Khadije El Zein
 */
public class HuffmanCode {
    // input is an array of frequencies, indexed by character code
    public static HuffmanTree buildTree(Map<Object, Integer> map) {
        PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
        for (Map.Entry<Object, Integer> entry : map.entrySet()) {
            String key = entry.getKey().toString();
            Integer frequencia = entry.getValue();
            trees.offer(new HuffmanLeaf(frequencia, key));
        }
        assert trees.size() > 0;
        while (trees.size() > 1) {
            // two trees with least frequency
            HuffmanTree a = trees.poll();
            HuffmanTree b = trees.poll();
            // put into new node and re-insert into queue
            trees.offer(new HuffmanNode(a, b));
        }
        return trees.poll();
    }

}
