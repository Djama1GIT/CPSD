import java.util.*;

// Объявление интерфейса Place с методами для работы с местами
interface Place {
    String getName(); // метод для получения имени

    void setName(String name) throws InvalidNameException, InvalidValueException; // метод для установки имени

    String getDescription(); // метод для получения описания

    void setDescription(String description) throws InvalidValueException; // метод для установки описания
}

// Объявление абстрактного класса Region, который реализует интерфейс Place и интерфейс Cloneable (для клонирования)
abstract class Region implements Place, Cloneable {
    private String name; // поле для хранения имени
    private String description; // поле для хранения описания

    // Конструктор по умолчанию
    public Region() {
        this.name = "Default Name"; // установка значения по умолчанию для имени
        this.description = "Default Description"; // установка значения по умолчанию для описания
    }

    // Конструктор с параметрами
    public Region(String name, String description) throws InvalidNameException {
        this.name = name; // установка имени
        this.description = description; // установка описания
        if ("".equals(name)) { // Проверяем, что использутся не пустое имя
            throw new InvalidNameException("Имя не должно быть пустым");
        }
        if ("".equals(description)) { // Проверяем, что использутся не пустое описание
            throw new InvalidNameException("Описание не должно быть пустым");
        }
        if ("Moscow".equals(name)) { // Проверяем, что использутся не зарезервированное имя
            throw new InvalidNameException("Зарезервированное имя: " + name);
        }
    }

    // Геттеры и сеттеры для полей
    public String getName() {
        return this.name; // Возвращаем name
    }

    public void setName(String name) throws InvalidNameException, InvalidValueException {
        if (name == null || name.isEmpty()) {
            throw new InvalidValueException("Имя не может быть пустым");
        }
        if ("Moscow".equals(name)) { // Проверяем, что использутся не зарезервированное имя
            throw new InvalidNameException("Зарезервированное имя: " + name);
        }
        this.name = name;
    }


    public String getDescription() {
        return this.description; // Возвращаем description
    }

    public void setDescription(String description) throws InvalidValueException {
        if (name == null || name.isEmpty()) {
            throw new InvalidValueException("Имя не может быть пустым");
        }
        this.description = description; // Возвращаем новый description
    }

    // Реализация метода clone() для выполнения глубокого клонирования
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Region regionClone = (Region) super.clone(); // Вызываем клонирование в род. классе
        try {
            regionClone.setName(getName()); // копируем имя
        } catch (InvalidNameException | InvalidValueException e) {
            throw new RuntimeException(e);
        }
        try {
            regionClone.setDescription(getDescription()); // копируем описание
        } catch (InvalidValueException e) {
            throw new RuntimeException(e);
        }
        return regionClone; // возвращаем клон
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
        // Приводим переданный объект к типу Region
        Region region = (Region) obj;
        // Сравниваем поля name и description текущего объекта и переданного объекта, возвращаем true, если они равны, иначе false
        return Objects.equals(name, region.name) &&
                Objects.equals(description, region.description);
    }


    // Переопределение метода toString() для корректного представления объекта в виде строки
    @Override
    public String toString() {
        return "Region{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

// Объявление класса City, который наследуется от класса Region и реализует интерфейс Cloneable
class City extends Region implements Cloneable {
    // Замена примитивного типа на класс-обертку
    private Integer population; // поле для хранения численности населения

    // Конструктор по умолчанию
    public City() {
        super(); // вызов конструктора суперкласса
        this.population = 0; // установка значения по умолчанию для численности населения
    }

    // Конструктор с параметрами
    public City(String name, String description, Integer population) throws InvalidPopulationException, InvalidValueException, InvalidNameException {
        super(name, description); // вызов конструктора суперкласса с параметрами
        this.population = population; // установка численности населения
        if (population == null || population < 0) { // Проверка на неотрицательность
            throw new InvalidValueException("Население не может быть отрицательным");
        }
        if (population > 100_000_000) { // Проверка на то, что население не слишком большое
            throw new InvalidPopulationException("Слишком большое население: " + population);
        }
    }

    // Методы для демонстрации автоупаковки и автораспаковки
    public Integer getPopulation() {
        return this.population; // возвращает число населения
    }

    public void setPopulation(Integer population) throws InvalidPopulationException, InvalidValueException {
        if (population == null || population < 0) { // Проверка на неотрицательность
            throw new InvalidValueException("Население не может быть отрицательным");
        }
        if (population > 100_000_000) { // Проверка на то, что население не слишком большое
            throw new InvalidPopulationException("Слишком большое население: " + population);
        }
        this.population = population;
    }


    // Перегруженный метод для демонстрации инициализации из строки
    public void setPopulation(String population) throws InvalidPopulationException, InvalidValueException {
        int in = Integer.valueOf(population); // преобразует строку в число и устанавливает число населения
        if (in < 0) { // Проверка на неотрицательность
            throw new InvalidValueException("Население не может быть отрицательным");
        }
        if (in > 100_000_000) { // Проверка на то, что население не слишком большое
            throw new InvalidPopulationException("Слишком большое население: " + population);
        }
        this.population = in;
    }

    // Реализация глубокого клонирования
    @Override
    protected Object clone() throws CloneNotSupportedException {
        City cityClone = (City) super.clone(); // вызываем клонирование в род. классе
        try { // Обработка исключений
            cityClone.setPopulation(getPopulation());
        } catch (InvalidPopulationException | InvalidValueException e) {
            throw new RuntimeException(e);
        }
        return cityClone; // возвращаем клон
    }

    // Переопределение методов hashCode, equals, toString
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), population); // возвращает хэш-код объекта
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // если объект сравнивается сам с собой, возвращает true
        if (!(obj instanceof City)) return false; // если объект не является экземпляром City, возвращает false
        if (!super.equals(obj)) return false; // если объект не равен объекту суперкласса, возвращает false
        City city = (City) obj; // приводит obj к типу City
        return Objects.equals(population, city.population); // возвращает true, если поле population текущего объекта равно полю population объекта obj
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", population=" + population +
                '}'; // возвращает строковое представление объекта
    }
}

