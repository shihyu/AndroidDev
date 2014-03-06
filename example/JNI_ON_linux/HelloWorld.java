class HelloWorld {
    public native void sayHello();
    static {
        System.loadLibrary("HelloWorld");
    }
    public static void main(String[] args) {
        (new HelloWorld()).sayHello();
    }
}
