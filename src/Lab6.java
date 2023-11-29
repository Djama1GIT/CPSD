import java.util.*;
import java.util.function.Predicate;

// Объявление интерфейса Place6 с методами для работы с местами
interface Place6 {
    String getName(); // метод для получения имени

    void setName(String name); // метод для установки имени

    String getDescription(); // метод для получения описания

    void setDescription(String description); // метод для установки описания
}

// Объявление абстрактного класса Region6, который реализует интерфейс Place6 и интерфейс Cloneable (для клонирования)
abstract class Region6 implements Place6, Cloneable {
    private String name; // поле для хранения имени
    private String description; // поле для хранения описания

    // Конструктор по умолчанию
    public Region6() {
        this.name = "Default Name"; // установка значения по умолчанию для имени
        this.description = "Default Description"; // установка значения по умолчанию для описания
    }

    // Конструктор с параметрами
    public Region6(String name, String description) {
        this.name = name; // установка имени
        this.description = description; // установка описания
    }

    // Геттеры и сеттеры для полей
    public String getName() {
        return this.name; // Возвращаем name
    }

    public void setName(String name) {
        this.name = name; // Устанавливаем новый name
    }

    public String getDescription() {
        return this.description; // Возвращаем description
    }

    public void setDescription(String description) {
        this.description = description; // Возвращаем новый description
    }

    // Реализация метода clone() для выполнения глубокого клонирования
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Region6 Region6Clone = (Region6) super.clone(); // Вызываем клонирование в род. классе
        Region6Clone.setName(getName()); // копируем имя
        Region6Clone.setDescription(getDescription()); // копируем описание
        return Region6Clone; // возвращаем клон
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
        // Приводим переданный объект к типу Region6
        Region6 Region6 = (Region6) obj;
        // Сравниваем поля name и description текущего объекта и переданного объекта, возвращаем true, если они равны, иначе false
        return Objects.equals(name, Region6.name) &&
                Objects.equals(description, Region6.description);
    }


    // Переопределение метода toString() для корректного представления объекта в виде строки
    @Override
    public String toString() {
        return "Region6{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

// Объявление класса City6, который наследуется от класса Region6 и реализует интерфейс Cloneable
class City6 extends Region6 implements Cloneable {
    // Замена примитивного типа на класс-обертку
    private Integer population; // поле для хранения численности населения

    // Конструктор по умолчанию
    public City6() {
        super(); // вызов конструктора суперкласса
        this.population = 0; // установка значения по умолчанию для численности населения
    }

    // Конструктор с параметрами
    public City6(String name, String description, Integer population) {
        super(name, description); // вызов конструктора суперкласса с параметрами
        this.population = population; // установка численности населения
    }

    // Методы для демонстрации автоупаковки и автораспаковки
    public Integer getPopulation() {
        return this.population; // возвращает число населения
    }

    public void setPopulation(Integer population) {
        this.population = population; // устанавливает число населения
    }

    // Перегруженный метод для демонстрации инициализации из строки
    public void setPopulation(String population) {
        this.population = Integer.valueOf(population); // преобразует строку в число и устанавливает число населения
    }

    // Реализация глубокого клонирования
    @Override
    protected Object clone() throws CloneNotSupportedException {
        City6 City6Clone = (City6) super.clone(); // вызываем клонирование в род. классе
        City6Clone.setPopulation(getPopulation());
        return City6Clone; // возвращаем клон
    }

    // Переопределение методов hashCode, equals, toString
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), population); // возвращает хэш-код объекта
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // если объект сравнивается сам с собой, возвращает true
        if (!(obj instanceof City6)) return false; // если объект не является экземпляром City6, возвращает false
        if (!super.equals(obj)) return false; // если объект не равен объекту суперкласса, возвращает false
        City6 City6 = (City6) obj; // приводит obj к типу City6
        return Objects.equals(population, City6.population); // возвращает true, если поле population текущего объекта равно полю population объекта obj
    }

    @Override
    public String toString() {
        return "City6{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", population=" + population +
                '}'; // возвращает строковое представление объекта
    }
}

class Megapolis6 extends City6 implements Cloneable {
    // Замена примитивного типа на класс-обертку
    private Integer skyscrapers; // поле для хранения количества небоскребов

    public Megapolis6() {
        this("Default Name", "Default Description", 0, 0); // конструктор по умолчанию
    }

