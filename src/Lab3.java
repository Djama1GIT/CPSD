interface Place3 { // Определение интерфейса Place3
    String getName(); // Метод для получения имени

    void setName(String name); // Метод для задания имени

    String getDescription(); // Метод для получения описания

    void setDescription(String description); // Метод для задания описания
}


// Определение абстрактного класса Region3, который реализует интерфейс Place3.
abstract class Region3 implements Place3 {
    private String name; // Приватное поле для хранения имени региона.
    private String description; // Приватное поле для хранения описания региона.

    // Конструктор по умолчанию, который инициализирует поля name и description значениями по умолчанию.
    public Region3() {
        this.name = "Default Name"; // Инициализация поля name значением "Default Name".
        this.description = "Default Description"; // Инициализация поля description значением "Default Description".
    }

    // Конструктор с параметрами, который позволяет инициализировать поля name и description заданными значениями.
    public Region3(String name, String description) {
        this.name = name; // Инициализация поля name заданным значением.
        this.description = description; // Инициализация поля description заданным значением.
    }

    // Метод getName, который возвращает значение поля name.
    public String getName() {
        return this.name; // Возвращение значения поля name.
    }

    // Метод setName, который позволяет установить значение для поля name.
    public void setName(String name) {
        this.name = name; // Установка значения для поля name.
    }

    // Метод getDescription, который возвращает значение поля description.
    public String getDescription() {
        return this.description; // Возвращение значения поля description.
    }

    // Метод setDescription, который позволяет установить значение для поля description.
    public void setDescription(String description) {
        this.description = description; // Установка значения для поля description.
    }
}

// Определение класса City3, который наследует от абстрактного класса Region3.
class City3 extends Region3 {
    // Приватное поле для хранения численности населения города.
    private int population;

    // Конструктор по умолчанию, который инициализирует поля родительского класса и поле population значением по умолчанию.
    public City3() {
        super(); // Вызов конструктора родительского класса.
        this.population = 0; // Инициализация поля population значением 0.
    }

    // Конструктор с параметрами, который позволяет инициализировать поля родительского класса и поле population заданными значениями.
    public City3(String name, String description, int population) {
        super(name, description); // Вызов конструктора родительского класса с параметрами.
        this.population = population; // Инициализация поля population заданным значением.
    }

    // Метод getPopulation, который возвращает значение поля population.
    public int getPopulation() {
        return this.population; // Возвращение значения поля population.
    }

    // Метод setPopulation, который позволяет установить значение для поля population.
    public void setPopulation(int population) {
        this.population = population; // Установка значения для поля population.
    }
}

// Определение класса Megapolis3, который наследует от класса City3.
class Megapolis3 extends City3 {
    private int skyscrapers; // Приватное поле для хранения количества небоскребов в мегаполисе.

    // Конструктор по умолчанию, который инициализирует поля родительского класса и поле skyscrapers значением по умолчанию.
    public Megapolis3() {
        // Вызов конструктора родительского класса с параметрами.
        this("Default Name", "Default Description", 0, 0);
    }

    // Конструктор с параметрами, который позволяет инициализировать поля родительского класса и поле skyscrapers заданными значениями.
    public Megapolis3(String name, String description, int population, int skyscrapers) {
        super(name, description, population); // Вызов конструктора родительского класса с параметрами.
        this.skyscrapers = skyscrapers; // Инициализация поля skyscrapers заданным значением.
    }

    // Метод getSkyscrapers, который возвращает значение поля skyscrapers.
    public int getSkyscrapers() {
        return this.skyscrapers; // Возвращение значения поля skyscrapers.
    }

    // Метод setSkyscrapers, который позволяет установить значение для поля skyscrapers.
    public void setSkyscrapers(int skyscrapers) {
        this.skyscrapers = skyscrapers; // Установка значения для поля skyscrapers.
    }
}
// Определение основного класса программы Lab3.
public class Lab3 {
    // Основной метод программы.
    public static void main(String[] args) {
        // Создание объекта класса City3 с именем "City3 1", описанием "This is a City3" и численностью населения 100000.
        City3 City3 = new City3("City3 1", "This is a City3", 100000);
        // Вывод имени города в консоль.
        System.out.println("City3 Name: " + City3.getName());
        // Вывод описания города в консоль.
        System.out.println("City3 Description: " + City3.getDescription());
        // Вывод численности населения города в консоль.
        System.out.println("City3 Population: " + City3.getPopulation());

        // Создание объекта класса Megapolis3 с именем "Megapolis3 1", описанием "This is a Megapolis3", численностью населения 1000000 и количеством небоскребов 200.
        Megapolis3 Megapolis3 = new Megapolis3("Megapolis3 1", "This is a Megapolis3", 1000000, 200);
        // Вывод имени мегаполиса в консоль.
        System.out.println("Megapolis3 Name: " + Megapolis3.getName());
        // Вывод описания мегаполиса в консоль.
        System.out.println("Megapolis3 Description: " + Megapolis3.getDescription());
        // Вывод численности населения мегаполиса в консоль.
        System.out.println("Megapolis3 Population: " + Megapolis3.getPopulation());
        // Вывод количества небоскребов в мегаполисе в консоль.
        System.out.println("Megapolis3 Skyscrapers: " + Megapolis3.getSkyscrapers());

        // Проверка, является ли объект City3 экземпляром интерфейса Place3, и вывод результата в консоль.
        System.out.println("City3 is an instance of Place3: " + (City3 instanceof Place3));

        // Проверка, является ли объект Megapolis3 экземпляром класса City3, и вывод результата в консоль.
        System.out.println("Megapolis3 is an instance of City3: " + (Megapolis3 instanceof City3));
    }
}
