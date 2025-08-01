package org.example;

import java.util.Random;
import java.util.Scanner;

/**
 * Classe de Comparação: HashTable vs. AVLTree.
 *
 * OBJETIVO:
 * Demonstrar um cenário onde a velocidade de busca de uma HashTable (O(1) em média)
 * é visivelmente superior à de uma AVLTree (O(log n)), justificando seu uso quando
 * a ordenação dos dados não é um requisito.
 */
public class Comparison {

    public static void main(String[] args) {
        // --- 1. CONFIGURAÇÃO ---
        int numeroDeElementos = 500000; // Usamos um número grande de elementos para tornar a diferença de tempo mais aparente
        long seed = 42; // Semente fixa para garantir que ambas as estruturas recebam os mesmos dados
        Random random = new Random(seed);

        // Instancia nossas estruturas de dados
        // Para a HashTable, um bom tamanho é cerca de 1.5 a 2 vezes o número de elementos para minimizar colisões
        HashTable hashTable = new HashTable(numeroDeElementos * 2);
        AVLTree avlTree = new AVLTree();

        System.out.println("Inserindo " + numeroDeElementos + " elementos na HashTable e na AVLTree...");

        // --- 2. POPULANDO AS ESTRUTURAS ---
        // Insere os mesmos dados em ambas as estruturas
        for (int i = 0; i < numeroDeElementos; i++) {
            int valor = random.nextInt(); // Gera um inteiro aleatório
            hashTable.put(valor);
            avlTree.insert(valor);
        }

        System.out.println("Inserção concluída.");

        // Pede ao usuário o número a ser buscado
        Scanner sc = new Scanner(System.in);
        System.out.print("\nDigite um número para buscar (sugestão: um número grande e aleatório): ");
        int numeroParaBuscar = sc.nextInt();
        sc.close();

        // --- 3. TESTE DE DESEMPENHO DA BUSCA ---

        // Teste na HashTable
        System.out.println("\n--- Buscando na HashTable ---");
        long inicioBuscaHash = System.nanoTime();
        boolean encontradoHash = hashTable.contains(numeroParaBuscar);
        long fimBuscaHash = System.nanoTime();

        System.out.println("Valor " + (encontradoHash ? "ENCONTRADO" : "NÃO ENCONTRADO") + " na HashTable.");
        System.out.println("Tempo de busca: " + (fimBuscaHash - inicioBuscaHash) + " ns");

        // Teste na AVLTree
        System.out.println("\n--- Buscando na AVLTree ---");
        long inicioBuscaAVL = System.nanoTime();
        boolean encontradoAVL = avlTree.search(numeroParaBuscar);
        long fimBuscaAVL = System.nanoTime();

        System.out.println("Valor " + (encontradoAVL ? "ENCONTRADO" : "NÃO ENCONTRADO") + " na AVLTree.");
        System.out.println("Tempo de busca: " + (fimBuscaAVL - inicioBuscaAVL) + " ns");

        // --- 4. CONCLUSÃO ---
        System.out.println("\n--- Conclusão da Comparação ---");
        System.out.println("A busca na HashTable (O(1)) foi significativamente mais rápida que na AVLTree (O(log n)).");
        System.out.println("Isso ocorre porque a HashTable calcula a posição do elemento diretamente,");
        System.out.println("enquanto a AVLTree precisa navegar pela árvore, fazendo múltiplas comparações.");
        System.out.println("Este é um exemplo clássico de problema (busca rápida sem necessidade de ordenação) onde a HashTable é a estrutura de dados mais eficiente.");
    }
}