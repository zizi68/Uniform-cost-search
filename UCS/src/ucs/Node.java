/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucs;

import java.util.Comparator;

/**
 *
 * @author Admin
 */
public class Node implements Comparator<Node> {

    private int node;
    private int cost;

    public Node() {
    }

    public Node(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    public int getNode() {
        return node;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public int compare(Node node1, Node node2) {
        if (node1.cost < node2.cost) {
            return -1;
        }
        if (node1.cost > node2.cost) {
            return 1;
        }
        if (node1.node < node2.node) {
            return -1;
        }
        return 0;
    }
}
