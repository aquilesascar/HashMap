package org.example;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int tamanho = 1000;
        int[] array = new int[tamanho];
        long seed = 42;
        Random random = new Random(seed);


        for (int i = 0; i < tamanho; i++) {
            array[i] = random.nextInt(10000);
        }

        System.out.println("Array gerado:");
        System.out.println(Arrays.toString(array));


        //não ordenado
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um número para buscar: ");
        int x = sc.nextInt();

        long inicioDaBusca = System.nanoTime();
        boolean encontrado = false;
        for (int num : array) {
            if (num == x) {
                encontrado = true;
                break;
            }
        }
        long fimDaBusca = System.nanoTime();

        if (encontrado) {
            System.out.println("Valor encontrado no array desordenado.");
        } else {
            System.out.println("Valor não encontrado no array desordenado.");
        }
        System.out.println("Tempo de busca linear: " + (fimDaBusca - inicioDaBusca) + " ns\n");

        int[] arrayOrdenado = Arrays.copyOf(array, array.length);

        long inicioOrdenacao = System.nanoTime();
        Arrays.sort(arrayOrdenado);
        long fimOrdenacao = System.nanoTime();

        long inicioBuscaBinaria = System.nanoTime();
        int index = Arrays.binarySearch(arrayOrdenado, x);
        long fimBuscaBinaria = System.nanoTime();

        if (index >= 0) {
            System.out.println("Valor encontrado no array ordenado.");
        } else {
            System.out.println("Valor não encontrado no array ordenado.");
        }
        System.out.println("Tempo de ordenação: " + (fimOrdenacao - inicioOrdenacao) + " ns");
        System.out.println("Tempo de busca binária: " + (fimBuscaBinaria - inicioBuscaBinaria) + " ns\n");

        //hasmap
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        random = new Random(seed);
        for (int i = 0; i < tamanho; i++) {
            int valor = random.nextInt(10000);
            hashMap.put(valor, i);
        }

        //buscando valor no hashmap
        long inicioHash = System.nanoTime();
        boolean encontradoHash = hashMap.containsKey(x);
        long fimHash = System.nanoTime();

        if (encontradoHash) {
            System.out.println("Valor encontrado na HashMap.");
        } else {
            System.out.println("Valor não encontrado na HashMap.");
        }
        System.out.println("Tempo de busca na HashMap: " + (fimHash - inicioHash) + " ns");
    }
}





