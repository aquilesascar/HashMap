package org.example;

import java.util.LinkedList;

public class HashTable {

    //a estrutura interna é um array de listas encadeadas (buckets)
    private LinkedList<Integer>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        this.size = size;
        this.table = new LinkedList[size];
        //inicializa cada bucket como uma nova lista encadeada
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    int hashFunction(int value) {
        return Math.abs(value % size);
    }

    public void put(int value) {
        int index = hashFunction(value);
        //pega a lista no índice calculado
        LinkedList<Integer> bucket = table[index];
        bucket.add(value);
    }

    public boolean contains(int value) {
        int index = hashFunction(value);
        return table[index].contains(value);
    }
}
