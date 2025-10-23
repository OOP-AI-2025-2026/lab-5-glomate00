package ua.opnu;

public class Main {
    public static void main(String[] args) {

        // --- Тестування TimeSpan (Завдання 1) ---
        TimeSpan t1 = new TimeSpan();        // Конструктор без аргументів
        TimeSpan t2 = new TimeSpan(90);      // Конструктор з хвилинами: 1:30
        TimeSpan t3 = new TimeSpan(1, 45);   // Конструктор з годинами/хвилинами
        TimeSpan t4 = new TimeSpan(t3);      // Конструктор з TimeSpan: 1:45

        // Тестування перевантаженого add
        t1.add(1, 30);      // add(години, хвилини) -> t1 = 1:30
        t2.add(15);         // add(хвилини) -> t2 = 1:45
        t3.add(t2);         // add(TimeSpan) -> t3 = 1:45 + 1:45 = 3:30

       ё
        t4.subtract(1, 15); 
        t4.subtract(30);    
        t4.subtract(t1);   

        System.out.println("--- Результати TimeSpan (Завдання 1) ---");
        System.out.println("t1 = " + t1);
        System.out.println("t2 = " + t2);
        System.out.println("t3 = " + t3);
        System.out.println("t4 = " + t4);


        Person[] people = {
                
                new Person("Шевченко", "Андрій", 35),
                new Student("Кузьменко", "Дарина", 21, "ЕК-22", "789012"),
                new Student("Задорожний", "Максим", 18, "ІП-24", "345678"),
                new Lecturer("Григоренко", "Сергій", 52, "Математики", 31500.0)
        };

        System.out.println("\n--- Інформація про людей (Завдання 2) ---");
        // Поліморфний виклик toString()
        for (Person person : people) {
            System.out.println(person);
        }
    }
}
