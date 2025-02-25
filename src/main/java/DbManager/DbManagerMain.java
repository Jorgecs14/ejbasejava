package DbManager;

public class DbManagerMain {
    public static void main(String[] args) {
        DbManager dbManager = new DbManager();
        try {
            dbManager.connect();
            dbManager.disconnect();
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
    }
}
