import java.util.*;

// Объявление интерфейса Place5 с методами для работы с местами
interface Place5 {
    String getName(); // метод для получения имени

    void setName(String name) throws InvalidNameException5, InvalidValueException5; // метод для установки имени

    String getDescription(); // метод для получения описания

    void setDescription(String description) throws InvalidValueException5; // метод для установки описания
}

// Объявление абстрактного класса Region5, который реализует интерфейс Place5 и интерфейс Cloneable (для клонирования)
abstract class Region5 implements Place5, Cloneable {
    private String name; // поле для хранения имени
    private String description; // поле для хранения описания

    // Конструктор по умолчанию
    public Region5() {
        this.name = "Default Name"; // установка значения по умолчанию для имени
        this.description = "Default Description"; // установка значения по умолчанию для описания
    }

    // Конструктор с параметрами
    public Region5(String name, String description) throws InvalidNameException5 {
        this.name = name; // установка имени
        this.description = description; // установка описания
        if ("".equals(name)) { // Проверяем, что использутся не пустое имя
            throw new InvalidNameException5("Имя не должно быть пустым");
        }
        if ("".equals(description)) { // Проверяем, что использутся не пустое описание
            throw new InvalidNameException5("Описание не должно быть пустым");
        }
        if ("Moscow".equals(name)) { // Проверяем, что использутся не зарезервированное имя
            throw new InvalidNameException5("Зарезервированное имя: " + name);
        }
    }

    // Геттеры и сеттеры для полей
    public String getName() {
        return this.name; // Возвращаем name
    }

    public void setName(String name) throws InvalidNameException5, InvalidValueException5 {
        if (name == null || name.isEmpty()) {
            throw new InvalidValueException5("Имя не может быть пустым");
        }
        if ("Moscow".equals(name)) { // Проверяем, что использутся не зарезервированное имя
            throw new InvalidNameException5("Зарезервированное имя: " + name);
        }
        this.name = name;
    }


    public String getDescription() {
        return this.description; // Возвращаем description
    }

    public void setDescription(String description) throws InvalidValueException5 {
        if (name == null || name.isEmpty()) {
            throw new InvalidValueException5("Имя не может быть пустым");
        }
        this.description = description; // Возвращаем новый description
    }

    // Реализация метода clone() для выполнения глубокого клонирования
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Region5 Region5Clone = (Region5) super.clone(); // Вызываем клонирование в род. классе
        try {
            Region5Clone.setName(getName()); // копируем имя
        } catch (InvalidNameException5 | InvalidValueException5 e) {
            throw new RuntimeException(e);
        }
        try {
            Region5Clone.setDescription(getDescription()); // копируем описание
        } catch (InvalidValueException5 e) {
            throw new RuntimeException(e);
        }
        return Region5Clone; // возвращаем клон
    }

    // Переопределение метода hashCode() для корректного сравнения объектов
    @Override
    public int hashCode() {
        return Objects.hash(name, description);  // Возвращаем хэш
    }

    // Переопределение метода equals() для корректного сравнения объектов
    @Override
    public boolean equals(Object obj) {
        // Если текущий объект и переданный объект являются одним и тем же объектом, возвращаем true
        if (this == obj) return true;
        // Если переданный объект равен null или если класс переданного объекта не совпадает с классом текущего объекта, возвращаем false
        if (obj == null || getClass() != obj.getClass()) return false;
        // Приводим переданный объект к типу Region5
        Region5 Region5 = (Region5) obj;
        // Сравниваем поля name и description текущего объекта и переданного объекта, возвращаем true, если они равны, иначе false
        return Objects.equals(name, Region5.name) &&
                Objects.equals(description, Region5.description);
    }


    // Переопределение метода toString() для корректного представления объекта в виде строки
    @Override
    public String toString() {
        return "Region5{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

// Объявление класса City5, который наследуется от класса Region5 и реализует интерфейс Cloneable
class City5 extends Region5 implements Cloneable {
    // Замена примитивного типа на класс-обертку
    private Integer population; // поле для хранения численности населения

    // Конструктор по умолчанию
    public City5() {
        super(); // вызов конструктора суперкласса
        this.population = 0; // установка значения по умолчанию для численности населения
    }

    // Конструктор с параметрами
    public City5(String name, String description, Integer population) throws InvalidPopulationException5, InvalidValueException5, InvalidNameException5 {
        super(name, description); // вызов конструктора суперкласса с параметрами
        this.population = population; // установка численности населения
        if (population == null || population < 0) { // Проверка на неотрицательность
            throw new InvalidValueException5("Население не может быть отрицательным");
        }
        if (population > 100_000_000) { // Проверка на то, что население не слишком большое
            throw new InvalidPopulationException5("Слишком большое население: " + population);
        }
    }

    // Методы для демонстрации автоупаковки и автораспаковки
    public Integer getPopulation() {
        return this.population; // возвращает число населения
    }

    public void setPopulation(Integer population) throws InvalidPopulationException5, InvalidValueException5 {
        if (population == null || population < 0) { // Проверка на неотрицательность
            throw new InvalidValueException5("Население не может быть отрицательным");
        }
        if (population > 100_000_000) { // Проверка на то, что население не слишком большое
            throw new InvalidPopulationException5("Слишком большое население: " + population);
        }
        this.population = population;
    }


    // Перегруженный метод для демонстрации инициализации из строки
    public void setPopulation(String population) throws InvalidPopulationException5, InvalidValueException5 {
        int in = Integer.valueOf(population); // преобразует строку в число и устанавливает число населения
        if (in < 0) { // Проверка на неотрицательность
            throw new InvalidValueException5("Население не может быть отрицательным");
        }
        if (in > 100_000_000) { // Проверка на то, что население не слишком большое
            throw new InvalidPopulationException5("Слишком большое население: " + population);
        }
        this.population = in;
    }

    // Реализация глубокого клонирования
    @Override
    protected Object clone() throws CloneNotSupportedException {
        City5 City5Clone = (City5) super.clone(); // вызываем клонирование в род. классе
        try { // Обработка исключений
            City5Clone.setPopulation(getPopulation());
        } catch (InvalidPopulationException5 | InvalidValueException5 e) {
            throw new RuntimeException(e);
        }
        return City5Clone; // возвращаем клон
    }

    // Переопределение методов hashCode, equals, toString
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), population); // возвращает хэш-код объекта
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // если объект сравнивается сам с собой, возвращает true
        if (!(obj instanceof City5)) return false; // если объект не является экземпляром City5, возвращает false
        if (!super.equals(obj)) return false; // если объект не равен объекту суперкласса, возвращает false
        City5 City5 = (City5) obj; // приводит obj к типу City5
        return Objects.equals(population, City5.population); // возвращает true, если поле population текущего объекта равно полю population объекта obj
    }

    @Override
    public String toString() {
        return "City5{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", population=" + population +
                '}'; // возвращает строковое представление объекта
    }
}

