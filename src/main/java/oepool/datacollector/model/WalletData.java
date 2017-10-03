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

package oepool.datacollector.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "wallet")
public class WalletData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "wallet")
    private String wallet;
    @Column(name = "record_timestamp")
    private long record_timestamp;
    @Column(name = "current_hashrate")
    private Long currentHashrate;
    @Column(name = "hashrate")
    private Long hashrate;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "worker_to_wallet",
            joinColumns = {@JoinColumn(name = "wallet_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "worker_id", referencedColumnName = "id", unique = true)})
    private List<Worker> workers;
    @Column(name = "workers_offline")
    private Integer workersOffline;
    @Column(name = "workers_online")
    private Integer workersOnline;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public long getRecord_timestamp() {
        return record_timestamp;
    }

    public void setRecord_timestamp(long record_timestamp) {
        this.record_timestamp = record_timestamp;
    }

    public Long getCurrentHashrate() {
        return currentHashrate;
    }

    public void setCurrentHashrate(Long currentHashrate) {
        this.currentHashrate = currentHashrate;
    }

    public Long getHashrate() {
        return hashrate;
    }

    public void setHashrate(Long hashrate) {
        this.hashrate = hashrate;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public Integer getWorkersOffline() {
        return workersOffline;
    }

    public void setWorkersOffline(Integer workersOffline) {
        this.workersOffline = workersOffline;
    }

    public Integer getWorkersOnline() {
        return workersOnline;
    }

    public void setWorkersOnline(Integer workersOnline) {
        this.workersOnline = workersOnline;
    }

    @Override
    public String toString() {
        return "WalletData{" +
                "wallet='" + wallet + '\'' +
                ", record_timestamp=" + record_timestamp +
                ", currentHashrate=" + currentHashrate +
                ", hashrate=" + hashrate +
                ", workers=" + workers +
                ", workersOffline=" + workersOffline +
                ", workersOnline=" + workersOnline +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WalletData that = (WalletData) o;

        if (record_timestamp != that.record_timestamp) return false;
        if (!wallet.equals(that.wallet)) return false;
        if (!currentHashrate.equals(that.currentHashrate)) return false;
        if (!hashrate.equals(that.hashrate)) return false;
        if (!workers.equals(that.workers)) return false;
        if (!workersOffline.equals(that.workersOffline)) return false;
        return workersOnline.equals(that.workersOnline);
    }

    @Override
    public int hashCode() {
        int result = wallet.hashCode();
        result = 31 * result + (int) (record_timestamp ^ (record_timestamp >>> 32));
        result = 31 * result + currentHashrate.hashCode();
        result = 31 * result + hashrate.hashCode();
        result = 31 * result + workers.hashCode();
        result = 31 * result + workersOffline.hashCode();
        result = 31 * result + workersOnline.hashCode();
        return result;
    }
}
