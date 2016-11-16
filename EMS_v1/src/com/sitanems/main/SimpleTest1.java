package com.sitanems.main;

import com.sitanems.device.commonDevice.ModbusTcpDevice;
import com.sitanems.device.util.ModbusTcpDeviceFactory;

public class SimpleTest1 {

	public static void main(String[] args) {
		ModbusTcpDevice device = ModbusTcpDeviceFactory.getDevice("src/com/sitanems/modbus/inPower/InPowerModbus.cfg");

		if (device != null)
		{
			device.execute( "485_3’æµÿ÷∑(IOÕ®—∂∞Â)", "W");
		}
	}

}
