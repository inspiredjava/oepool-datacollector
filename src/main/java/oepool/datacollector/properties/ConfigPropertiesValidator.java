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

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class ConfigPropertiesValidator implements PropertyValidator {

    @Override
    public boolean validate(ConfigProperties configProperties) {
        return poolAddressValidation(configProperties.getPoolAddress()) &&
                periodValidation(configProperties.getRequestPeriod()) &&
                walletsValidation(configProperties.getWalletArray());
    }

    private boolean walletsValidation(String[] walletArray) {
        // Null check and Empty array check
        if (walletArray == null || walletArray.length == 0) {
            return false;
        }

        // Regex check
        Set<String> testSet = new HashSet<>();
        boolean matcherFlag = true;
        boolean duplicateFlag = true;
        for (int i = 0; i < walletArray.length; i++) {
            String ethWalletPattern = "^0x[0-9a-zA-Z]{40}$";
            Pattern walletPattern = Pattern.compile(ethWalletPattern);
            matcherFlag = walletPattern.matcher(walletArray[i]).matches();
            duplicateFlag = testSet.add(walletArray[i]);
            if (!matcherFlag || !duplicateFlag) {
                break;
            }
        }
        return (matcherFlag && duplicateFlag);
    }

    private boolean periodValidation(Integer period) {
        return (period != null && period > 0);
    }

    private boolean poolAddressValidation(String poolAddress) {
        UrlValidator urlValidator = new UrlValidator();
        if (poolAddress == null ||
                StringUtils.isEmpty(poolAddress) ||
                !urlValidator.isValid(poolAddress)) {
            return false;
        }

        return true;
    }
}