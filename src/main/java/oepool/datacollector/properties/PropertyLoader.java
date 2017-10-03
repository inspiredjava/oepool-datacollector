/** Copyright (C) 2017 Oleksandr Los
 *  This file is part of oepool-datacollector project.
 *
 *  oepool-datacollector is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  oepool-datacollector project is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with oepool-datacollector project.  If not, see <http://www.gnu.org/licenses/>.
 */

package oepool.datacollector.properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    private static final String CONFIG_FILE_NAME = "config";
    private static final String CONFIG_PARAM_POOL = "pool";
    private static final String CONFIG_PARAM_WALLETS = "wallets";
    private static final String CONFIG_PARAM_PERIOD = "period";
    @Autowired
    private ConfigProperties configProperties = new ConfigProperties();
    private PropertyValidator propertyValidator;
    private final Logger LOGGER = LogManager.getLogger(this.getClass().getName());

    public ConfigProperties loadProperties() throws RuntimeException {
        LOGGER.info("Loading config file");
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(new File(CONFIG_FILE_NAME)));
            configProperties.setPoolAddress(properties.getProperty(CONFIG_PARAM_POOL));
            configProperties.setWalletArray(properties.getProperty(CONFIG_PARAM_WALLETS).split(","));
            configProperties.setRequestPeriod(Integer.valueOf(properties.getProperty(CONFIG_PARAM_PERIOD)));
        } catch (IOException e) {
            LOGGER.error("Can't read config file");
            System.exit(0);
        }

        if (propertyValidator.validate(configProperties)) {
            return configProperties;
        } else {
            throw new RuntimeException("Malformed config file");
        }
    }

    public void setPropertyValidator(PropertyValidator propertyValidator) {
        this.propertyValidator = propertyValidator;
    }

    public void setConfigProperties(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    public ConfigProperties getConfigProperties() {
        return configProperties;
    }
}