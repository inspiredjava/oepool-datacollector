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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import oepool.datacollector.properties.PropertyLoader;

public class BootStrap {

    @Autowired
    private LoopThread loopThread;
    @Autowired
    private PropertyLoader propertyManager;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml", "hibernate-context.xml");
        BootStrap bootStrap = (BootStrap) applicationContext.getBean("bootStrap");
        bootStrap.start();
    }

    private void start() {
        new Thread(loopThread).start();
    }

    public void setLoopThread(LoopThread loopThread) {
        this.loopThread = loopThread;
    }

    public void setPropertyManager(PropertyLoader propertyManager) {
        this.propertyManager = propertyManager;
    }
}
