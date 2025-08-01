package org.example;

/**
 * Implementação de uma Árvore AVL (Árvore de Busca Binária Autobalanceada).
 * Garante que a árvore permaneça balanceada, resultando em buscas, inserções e remoções
 * com complexidade de tempo O(log n).
 */
public class AVLTree {

    // Nó da árvore
    class Node {
        int key, height;
        Node left, right;

        Node(int d) {
            key = d;
            height = 1; // A altura de um novo nó é sempre 1
        }
    }

    Node root;

    // Retorna a altura de um nó (ou 0 se for nulo)
    private int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    // Calcula o fator de balanceamento de um nó
    private int getBalance(Node n) {
        if (n == null) {
            return 0;
        }
        return height(n.left) - height(n.right);
    }

    // --- ROTAÇÕES PARA BALANCEAMENTO ---

    // Rotação para a direita
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Realiza a rotação
        x.right = y;
        y.left = T2;

        // Atualiza as alturas dos nós rotacionados
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Retorna a nova raiz da subárvore
        return x;
    }

    // Rotação para a esquerda
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Realiza a rotação
        y.left = x;
        x.right = T2;

        // Atualiza as alturas
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Retorna a nova raiz
        return y;
    }

    // --- MÉTODOS PÚBLICOS ---

    /**
     * Insere um valor na árvore AVL.
     * @param key O valor a ser inserido.
     */
    public void insert(int key) {
        root = insertRec(root, key);
    }

    // Função recursiva de inserção
    private Node insertRec(Node node, int key) {
        // 1. Inserção padrão de uma Árvore de Busca Binária
        if (node == null) {
            return (new Node(key));
        }

        if (key < node.key) {
            node.left = insertRec(node.left, key);
        } else if (key > node.key) {
            node.right = insertRec(node.right, key);
        } else {
            // Chaves duplicadas não são permitidas na nossa implementação
            return node;
        }

        // 2. Atualiza a altura do nó atual
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 3. Calcula o fator de balanceamento para verificar se o nó ficou desbalanceado
        int balance = getBalance(node);

        // 4. Se o nó estiver desbalanceado, executa uma das 4 rotações possíveis

        // Caso Rotação Simples à Direita (Left Left Case)
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        // Caso Rotação Simples à Esquerda (Right Right Case)
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        // Caso Rotação Dupla à Direita (Left Right Case)
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Caso Rotação Dupla à Esquerda (Right Left Case)
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Se a árvore já estava balanceada, retorna o nó sem modificações
        return node;
    }

    /**
     * Busca um valor na árvore.
     * @param key O valor a ser buscado.
     * @return true se o valor for encontrado, false caso contrário.
     */
    public boolean search(int key) {
        return searchRec(root, key);
    }

    // Função recursiva de busca
    private boolean searchRec(Node node, int key) {
        // Caso base: nó é nulo ou a chave foi encontrada
        if (node == null) {
            return false;
        }
        if (node.key == key) {
            return true;
        }

        // Se a chave for menor, busca na subárvore esquerda
        if (key < node.key) {
            return searchRec(node.left, key);
        }
        // Se for maior, busca na subárvore direita
        return searchRec(node.right, key);
    }
}