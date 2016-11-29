package com.sitanems.dataStorage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.sitanems.dataAcquire.AcquireDataEvent;
import com.sitanems.event.util.IEvent;
import com.sitanems.event.util.IEventListener;
import com.sitanems.springInterface.ContextFactory;

public class DataStorageAssistant implements IEventListener{

	@Override
	public void handleData(IEvent event) {
		// TODO Auto-generated method stub
		AcquireDataEvent dataEvent = (AcquireDataEvent) event;
		SessionFactory factory = (SessionFactory) ContextFactory.
				getApplicationContext().getBean("sessionFactory");
        Session session = null;  
        try{  
            session = factory.openSession();  
            //开启事务  
            session.beginTransaction();  
              
            session.save(dataEvent.getEvent());  
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