    public Megapolis6(String name, String description, Integer population, Integer skyscrapers) {
        super(name, description, population); // вызов конструктора суперкласса с параметрами
        this.skyscrapers = skyscrapers; // установка количества небоскребов
    }

    // Методы для демонстрации автоупаковки и автораспаковки
    public Integer getSkyscrapers() {
        return this.skyscrapers; // возвращает количество небоскребов
    }

    public void setSkyscrapers(Integer skyscrapers) {
        this.skyscrapers = skyscrapers; // устанавливает количество небоскребов
    }

    // Перегруженный метод для демонстрации инициализации из строки
    public void setSkyscrapers(String skyscrapers) {
        this.skyscrapers = Integer.valueOf(skyscrapers); // преобразует строку в число и устанавливает количество небоскребов
    }

    // Реализация глубокого клонирования
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Megapolis6 Megapolis6Clone = (Megapolis6) super.clone(); // Вызываем клонирование в род. классе
        Megapolis6Clone.setSkyscrapers(getSkyscrapers()); // копируем кол-во небоскребов
        return Megapolis6Clone; // возвращаем клон
    }

    // Переопределение методов hashCode, equals, toString
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), skyscrapers); // возвращает хэш-код объекта
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // если объект сравнивается сам с собой, возвращает true
        if (!(obj instanceof Megapolis6))
            return false; // если объект не является экземпляром Megapolis6, возвращает false
        if (!super.equals(obj)) return false; // если объект не равен объекту суперкласса, возвращает false
        Megapolis6 Megapolis6 = (Megapolis6) obj; // приводит obj к типу Megapolis6
        return Objects.equals(skyscrapers, Megapolis6.skyscrapers); // возвращает true, если поле skyscrapers текущего объекта равно полю skyscrapers объекта obj
    }

    @Override
    public String toString() {
        return "Megapolis6{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", population=" + getPopulation() +
                ", skyscrapers=" + skyscrapers +
                '}'; // возвращает строковое представление объекта
    }
}

// Объявление класса Country, который наследуется от Megapolis6 и реализует интерфейс Cloneable
class Country extends Megapolis6 implements Cloneable {
    /*
     * Многострочный комментарий, объясняющий общую логику:
     * Т.к. в программе только 2 неабстрактные сущности, добавлена 3-я, чтобы можно было их распределить так:
     * в list - City6
     * set - Megapolis6
     * map - country
     */

    // Приватное поле для хранения имени президента
    private String president;

    // Конструктор без параметров
    public Country() {
        // Вызов другого конструктора с предопределенными значениями
        this("Default Name", "Default Description", 0, 0, "Default President");
    }

    // Конструктор с параметрами
    public Country(String name, String description, Integer population, Integer skyscrapers, String president) {
        // Вызов конструктора родительского класса
        super(name, description, population, skyscrapers);
        // Инициализация приватного поля президента
        this.president = president;
    }

    // Геттер для поля президента
    public String getPresident() {
        return this.president;
    }

    // Сеттер для поля президента
    public void setPresident(String president) {
        this.president = president;
    }

    // Переопределение метода clone() для создания копии объекта
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // Создание копии текущего объекта
        Country countryClone = (Country) super.clone();
        // Установка президента для копии
        countryClone.setPresident(getPresident());
        // Возвращение копии
        return countryClone;
    }

    // Переопределение метода hashCode() для генерации уникального идентификатора объекта
    @Override
    public int hashCode() {
        // Генерация хэш-кода на основе хэш-кода родительского класса и президента
        return Objects.hash(super.hashCode(), president);
    }

    // Переопределение метода equals() для сравнения объектов
    @Override
    public boolean equals(Object obj) {
        // Сравнение ссылок на объекты
        if (this == obj) return true;
        // Проверка, является ли переданный объект экземпляром класса Country
        if (!(obj instanceof Country)) return false;
        // Сравнение с родительским классом
        if (!super.equals(obj)) return false;
        // Приведение типа и сравнение полей президента
        Country country = (Country) obj;
        return Objects.equals(president, country.president);
    }

    // Переопределение метода toString() для представления объекта в виде строки
    @Override
    public String toString() {
        return "Country{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", population=" + getPopulation() +
                ", skyscrapers=" + getSkyscrapers() +
                ", president='" + president + '\'' +
                '}';
    }
}


