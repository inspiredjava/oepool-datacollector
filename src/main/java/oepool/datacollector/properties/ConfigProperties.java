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

public class ConfigProperties {

    private String poolAddress;
    private String[] walletArray;
    private Integer requestPeriod;

    public String getPoolAddress() {
        return poolAddress;
    }

    public void setPoolAddress(String poolAddress) {
        this.poolAddress = poolAddress;
    }

    public String[] getWalletArray() {
        return walletArray;
    }

    public void setWalletArray(String[] walletArray) {
        this.walletArray = walletArray;
    }

    public Integer getRequestPeriod() {
        return requestPeriod;
    }

    public void setRequestPeriod(Integer requestPeriod) {
            this.requestPeriod = requestPeriod;
        }
}
