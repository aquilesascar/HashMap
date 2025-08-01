# Análise Comparativa: Hash Table vs. Árvore AVL

Este projeto demonstra, através de implementações práticas em Java, a diferença de desempenho entre uma **Tabela Hash (Hash Table)** e uma **Árvore AVL**, focando em operações de busca.

## O Problema

Queremos encontrar a estrutura de dados mais eficiente para um cenário onde a **velocidade de busca por um elemento específico é a prioridade máxima** e a **ordenação dos dados é irrelevante**. Um exemplo clássico é a implementação de um **cache de sistema**: verificar rapidamente se um dado já está na memória para evitar uma consulta lenta a um banco de dados.

## As Estruturas de Dados

### Tabela Hash (`HashTable.java`)
- **Como funciona?** Utiliza uma `função de hash` para mapear uma chave (o valor que queremos guardar) a um índice de um array. A busca é quase instantânea porque a função de hash nos diz exatamente onde o elemento *deveria* estar.
- **Tratamento de Colisões:** Nossa implementação usa *listas encadeadas*. Se duas chaves diferentes geram o mesmo índice (colisão), elas são armazenadas em uma lista nesse mesmo índice.
- **Complexidade de Tempo (Busca):**
    - **Caso Médio: O(1)** - Tempo constante. A busca leva praticamente o mesmo tempo, não importa quantos elementos existam na tabela.
    - **Pior Caso: O(n)** - Ocorre se todas as chaves colidirem no mesmo índice, transformando a busca em uma busca linear na lista encadeada. É um cenário raro com uma boa função de hash.

### Árvore AVL (`AVLTree.java`)
- **Como funciona?** É uma árvore de busca binária que se **autobalanceia**. Após cada inserção ou remoção, ela realiza `rotações` para garantir que a diferença de altura entre as subárvores de qualquer nó seja no máximo 1. Isso impede que a árvore se "degenere" em uma lista encadeada.
- **Principal Vantagem:** Mantém os dados **sempre ordenados**. Uma travessia na árvore pode retornar todos os elementos em ordem crescente.
- **Complexidade de Tempo (Busca):**
    - **Caso Médio e Pior Caso: O(log n)** - Tempo logarítmico. A cada passo da busca, metade dos elementos restantes é descartada. É extremamente eficiente, mas o número de passos aumenta conforme o número de elementos (`n`) cresce.

## Justificativa da Escolha: Por que a Hash Table é Melhor para Este Cenário?

O arquivo `Comparison.java` executa a comparação definitiva:

1.  **Cenário Simulado:** Populamos ambas as estruturas com centenas de milhares de números aleatórios.
2.  **Medição de Desempenho:** Medimos o tempo (em nanossegundos) que cada estrutura leva para encontrar um número específico.

**Resultados Esperados:**
A busca na **Hash Table será ordens de magnitude mais rápida** que na Árvore AVL.

**Por quê?**

- **Tempo de Busca:** A complexidade **O(1)** da Hash Table supera a **O(log n)** da AVL. Para encontrar um item em 1 milhão, a AVL precisa de cerca de 20 comparações (`log₂(1.000.000) ≈ 20`), enquanto a Hash Table precisa, em média, de apenas 1 (o cálculo do hash).
- **Complexidade da Estrutura:** A Árvore AVL paga um preço pela sua garantia de ordenação. Cada inserção requer verificações de balanceamento e possíveis rotações, que são operações mais complexas do que simplesmente calcular um hash e adicionar a uma lista. Para um problema que não precisa de ordenação, essa complexidade extra é um desperdício de processamento.

**Conclusão Final:**
Para problemas de busca de alta velocidade onde a ordem dos elementos não importa (como caches, dicionários ou verificação de existência), a **Hash Table é a escolha superior devido à sua incomparável eficiência de tempo de busca O(1)**.