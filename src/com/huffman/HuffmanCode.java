package com.huffman;

import org.omg.CORBA.INTERNAL;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
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
    public static void printCodes(HuffmanTree tree, Stack<Character> prefix) {
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
            printCodes(node.left, prefix);
            prefix.pop();
            // traverse right
            prefix.push('1');
            printCodes(node.right, prefix);
            prefix.pop();
        }
    }

}
