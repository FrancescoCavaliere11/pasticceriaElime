package org.elime.elimebackend;

import org.junit.jupiter.api.Test;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;

public class FakeFailTest {
    private int i = 0;
    @Test
    void testThatAlwaysFails() {
        assertTrue(true);
    }
}
