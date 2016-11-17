package com.sitanems.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sitanems.device.commonDevice.ModbusTcpDevice;

public class SimpleTest1 {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		ModbusTcpDevice inPowerDevice = (ModbusTcpDevice) ctx.getBean("inPowerDevice");
		ModbusTcpDevice highVoltageDevice = (ModbusTcpDevice) ctx.getBean("highVoltageDevice");
		if (inPowerDevice != null)
		{
			//inPowerDevice.execute( "485_3站地址(IO通讯板)", "W");
		}
		
		if (highVoltageDevice != null)
		{
			highVoltageDevice.execute( "系统状态", "W", 5);
		}
		
		ctx.close();
	}

}
