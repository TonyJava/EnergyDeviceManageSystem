package com.sitanems.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sitanems.device.commonDevice.ModbusTcpDevice;
import com.sitanems.device.util.ModbusTcpDeviceFactory;

public class SimpleTest1 {

	public static void main(String[] args) {
		//ModbusTcpDevice device = ModbusTcpDeviceFactory.getDevice("src/com/sitanems/modbus/inPower/InPowerModbus.cfg");

		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		ModbusTcpDevice device = (ModbusTcpDevice) ctx.getBean("inPowerDevice");
		
		if (device != null)
		{
			device.execute( "485_3’æµÿ÷∑(IOÕ®—∂∞Â)", "W");
		}
	}

}
