package org.solver;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        System.out.printf("Start...\n");
        Scanner in = new Scanner(System.in);
        System.out.print("Input numbers of nodes:");
        int N = in.nextInt();

        Scheme scheme = new Scheme();
        System.out.print("Create scheme\n");
        for(int i = 0; i < N; i++){
            scheme.AddNewNode(i);
            System.out.print("Add new node to scheme\n");
        }
        System.out.print("Input numbers of elements: ");
        int M = in.nextInt();

        for(int i = 0; i < M; i++){

            System.out.print("Input type of new element:");
            int type_elem;
            type_elem = in.nextInt();


            System.out.print("Input start node of new element:");
            int start = in.nextInt();

            System.out.print("Input finish node of new element:");
            int finish = in.nextInt();

            scheme.AddNewElem(type_elem, start, finish);
            System.out.print("Add new element to scheme");

        }




    }
}