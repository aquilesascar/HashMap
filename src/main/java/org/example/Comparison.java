package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Comparison {

    public static void main(String[] args) {
        int numeroDeElementos = 500000;
        long seed = 42;
        Random random = new Random(seed);

        List<Integer> numerosGerados = new ArrayList<>();
        System.out.println("Gerando " + numeroDeElementos + " números aleatórios...");
        for (int i = 0; i < numeroDeElementos; i++) {
            numerosGerados.add(random.nextInt());
        }
        System.out.println("Números gerados com sucesso.");

        // Instancia nossas estruturas de dados
        HashTable hashTable = new HashTable(numeroDeElementos * 2);
        AVLTree avlTree = new AVLTree();

        System.out.println("Inserindo os números na HashTable e na AVLTree...");

        // Agora, usamos a lista que já criamos para popular as estruturas.
        for (int numero : numerosGerados) {
            hashTable.put(numero);
            avlTree.insert(numero);
        }
        System.out.println("Inserção concluída.");

        System.out.println("A lista de números é muito grande. Aqui estão os primeiros 20 números como exemplo:");
        for (int i = 0; i < 20; i++) {
            System.out.println(" - " + numerosGerados.get(i));
        }
        System.out.println("-----------------------------------------------------");


        Scanner sc = new Scanner(System.in);
        System.out.print("\nDigite um dos números da lista acima para buscar: ");
        int numeroParaBuscar = sc.nextInt();
        sc.close();

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
    }
}