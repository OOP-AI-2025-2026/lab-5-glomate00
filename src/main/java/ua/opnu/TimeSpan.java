package ua.opnu;

public class TimeSpan {
    private int hours;
    private int minutes;

    private void normalize() {
        
        if (this.minutes >= 60) {
            this.hours += this.minutes / 60;
            this.minutes %= 60;
        } 
    
        else if (this.minutes < 0) {
            int hoursToSubtract = (-this.minutes + 59) / 60;
            this.hours -= hoursToSubtract;
            this.minutes += hoursToSubtract * 60;
        }

        if (this.hours < 0 || (this.hours == 0 && this.minutes < 0)) {
            this.hours = 0;
            this.minutes = 0;
        }
    }

    public TimeSpan() {
        this(0, 0); 
    }

    public TimeSpan(int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Minutes cannot be negative.");
        }
        this.hours = 0;
        this.minutes = minutes;
        normalize(); 
    }

    public TimeSpan(int hours, int minutes) {
        if (hours < 0 || minutes < 0) {
            throw new IllegalArgumentException("Arguments cannot be negative.");
        }
        this.hours = hours;
        this.minutes = minutes;
        normalize(); 
    }

    public TimeSpan(TimeSpan timeSpan) {
        // Зчитуємо дані з об'єкта
        this.hours = timeSpan.getHours();
        this.minutes = timeSpan.getMinutes();
    }

    public int getHours() {
        return this.hours;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public double getTotalHours() {
        return this.hours + this.minutes / 60.0;
    }

    public int getTotalMinutes() {
        return this.hours * 60 + this.minutes;
    }


    // 5. add(години, хвилини)
    public void add(int hours, int minutes) {
        if (hours < 0 || minutes < 0) return;
        this.hours += hours;
        this.minutes += minutes;
        normalize(); 
    }
    
    // 6. add(хвилини)
    public void add(int minutes) {
        add(0, minutes); 
    }

    
    public void add(TimeSpan timespan) {
        add(timespan.getHours(), timespan.getMinutes()); 
    }

   
    public void addTimeSpan(TimeSpan timespan) {
        add(timespan);
    }
    

    public void subtract(int hours, int minutes) {
        if (hours < 0 || minutes < 0) return;
        
        int totalToSubtract = hours * 60 + minutes;
        
        if (totalToSubtract > getTotalMinutes()) {
            return; 
        }
        
        this.hours -= hours;
        this.minutes -= minutes;
        normalize(); 
    }
    
    // 9. subtract(хвилини)
    public void subtract(int minutes) {
        subtract(0, minutes); 
    }

    public void subtract(TimeSpan span) {
       
        int total1 = this.getTotalMinutes();
        int total2 = span.getTotalMinutes();

        if (total2 > total1) {
            return;
        }

        int newTotal = total1 - total2;
        this.hours = newTotal / 60;
        this.minutes = newTotal % 60;
        
    }
    

    public void scale(int factor) {
        if (factor <= 0) {
            return;
        }

        long total = (long) getTotalMinutes() * factor; 
        this.hours = (int) (total / 60);
        this.minutes = (int) (total % 60);
    }

    @Override
    public String toString() {
        return String.format("%d hours, %d minutes", hours, minutes);
    }
}
