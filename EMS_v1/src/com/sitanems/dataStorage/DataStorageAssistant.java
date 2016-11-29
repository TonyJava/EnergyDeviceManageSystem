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
            //��������  
            session.beginTransaction();  
              
            session.save(dataEvent.getEvent());  
            //�ύ����  
            session.getTransaction().commit();  
              
        }catch(Exception e){  
            e.printStackTrace();  
            //�ع�����  
            session.getTransaction().rollback();  
        }finally{  
            if(session != null){  
                if(session.isOpen()){  
                    //�ر�session  
                    session.close();  
                }  
            }  
        }  
	}

}
