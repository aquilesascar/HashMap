package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Classe principal para demonstrar e comparar os tempos de busca em:
 * 1. Array desordenado (Busca Linear)
 * 2. Array ordenado (Busca Binária)
 * 3. HashMap nativo do Java
 */
public class Main {

    public static void main(String[] args) {
        // --- 1. CONFIGURAÇÃO INICIAL ---
        int tamanho = 1000; // Define o número de elementos a serem gerados
        int[] array = new int[tamanho];
        long seed = 42; // Semente para o gerador de números aleatórios, para garantir resultados consistentes
        Random random = new Random(seed);

        // Preenche o array com números aleatórios entre 0 e 9999
        for (int i = 0; i < tamanho; i++) {
            array[i] = random.nextInt(10000);
        }

        System.out.println("Array original gerado:");
        System.out.println(Arrays.toString(array));

        // Pede ao usuário para inserir o número que deseja buscar
        Scanner sc = new Scanner(System.in);
        System.out.print("\nDigite um número para buscar: ");
        int numeroParaBuscar = sc.nextInt();
        sc.close();

        // --- 2. BUSCA LINEAR (ARRAY DESORDENADO) ---
        System.out.println("\n--- Comparando com Array Desordenado ---");
        long inicioBuscaLinear = System.nanoTime(); // Marca o tempo de início

        boolean encontradoLinear = false;
        // Percorre cada elemento do array um por um
        for (int num : array) {
            if (num == numeroParaBuscar) {
                encontradoLinear = true;
                break; // Para a busca assim que encontra o elemento
            }
        }
        long fimBuscaLinear = System.nanoTime(); // Marca o tempo de fim

        if (encontradoLinear) {
            System.out.println("Valor encontrado no array desordenado.");
        } else {
            System.out.println("Valor NÃO encontrado no array desordenado.");
        }
        // Exibe o tempo total da busca linear
        System.out.println("Tempo de busca linear: " + (fimBuscaLinear - inicioBuscaLinear) + " ns");

        // --- 3. BUSCA BINÁRIA (ARRAY ORDENADO) ---
        System.out.println("\n--- Comparando com Array Ordenado ---");
        int[] arrayOrdenado = Arrays.copyOf(array, array.length); // Cria uma cópia para não alterar o original

        // Mede o tempo de ordenação
        long inicioOrdenacao = System.nanoTime();
        Arrays.sort(arrayOrdenado); // Ordena o array, um pré-requisito para a busca binária
        long fimOrdenacao = System.nanoTime();

        // Mede o tempo da busca binária
        long inicioBuscaBinaria = System.nanoTime();
        int index = Arrays.binarySearch(arrayOrdenado, numeroParaBuscar); // Realiza a busca binária
        long fimBuscaBinaria = System.nanoTime();

        if (index >= 0) {
            System.out.println("Valor encontrado no array ordenado.");
        } else {
            System.out.println("Valor NÃO encontrado no array ordenado.");
        }
        // Exibe os tempos de ordenação e busca
        System.out.println("Tempo de ordenação: " + (fimOrdenacao - inicioOrdenacao) + " ns");
        System.out.println("Tempo de busca binária: " + (fimBuscaBinaria - inicioBuscaBinaria) + " ns");

        // --- 4. BUSCA EM HASHMAP ---
        System.out.println("\n--- Comparando com HashMap ---");
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        random = new Random(seed); // Reinicia o gerador com a mesma semente para gerar os mesmos números

        // Preenche o HashMap com os mesmos dados do array original
        for (int i = 0; i < tamanho; i++) {
            int valor = random.nextInt(10000);
            hashMap.put(valor, i); // A chave é o número, o valor é seu índice de geração
        }

        // Mede o tempo da busca no HashMap
        long inicioHash = System.nanoTime();
        boolean encontradoHash = hashMap.containsKey(numeroParaBuscar); // Verifica se a chave existe
        long fimHash = System.nanoTime();

        if (encontradoHash) {
            System.out.println("Valor encontrado na HashMap.");
        } else {
            System.out.println("Valor NÃO encontrado na HashMap.");
        }
        // Exibe o tempo total da busca no HashMap
        System.out.println("Tempo de busca na HashMap: " + (fimHash - inicioHash) + " ns");
    }
}