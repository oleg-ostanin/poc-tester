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

import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.scenario.internal.AbstractSberbankTask;
import org.apache.ignite.scenario.internal.PocTesterArguments;
import org.apache.ignite.scenario.internal.TaskProperties;
import org.apache.ignite.scenario.internal.utils.PocTesterUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

public class SberProcessingCleanupTask extends AbstractSberbankTask {
    private static final Logger LOG = LogManager.getLogger(SberProcessingCleanupTask.class.getName());

    private static final String SQL_DELETE_HISTORY = "DELETE FROM " + AUTH_HISTORY_TABLE_NAME + " WHERE ts < ?";

    private long timeThresholdMillis;

    public SberProcessingCleanupTask(PocTesterArguments args) {
        super(args);
    }

    public SberProcessingCleanupTask(PocTesterArguments args, TaskProperties props) {
        super(args, props);
    }

    @Override
    public void init() throws IOException {
        super.init();

        timeThresholdMillis = props.getLong("timeThreshold", DFLT_CLEANUP_THRESHOLD_SECONDS) * 1000;
    }

    @Override
    protected String reportString() throws Exception {
        return null;
    }

    @Override
    protected void body0() throws Exception {
        LOG.info("Iteration start");

        long timeNow = Instant.now().toEpochMilli();

        IgniteCache<Object, Object> histCache = ignite().cache(AUTH_HISTORY_CACHE_NAME);

        SqlFieldsQuery deleteQry = new SqlFieldsQuery(SQL_DELETE_HISTORY)
                .setArgs(timeNow - timeThresholdMillis);

        try {
            histCache.query(deleteQry);
        }
        catch (Exception e) {
            LOG.error("Failed to delete old entries from {}", AUTH_HISTORY_CACHE_NAME, e);
        }

        LOG.info("Iteration end");

        PocTesterUtils.sleep(interval);
    }
}
