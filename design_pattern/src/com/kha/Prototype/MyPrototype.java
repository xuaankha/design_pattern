
public class MyPrototype {
    private String name;
    private int age;
	private Object context;

    public MyPrototype(String name, int age) {
        this.name = name;
        this.age = age;
    }
    MyPrototype obj1 = ((Object) context).getBean(MyPrototype.class, "Alice", 25);
    MyPrototype obj2 = ((Object) context).getBean(MyPrototype.class, "Bob", 30);
    // Các phương thức khác của đối tượng
}
