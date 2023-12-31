// Lab1

import java.util.Random; // Импорт класса Random из пакета java.util

public class Main { // Определение класса Main
    public static void main(String[] args) { // Определение метода main, который является точкой входа в программу
        System.out.println("Задача №1");// Вывод строки "Задача №1"
        divisionWithRemainder(); // Вызов метода divisionWithRemainder() - он реализует первую задачу

        System.out.println("\nЗадача №2");// Вывод строки "Задача №2"
        sumOfTwoDigits(); // Вызов метода sumOfTwoDigits() - он реализует вторую задачу

        System.out.println("\nЗадача №3"); // Вывод строки "Задача №3"
        roundNumber(); // Вызов метода roundNumber() - он реализует третью задачу


        System.out.println("\nЗадача №4"); // Вывод строки "Задача №4"
        sumOfThreeDigits(); // Вызов метода sumOfThreeDigits() - он реализует четвертую задачу


        System.out.println("\nЗадача №5"); // Вывод строки "Задача №5"
        matrix(); // Вызов метода matrix() - он реализует пятую задачу


        System.out.println("\nЗадача №6"); // Вывод строки "Задача №6"
        primes(); // Вызов метода primes() - он реализует шестую задачу
    }

    // Здесь будет код всех задач

    // Объявление статического метода divisionWithRemainder без возвращаемого значения
    public static void divisionWithRemainder() {
        // Объявление и инициализация целочисленной переменной q со значением 21
        int q = 21;
        // Объявление и инициализация целочисленной переменной w со значением 28
        int w = 28;
        // Вычисление целочисленного деления q на w и сохранение результата в переменной result
        int result = q / w;
        // Вычисление остатка от деления q на w и сохранение результата в переменной remainder
        int remainder = q % w;
        // Вывод на экран результата деления и остатка от деления
        System.out.println(q + " / " + w + " = " + result + " и " + remainder + " в остатке");
    }


    // Объявление статического метода sumOfTwoDigits без возвращаемого значения
    public static void sumOfTwoDigits() {
        // Объявление и инициализация целочисленной переменной n со значением 45
        int n = 45;
        // Вывод на экран строки, которая указывает, что следует вычислить сумму цифр двухзначного числа
        System.out.print("Сумма цифр двухзначного числа " + n + ": ");
        // Объявление и инициализация целочисленной переменной sum со значением 0
        int sum = 0;
        // Цикл while продолжается до тех пор, пока n не станет равным 0
        while (n != 0) {
            // Добавление последней цифры числа n к сумме
            sum += n % 10;
            // Удаление последней цифры числа n
            n /= 10;
        }
        // Вывод на экран суммы цифр двухзначного числа
        System.out.println(sum);
    }

    // Объявление статического метода roundNumber без возвращаемого значения
    public static void roundNumber() {
        // Объявление и инициализация переменной n типа double со значением 4.7
        double n = 4.7;
        // Округление числа n до ближайшего целого числа с помощью метода Math.round и преобразование результата в int
        int rounded = (int) Math.round(n);
        // Вывод на экран строки с округленным числом
        System.out.println("Округленное число " + n + ": " + rounded);
    }

    public static void sumOfThreeDigits() { // Объявление статического метода
        int n = 457; // Объявление и инициализация переменной n со значением 457
        System.out.print("Сумма цифр трехзначного числа " + n + ": "); // Вывод сообщения на экран

        int sum = 0; // Объявление и инициализация переменной sum со значением 0
        while (n != 0) { // Цикл while продолжается, пока n не станет равным 0
            sum += n % 10; // Добавление последней цифры числа n к сумме
            n /= 10; // Удаление последней цифры числа n
        }
        System.out.println(sum); // Вывод суммы на экран
    }

    public static void matrix() { // Объявление статического метода
        final int N = 5; // Объявление и инициализация константы N, количество строк
        final int M = 5; // Объявление и инициализация константы M, количество столбцов
        int[][] array = new int[N][M]; // Создание двумерного массива размером NxM
        Random random = new Random(); // Создание объекта класса Random для генерации случайных чисел

        // Заполняем массив случайными числами и выводим его на экран
        for (int i = 0; i < N; i++) { // Цикл по строкам массива
            for (int j = 0; j < M; j++) { // Цикл по столбцам массива
                array[i][j] = random.nextInt(11) - 5; // Генерируем случайное число от -5 до 5 и присваиваем его элементу массива
                System.out.print(array[i][j] + " "); // Выводим элемент массива на экран
            }
            System.out.println(); // Переходим на новую строку после вывода элементов строки
        }

        int maxProductIndex = 0; // Объявление и инициализация переменной, хранящей индекс строки с максимальным произведением
        int maxProduct = 1; // Объявление и инициализация переменной, хранящей максимальное произведение
        // Вычисляем произведение элементов каждой строки
        for (int i = 0; i < N; i++) { // Цикл по строкам массива
            int product = 1; // Объявление и инициализация переменной, хранящей текущее произведение
            for (int j = 0; j < M; j++) { // Цикл по столбцам массива
                product *= array[i][j]; // Умножаем текущее произведение на элемент массива
            }
            // Если текущее произведение больше максимального по модулю, обновляем максимальное произведение и индекс
            if (Math.abs(product) > Math.abs(maxProduct)) {
                maxProduct = product; // Обновляем максимальное произведение
                maxProductIndex = i; // Обновляем индекс строки с максимальным произведением
            }
        }

        System.out.println("Индекс строки с наибольшим по модулю произведением элементов: " + maxProductIndex); // Выводим индекс строки с максимальным произведением на экран
    }

    public static void primes() { // Объявление статического метода
        int[] array = {10, 2, 3, 4, 5, 6, 7, 8, 9}; // Объявление и инициализация массива

        for (int i = 0; i < array.length; i++) { // Цикл по элементам массива
            if (isPrime(array[i])) { // Если элемент является простым числом
                System.out.println("Индекс простого числа " + array[i] + ": " + i); // Выводим индекс и значение простого числа
            }
        }
    }

    public static boolean isPrime(int n) { // Объявление статического метода, проверяющего, является ли число простым
        if (n <= 1) { // Если число меньше или равно 1
            return false; // Оно не является простым
        }

        for (int i = 2; i <= Math.sqrt(n); i++) { // Цикл по делителям числа
            if (n % i == 0) { // Если число делится на делитель без остатка
                return false; // Оно не является простым
            }
        }

        return true; // Если число прошло все проверки, оно является простым
    }

}