class Megapolis extends City implements Cloneable {
    // Замена примитивного типа на класс-обертку
    private Integer skyscrapers; // поле для хранения количества небоскребов

    public Megapolis() throws InvalidValueException, InvalidPopulationException, InvalidNameException {
        this("Default Name", "Default Description", 0, 0); // конструктор по умолчанию
    }

    public Megapolis(String name, String description, Integer population, Integer skyscrapers) throws InvalidValueException, InvalidPopulationException, InvalidNameException {
        super(name, description, population); // вызов конструктора суперкласса с параметрами
        this.skyscrapers = skyscrapers; // установка количества небоскребов
        if (skyscrapers == null || skyscrapers < 0) { // Проверка на неотрицательность
            throw new InvalidValueException("Количество небоскребов не может быть отрицательным");
        }
    }

    // Методы для демонстрации автоупаковки и автораспаковки
    public Integer getSkyscrapers() {
        return this.skyscrapers; // возвращает количество небоскребов
    }

    public void setSkyscrapers(Integer skyscrapers) throws InvalidValueException {
        if (skyscrapers == null || skyscrapers < 0) { // Проверка на неотрицательность
            throw new InvalidValueException("Количество небоскребов не может быть отрицательным");
        }
        this.skyscrapers = skyscrapers;
    }


    // Перегруженный метод для демонстрации инициализации из строки
    public void setSkyscrapers(String skyscrapers) throws InvalidValueException {
        int in = Integer.valueOf(skyscrapers); // преобразует строку в число и устанавливает количество небоскребов
        if (in < 0) { // Проверка на неотрицательность
            throw new InvalidValueException("Количество небоскребов не может быть отрицательным");
        }
        this.skyscrapers = in;
    }

