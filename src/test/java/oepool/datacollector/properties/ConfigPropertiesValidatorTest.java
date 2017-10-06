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

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class ConfigPropertiesValidatorTest {

    private static final String VALID_POOL_ADDRESS = "http://open-ethereum-pool.com";
    private static final String INVALID_POOL_ADDRESS = "http://open-ethereum-pool..com";
    private static final String VALID_ETH_VALLET_1 = "0x4C705E7A03B044dcA0D13943ccfa4C9e63eD1E96";
    private static final String VALID_ETH_VALLET_2 = "0x4C705E7A03B044dcA0D13943ccfa4C9e63eD1E97";
    private static final String INVALID_ETH_VALLET_1 = "0x4C705EcA0D13943ccfa4C9e63eD1E96";
    private static final String INVALID_ETH_VALLET_2 = "0x4C705$%A03B044dcA0D13943ccfa4C9e63eD1E96";
    private static final String INVALID_ETH_VALLET_3 = "0x4C2";
    private static final String INVALID_ETH_VALLET_4 = "0x4C705E7A03B044dcA0D13943ccfa4C9e63eD1E96qqqqqqq";
    private static final String INVALID_ETH_VALLET_5 = "0x4C705E7A03B044dcA0D13943ccfa4C9e63eD1E96 ";
    private static final String INVALID_ETH_VALLET_6 = " 0x4C705E7A03B044dcA0D13943ccfa4C9e63eD1E96";
    private static final String INVALID_ETH_VALLET_7 = "124C705E7A03B044dcA0D13943ccfa4C9e63eD1E96";
    private static final String INVALID_ETH_VALLET_8 = "120x4C705E7A03B044dcA0D13943ccfa4C9e63eD1E96";
    private static final Integer PERIOD = 10;

    private ConfigProperties configProperties = new ConfigProperties();
    private PropertyValidator propertyValidator = new ConfigPropertiesValidator();
    private String[] walletsArray_length_1 = new String[1];
    private String[] walletsArray_length_2 = new String[2];

    @Test
    public void validatorShouldPassAllProperties() {
        walletsArray_length_2[0] = VALID_ETH_VALLET_1;
        walletsArray_length_2[1] = VALID_ETH_VALLET_2;

        configProperties.setPoolAddress(VALID_POOL_ADDRESS);
        configProperties.setWalletArray(walletsArray_length_2);
        configProperties.setRequestPeriod(PERIOD);

        boolean validationResult = propertyValidator.validate(configProperties);

        assertTrue(validationResult);
    }

    @Test
    public void propertiesShouldContainPoolAddressParameter() {
        walletsArray_length_1[0] = VALID_ETH_VALLET_1;
        configProperties.setWalletArray(walletsArray_length_1);
        configProperties.setRequestPeriod(PERIOD);

        boolean validationResult = propertyValidator.validate(configProperties);

        assertFalse(validationResult);
    }

    @Test
    public void validatorShouldNotAllowInvalidPoolAddress() {
        walletsArray_length_2[0] = VALID_ETH_VALLET_1;
        configProperties.setPoolAddress(INVALID_POOL_ADDRESS);
        configProperties.setWalletArray(walletsArray_length_2);
        configProperties.setRequestPeriod(PERIOD);

        boolean validationResult = propertyValidator.validate(configProperties);
        assertFalse(validationResult);
    }

    @Test
    public void propertiesShouldContainWalletsParameter() {
        configProperties.setPoolAddress(VALID_POOL_ADDRESS);
        configProperties.setRequestPeriod(PERIOD);

        boolean validationResult = propertyValidator.validate(configProperties);

        assertFalse(validationResult);
    }

    @Test
    public void validatorShouldNotAllowEmptyWalletArray() {
        walletsArray_length_1[0] = "";
        configProperties.setPoolAddress(VALID_POOL_ADDRESS);
        configProperties.setWalletArray(walletsArray_length_1);
        configProperties.setRequestPeriod(PERIOD);

        boolean validationResult = propertyValidator.validate(configProperties);

        assertFalse(validationResult);
    }

    @Test
    public void validatorShouldNotAllowSameWallets() {
        walletsArray_length_2[0] = VALID_ETH_VALLET_1;
        walletsArray_length_2[1] = VALID_ETH_VALLET_1;

        configProperties.setPoolAddress(VALID_POOL_ADDRESS);
        configProperties.setWalletArray(walletsArray_length_2);
        configProperties.setRequestPeriod(PERIOD);

        boolean validationResult = propertyValidator.validate(configProperties);

        assertFalse(validationResult);
    }

    private Object[] getProperiesWithMalformedWallets() {
        String[] wallet1 = new String[1];
        String[] wallet2 = new String[1];
        String[] wallet3 = new String[1];
        String[] wallet4 = new String[1];
        String[] wallet5 = new String[1];
        String[] wallet6 = new String[1];
        String[] wallet7 = new String[1];
        String[] wallet8 = new String[1];
        wallet1[0] = INVALID_ETH_VALLET_1;
        wallet2[0] = INVALID_ETH_VALLET_2;
        wallet3[0] = INVALID_ETH_VALLET_3;
        wallet4[0] = INVALID_ETH_VALLET_4;
        wallet5[0] = INVALID_ETH_VALLET_5;
        wallet6[0] = INVALID_ETH_VALLET_6;
        wallet7[0] = INVALID_ETH_VALLET_7;
        wallet8[0] = INVALID_ETH_VALLET_8;

        ConfigProperties configProperties1 = new ConfigProperties();
        ConfigProperties configProperties2 = new ConfigProperties();
        ConfigProperties configProperties3 = new ConfigProperties();
        ConfigProperties configProperties4 = new ConfigProperties();
        ConfigProperties configProperties5 = new ConfigProperties();
        ConfigProperties configProperties6 = new ConfigProperties();
        ConfigProperties configProperties7 = new ConfigProperties();
        ConfigProperties configProperties8 = new ConfigProperties();

        configProperties1.setPoolAddress(VALID_POOL_ADDRESS);
        configProperties1.setWalletArray(wallet1);
        configProperties1.setRequestPeriod(PERIOD);

        configProperties2.setPoolAddress(VALID_POOL_ADDRESS);
        configProperties2.setWalletArray(wallet2);
        configProperties2.setRequestPeriod(PERIOD);

        configProperties3.setPoolAddress(VALID_POOL_ADDRESS);
        configProperties3.setWalletArray(wallet3);
        configProperties3.setRequestPeriod(PERIOD);

        configProperties4.setPoolAddress(VALID_POOL_ADDRESS);
        configProperties4.setWalletArray(wallet4);
        configProperties4.setRequestPeriod(PERIOD);

        configProperties4.setPoolAddress(VALID_POOL_ADDRESS);
        configProperties4.setWalletArray(wallet5);
        configProperties4.setRequestPeriod(PERIOD);

        configProperties4.setPoolAddress(VALID_POOL_ADDRESS);
        configProperties4.setWalletArray(wallet6);
        configProperties4.setRequestPeriod(PERIOD);

        configProperties4.setPoolAddress(VALID_POOL_ADDRESS);
        configProperties4.setWalletArray(wallet7);
        configProperties4.setRequestPeriod(PERIOD);

        configProperties4.setPoolAddress(VALID_POOL_ADDRESS);
        configProperties4.setWalletArray(wallet8);
        configProperties4.setRequestPeriod(PERIOD);

        return new ConfigProperties[] {
                configProperties1,
                configProperties2,
                configProperties3,
                configProperties4,
                configProperties5,
                configProperties6,
                configProperties7,
                configProperties8
        };
    }

    @Test
    @Parameters(method = "getProperiesWithMalformedWallets")
    public void walletsShouldFitFormat(ConfigProperties configProperties) {
        assertFalse(propertyValidator.validate(configProperties));
    }

    @Test
    public void propertiesShouldContainPeriodParameter() {
        walletsArray_length_1[0] = VALID_ETH_VALLET_1;
        configProperties.setPoolAddress(VALID_POOL_ADDRESS);
        configProperties.setWalletArray(walletsArray_length_1);

        boolean validationResult = propertyValidator.validate(configProperties);

        assertFalse(validationResult);
    }

    private Object[] getPropertiesWithNegativeAndZeroPeriod() {
        ConfigProperties configProperties_1 = new ConfigProperties();

        String[] wallets = new String[1];
        wallets[0] = VALID_ETH_VALLET_1;
        configProperties.setPoolAddress(VALID_POOL_ADDRESS);
        configProperties.setWalletArray(wallets);
        configProperties.setRequestPeriod(-1);

        configProperties_1.setPoolAddress(VALID_POOL_ADDRESS);
        configProperties_1.setWalletArray(wallets);
        configProperties_1.setRequestPeriod(0);

        return new ConfigProperties[] {
                configProperties,
                configProperties_1
        };
    }

    @Test
    @Parameters(method = "getPropertiesWithNegativeAndZeroPeriod")
    public void validatorShouldNotAllowNegativeOrZeroPeriod(ConfigProperties configProperties) {
        assertFalse(propertyValidator.validate(configProperties));
    }
}