package ua.opnu;

public class TimeSpan {
    // Приватні поля для зберігання часового інтервалу (Інкапсуляція)
    private int hours;
    private int minutes;

    // --- Допоміжний метод ---
    private void normalize() {
        if (this.minutes >= 60) {
            this.hours += this.minutes / 60;
            this.minutes %= 60;
        } else if (this.minutes < 0) {
            int hoursToSubtract = (-this.minutes + 59) / 60;
            this.hours -= hoursToSubtract;
            this.minutes += hoursToSubtract * 60;
        }
        if (this.hours < 0 || (this.hours == 0 && this.minutes < 0)) {
            this.hours = 0;
            this.minutes = 0;
        }
    }

    // --- Конструктори (Перевантаження) ---

    // 1. Конструктор без аргументів
    public TimeSpan() {
        this(0, 0);
    }

    // 2. Конструктор з 1 аргументом (хвилини)
    public TimeSpan(int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Minutes argument cannot be negative.");
        }
        this.hours = 0;
        this.minutes = minutes;
        normalize();
    }

    // 3. Конструктор з 2 аргументами (години та хвилини)
    public TimeSpan(int hours, int minutes) {
        if (hours < 0 || minutes < 0) {
            throw new IllegalArgumentException("Hours and minutes cannot be negative.");
        }
        this.hours = hours;
        this.minutes = minutes;
        normalize();
    }

    // 4. Конструктор з 1 аргументом типу TimeSpan
    public TimeSpan(TimeSpan timeSpan) {
        this.hours = timeSpan.getHours();
        this.minutes = timeSpan.getMinutes();
    }

    // --- Гетери ---

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    // --- Додаткові методи ---

    public double getTotalHours() {
        return this.hours + (this.minutes / 60.0);
    }

    public int getTotalMinutes() {
        return this.hours * 60 + this.minutes;
    }

    public void scale(int factor) {
        if (factor <= 0) {
            throw new IllegalArgumentException("Factor must be positive.");
        }
        long totalMinutes = (long) getTotalMinutes();
        totalMinutes *= factor;
        this.hours = (int) (totalMinutes / 60);
        this.minutes = (int) (totalMinutes % 60);
    }

    // --- Методи додавання (Перевантаження) ---

    // 5. add(hours, minutes)
    public void add(int hours, int minutes) {
        if (hours < 0 || minutes < 0) {
             throw new IllegalArgumentException("Addition arguments cannot be negative.");
        }
        this.hours += hours;
        this.minutes += minutes;
        normalize();
    }

    // 6. add(minutes)
    public void add(int minutes) {
        add(0, minutes);
    }

    // 7. add(timeSpan)
    public void add(TimeSpan timeSpan) {
        add(timeSpan.getHours(), timeSpan.getMinutes());
    }
    
    // addTimeSpan(timespan) - Для сумісності з вимогою
    public void addTimeSpan(TimeSpan timespan) {
        add(timespan);
    }

    // --- Методи віднімання (Перевантаження) ---

    // 8. subtract(hours, minutes)
    public void subtract(int hours, int minutes) {
        if (hours < 0 || minutes < 0) {
             throw new IllegalArgumentException("Subtraction arguments cannot be negative.");
        }
        int currentTotalMinutes = getTotalMinutes();
        int subtractTotalMinutes = hours * 60 + minutes;
        if (subtractTotalMinutes > currentTotalMinutes) {
            return;
        }
        this.hours -= hours;
        this.minutes -= minutes;
        normalize();
    }

    // 9. subtract(minutes)
    public void subtract(int minutes) {
        subtract(0, minutes);
    }

    // 10. subtract(timeSpan)
    public void subtract(TimeSpan timeSpan) {
        subtract(timeSpan.getHours(), timeSpan.getMinutes());
    }
}
