import PacmanAI.Trainer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TrainerTests {
    private Trainer trainer;
    @Before
    public void setup() {
        trainer = new Trainer();
    }

    @Test
    public void TrainerShoudReportAccuracy() {
        for(int i = 0; i < 10; i++) {
            double accuracy =  trainer.computeAccuracy("src/test/resources/trainingData.txt","src/test/resources/survivormanattributes.txt");
            System.out.println(accuracy);
        }
        //Assert.assertTrue(accuracy > 90);
    }

    @Test
    public void TrainerShoudReportAccuracy2() {
        System.out.println("SECOUND LIST");
        for(int i = 0; i < 10; i++) {
            double accuracy =  trainer.computeAccuracy("src/test/resources/trainingData.txt","src/test/resources/survivormanattributes2.txt");
            System.out.println(accuracy);
        }
       // Assert.assertTrue(accuracy > 90);
    }
}
