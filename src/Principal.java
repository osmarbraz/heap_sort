/*
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * Programa de Pós-Graduação em Ciências da Computação - PROPG
 * Disciplinas: Projeto e Análise de Algoritmos
 * Prof Alexandre Gonçalves da Silva 
 * Baseado nos slides 58 da aula 15/09/2017 
 *
 * Conceitos:
 * O Heapsort é um algoritmo de ordenação que usa uma estrutura de dados sofisticada chamada heap.
 * heap: árvore binária ordenada
 * Heaps podem ser utilizados para implementar filas de prioridade que são extremamente úteis em outros algoritmos.
 * Um heap é um vetor A que simula uma árvore binária completa, com exceção possivelmente do último nível.
 * Considere um vetor A[1...n] representando um heap.
 * Cada posição do vetor corresponde a um nó do heap.
 * O pai de um nó i é (i/2).
 * O nó 1 não tem pai, pois ele é a raiz da heap.
 *
 * maxHeap: heap onde nós pais são maiores que os respectivos nós filhos
 * maxHeapiFy: rearranja os elementos de uma heap.
 * 
 **/

/**
 * @author Osmar de Oliveira Braz Junior
 */
public class Principal {

    /**
     * O piso (= floor) de um número real x é o resultado do arredondamento de x para baixo. 
     * Em outras palavras, o piso de x é o único número inteiro i tal que 
     * i<=x<i+1.
     * Ex. O piso de 3.9 é 3
     * 
     * Em java pode ser utilizando Math.floor(double)
     * 
     * @param x Numero real a ser cálculado o piso.
     * @return um valor inteiro com o piso de x.
     */
    public static int piso(double x) {
        //Pego a parte inteira de x
        int parteInteira = (int) x;
        //Pego a parte fracionária de x
        double parteFracionaria = x - parteInteira;
        //Retorno x subtraindo a parte fracionaria 
        return (int) (x - parteFracionaria);
    }
    
    /**
     * Realiza a troca de posição de dois elementos do vetor.
     *
     * @param A Vetor que contem os dados
     * @param i Primeira posição de torca
     * @param j Segunda posição de torca
     */
    public static void troca(int[] A, int i, int j) {
        int aux = A[i];
        A[i] = A[j];
        A[j] = aux;
    }

    /**
     * MaxHeapiFy.
     * Recebe A e i >= 1 tais que subárvores com raízes 2i e 2i + 1.
     * São max-heaps e rearranja A de modo que subárvore com raiz i seja um max-heap. 
     * Organiza os elementos da heap, coloca o maior na raiz.
     * 
     * Slide 58 aula 15/09/2017 
     * T(h)<= T(h−1) + Theta(5)+O(2) 
     * @param A Vetor a ser ordenado
     * @param n Quantidade de elementos do vetor
     * @param i Representa a posição do nó Raiz da árvore
     */
    private static void maxHeapify(int A[], int n, int i) {
        //Armazena o maior elemento
        int maior = 0;
        //Filho da esquerda
        int esquerda = 2 * i;                          //Theta(1)
        //Filho da direita
        int direita = 2 * i + 1;                      //Theta(1)
        
        if ((esquerda < n) && (A[esquerda] > A[i])) {         //Theta(1)
            maior = esquerda;                          //O(1)
        } else {
            maior = i;                          //O(1)
        }
        if ((direita < n) && (A[direita] > A[maior])) {     //Theta(1)
            maior = direita;                          //O(1)
        }
        if (maior != i) {                       //Theta(1)
            troca(A, i, maior);                 //O(1)
            maxHeapify(A, n, maior);            //Theta(h-1)
        }
    }

    /**
     * Recebe um vetor A e rearranja A para que seja max-heap.
     * Em maxHeap os nós pais são maiores que os respectivos nós filhos
     *
     * @param A Vetor dos nós da árvore
     * @param n Quantidade de elementos da árvore
     */
    private static void constroiMaxHeap(int A[], int n) {
        for (int i = piso(n/2); i >= 0; i--) {
            maxHeapify(A, n, i);
        }
    }

    /**
     * Heapsort.
     * Rearranja A em ordem crescente. 
     * Algoritmos de ordenação podem ser ou não in-place ou estáveis.
     * Um algoritmo de ordenação é in-place se a memória adicional requerida é 
     * independente do tamanho do vetor que está sendo ordenado.
     * O heapsort é in-place 
     * 
     * T(n) = nO(lg n)+Theta(4n+1)= O(n log n) 
     * Complexidade no pior caso O(n log n)
     *
     * @param A Vetor dos nós da árvore
     * @param n Quantidade de nós da árvore
     */
    private static void heapsort(int A[], int n) {
        constroiMaxHeap(A, n + 1);                      //Theta(n)
        int m = n + 1;                          //Theta(1)
        for (int i = n; i >= 0; i--) {          //Theta(n)
            troca(A, 0, i);                     //Theta(n)
            m = m - 1;                          //Theta(n)
            maxHeapify(A, m, 0);                //nO(log n)
        }
    }

    public static void main(String args[]) {

        //Vetor dos dados    
        int A[] = {50, 70, 60, 90, 10, 30, 20, 40};
        
        // r representa o número de elementos
	// Logo, o índice deve variar de 0 a r-1 elementos.
	// Portanto, o último índice é r-1
        int r = A.length - 1;

        System.out.println(">>> HeapSort <<<");
        System.out.println("Original: ");
        for (int i = 0; i <= r; i++) {
            System.out.println((i) + " - " + A[i]);
        }

        heapsort(A, r);

        System.out.println("Depois: ");
        for (int i = 0; i <= r; i++) {
            System.out.println((i) + " - " + A[i]);
        }
    }
}
