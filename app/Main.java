package app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parque parque = new Parque(); // Instância da classe Parque para gerenciar os visitantes e atrações do parque.
        parque.executar();
        scanner.close(); // Fechando o scanner para liberar recursos.
    }
}
