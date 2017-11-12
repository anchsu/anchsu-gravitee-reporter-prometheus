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
package io.gravitee.reporter.prometheus.config;

import io.gravitee.common.node.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class used to register the SupervisionMBean classes.
 *
 * @author sebastien devaux
 */
@Component
public class CpuMBeanConfiguration {

    /**
     * Set of registered mbean.
     */
    private final Map<ObjectName, StandardMBean> registeredBean = new HashMap<>();

    @Autowired
    private Node node;

    /**
     * Initialize a SupervisionMBean
     */
    @PostConstruct
    private void createMBean() {
        final MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        try {

            final ObjectName mbeanName = new ObjectName("gravitee:type=" + node.id() + ",step=cpu");
            final CpuMBean supervisionMbean = new CpuMBeanImpl();
            final StandardMBean standardMBean = new StandardMBean(supervisionMbean, CpuMBean.class, false);

            server.registerMBean(standardMBean, mbeanName);

            this.registeredBean.put(mbeanName, standardMBean);
        } catch (final MalformedObjectNameException | NotCompliantMBeanException | InstanceAlreadyExistsException | MBeanRegistrationException exception) {
            // tODO
            //log.error("Impossible to register MBean for step {}, error is {}", supervisionDriver.getName(), exception.getMessage(), exception);
        }
    }

    /**
     * Unregister JMX mbean
     * @throws InstanceNotFoundException
     * @throws MBeanRegistrationException
     */
    @PreDestroy
    private void destroy() throws MBeanRegistrationException, InstanceNotFoundException {
        final MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        for (final ObjectName beanName : this.registeredBean.keySet()) {
            server.unregisterMBean(beanName);
        }
    }
}