class Megapolis5 extends City5 implements Cloneable {
    // Замена примитивного типа на класс-обертку
    private Integer skyscrapers; // поле для хранения количества небоскребов

    public Megapolis5() throws InvalidValueException5, InvalidPopulationException5, InvalidNameException5 {
        this("Default Name", "Default Description", 0, 0); // конструктор по умолчанию
    }

    public Megapolis5(String name, String description, Integer population, Integer skyscrapers) throws InvalidValueException5, InvalidPopulationException5, InvalidNameException5 {
        super(name, description, population); // вызов конструктора суперкласса с параметрами
        this.skyscrapers = skyscrapers; // установка количества небоскребов
        if (skyscrapers == null || skyscrapers < 0) { // Проверка на неотрицательность
            throw new InvalidValueException5("Количество небоскребов не может быть отрицательным");
        }
    }

    // Методы для демонстрации автоупаковки и автораспаковки
    public Integer getSkyscrapers() {
        return this.skyscrapers; // возвращает количество небоскребов
    }

    public void setSkyscrapers(Integer skyscrapers) throws InvalidValueException5 {
        if (skyscrapers == null || skyscrapers < 0) { // Проверка на неотрицательность
            throw new InvalidValueException5("Количество небоскребов не может быть отрицательным");
        }
        this.skyscrapers = skyscrapers;
    }


    // Перегруженный метод для демонстрации инициализации из строки
    public void setSkyscrapers(String skyscrapers) throws InvalidValueException5 {
        int in = Integer.valueOf(skyscrapers); // преобразует строку в число и устанавливает количество небоскребов
        if (in < 0) { // Проверка на неотрицательность
            throw new InvalidValueException5("Количество небоскребов не может быть отрицательным");
        }
        this.skyscrapers = in;
    }

    // Реализация глубокого клонирования
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Megapolis5 Megapolis5Clone = (Megapolis5) super.clone(); // Вызываем клонирование в род. классе
        try {
            Megapolis5Clone.setSkyscrapers(getSkyscrapers()); // копируем кол-во небоскребов
        } catch (InvalidValueException5 e) {
            throw new RuntimeException(e);
        }
        return Megapolis5Clone; // возвращаем клон
    }

    // Переопределение методов hashCode, equals, toString
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), skyscrapers); // возвращает хэш-код объекта
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // если объект сравнивается сам с собой, возвращает true
        if (!(obj instanceof Megapolis5))
            return false; // если объект не является экземпляром Megapolis5, возвращает false
        if (!super.equals(obj)) return false; // если объект не равен объекту суперкласса, возвращает false
        Megapolis5 Megapolis5 = (Megapolis5) obj; // приводит obj к типу Megapolis5
        return Objects.equals(skyscrapers, Megapolis5.skyscrapers); // возвращает true, если поле skyscrapers текущего объекта равно полю skyscrapers объекта obj
    }

    @Override
    public String toString() {
        return "Megapolis5{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", population=" + getPopulation() +
                ", skyscrapers=" + skyscrapers +
                '}'; // возвращает строковое представление объекта
    }
}
// Создание класса исключения для недействительных значений
class InvalidValueException5 extends Exception {
    // Конструктор класса исключения принимает сообщение об ошибке в качестве аргумента
    public InvalidValueException5(String message) {
        // Вызов конструктора родительского класса Exception с сообщением об ошибке
        super(message);
    }
}

