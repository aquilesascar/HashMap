package org.example;

import java.util.LinkedList;

/**
 * Implementação de uma Tabela Hash (HashTable) simples.
 * Usa um array de listas encadeadas para tratar colisões.
 */
public class HashTable {

    // A estrutura interna é um array de listas encadeadas (buckets)
    private LinkedList<Integer>[] table;
    private int size;

    /**
     * Construtor que inicializa a tabela com um tamanho específico.
     * @param size O número de "buckets" na tabela.
     */
    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        this.size = size;
        this.table = new LinkedList[size];
        // Inicializa cada bucket como uma nova lista encadeada
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    /**
     * Função de hash. Calcula o índice onde o valor deve ser armazenado.
     * Usa o operador de módulo para garantir que o índice esteja dentro dos limites do array.
     * @param value O valor a ser inserido.
     * @return O índice calculado.
     */
    private int hashFunction(int value) {
        // Garante que o hash seja positivo antes de aplicar o módulo
        return Math.abs(value % size);
    }

    /**
     * Insere um valor na tabela hash.
     * @param value O valor a ser inserido.
     */
    public void put(int value) {
        int index = hashFunction(value);
        // Pega a lista no índice calculado
        LinkedList<Integer> bucket = table[index];
        // Adiciona o valor à lista (bucket). Não verifica duplicatas para simplificar.
        bucket.add(value);
    }

    /**
     * Verifica se um valor existe na tabela hash.
     * @param value O valor a ser buscado.
     * @return true se o valor for encontrado, false caso contrário.
     */
    public boolean contains(int value) {
        int index = hashFunction(value);
        // Pega a lista no índice correto e usa o método contains() da lista,
        // que fará uma busca linear apenas nos elementos daquele bucket.
        return table[index].contains(value);
    }
}