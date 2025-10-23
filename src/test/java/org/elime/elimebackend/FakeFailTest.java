package org.elime.elimebackend;

import org.junit.jupiter.api.Test;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;

public class FakeFailTest {
    @Test
    void testThatAlwaysFails() {
        fail("Questo test fallisce intenzionalmente per testare la CI");
    }
}