// Объявляем класс InfoPrinter
class InfoPrinter {
    // Объявляем статический обобщенный метод print
    public static <T> void print(T obj) {
        // Выводим на печать результат вызова метода toString для объекта obj
        System.out.println(obj.toString());
    }

    // Объявляем статический обобщенный метод printAll, который принимает коллекцию
    public static <T> void printAll(Collection<T> collection) {
        // Итерируемся по каждому элементу коллекции
        for (T obj : collection) {
            // Вызываем метод print для каждого объекта
            print(obj);
        }
    }
}

// Объявляем класс CollectionSearcher
class CollectionSearcher {
    // Объявляем статический обобщенный метод findFirst, который принимает коллекцию и предикат
    public static <T> T findFirst(Collection<? extends T> collection, Predicate<T> predicate) {
        // Итерируемся по каждому элементу коллекции
        for (T item : collection) {
            // Если предикат выполняется для элемента, возвращаем этот элемент
            if (predicate.test(item)) {
                return item;
            }
        }
        // Если ни один элемент не удовлетворяет предикату, возвращаем null
        return null;
    }
}

// Объявляем класс MyCollection с обобщенным типом T
class MyCollection<T> {
    // Объявляем приватное поле items, которое является коллекцией объектов типа T
    private Collection<T> items;

    // Объявляем публичный конструктор без аргументов
    public MyCollection() {
        // Инициализируем поле items как новый ArrayList
        items = new ArrayList<>();
    }

    // Объявляем метод add, который добавляет элемент в коллекцию
    public void add(T item) {
        items.add(item);
    }

    // Объявляем метод addAll, который добавляет все элементы из другой коллекции
    public void addAll(Collection<? extends T> collection) {
        items.addAll(collection);
    }

    // Объявляем метод printAll, который выводит все элементы коллекции
    public void printAll() {
        for (T item : items) {
            System.out.println(item.toString());
        }
    }
}

public class Lab6 {
    public static void main(String[] args) throws CloneNotSupportedException {
        // Создаем объекты
        City6 City61 = new City6("City6 1", "This is a City6", 100000);
        City6 City62 = new City6("City6 2", "This is another City6", 200000);
        City6 City63 = new City6("City6 3", "This is yet another City6", 300000);

        // Добавляем их в список
        List<City6> City6List = new ArrayList<>();
        City6List.add(City61);
        City6List.add(City62);
        City6List.add(City63);

        // Создаем объекты Megapolis6
        Megapolis6 Megapolis61 = new Megapolis6("Megapolis6 1", "This is a Megapolis6", 1000000, 200);
        Megapolis6 Megapolis62 = new Megapolis6("Megapolis6 2", "This is another Megapolis6", 2000000, 400);

        // Добавляем их в Set
        Set<Megapolis6> Megapolis6Set = new HashSet<>();
        Megapolis6Set.add(Megapolis61);
        Megapolis6Set.add(Megapolis62);

        // Создаем объекты Country
        Country country1 = new Country("Country 1", "This is a Country", 10000000, 2000, "President 1");
        Country country2 = new Country("Country 2", "This is another Country", 20000000, 4000, "President 2");

        // Добавляем их в Map
        Map<String, Country> countryMap = new HashMap<>();
        countryMap.put(country1.getName(), country1);
        countryMap.put(country2.getName(), country2);

        // Выводим информацию об объектах
        InfoPrinter.printAll(City6List); // Выводим информацию об объектах City6
        InfoPrinter.printAll(Megapolis6Set); // Выводим информацию об объектах Megapolis6
        InfoPrinter.printAll(countryMap.values()); // Выводим информацию об объектах country

        // Ищем город с населением больше 150000
        City6 bigCity6 = CollectionSearcher.findFirst(City6List, City6 -> City6.getPopulation() > 150000);
        System.out.println("\nFirst City6 with population over 150000: " + bigCity6 + "\n");

        // Используем наш собственный класс коллекции
        MyCollection<Place6> Place6Collection = new MyCollection<>();
        Place6Collection.addAll(City6List); // добавляем все города
        Place6Collection.addAll(Megapolis6Set); // добавляем все мегаполисы
        Place6Collection.addAll(countryMap.values()); // добавляем все страны
        Place6Collection.printAll(); // выводим всё
    }
}
