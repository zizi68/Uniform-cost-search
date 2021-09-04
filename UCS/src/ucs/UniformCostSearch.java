/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author Admin
 */
public class UniformCostSearch {

    public static final int MAX_VALUE = 999;
    private PriorityQueue<Node> priorityQueue; //Các phần tử của PriorityQueue có thể không được sắp xếp nhưng các phần tử luôn được lấy ra theo thứ tự được sắp xếp.
    private Set<Integer> nodeDaDiQua;
    private int chiPhi[];
    private int soNode;
    private int maTranKe[][];
    private LinkedList<Integer> duongDi;
    private int[] nodeCha;
    private int nguon, dich;

    public UniformCostSearch(int numberOfNodes) {
        this.soNode = numberOfNodes;
        this.nodeDaDiQua = new HashSet<Integer>();
        this.priorityQueue = new PriorityQueue<>(numberOfNodes, new Node());
        this.chiPhi = new int[numberOfNodes];
        this.duongDi = new LinkedList<Integer>();
        this.maTranKe = new int[numberOfNodes][numberOfNodes];
        this.nodeCha = new int[numberOfNodes];
    }

    private void addFrontiersToQueue(int nodeDangXet) {
        for (int nodeKe = 0; nodeKe < soNode; nodeKe++) {
            if (!nodeDaDiQua.contains(nodeKe)) {
                if (maTranKe[nodeDangXet][nodeKe] != MAX_VALUE) {
                    if (chiPhi[nodeKe] > maTranKe[nodeDangXet][nodeKe] + chiPhi[nodeDangXet]) {
                        chiPhi[nodeKe] = maTranKe[nodeDangXet][nodeKe] + chiPhi[nodeDangXet];
                        nodeCha[nodeKe] = nodeDangXet;
                    }

                    Node node = new Node(nodeKe, chiPhi[nodeKe]);
                    if (priorityQueue.contains(node)) {
                        priorityQueue.remove(node);
                    }
                    priorityQueue.add(node);
                }
            }
        }
    }

    public int uniformCostSearch(int maTran[][], int source, int destination) {
        int nodeDangXet;
        this.nguon = source;
        this.dich = destination;

        for (int i = 0; i < soNode; i++) {
            chiPhi[i] = MAX_VALUE;
        }

        //Nạp ma trận
        for (int dinhNguon = 0; dinhNguon < soNode; dinhNguon++) {
            for (int dinhDich = 0; dinhDich < soNode; dinhDich++) {
                this.maTranKe[dinhNguon][dinhDich] = maTran[dinhNguon][dinhDich];
            }
        }

        priorityQueue.add(new Node(source, 0));
        chiPhi[source] = 0;

        while (!priorityQueue.isEmpty()) {
            nodeDangXet = priorityQueue.poll().getNode();
            if (nodeDangXet == destination) {
                return chiPhi[destination];
            }
            nodeDaDiQua.add(nodeDangXet);
            addFrontiersToQueue(nodeDangXet);
        }
        return chiPhi[destination];
    }

    public void printPath() {
        duongDi.add(dich);
        boolean found = false;
        int dinh = dich;
        while (!found) {
            if (dinh == nguon) {
                found = true;
                continue;
            }
            duongDi.add(nodeCha[dinh]);
            dinh = nodeCha[dinh];
        }

        System.out.print("Đường đi có chi phí nhỏ nhất từ " + (nguon + 1) + " đến " + (dich + 1) + " là ");
        Iterator<Integer> iterator = duongDi.descendingIterator(); //Trả về một iterator trên các phần tử trong LinkedList theo thứ tự tuần tự ngược lại
        System.out.print(iterator.next() + 1);
        while (iterator.hasNext()) {
            System.out.print("   ->   " + (iterator.next() + 1));
        }
    }
}
