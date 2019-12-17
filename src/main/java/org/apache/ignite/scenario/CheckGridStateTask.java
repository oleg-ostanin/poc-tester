/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.scenario;

import java.io.IOException;
import java.util.Map;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.internal.pagemem.PageUtils;
import org.apache.ignite.scenario.internal.AbstractTask;
import org.apache.ignite.scenario.internal.PocTesterArguments;
import org.apache.ignite.scenario.internal.TaskProperties;
import org.apache.ignite.scenario.internal.exceptions.TestFailedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

/**
 * Activates cluster.
 */
public class CheckGridStateTask extends AbstractTask {
    /** */
    private static final Logger LOG = LogManager.getLogger(CheckGridStateTask.class.getName());

    /**
     *
     * @param args Arguments.
     * @param props Task properties.
     */
    public CheckGridStateTask(PocTesterArguments args, TaskProperties props){
        super(args, props);
    }

    /** {@inheritDoc} */
    @Override public void setUp() throws Exception {
        super.setUp();
    }

    /** {@inheritDoc} */
    @Override public void body0() throws TestFailedException {
        LOG.info(String.format("Total size of all caches is %d", getTotalCachesSize()));

        IgniteCache<String, String> msgCache = ignite().cache(MSG_CACHE_NAME);

        // TODO: remove hardcode
        LOG.info(String.format("Flag for 'dataLost' key is '%s'", msgCache.get("dataLost")));
        LOG.info(String.format("Flag for 'pauseRestart' key is '%s'", msgCache.get("pauseRestart")));
        LOG.info(String.format("Flag for 'pauseRestart2' key is '%s'", msgCache.get("pauseRestart2")));
        LOG.info(String.format("Flag for 'pauseTX' key is '%s'", msgCache.get("pauseTX")));
        LOG.info(String.format("Flag for 'pauseCheck' key is '%s'", msgCache.get("pauseCheck")));
    }

    /** {@inheritDoc} */
    @Override public String getTaskReport() {
        //TODO: avoid null result.
        return null;
    }

    /** {@inheritDoc} */
    @Override public void tearDown() {
        super.tearDown();
    }

    /** */
    protected void addPropsToMap(TaskProperties props){
        super.addPropsToMap(props);

        Map<String, String> hdrMap = (Map<String, String>) propMap.get("headersMap");

        hdrMap.put("unit", "boolean");
        hdrMap.put("data", "status");

        propMap.put("reportDir", "reports");
    }
}
