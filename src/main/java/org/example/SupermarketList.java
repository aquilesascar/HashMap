package org.example;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class SupermarketList {

    public static void main(String[] args) {
        HashSet<String> listaDeCompras = new HashSet<>();
        int numeroDeProdutos = 500000;
        System.out.println("Criando uma lista de compras gigante com 500000 produtos...");

        String[] produtosBase = {
                "leite", "pão", "queijo", "presunto", "iogurte", "manteiga", "café", "açúcar", "sal",
                "arroz", "feijão", "macarrão", "molho de tomate", "óleo de cozinha", "vinagre",
                "frango", "carne moída", "peixe", "ovos", "cebola", "alho", "batata", "tomate",
                "alface", "cenoura", "maçã", "banana", "laranja", "sabão em pó", "detergente",
                "shampoo", "condicionador", "papel higiênico", "pasta de dente"
        };
        Random random = new Random();

        long inicioInsercao = System.nanoTime();
        for (int i = 0; i < numeroDeProdutos; i++) {
            String produto = produtosBase[random.nextInt(produtosBase.length)] + "-" + i;
            listaDeCompras.add(produto);
        }
        long fimInsercao = System.nanoTime();
        System.out.println("Lista criada com sucesso!");
        System.out.println("Tempo para inserir " + numeroDeProdutos + " produtos: " + (fimInsercao - inicioInsercao) / 1_000_000 + " ms");

        //buscando produto
        System.out.println("\nAgora, vamos testar a velocidade da busca.");

        System.out.println("A lista completa é muito grande para ser exibida! Aqui estão os primeiros 20 itens como exemplo:");
        int contador = 0;
        for (String produto : listaDeCompras) {
            if (contador < 20) {
                System.out.println(" - " + produto);
                contador++;
            } else {
                break;
            }
        }
        System.out.println("... e outros " + (listaDeCompras.size() - 20) + " itens.");

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDigite o nome exato de um produto da lista para buscar (ex: 'pão-123'): ");
        String produtoParaBuscar = scanner.nextLine();

        long inicioBusca = System.nanoTime();
        boolean encontrado = listaDeCompras.contains(produtoParaBuscar);
        long fimBusca = System.nanoTime();

        if (encontrado) {
            System.out.println("\nENCONTRADO! O produto '" + produtoParaBuscar + "' está na lista.");
        } else {
            System.out.println("\nNÃO ENCONTRADO. O produto '" + produtoParaBuscar + "' não está na lista.");
        }

        System.out.println("------------------------------------------");
        System.out.println("Tempo de busca: " + (fimBusca - inicioBusca) + " nanossegundos!");

        scanner.close();
    }
}