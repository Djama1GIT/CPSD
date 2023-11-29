import java.util.*;
import java.util.function.Predicate;

// Объявление интерфейса Place с методами для работы с местами
interface Place {
    String getName(); // метод для получения имени

    void setName(String name); // метод для установки имени

    String getDescription(); // метод для получения описания

    void setDescription(String description); // метод для установки описания
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
    public Region(String name, String description) {
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
        Region RegionClone = (Region) super.clone(); // Вызываем клонирование в род. классе
        RegionClone.setName(getName()); // копируем имя
        RegionClone.setDescription(getDescription()); // копируем описание
        return RegionClone; // возвращаем клон
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
        Region Region = (Region) obj;
        // Сравниваем поля name и description текущего объекта и переданного объекта, возвращаем true, если они равны, иначе false
        return Objects.equals(name, Region.name) &&
                Objects.equals(description, Region.description);
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
    public City(String name, String description, Integer population) {
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
        City CityClone = (City) super.clone(); // вызываем клонирование в род. классе
        CityClone.setPopulation(getPopulation());
        return CityClone; // возвращаем клон
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
        City City = (City) obj; // приводит obj к типу City
        return Objects.equals(population, City.population); // возвращает true, если поле population текущего объекта равно полю population объекта obj
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

    public Megapolis() {
        this("Default Name", "Default Description", 0, 0); // конструктор по умолчанию
    }

    public Megapolis(String name, String description, Integer population, Integer skyscrapers) {
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
        Megapolis MegapolisClone = (Megapolis) super.clone(); // Вызываем клонирование в род. классе
        MegapolisClone.setSkyscrapers(getSkyscrapers()); // копируем кол-во небоскребов
        return MegapolisClone; // возвращаем клон
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
        Megapolis Megapolis = (Megapolis) obj; // приводит obj к типу Megapolis
        return Objects.equals(skyscrapers, Megapolis.skyscrapers); // возвращает true, если поле skyscrapers текущего объекта равно полю skyscrapers объекта obj
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

// Объявление класса Country, который наследуется от Megapolis и реализует интерфейс Cloneable
class Country extends Megapolis implements Cloneable {
    /*
     * Многострочный комментарий, объясняющий общую логику:
     * Т.к. в программе только 2 неабстрактные сущности, добавлена 3-я, чтобы можно было их распределить так:
     * в list - city
     * set - megapolis
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
        City city1 = new City("City 1", "This is a City", 100000);
        City city2 = new City("City 2", "This is another City", 200000);
        City city3 = new City("City 3", "This is yet another City", 300000);

        // Добавляем их в список
        List<City> cityList = new ArrayList<>();
        cityList.add(city1);
        cityList.add(city2);
        cityList.add(city3);

        // Создаем объекты Megapolis
        Megapolis megapolis1 = new Megapolis("Megapolis 1", "This is a Megapolis", 1000000, 200);
        Megapolis megapolis2 = new Megapolis("Megapolis 2", "This is another Megapolis", 2000000, 400);

        // Добавляем их в Set
        Set<Megapolis> megapolisSet = new HashSet<>();
        megapolisSet.add(megapolis1);
        megapolisSet.add(megapolis2);

        // Создаем объекты Country
        Country country1 = new Country("Country 1", "This is a Country", 10000000, 2000, "President 1");
        Country country2 = new Country("Country 2", "This is another Country", 20000000, 4000, "President 2");

        // Добавляем их в Map
        Map<String, Country> countryMap = new HashMap<>();
        countryMap.put(country1.getName(), country1);
        countryMap.put(country2.getName(), country2);

        // Выводим информацию об объектах
        InfoPrinter.printAll(cityList); // Выводим информацию об объектах city
        InfoPrinter.printAll(megapolisSet); // Выводим информацию об объектах megapolis
        InfoPrinter.printAll(countryMap.values()); // Выводим информацию об объектах country

        // Ищем город с населением больше 150000
        City bigCity = CollectionSearcher.findFirst(cityList, city -> city.getPopulation() > 150000);
        System.out.println("\nFirst city with population over 150000: " + bigCity + "\n");

        // Используем наш собственный класс коллекции
        MyCollection<Place> placeCollection = new MyCollection<>();
        placeCollection.addAll(cityList); // добавляем все города
        placeCollection.addAll(megapolisSet); // добавляем все мегаполисы
        placeCollection.addAll(countryMap.values()); // добавляем все страны
        placeCollection.printAll(); // выводим всё
    }
}
