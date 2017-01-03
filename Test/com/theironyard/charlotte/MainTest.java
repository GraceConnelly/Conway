package com.theironyard.charlotte;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by graceconnelly on 1/2/17.
 */
public class MainTest {
    @Test
    public void main() throws Exception {

    }
    @Test
    public void checkAliveNeighbors() throws Exception {
        assertEquals(2, Main.getAliveNeighbors(new String[][]{{"1", "0", "0"}, {"0", "1", "0"}, {"0", "0", "1"}},1,1));

    }
    @Test
    public void checkBensAliveNeighbors() throws Exception {
        assertEquals(2, Main.bensGetAliveNeighbors(new String[][]{{"1", "0", "0"}, {"0", "1", "0"}, {"0", "0", "1"}},1,1));

    }
}