    // Реализация глубокого клонирования
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Megapolis megapolisClone = (Megapolis) super.clone(); // Вызываем клонирование в род. классе
        try {
            megapolisClone.setSkyscrapers(getSkyscrapers()); // копируем кол-во небоскребов
        } catch (InvalidValueException e) {
            throw new RuntimeException(e);
        }
        return megapolisClone; // возвращаем клон
    }

    // Переопределение методов hashCode, equals, toString
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), skyscrapers); // возвращает хэш-код объекта
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // если объект сравнивается сам с собой, возвращает true
        if (!(obj instanceof Megapolis))
            return false; // если объект не является экземпляром Megapolis, возвращает false
        if (!super.equals(obj)) return false; // если объект не равен объекту суперкласса, возвращает false
        Megapolis megapolis = (Megapolis) obj; // приводит obj к типу Megapolis
        return Objects.equals(skyscrapers, megapolis.skyscrapers); // возвращает true, если поле skyscrapers текущего объекта равно полю skyscrapers объекта obj
    }

    @Override
    public String toString() {
        return "Megapolis{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", population=" + getPopulation() +
                ", skyscrapers=" + skyscrapers +
                '}'; // возвращает строковое представление объекта
    }
}
// Создание класса исключения для недействительных значений
class InvalidValueException extends Exception {
    // Конструктор класса исключения принимает сообщение об ошибке в качестве аргумента
    public InvalidValueException(String message) {
        // Вызов конструктора родительского класса Exception с сообщением об ошибке
        super(message);
    }
}

// Создание класса исключения для недействительных имен
class InvalidNameException extends Exception {
    // Конструктор класса исключения принимает сообщение об ошибке в качестве аргумента
    public InvalidNameException(String message) {
        // Вызов конструктора родительского класса Exception с сообщением об ошибке
        super(message);
    }
}

// Создание класса исключения для недействительного населения
class InvalidPopulationException extends Exception {
    // Конструктор класса исключения принимает сообщение об ошибке в качестве аргумента
    public InvalidPopulationException(String message) {
        // Вызов конструктора родительского класса Exception с сообщением об ошибке
        super(message);
    }
}

// Главный класс программы
public class Lab5 {
    // Инициализация сканера для чтения пользовательского ввода
    private static final Scanner scanner = new Scanner(System.in);
    // Инициализация списка мест
    private static final List<Place> places = new ArrayList<>();

    // Главный метод программы
    public static void main(String[] args) {
        // Переменная для контроля выхода из программы
        boolean exit = false;

        // Цикл выполняется до тех пор, пока пользователь не решит выйти
        while (!exit) {
            // Вывод меню на экран
            System.out.println("\n1. Создать City");
            System.out.println("2. Создать Megapolis");
            System.out.println("3. Просмотреть список созданных объектов");
            System.out.println("4. Выход");

            // Запрос на ввод команды
            System.out.print("Введите номер команды: ");
            String command = scanner.nextLine();

            // Обработка введённой команды
            switch (command) {
                case "1":
                    // Создание города
                    createCity();
                    break;
                case "2":
                    // Создание мегаполиса
                    createMegapolis();
                    break;
                case "3":
                    // Отображение списка мест
                    displayPlaces();
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
    private static void createCity() {
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
            City city = new City(name, description, population);
            places.add(city);
        } catch (InvalidValueException e) {
            // Обработка исключения недействительного значения
            System.out.println("Ошибка: " + e.getMessage());
        } catch (InputMismatchException e) {
            // Обработка исключения несоответствия ввода
            System.out.println("Ошибка: введено некорректное значение");
            scanner.nextLine(); // очистка буфера сканера
        } catch (InvalidPopulationException e) {
            // Обработка исключения недействительного населения
            throw new RuntimeException(e);
        } catch (InvalidNameException e) {
            throw new RuntimeException(e);
        }
    }

    // Метод для создания мегаполиса
    private static void createMegapolis() {
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
            Megapolis megapolis = new Megapolis(name, description, population, skyscrapers);
            places.add(megapolis);
        } catch (InvalidValueException e) {
            // Обработка исключения недействительного значения
            System.out.println("Ошибка: " + e.getMessage());
        } catch (InputMismatchException e) {
            // Обработка исключения несоответствия ввода
            System.out.println("Ошибка: введено некорректное значение");
            scanner.nextLine(); // очистка буфера сканера
        } catch (InvalidPopulationException e) {
            // Обработка исключения недействительного населения
            System.out.println("Ошибка: " + e.getMessage());
        } catch (InvalidNameException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    // Метод для отображения списка мест
    private static void displayPlaces() {
        // Прохождение по всем местам в списке
        for (Place place : places) {
            // Вывод информации о месте
            System.out.println(place);
        }
    }
}