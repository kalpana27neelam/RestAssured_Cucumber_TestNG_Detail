package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.TestContext;

public class Hooks {
    private TestContext testContext;

    public Hooks(TestContext testContext)
    {
        this.testContext=testContext;
    }


    @Before
    public void beforeScenario(Scenario scenario)
    {
        System.out.println("==== Starting  scenario  :"+scenario.getName()+" =========");
    }

    @After
    public void afterScenario(Scenario scenario)
    {
        if (scenario.isFailed() && testContext.getResponse() != null)
        {
            System.out.println(" ===== Failed : Scenario :"+scenario.getName());
            System.out.println(" ======= FAILED last Response Body ======");
            System.out.println(testContext.getResponse().getBody().asPrettyString());
        }
        System.out.println(" ===== Finished : Scenario :"+scenario.getName()+" ========");
    }
}
