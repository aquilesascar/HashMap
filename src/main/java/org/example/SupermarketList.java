package org.example;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/**
 * Exemplo prático: Lista de Compras de Supermercado Gigante.
 *
 * OBJETIVO:
 * Demonstrar a eficiência absurda de uma Tabela Hash (implementada pela classe HashSet)
 * para verificar a existência de um item em uma coleção de dados massiva.
 *
 * Mesmo com meio milhão de itens, a busca é quase instantânea (O(1)).
 */
public class SupermarketList {

    public static void main(String[] args) {
        // --- 1. CONFIGURAÇÃO ---
        HashSet<String> listaDeCompras = new HashSet<>();
        int numeroDeProdutos = 500000;
        System.out.println("Criando uma lista de compras gigante com " + numeroDeProdutos + " produtos...");

        String[] produtosBase = {
                "leite", "pão", "queijo", "presunto", "iogurte", "manteiga", "café", "açúcar", "sal",
                "arroz", "feijão", "macarrão", "molho de tomate", "óleo de cozinha", "vinagre",
                "frango", "carne moída", "peixe", "ovos", "cebola", "alho", "batata", "tomate",
                "alface", "cenoura", "maçã", "banana", "laranja", "sabão em pó", "detergente",
                "shampoo", "condicionador", "papel higiênico", "pasta de dente"
        };
        Random random = new Random();

        // --- 2. POPULANDO A LISTA ---
        long inicioInsercao = System.nanoTime();
        for (int i = 0; i < numeroDeProdutos; i++) {
            String produto = produtosBase[random.nextInt(produtosBase.length)] + "-" + i;
            listaDeCompras.add(produto);
        }
        long fimInsercao = System.nanoTime();
        System.out.println("Lista criada com sucesso!");
        System.out.println("Tempo para inserir " + numeroDeProdutos + " produtos: " + (fimInsercao - inicioInsercao) / 1_000_000 + " ms");


        // --- 3. BUSCANDO UM PRODUTO ---
        System.out.println("\nAgora, vamos testar a velocidade da busca.");

        // --- MUDANÇA AQUI: MOSTRANDO UMA AMOSTRA DA LISTA ---
        System.out.println("A lista completa é muito grande para ser exibida! Aqui estão os primeiros 20 itens como exemplo:");
        int contador = 0;
        for (String produto : listaDeCompras) {
            if (contador < 20) {
                System.out.println(" - " + produto);
                contador++;
            } else {
                break; // Para de imprimir após 20 itens
            }
        }
        System.out.println("... e outros " + (listaDeCompras.size() - 20) + " itens.");
        // --- FIM DA MUDANÇA ---


        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDigite o nome exato de um produto da lista para buscar (ex: 'pão-123'): ");
        String produtoParaBuscar = scanner.nextLine();

        // Mede o tempo da busca. Esta é a operação chave!
        long inicioBusca = System.nanoTime();
        boolean encontrado = listaDeCompras.contains(produtoParaBuscar);
        long fimBusca = System.nanoTime();

        if (encontrado) {
            System.out.println("\nENCONTRADO! O produto '" + produtoParaBuscar + "' está na lista.");
        } else {
            System.out.println("\nNÃO ENCONTRADO. O produto '" + produtoParaBuscar + "' não está na lista.");
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("Tempo de busca: " + (fimBusca - inicioBusca) + " nanossegundos!");
        System.out.println("-----------------------------------------------------");
        System.out.println("\nPerceba como a busca foi instantânea, mesmo em uma lista com meio milhão de itens.");
        System.out.println("Isso demonstra o poder da Tabela Hash para buscas rápidas, o cenário onde ela é imbatível.");

        scanner.close();
    }
}