import java.util.Objects; // Импорт класса Objects для работы с объектами

// Объявление интерфейса Place с методами для работы с местами
interface Place4 {
    String getName(); // метод для получения имени
    void setName(String name); // метод для установки имени
    String getDescription(); // метод для получения описания
    void setDescription(String description); // метод для установки описания
}

// Объявление абстрактного класса Region4, который реализует интерфейс Place и интерфейс Cloneable (для клонирования)
abstract class Region4 implements Place, Cloneable {
    private String name; // поле для хранения имени
    private String description; // поле для хранения описания

    // Конструктор по умолчанию
    public Region4() {
        this.name = "Default Name"; // установка значения по умолчанию для имени
        this.description = "Default Description"; // установка значения по умолчанию для описания
    }

    // Конструктор с параметрами
    public Region4(String name, String description) {
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
        Region4 Region4Clone = (Region4) super.clone(); // Вызываем клонирование в род. классе
        Region4Clone.setName(getName()); // копируем имя
        Region4Clone.setDescription(getDescription()); // копируем описание
        return Region4Clone; // возвращаем клон
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
        // Приводим переданный объект к типу Region4
        Region4 Region4 = (Region4) obj;
        // Сравниваем поля name и description текущего объекта и переданного объекта, возвращаем true, если они равны, иначе false
        return Objects.equals(name, Region4.name) &&
                Objects.equals(description, Region4.description);
    }


    // Переопределение метода toString() для корректного представления объекта в виде строки
    @Override
    public String toString() {
        return "Region4{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

// Объявление класса City4, который наследуется от класса Region4 и реализует интерфейс Cloneable
class City4 extends Region4 implements Cloneable {
    // Замена примитивного типа на класс-обертку
    private Integer population; // поле для хранения численности населения

    // Конструктор по умолчанию
    public City4() {
        super(); // вызов конструктора суперкласса
        this.population = 0; // установка значения по умолчанию для численности населения
    }

    // Конструктор с параметрами
    public City4(String name, String description, Integer population) {
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
        City4 City4Clone = (City4) super.clone(); // вызываем клонирование в род. классе
        City4Clone.setPopulation(getPopulation());
        return City4Clone; // возвращаем клон
    }

    // Переопределение методов hashCode, equals, toString
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), population); // возвращает хэш-код объекта
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // если объект сравнивается сам с собой, возвращает true
        if (!(obj instanceof City4)) return false; // если объект не является экземпляром City4, возвращает false
        if (!super.equals(obj)) return false; // если объект не равен объекту суперкласса, возвращает false
        City4 City4 = (City4) obj; // приводит obj к типу City4
        return Objects.equals(population, City4.population); // возвращает true, если поле population текущего объекта равно полю population объекта obj
    }

    @Override
    public String toString() {
        return "City4{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", population=" + population +
                '}'; // возвращает строковое представление объекта
    }
}

class Megapolis4 extends City4 implements Cloneable {
    // Замена примитивного типа на класс-обертку
    private Integer skyscrapers; // поле для хранения количества небоскребов

    public Megapolis4() {
        this("Default Name", "Default Description", 0, 0); // конструктор по умолчанию
    }

    public Megapolis4(String name, String description, Integer population, Integer skyscrapers) {
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
        Megapolis4 Megapolis4Clone = (Megapolis4) super.clone(); // Вызываем клонирование в род. классе
        Megapolis4Clone.setSkyscrapers(getSkyscrapers()); // копируем кол-во небоскребов
        return Megapolis4Clone; // возвращаем клон
    }

    // Переопределение методов hashCode, equals, toString
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), skyscrapers); // возвращает хэш-код объекта
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // если объект сравнивается сам с собой, возвращает true
        if (!(obj instanceof Megapolis4))
            return false; // если объект не является экземпляром Megapolis4, возвращает false
        if (!super.equals(obj)) return false; // если объект не равен объекту суперкласса, возвращает false
        Megapolis4 Megapolis4 = (Megapolis4) obj; // приводит obj к типу Megapolis4
        return Objects.equals(skyscrapers, Megapolis4.skyscrapers); // возвращает true, если поле skyscrapers текущего объекта равно полю skyscrapers объекта obj
    }

    @Override
    public String toString() {
        return "Megapolis4{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", population=" + getPopulation() +
                ", skyscrapers=" + skyscrapers +
                '}'; // возвращает строковое представление объекта
    }
}

public class Lab4 {
    public static void main(String[] args) throws CloneNotSupportedException {
        // Создаем объект класса City4
        City4 City4 = new City4("City4 1", "This is a City4", 100000);
        // Выводим информацию об объекте City4
        System.out.println("City4 Name: " + City4.getName()); // Выводим имя
        System.out.println("City4 Description: " + City4.getDescription()); // Выводим описание
        System.out.println("City4 Population: " + City4.getPopulation()); // Выводим популяцию

        // Создаем объект класса Megapolis4
        Megapolis4 Megapolis4 = new Megapolis4("Megapolis4 1", "This is a Megapolis4", 1000000, 200);
        // Выводим информацию об объекте Megapolis4
        System.out.println("Megapolis4 Name: " + Megapolis4.getName()); // Выводим имя
        System.out.println("Megapolis4 Description: " + Megapolis4.getDescription()); // Выводим описани
        System.out.println("Megapolis4 Population: " + Megapolis4.getPopulation()); //  Выводим популяцию
        System.out.println("Megapolis4 Skyscrapers: " + Megapolis4.getSkyscrapers()); // Выводим небоскребы

        // Проверяем, является ли объект City4 экземпляром интерфейса Place
        System.out.println("City4 is an instance of Place: " + (City4 instanceof Place));
        // Проверяем, является ли объект Megapolis4 экземпляром класса City4
        System.out.println("Megapolis4 is an instance of City4: " + (Megapolis4 instanceof City4));

        // Демонстрируем глубокое клонирование
        City4 City4Clone = (City4) City4.clone();
        System.out.println("Clone of City4: " + City4Clone);

        // Демонстрируем переопределенные методы hashCode, equals, toString
        System.out.println("City4 hash code: " + City4.hashCode()); // Выводим хэш
        System.out.println("City4 equals clone: " + City4.equals(City4Clone));
        System.out.println("City4: " + City4.toString()); // Выводим информацию о городе
    }
}
