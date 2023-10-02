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
        for(int i = 1; i < N+1; i++){
            scheme.AddNewNode(i);
            System.out.print("Add new node to scheme\n");
        }
        System.out.print("Input numbers of elements: ");
        int M = in.nextInt();

        for(int i = 0; i < M; i++){

            System.out.print("Input type of new element:\n");
            int type_elem;
            type_elem = in.nextInt();

            System.out.print("Input value of new element:\n");
            double value;
            value = in.nextDouble();

            System.out.print("Input start node of new element:\n");
            int start = in.nextInt();

            System.out.print("Input finish node of new element:\n");
            int finish = in.nextInt();
            in.nextLine();
            System.out.print("Input name of new element:\n");
            String name = in.nextLine();

//            System.out.printf("%d, %d, %d, %s, %f",type_elem, start, finish, name, value);
            scheme.AddNewElem(type_elem, start, finish, name, value);
            System.out.print("Add new element to scheme");



        }
        scheme.printInfo();




    }
}