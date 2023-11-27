// Lab2

// Объявление класса Product
public class Product {
    // Приватные переменные класса Product: name, description и price
    private String name;
    private String description;
    private double price;

    // Статическая переменная для подсчета количества созданных объектов класса Product
    private static int count = 0;

    // Методы для получения значений приватных переменных (getters)
    public String getName() {
        return name; // Возвращает значение переменной name
    }

    public String getDescription() {
        return description; // Возвращает значение переменной description
    }

    public double getPrice() {
        return price; // Возвращает значение переменной price
    }

    // Методы для задания значений приватных переменных (setters)
    public void setName(String name) {
        this.name = name; // Задает значение переменной name
    }

    public void setDescription(String description) {
        this.description = description; // Задает значение переменной description
    }

    public void setPrice(double price) {
        this.price = price; // Задает значение переменной price
    }

    // Статический метод для получения значения статической переменной count
    public static int getCount() {
        return count; // Возвращает значение переменной count
    }

    // Конструктор по умолчанию
    public Product() {
        this.name = "Default Name"; // Инициализация переменной name
        this.description = "Default Description"; // Инициализация переменной description
        this.price = 0.0; // Инициализация переменной price
        count++; // Увеличение значения переменной count
    }

    // Конструктор с параметрами
    public Product(String name, String description) {
        this.name = name; // Инициализация переменной name
        this.description = description; // Инициализация переменной description
        this.price = 0.0; // Инициализация переменной price
        count++; // Увеличение значения переменной count
    }

    // Конструктор с параметрами
    public Product(String name, String description, double price) {
        this.name = name; // Инициализация переменной name
        this.description = description; // Инициализация переменной description
        this.price = price; // Инициализация переменной price
        count++; // Увеличение значения переменной count
    }

    // Метод для обновления значений переменных name и description
    public void updateDetails(String name, String description) {
        this.name = name; // Обновление значения переменной name
        this.description = description; // Обновление значения переменной description
    }

    // Метод для применения скидки к цене товара
    public void applyDiscount(double discount) {
        this.price -= this.price * discount / 100; // Уменьшение значения переменной price на процент от скидки
    }

    // Метод для вывода информации о товаре
    public void printDetails() {
        System.out.println("Name: " + this.name); // Вывод значения переменной name
        System.out.println("Description: " + this.description); // Вывод значения переменной description
        System.out.println("Price: " + this.price); // Вывод значения переменной price
    }

    // Главный метод класса
    public static void main(String[] args) {
        // Создание объекта product1 с использованием конструктора по умолчанию
        Product product1 = new Product();
        product1.printDetails(); // Вывод информации о product1

        // Создание объекта product2 с использованием конструктора с параметрами
        Product product2 = new Product("Product 2", "This is product 2", 100.0);
        product2.printDetails(); // Вывод информации о product2

        // Обновление информации о product1
        product1.updateDetails("Updated Product 1", "This is updated product 1");
        product1.setPrice(50.0);
        product1.printDetails(); // Вывод обновленной информации о product1

        // Применение скидки к product2
        product2.applyDiscount(10);
        product2.printDetails(); // Вывод информации о product2 после применения скидки


        // Выводим общее количество товаров
        System.out.println("Total products: " + Product.getCount());
    }
}


