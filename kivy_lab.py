# Импорт класса App из модуля kivy.app
from kivy.app import App

# Импорт различных виджетов из модулей kivy.uix
from kivy.uix.boxlayout import BoxLayout
from kivy.uix.button import Button
from kivy.uix.label import Label
from kivy.uix.gridlayout import GridLayout
from kivy.uix.widget import Widget

# Импорт модуля Config для настройки окна и функций sin, cos, radians из модуля math для вычислений
from kivy.config import Config
from math import sin, cos, radians

# Настройка параметров окна
Config.set('graphics', 'resizable', 0)  # Запрет изменения размера окна
Config.set('graphics', 'width', 600)  # Установка ширины окна
Config.set('graphics', 'height', 800)  # Установка высоты окна


# Определение класса Calculator, наследующего от App
class Calculator(App):
    def update_label(self):  # Обновление текста лейбла
        self.label.text = self.calculating

    def add_number(self, instance):  # Добавление числа в текст лейбла
        if (self.calculating == "0"):
            self.calculating = ""  # Очищаем калькулируемое выражение

        self.calculating += str(instance.text)  # Добавляем число в калькулируемое выражение
        self.update_label()  # Обновляем лейбл

    def add_operation(self, instance):  # Добавление операции в текст лейбла и проверка на умножение
        if (str(instance.text).lower() == "x"):
            self.calculating += "*"  # Добавляем умножение в калькулируемое выражение
        else:
            self.calculating += str(instance.text)  # Добавляем операцию в калькулируемое выражение

        self.update_label()  # Обновляем лейбл

    def calc_result(self, instance):  # Выполнение основных операций
        self.label.text = str(eval(self.label.text))
        self.calculating = "0"  # Очищаем калькулируемое выражение

    def reset(self, instance):  # Сброс текста лейбла
        self.calculating = "0"  # Очищаем калькулируемое выражение
        self.update_label()  # Обновляем лейбл

    def to_binary(self, instance):  # Преобразование числа в двоичную систему счисления
        decimal_number = int(self.label.text)  # Преобразуем текст в число
        binary_number = bin(decimal_number)[2:]  # Преобразуем число в двоичную систему счисления
        self.label.text = binary_number  # Записываем результат в лейбл

    def to_octal(self, instance):  # Преобразование числа в восьмеричную систему счисления
        decimal_number = int(self.label.text)  # Преобразуем текст в число
        octal_number = oct(decimal_number)[2:]  # Преобразуем число в восьмеричную систему счисления
        self.label.text = octal_number  # Записываем результат в лейбл

    def to_decimal(self, instance):  # Преобразование числа из двоичной системы счисления в десятичную
        try:
            decimal_number = int(self.label.text, 2)  # Преобразуем число из двоичной системы счисления в десятичную
            self.label.text = str(decimal_number)  # Записываем результат в лейбл
        except ValueError:
            self.label.text = "Invalid input"  # При наличии ошибки выводим ошибку

    def to_hexadecimal(self, instance):  # Преобразование числа в шестнадцатеричную систему счисления
        decimal_number = int(self.label.text)  # Преобразуем текст в число
        hexadecimal_number = hex(decimal_number)[2:]  # Преобразуем число в шестнадцатеричную систему счисления
        self.label.text = hexadecimal_number  # Записываем результат в лейбл

    def calculate_sin(self, instance):  # Вычисление синуса числа
        number = float(self.label.text)  # Преобразуем текст в число
        result = sin(radians(number))  # Вычисляем синус
        self.label.text = str(result)  # Записываем результат в лейбл

    def calculate_cos(self, instance):  # Вычисление косинуса числа
        number = float(self.label.text)  # Преобразуем текст в число
        result = cos(radians(number))  # Вычисляем косинус
        self.label.text = str(result)  # Записываем результат в лейбл

    def to_decimal_from_octal(self, instance):  # Преобразование числа из восьмеричной системы счисления в десятичную
        try:
            decimal_number = int(self.label.text, 8)  # Преобразуем число из восьмеричной системы счисления в десятичную
            self.label.text = str(decimal_number)  # Записываем результат в лейбл
        except ValueError:  # При ошибке
            self.label.text = "Invalid input"  # Выводим текст ошибки

    def to_decimal_from_hexadecimal(self, instance):
        # Преобразование числа из шестнадцатеричной системы счисления в десятичную
        try:
            decimal_number = int(self.label.text, 16)  # Преобразуем из шестнадцатеричной системы счисления в десятичную
            self.label.text = str(decimal_number)  # Записываем результат в лейбл
        except ValueError:  # При ошибке
            self.label.text = "Invalid input"  # Выводим текст ошибки

    # Определение метода для настройки графической части приложения
    def build(self):
        # Инициализация переменной, отвечающей за текущие вычисления
        self.calculating = "0"

        # Создание вертикального BoxLayout с отступами 20 пикселей со всех сторон
        box_layout = BoxLayout(orientation='vertical', padding=20)

        # Создание GridLayout с 7 столбцами, отступами 4 пикселя между виджетами и
        # размером, занимающим 70% от родительского виджета
        grid_layout = GridLayout(cols=7, spacing=4, size_hint=(1, 0.7))

        # Создание Label с начальным текстом "0", размером шрифта 50,
        # выравниванием текста по правому краю и по центру по вертикали,
        # а также размером, занимающим 50% по вертикали и 400 пикселей по горизонтали
        self.label = Label(text="0", font_size=50, halign="right", valign="center", size_hint=(1, 0.5),
                           text_size=(400, 150))

        # Добавление Label в BoxLayout
        box_layout.add_widget(self.label)

        # Создание списка виджетов с указанием типа виджета, его текста и функции,
        # которая будет вызываться при нажатии на виджет
        widgets = [
            (Button, "7", self.add_number),    # Кнопка 7
            (Button, "8", self.add_number),    # Кнопка 8
            (Button, "9", self.add_number),    # Кнопка 9
            (Button, "X", self.add_operation),  # Кнопка Умножения
            (Button, "10->2", self.to_binary),  # Кнопка преобразования из десятичной во вторичную СС
            (Button, "10->8", self.to_octal),  # Кнопка преобразования из десятичной в восьмеричную СС
            (Button, "10->16", self.to_hexadecimal),  # Кнопка преобразования из десятичной в шестнадцатеричную СС
            (Button, "4", self.add_number),  # Кнопка 4
            (Button, "5", self.add_number),  # Кнопка 5
            (Button, "6", self.add_number),  # Кнопка 6
            (Button, "-", self.add_operation),  # Кнопка Вычитания
            (Button, "2->10", self.to_decimal),  # Кнопка преобразования из двоичной в десятичную СС
            (Button, "8->10", self.to_decimal_from_octal),  # Кнопка преобразования из восьмеричной в десятичную СС
            (Button, "16->10", self.to_decimal_from_hexadecimal),  # Кнопка преобразования из 16-ричной в 10-чную СС
            (Button, "1", self.add_number),  # Кнопка 1
            (Button, "2", self.add_number),  # Кнопка 2
            (Button, "3", self.add_number),  # Кнопка 3
            (Button, "+", self.add_operation),  # Кнопка Сложения
            (Button, "sin", self.calculate_sin),  # Кнопка вычисления синуса
            (Button, "cos", self.calculate_cos),  # Кнопка вычисления косинуса
            (Widget,),  # Пустое пространство
            (Button, "C", self.reset),  # Кнопка очищения
            (Button, "0", self.add_number),  # Кнопка 0
            (Button, ".", self.add_number),  # Кнопка точка
            (Button, "/", self.add_operation),  # Кнопка Деления
            (Button, "=", self.calc_result),  # Кнопка вычисления
            (Widget,),  # Пустое пространство
            (Widget,),  # Пустое пространство
        ]

        # Добавление виджетов из списка в GridLayout
        for widget in widgets:
            params = {}
            if widget[0] != Widget:
                params = {
                    "text": widget[1],
                    "on_press": widget[2]
                }
            grid_layout.add_widget(widget[0](**params))

        # Добавление GridLayout в BoxLayout
        box_layout.add_widget(grid_layout)

        # Возврат рамки layout для отображения в приложении
        return box_layout


# Запуск приложения
Calculator().run()
