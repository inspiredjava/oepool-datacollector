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

package oepool.datacollector;

import oepool.datacollector.properties.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import oepool.datacollector.controller.WalletController;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoopThread implements Runnable {

    @Autowired
    private WalletController walletController;

    @Autowired
    private ConfigProperties configProperties;

    private List<String> wallets;
    private int period;

    @Override
    public void run() {
        while (true) {
            for (String listItem : configProperties.getWalletArray()) {
                walletController.requestData(listItem);
            }
            try {
                TimeUnit.SECONDS.sleep(60 * configProperties.getRequestPeriod());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setWalletController(WalletController walletController) {
        this.walletController = walletController;
    }

    public void setWallets(List<String> wallets) {
        this.wallets = wallets;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
