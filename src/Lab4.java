import java.util.Objects; // Импорт класса Objects для работы с объектами

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
        Region regionClone = (Region) super.clone(); // Вызываем клонирование в род. классе
        regionClone.setName(getName()); // копируем имя
        regionClone.setDescription(getDescription()); // копируем описание
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
        City cityClone = (City) super.clone(); // вызываем клонирование в род. классе
        cityClone.setPopulation(getPopulation());
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
        Megapolis megapolisClone = (Megapolis) super.clone(); // Вызываем клонирование в род. классе
        megapolisClone.setSkyscrapers(getSkyscrapers()); // копируем кол-во небоскребов
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

public class Lab4 {
    public static void main(String[] args) throws CloneNotSupportedException {
        // Создаем объект класса City
        City city = new City("City 1", "This is a city", 100000);
        // Выводим информацию об объекте city
        System.out.println("City Name: " + city.getName()); // Выводим имя
        System.out.println("City Description: " + city.getDescription()); // Выводим описание
        System.out.println("City Population: " + city.getPopulation()); // Выводим популяцию

        // Создаем объект класса Megapolis
        Megapolis megapolis = new Megapolis("Megapolis 1", "This is a megapolis", 1000000, 200);
        // Выводим информацию об объекте megapolis
        System.out.println("Megapolis Name: " + megapolis.getName()); // Выводим имя
        System.out.println("Megapolis Description: " + megapolis.getDescription()); // Выводим описани
        System.out.println("Megapolis Population: " + megapolis.getPopulation()); //  Выводим популяцию
        System.out.println("Megapolis Skyscrapers: " + megapolis.getSkyscrapers()); // Выводим небоскребы

        // Проверяем, является ли объект city экземпляром интерфейса Place
        System.out.println("City is an instance of Place: " + (city instanceof Place));
        // Проверяем, является ли объект megapolis экземпляром класса City
        System.out.println("Megapolis is an instance of City: " + (megapolis instanceof City));

        // Демонстрируем глубокое клонирование
        City cityClone = (City) city.clone();
        System.out.println("Clone of city: " + cityClone);

        // Демонстрируем переопределенные методы hashCode, equals, toString
        System.out.println("City hash code: " + city.hashCode()); // Выводим хэш
        System.out.println("City equals clone: " + city.equals(cityClone));
        System.out.println("City: " + city.toString()); // Выводим информацию о городе
    }
}
