package org.example;

import org.junit.Assert;
import org.junit.Test;

public class IndexDebuggerTest {

    @Test
    public void shouldDebugIndexes() {
        String location = "index";
        boolean result = IndexDebugger.debug(location);
        Assert.assertTrue(result);
    }
}