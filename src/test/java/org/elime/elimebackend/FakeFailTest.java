package org.elime.elimebackend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FakeFailTest {
    private int i = 100;
    @Test
    void testThatAlwaysFails() {
        assertTrue(true);
    }

    @Test
    void testFail() {
        Assertions.fail();
    }
}
