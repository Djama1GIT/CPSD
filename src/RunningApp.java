import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Класс RunningApp наследуется от JFrame, то есть является окном
public class RunningApp extends JFrame {
    // Объявляем компоненты нашего интерфейса
    private JTextField distanceField; // Текстовое поле для ввода дистанции
    private JLabel totalLabel; // Метка для отображения общего пробега
    private JLabel averageLabel; // Метка для отображения среднего пробега в день
    private JLabel minLabel; // Метка для отображения наименьшего пробега
    private JLabel maxLabel; // Метка для отображения наибольшего пробега

    private JList<String> daysList; // Список для отображения пробега по дням
    private DefaultListModel<String> listModel; // Модель данных для этого списка

    private ArrayList<Double> distances; // Список для хранения пробега по дням

    // Конструктор класса RunningApp
    public RunningApp() {
        distances = new ArrayList<>(); // Создаем список для хранения пробега
        listModel = new DefaultListModel<>(); // Создаем модель данных для списка
        createView(); // Создаем и настраиваем компоненты интерфейса

        setTitle("🏃‍♂️ Бег 🏃‍♀️"); // Устанавливаем параметры окна
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Устанавливаем операцию закрытия окна на выход из приложения
        pack(); // Устанавливаем размер окна так, чтобы он соответствовал размеру его содержимого
        setLocationRelativeTo(null); // Устанавливаем позицию окна относительно его родительского окна или экрана
        setResizable(false); // Запрещаем изменение размера окна пользователем

    }

    // Метод для создания и настройки компонентов интерфейса
    private void createView() {
        // Создаем разделительное окно с горизонтальным разделителем
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        // Добавляем разделительное окно в главное окно приложения
        getContentPane().add(splitPane);

        // Создаем левую панель и добавляем ее в разделительное окно
        JPanel leftPanel = new JPanel();
        // Устанавливаем менеджер компоновки для левой панели
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        // Добавляем левую панель в разделительное окно
        splitPane.setLeftComponent(leftPanel);

        // Создаем и добавляем на левую панель метку с поясняющим текстом
        JLabel label = new JLabel("Введите расстояние в км:");
        // Устанавливаем шрифт метки
        label.setFont(new Font("Courier", Font.BOLD, 18));
        // Добавляем метку на левую панель
        leftPanel.add(label);

        // Создаем и добавляем на левую панель текстовое поле для ввода дистанции
        distanceField = new JTextField();
        // Устанавливаем предпочтительный и максимальный размеры текстового поля
        distanceField.setPreferredSize(new Dimension(100, 20));
        distanceField.setMaximumSize(new Dimension(700, 30));
        // Добавляем слушателя событий, который реагирует на нажатие клавиши Enter в текстовом поле
        distanceField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Получаем введенный текст
                String input = distanceField.getText();
                // Если текст не пустой
                if (!input.isEmpty()) {
                    // Парсим введенный текст как число и добавляем его в список дистанций
                    distances.add(Double.parseDouble(input));
                    // Добавляем введенную дистанцию в модель данных для списка с указанием номера дня
                    listModel.addElement("День " + (listModel.size() + 1) + ": " + input + " км ❌");
                    // Очищаем текстовое поле
                    distanceField.setText("");
                    // Пересчитываем и обновляем статистику
                    calculate();
                }
            }
        });
        // Добавляем текстовое поле на левую панель
        leftPanel.add(distanceField);

        // Создаем метку для отображения общего пробега и добавляем ее на левую панель
        totalLabel = new JLabel("Всего: 0.0 км 📊🏁");
        // Устанавливаем размер шрифта метки
        totalLabel.setFont(new Font("Courier", Font.PLAIN, 16));
        // Добавляем метку на левую панель
        leftPanel.add(totalLabel);

        // Создаем метку для отображения среднего пробега в день и добавляем ее на левую панель
        averageLabel = new JLabel("Среднее: 0.0 км/день 📊");
        // Устанавливаем размер шрифта метки
        averageLabel.setFont(new Font("Courier", Font.PLAIN, 16));
        // Добавляем метку на левую панель
        leftPanel.add(averageLabel);

        // Создаем метку для отображения наименьшего пробега и добавляем ее на левую панель
        minLabel = new JLabel("Минимум: 0.0 км 📉");
        // Устанавливаем размер шрифта метки
        minLabel.setFont(new Font("Courier", Font.PLAIN, 16));
        // Добавляем метку на левую панель
        leftPanel.add(minLabel);

        // Создаем метку для отображения наибольшего пробега и добавляем ее на левую панель
        maxLabel = new JLabel("Максимум: 0.0 км 📈");
        // Устанавливаем размер шрифта метки
        maxLabel.setFont(new Font("Courier", Font.PLAIN, 16));
        // Добавляем метку на левую панель
        leftPanel.add(maxLabel);

        // Создаем правую панель и добавляем ее в разделительное окно
        JPanel rightPanel = new JPanel();
        // Устанавливаем менеджер компоновки для правой панели
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        // Добавляем правую панель в разделительное окно
        splitPane.setRightComponent(rightPanel);


        // Создаем список дней и добавляем его в правую панель
        daysList = new JList<>(listModel);
        // Добавляем слушателя мыши к списку дней
        daysList.addMouseListener(new MouseAdapter() {
            // Определяем, что произойдет при клике мыши
            public void mouseClicked(MouseEvent evt) {
                // Если клик двойной
                if (evt.getClickCount() == 2) {
                    // Получаем индекс элемента, по которому кликнули
                    int index = daysList.locationToIndex(evt.getPoint());
                    // Удаляем этот элемент из модели данных списка
                    listModel.remove(index);
                    // Удаляем соответствующую дистанцию из списка дистанций
                    distances.remove(index);
                    // Пересчитываем и обновляем статистику
                    calculate();
                }
            }
        });
        // Создаем прокручиваемую область и добавляем в нее список дней
        JScrollPane scrollPane = new JScrollPane(daysList);
        // Устанавливаем предпочтительный размер прокручиваемой области
        scrollPane.setPreferredSize(new Dimension(160, 240));
        // Добавляем прокручиваемую область на правую панель
        rightPanel.add(scrollPane);
    }

    // Метод для пересчета и обновления статистики
    private void calculate() {
        // Инициализация переменных для хранения суммы, минимума и максимума
        double total = 0; // Сумма всех дистанций
        double min = Double.MAX_VALUE; // Минимальная дистанция, инициализируется максимально возможным числом
        double max = Double.MIN_VALUE; // Максимальная дистанция, инициализируется минимально возможным числом

        // Цикл для перебора всех дистанций
        for (double distance : distances) {
            // Суммируем все дистанции
            total += distance;

            // Если текущая дистанция меньше минимальной, обновляем минимум
            if (distance < min) {
                min = distance;
            }

            // Если текущая дистанция больше максимальной, обновляем максимум
            if (distance > max) {
                max = distance;
            }
        }

        // Вычисляем среднее значение (сумма дистанций делить на их количество)
        double average = total / distances.size();

        // Обновляем текст меток
        totalLabel.setText("Всего: " + total + " км 🏁📊");
        averageLabel.setText("Среднее: " + average + " км/день 📊");
        minLabel.setText("Минимум: " + min + " км 📉");
        maxLabel.setText("Максимум: " + max + " км 📈");
    }

    // Точка входа в программу
    public static void main(String[] args) {
        // Запускаем графический интерфейс в отдельном потоке
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Создаем новое окно и делаем его видимым
                new RunningApp().setVisible(true);
            }
        });
    }

}
