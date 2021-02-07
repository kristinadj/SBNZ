package sbz.cardiagnosticbe.droolsunit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        FormatDtcCode.class,
        FilterFailureByDtc.class,
        CheckFailureHistory.class,
        SortPossibleFailures.class,
        DetectRelatedFailures.class,
        DetectRelatedFailures.class
})
public class AllDroolsUnitTests {
}
