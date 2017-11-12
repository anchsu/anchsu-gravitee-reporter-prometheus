/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.reporter.prometheus.engine.impl;

import io.gravitee.reporter.api.Reportable;
import io.gravitee.reporter.api.http.Metrics;
import io.gravitee.reporter.api.monitor.Monitor;
import io.gravitee.reporter.prometheus.config.CpuMBean;
import io.gravitee.reporter.prometheus.engine.ReportEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Sebastien Devaux (Zenika)
 *
 */
public final class PrometheusReportEngine implements ReportEngine {

	@Autowired
	private CpuMBean cpuMBean;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void report(Reportable reportable) {
		// TODO
        if (reportable instanceof Monitor) {
            final Monitor monitor = (Monitor) reportable;
            this.reportMonitor(monitor);
        }
	}

    private void reportMonitor(Monitor monitor) {
        final short percent = monitor.getOs().cpu.percent;
        this.cpuMBean.setCpuPercent(String.valueOf(percent));

    }

    /**
	 * {@inheritDoc}
	 */
	@Override
	public void start() {
	    // TODO
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop() {
	    // TODO
	}
}
