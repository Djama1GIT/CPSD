import java.util.*;

// Объявление класса для дополнительного задания в лабе 5
public class Lab5_extra {

    // Основной метод программы
    public static void main(String[] args) {
        // Инициализация тестового текста
        String text = "Тестовый текст. Проверка работы программы.";
        // Вывод в консоль количества слов в тексте
        System.out.println("Количество слов в тексте: " + countWordsInText(text));
        // Создание и заполнение карты подсчета букв в тексте
        Map<Character, Integer> letterCounts = countLettersInText(text);
        // Цикл для вывода информации о каждой букве и ее количестве в тексте
        for (Map.Entry<Character, Integer> entry : letterCounts.entrySet()) {
            System.out.println("Буква '" + entry.getKey() + "' встречается " + entry.getValue() + " раз");
        }
    }

    // Метод для подсчета количества слов в тексте
    public static int countWordsInText(String text) {
        // Проверка, является ли текст пустым или null
        if (text == null || text.isEmpty()) {
            return 0;
        }
        // Разделение текста на слова
        String[] words = text.trim().split("\\s+");
        // Возврат количества слов в тексте
        return words.length;
    }

    // Определение метода подсчета количества букв в тексте
    public static Map<Character, Integer> countLettersInText(String text) {
    // Создаем новую хэш-карту для хранения символов в качестве ключей и их количества в качестве значений
    Map<Character, Integer> letterCounts = new HashMap<>();
        // Выполняем итерацию по каждому символу в тексте
        for (char c : text.toCharArray()) {
            // Проверяем, является ли символ буквой
            if (Character.isLetter(c)) {
                // Преобразуем символ в нижний регистр, чтобы убедиться, что счетчик не учитывает регистр
                c = Character.toLowerCase(c);
                // Добавляем символ в map или увеличиваем количество int, если он уже присутствует
                letterCounts.put(c, letterCounts.getOrDefault(c, 0) + 1);
            }
        }
        // Возвращаеv Map, содержащую количество каждой буквы
        return letterCounts;
    }
}
