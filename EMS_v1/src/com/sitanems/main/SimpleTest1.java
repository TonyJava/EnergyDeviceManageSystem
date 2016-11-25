package com.sitanems.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sitanems.appInterface.DataInfo;
import com.sitanems.appInterface.DeviceInfo;
import com.sitanems.device.commonDevice.ModbusTcpDevice;
import com.sitanems.device.util.DeviceManager;
import com.sitanems.springInterface.ContextFactory;

public class SimpleTest1 {

	public static void main(String[] args) {

		List<DeviceInfo> dInfo = new ArrayList<DeviceInfo>();
		DeviceInfo inPower = new DeviceInfo("inPower", "127.0.0.1", 502, 1);
		DeviceInfo highVoltage = new DeviceInfo("highVoltage", "127.0.0.1", 503, 1);
		dInfo.add(inPower);
		dInfo.add(highVoltage);
		
		DeviceManager dManager = new DeviceManager();
		dManager.addDevice(dInfo);
		dManager.initDevice();
		ModbusTcpDevice inPowerDevice = dManager.getDevice(inPower);
		ModbusTcpDevice highVoltageDevice = dManager.getDevice(highVoltage);
		
		inPowerDevice.updateAllVar();
		highVoltageDevice.updateAllVar();
		
		SessionFactory factory = (SessionFactory) ContextFactory.
				getApplicationContext().getBean("sessionFactory");
        Session session = null;  
        try{  
            session = factory.openSession();  
            //开启事务  
            session.beginTransaction();  
              
            DataInfo dataInfo = 
            		new DataInfo(1, new Date(), inPowerDevice.getAllVarValue());
            session.save(dataInfo);  
            //提交事务  
            session.getTransaction().commit();  
              
        }catch(Exception e){  
            e.printStackTrace();  
            //回滚事务  
            session.getTransaction().rollback();  
        }finally{  
            if(session != null){  
                if(session.isOpen()){  
                    //关闭session  
                    session.close();  
                }  
            }  
        }  
	}

}
