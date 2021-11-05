import controller.StudentController;
import org.apache.log4j.Logger;

public class App {
    public static Logger logger = Logger.getLogger(StudentController.class);

    public static void main(String[] args) {
        logger.info(App.class);
        logger.error(App.class);
    }
}
