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

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import org.apache.ignite.IgniteClientDisconnectedException;
import org.apache.ignite.IgniteServices;
import org.apache.ignite.cluster.ClusterGroup;
import org.apache.ignite.scenario.internal.AbstractTask;
import org.apache.ignite.scenario.internal.PocServiceImpl;
import org.apache.ignite.scenario.internal.PocTesterArguments;
import org.apache.ignite.scenario.internal.TaskProperties;
import org.apache.ignite.scenario.internal.exceptions.TestFailedException;
import org.apache.ignite.scenario.internal.utils.PocTesterUtils;
import org.apache.ignite.services.ServiceConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import static org.apache.ignite.scenario.internal.utils.PocTesterUtils.sleep;

/**
 * Activates cluster.
 */
public class ServiceTask extends AbstractTask {
    /** */
    private static final Logger LOG = LogManager.getLogger(ServiceTask.class.getName());

    /**
     *
     * @param args Arguments.
     * @param props Task properties.
     */
    public ServiceTask(PocTesterArguments args, TaskProperties props){
        super(args, props);
    }

    /** {@inheritDoc} */
    @Override public void init() throws IOException {
        super.init();
    }

    /** {@inheritDoc} */
    @Override public void body0() throws TestFailedException {
        String threadName = Thread.currentThread().getName();

        deployServices(threadName);

        cancelServices(threadName);
    }



    /** {@inheritDoc} */
    @Nullable public String getTaskReport() {
        //TODO: avoid null result.
        return null;
    }

    /** */
    @Override protected void addPropsToMap(TaskProperties props){
        super.addPropsToMap(props);

        Map<String, String> hdrMap = (Map<String, String>) propMap.get("headersMap");

        hdrMap.put("unit", "boolean");
        hdrMap.put("data", "status");

        propMap.put("reportDir", "reports");
    }
}
