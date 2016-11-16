package com.sitanems.device.util;

import com.sitanems.device.commonDevice.ModbusTcpDevice;
import com.sitanems.modbus.inPower.InPowerCfgFileParser;
import com.sitanems.modbus.inPower.InPowerRequestGen;
import com.sitanems.modbus.parser.ModbusContext;

public class ModbusTcpDeviceFactory {
	public static ModbusTcpDevice getDevice(String cfgFile)
	{
		ModbusTcpDevice device = null;
		ModbusContext context = 
				new ModbusContext(new InPowerCfgFileParser(cfgFile));
		InPowerRequestGen reqGen = new InPowerRequestGen(context);
		device = new ModbusTcpDevice("127.0.0.1", 502, 1, reqGen);
		if (device.init() == true)
		{
			System.out.println("设备初始化成功！");
		}
		else
		{
			device= null;
			System.out.println("设备初始化失败！");
		}
		return device;
	}
}
