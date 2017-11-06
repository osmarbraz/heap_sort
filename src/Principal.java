/*
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * Programa de Pós-Graduação em Ciências da Computação - PROPG
 * Disciplinas: Projeto e Análise de Algoritmos
 * Prof Alexandre Gonçalves da Silva 
 * Baseado nos slides da aula 15/09/2017 
 */

/**
 * @author Osmar de Oliveira Braz Junior
 */
public class Principal {

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
     * MaxHeapFy Recebe A e i >= 1 tais que subárvores com raízes 2i e 2i + 1.
     * são max-heaps e rearranja A de modo que sub´arvore com raiz i seja um
     * max-heap. 
     * Slide 58 
     * T(h)<= T(h−1) + Theta(5)+O(2) 
     * @param A Vetor a ser ordenado
     * @param n Quantidade de elementos do vetor
     * @param i No da árvore
     */
    private static void maxHeapify(int A[], int n, int i) {
        int maior = 0;
        int e = 2 * i;                          //Theta(1)
        int d = 2 * i + 1;                      //Theta(1)
        if ((e < n) && (A[e] > A[i])) {         //Theta(1)
            maior = e;                          //O(1)
        } else {
            maior = i;                          //O(1)
        }
        if ((d < n) && (A[d] > A[maior])) {     //Theta(1)
            maior = d;                          //O(1)
        }
        if (maior != i) {                       //Theta(1)
            troca(A, i, maior);                 //O(1)
            maxHeapify(A, n, maior);            //Theta(h-1)
        }
    }

    /**
     * Recebe um vetor A e rearranja A para que seja max-heap.
     *
     * @param A Vetor dos nós da árvore
     * @param n Quantidade de elementos da árvore
     */
    private static void maxHeap(int A[], int n) {
        int x;
        if ((n % 2) == 0) {
            x = n / 2;
        } else {
            x = (n - 1) / 2;
        }
        for (int i = x; i >= 0; i--) {
            maxHeapify(A, n, i);
        }
    }

    /**
     * Heapsort rearranja A em ordem crescente. 
     * T(n) = nO(lg n)+Theta(4n+1)= O(n log n) Complexidade no pior caso O(n log n)
     *
     * @param A Vetor dos nós da árvore
     * @param n Quantidade de nós da árvore
     */
    private static void heapsort(int A[], int n) {
        maxHeap(A, n + 1);                        //Theta(n)
        int m = n + 1;                            //Theta(1)
        for (int i = n; i >= 0; i--) {          //Theta(n)
            troca(A, 0, i);                     //Theta(n)
            m = m - 1;                          //Theta(n)
            maxHeapify(A, m, 0);                //nO(log n)
        }
    }

    public static void main(String args[]) {

        //Vetor dos dados    
        int A[] = {50, 70, 60, 90, 10, 30, 20, 40};

        //Fim do vetor
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
