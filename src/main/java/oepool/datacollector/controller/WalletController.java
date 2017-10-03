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

package oepool.datacollector.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import oepool.datacollector.dao.WalletDao;
import oepool.datacollector.dao.JsonParser;
import oepool.datacollector.model.WalletData;

import java.io.IOException;

public class WalletController {

    @Autowired
    private JsonParser jsonParser;
    @Autowired
    private WalletDao walletDao;
    private String PoolAddress;
    private static final String PATH_POSTFIX = "/api/accounts/";
    private final Logger LOGGER = LogManager.getLogger(this.getClass().getName());

    public void requestData(String wallet) {
        LOGGER.info("Getting data from " + PoolAddress + PATH_POSTFIX + wallet);
        try {
            WalletData walletData = jsonParser.getData(PoolAddress + PATH_POSTFIX + wallet);
            walletDao.save(walletData);
        } catch (IOException e) {
            LOGGER.error("Error getting data", e);
        }
    }

    public JsonParser getJsonParser() {
        return jsonParser;
    }

    public void setJsonParser(JsonParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    public WalletDao getWalletDao() {
        return walletDao;
    }

    public void setWalletDao(WalletDao walletDao) {
        this.walletDao = walletDao;
    }

    public void setPoolAddress(String poolAddress) {
        PoolAddress = poolAddress;
    }
}
