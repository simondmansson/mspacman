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
        double accuracy =  trainer.computeAccuracy("src/test/resources/trainingData.txt","src/test/resources/survivormanattributes.txt");
        Assert.assertTrue(accuracy > 90);
    }
}
