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

package org.gridgain.poc.tasks;

import java.io.IOException;

/**
 * Created by oostanin on 10.02.18.
 */
public interface GridTask {

//    public void setArgs(PocTesterArguments args);

//    public PocTesterArguments getArgs();

//    public void setIgnite(Ignite ignite);

//    public String getTaskName();

    public void init() throws IOException;

//    public long getTimeToWork();

//    public long getReportInterval();

//    public int getThreads();

    public void setUp() throws Exception ;

    public void body() throws Exception;

    public void tearDown();

    public String getTaskReport() throws Exception;

}
