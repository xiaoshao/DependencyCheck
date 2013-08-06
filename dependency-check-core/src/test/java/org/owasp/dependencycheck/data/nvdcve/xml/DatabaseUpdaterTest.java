/*
 * This file is part of dependency-check-core.
 *
 * Dependency-check-core is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * Dependency-check-core is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * dependency-check-core. If not, see http://www.gnu.org/licenses/.
 *
 * Copyright (c) 2012 Jeremy Long. All Rights Reserved.
 */
package org.owasp.dependencycheck.data.nvdcve.xml;

import org.owasp.dependencycheck.data.nvdcve.xml.DatabaseUpdater;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.owasp.dependencycheck.utils.Settings;

/**
 *
 * @author Jeremy Long (jeremy.long@owasp.org)
 */
public class DatabaseUpdaterTest {

    public DatabaseUpdaterTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    private String old12;
    private String old20;

    @Before
    public void setUp() throws Exception {
        old12 = Settings.getString(Settings.KEYS.CVE_MODIFIED_12_URL);
        old20 = Settings.getString(Settings.KEYS.CVE_MODIFIED_20_URL);

        File file = new File("target/test-classes/nvdcve-2012.xml");
        String path = "file:///" + file.getCanonicalPath();
        Settings.setString(Settings.KEYS.CVE_MODIFIED_12_URL, path);

        file = new File("target/test-classes/nvdcve-2.0-2012.xml");
        path = "file:///" + file.getCanonicalPath();
        Settings.setString(Settings.KEYS.CVE_MODIFIED_20_URL, path);

        file = new File("target/test-classes/data.zip");
        path = "file:///" + file.getCanonicalPath();
        Settings.setString(Settings.KEYS.BATCH_UPDATE_URL, path);
    }

    @After
    public void tearDown() {
        Settings.setString(Settings.KEYS.CVE_MODIFIED_12_URL, old12);
        Settings.setString(Settings.KEYS.CVE_MODIFIED_20_URL, old20);
        Settings.setString(Settings.KEYS.BATCH_UPDATE_URL, "");
    }

    /**
     * Test of update method (when in batch mode), of class DatabaseUpdater.
     *
     * @throws Exception
     */
    @Test
    public void testBatchUpdate() throws Exception {
        DatabaseUpdater instance = new DatabaseUpdater();
        instance.deleteExistingData();
        instance.update();
    }
}
