package org.example.utility;

import org.apache.commons.cli.Options;
import org.example.handler.FileHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.junit.jupiter.MockitoExtension;

    @ExtendWith(MockitoExtension.class)
    class UtilityCmdTest {

        @InjectMocks
        private UtilityCmd utilityCmd;

        @Mock
        private FileHandler fileHandler;

    @Test
    void testSetOptions() {
        Options options = utilityCmd.setOptions();
        assertEquals(5, options.getOptions().size());
        assertTrue(options.hasOption("o"));
        assertTrue(options.hasOption("p"));
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("s"));
        assertTrue(options.hasOption("f"));
    }
}