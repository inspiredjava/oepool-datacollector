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

package oepool.datacollector.dao.json;

import com.fasterxml.jackson.core.*;
import oepool.datacollector.model.WalletData;
import oepool.datacollector.model.Worker;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class WalletJsonParser implements oepool.datacollector.dao.JsonParser {

    private WalletData parseUrl(String path, WalletData walletData) throws IOException {
        URL url = new URL(path);
        JsonFactory jsonFactory = new JsonFactory();

        com.fasterxml.jackson.core.JsonParser jsonParser = jsonFactory.createParser(url);
        jsonParser.nextToken();
        while (jsonParser.nextToken() == JsonToken.FIELD_NAME) {
            String fieldName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if ("currentHashrate".equals(fieldName)) {
                walletData.setCurrentHashrate(jsonParser.getLongValue());
            }
            if ("hashrate".equals(fieldName)) {
                walletData.setHashrate(jsonParser.getLongValue());
            }
        }
        // skip all until workers
        do {
            jsonParser.nextToken();
        } while (!"workers".equals(jsonParser.getCurrentName()));

        jsonParser.nextToken();
        List<Worker> workerList = new ArrayList<>();
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            Worker worker = new Worker();
            worker.setName(jsonParser.getCurrentName());
            jsonParser.nextToken();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String workerParam = jsonParser.getCurrentName();
                jsonParser.nextToken();
                if ("lastBeat".equals(workerParam)) {
                    worker.setLastBeat(jsonParser.getLongValue());
                }
                if ("hr".equals(workerParam)) {
                    worker.setHr(jsonParser.getLongValue());
                }
                if ("offline".equals(workerParam)) {
                    worker.setOffline(jsonParser.getBooleanValue());
                }
                if ("hr2".equals(workerParam)) {
                    worker.setHr2(jsonParser.getLongValue());
                }
            }
            workerList.add(worker);
        }
        walletData.setWorkers(workerList);

        while (jsonParser.nextToken() == JsonToken.FIELD_NAME) {
            String fieldName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if ("workersOffline".equals(fieldName)) {
                walletData.setWorkersOffline(jsonParser.getIntValue());
            }
            if ("workersOnline".equals(fieldName)) {
                walletData.setWorkersOnline(jsonParser.getIntValue());
            }
        }
        return walletData;
    }

    @Override
    public WalletData getData(String path) throws IOException {
        WalletData walletData = new WalletData();
        walletData.setRecord_timestamp(new Date().getTime());
        int index = path.indexOf("/0x");
        if (index != -1) {
            walletData.setWallet(path.substring(index + 1));
        } else {
            throw new IOException("No wallet address was found");
        }
        return parseUrl(path, walletData);
    }
}
