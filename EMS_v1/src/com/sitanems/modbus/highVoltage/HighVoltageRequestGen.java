package com.sitanems.modbus.highVoltage;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.msg.*;
import com.sitanems.modbus.parser.ModbusContext;
import com.sitanems.modbus.util.IModbusRequestGen;

public class HighVoltageRequestGen implements IModbusRequestGen{
	private ModbusContext modbusContext;
	public HighVoltageRequestGen(ModbusContext modbusContext)
	{
		this.modbusContext = modbusContext;
	}
	
	public WriteRegisterRequest genWriteRequest(int slaveId, String name, int value)
			throws ModbusTransportException
	{
		WriteRegisterRequest req = null;
		HighVoltageModbusMetaItem item = (HighVoltageModbusMetaItem) modbusContext.getItem(name);

		req = new WriteRegisterRequest(slaveId, item.addr, value);

		
		return req;
	}
	
	public ReadHoldingRegistersRequest genReadRequest(int slaveId, String name)
			throws ModbusTransportException
	{
		ReadHoldingRegistersRequest req = null;
		HighVoltageModbusMetaItem item = (HighVoltageModbusMetaItem) modbusContext.getItem(name);

		req = new ReadHoldingRegistersRequest(slaveId, item.addr + 0x1000, 1);
		
		return req;
	}
	
	private static boolean checkAuthority(String authority, String operate)
	{
		if (authority.contains(operate))
		{
			return true;
		}
		return false;
	}

	@Override
	public WriteRegisterRequest genWriteRequest(int slaveId, String name)
			throws ModbusTransportException {
		// TODO Auto-generated method stub
		return null;
	}

}