// Создание класса исключения для недействительных имен
class InvalidNameException5 extends Exception {
    // Конструктор класса исключения принимает сообщение об ошибке в качестве аргумента
    public InvalidNameException5(String message) {
        // Вызов конструктора родительского класса Exception с сообщением об ошибке
        super(message);
    }
}

// Создание класса исключения для недействительного населения
class InvalidPopulationException5 extends Exception {
    // Конструктор класса исключения принимает сообщение об ошибке в качестве аргумента
    public InvalidPopulationException5(String message) {
        // Вызов конструктора родительского класса Exception с сообщением об ошибке
        super(message);
    }
}

// Главный класс программы
public class Lab5 {
    // Инициализация сканера для чтения пользовательского ввода
    private static final Scanner scanner = new Scanner(System.in);
    // Инициализация списка мест
    private static final List<Place5> Place5s = new ArrayList<>();

    // Главный метод программы
    public static void main(String[] args) {
        // Переменная для контроля выхода из программы
        boolean exit = false;

        // Цикл выполняется до тех пор, пока пользователь не решит выйти
        while (!exit) {
            // Вывод меню на экран
            System.out.println("\n1. Создать City5");
            System.out.println("2. Создать Megapolis5");
            System.out.println("3. Просмотреть список созданных объектов");
            System.out.println("4. Выход");

            // Запрос на ввод команды
            System.out.print("Введите номер команды: ");
            String command = scanner.nextLine();

            // Обработка введённой команды
            switch (command) {
                case "1":
                    // Создание города
                    createCity5();
                    break;
                case "2":
                    // Создание мегаполиса
                    createMegapolis5();
                    break;
                case "3":
                    // Отображение списка мест
                    displayPlace5s();
                    break;
                case "4":
                    // Выход из программы
                    exit = true;
                    break;
                default:
                    // Сообщение об ошибке в случае неверной команды
                    System.out.println("Неверная команда.");
            }
        }
    }

    // Метод для создания города
    private static void createCity5() {
        try {
            // Запрос на ввод имени города
            System.out.print("Введите имя города: ");
            String name = scanner.nextLine();
            // Запрос на ввод описания города
            System.out.print("Введите описание города: ");
            String description = scanner.nextLine();
            // Запрос на ввод населения города
            System.out.print("Введите население города: ");
            Integer population = scanner.nextInt();

            // Создание объекта города и добавление его в список мест
            City5 City5 = new City5(name, description, population);
            Place5s.add(City5);
        } catch (InvalidValueException5 e) {
            // Обработка исключения недействительного значения
            System.out.println("Ошибка: " + e.getMessage());
        } catch (InputMismatchException e) {
            // Обработка исключения несоответствия ввода
            System.out.println("Ошибка: введено некорректное значение");
            scanner.nextLine(); // очистка буфера сканера
        } catch (InvalidPopulationException5 e) {
            // Обработка исключения недействительного населения
            throw new RuntimeException(e);
        } catch (InvalidNameException5 e) {
            throw new RuntimeException(e);
        }
    }

    // Метод для создания мегаполиса
    private static void createMegapolis5() {
        try {
            // Запрос на ввод имени мегаполиса
            System.out.print("Введите имя мегаполиса: ");
            String name = scanner.nextLine();
            // Запрос на ввод описания мегаполиса
            System.out.print("Введите описание мегаполиса: ");
            String description = scanner.nextLine();
            // Запрос на ввод населения мегаполиса
            System.out.print("Введите население мегаполиса: ");
            Integer population = scanner.nextInt();
            // Запрос на ввод количества небоскребов в мегаполисе
            System.out.print("Введите количество небоскребов в мегаполисе: ");
            Integer skyscrapers = scanner.nextInt();

            // Создание объекта мегаполиса и добавление его в список мест
            Megapolis5 Megapolis5 = new Megapolis5(name, description, population, skyscrapers);
            Place5s.add(Megapolis5);
        } catch (InvalidValueException5 e) {
            // Обработка исключения недействительного значения
            System.out.println("Ошибка: " + e.getMessage());
        } catch (InputMismatchException e) {
            // Обработка исключения несоответствия ввода
            System.out.println("Ошибка: введено некорректное значение");
            scanner.nextLine(); // очистка буфера сканера
        } catch (InvalidPopulationException5 e) {
            // Обработка исключения недействительного населения
            System.out.println("Ошибка: " + e.getMessage());
        } catch (InvalidNameException5 e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    // Метод для отображения списка мест
    private static void displayPlace5s() {
        // Прохождение по всем местам в списке
        for (Place5 Place5 : Place5s) {
            // Вывод информации о месте
            System.out.println(Place5);
        }
    }
}