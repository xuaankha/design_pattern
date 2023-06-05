public class MySingleton {
    private static MySingleton instance = null;
    
    private MySingleton() {
        // Khởi tạo đối tượng
    }
    
    public static synchronized MySingleton getInstance() {
        if (instance == null) {
            instance = new MySingleton();
        }
        return instance;
    }
    
    // Các phương thức khác của đối tượng
}