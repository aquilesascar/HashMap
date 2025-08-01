package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int tamanho = 1000;
        int[] array = new int[tamanho];
        long seed = 42;
        Random random = new Random(seed);

        for (int i = 0; i < tamanho; i++) {
            array[i] = random.nextInt(10000);
        }

        System.out.println("Array original gerado:");
        System.out.println(Arrays.toString(array));

        Scanner sc = new Scanner(System.in);
        System.out.print("\nDigite um número para buscar: ");
        int numeroParaBuscar = sc.nextInt();
        sc.close();

        //BUSCA LINEAR (ARRAY DESORDENADO)
        System.out.println("\n--- Comparando com Array Desordenado ---");
        long inicioBuscaLinear = System.nanoTime();

        boolean encontradoLinear = false;
        //percorre cada elemento do array um por um
        for (int num : array) {
            if (num == numeroParaBuscar) {
                encontradoLinear = true;
                break;
            }
        }
        long fimBuscaLinear = System.nanoTime();

        if (encontradoLinear) {
            System.out.println("Valor encontrado no array desordenado.");
        } else {
            System.out.println("Valor não encontrado no array desordenado.");
        }
        System.out.println("Tempo de busca linear: " + (fimBuscaLinear - inicioBuscaLinear) + " ns");

        //BUSCA BINÁRIA (ARRAY ORDENADO)
        System.out.println("\n--- Comparando com Array Ordenado ---");
        int[] arrayOrdenado = Arrays.copyOf(array, array.length);

        long inicioOrdenacao = System.nanoTime();
        Arrays.sort(arrayOrdenado); //ordena o array, um pré-requisito para a busca binária
        long fimOrdenacao = System.nanoTime();

        long inicioBuscaBinaria = System.nanoTime();
        int index = Arrays.binarySearch(arrayOrdenado, numeroParaBuscar);
        long fimBuscaBinaria = System.nanoTime();

        if (index >= 0) {
            System.out.println("Valor encontrado no array ordenado.");
        } else {
            System.out.println("Valor não encontrado no array ordenado.");
        }
        System.out.println("Tempo de ordenação: " + (fimOrdenacao - inicioOrdenacao) + " ns");
        System.out.println("Tempo de busca binária: " + (fimBuscaBinaria - inicioBuscaBinaria) + " ns");

        //BUSCA EM HASHMAP ---
        System.out.println("\n--- Comparando com HashMap ---");
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        random = new Random(seed);

        for (int i = 0; i < tamanho; i++) {
            int valor = random.nextInt(10000);
            hashMap.put(valor, i); // A chave é o número, o valor é seu índice de geração
        }

        long inicioHash = System.nanoTime();
        boolean encontradoHash = hashMap.containsKey(numeroParaBuscar); //verifica se a chave existe
        long fimHash = System.nanoTime();

        if (encontradoHash) {
            System.out.println("Valor encontrado na HashMap.");
        } else {
            System.out.println("Valor não encontrado na HashMap.");
        }
        System.out.println("Tempo de busca na HashMap: " + (fimHash - inicioHash) + " ns");
    }
}