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
import io.gravitee.reporter.prometheus.engine.ReportEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Sebastien Devaux (Zenika)
 *
 */
public final class PrometheusReportEngine implements ReportEngine {

	private final Logger LOGGER = LoggerFactory.getLogger(PrometheusReportEngine.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void report(Reportable reportable) {
		// TODO
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
