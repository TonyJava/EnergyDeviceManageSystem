package com.sitanems.modbus.inPower;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.msg.*;
import com.sitanems.modbus.parser.IModbusMetaItem;
import com.sitanems.modbus.parser.ModbusContext;
import com.sitanems.modbus.util.IModbusRequestGen;

public class InPowerRequestGen implements IModbusRequestGen{
	private ModbusContext modbusContext;
	public InPowerRequestGen(ModbusContext modbusContext)
	{
		this.modbusContext = modbusContext;
	}
	public WriteRegisterRequest genWriteRequest(int slaveId, String name)
			throws ModbusTransportException
	{
		WriteRegisterRequest req = null;
		InPowerModbusMetaItem item = (InPowerModbusMetaItem) modbusContext.getItem(name);
		if (checkAuthority(item.authority, "W"))
		{
			req = new WriteRegisterRequest(slaveId, item.addr, item.defaultValue);
		}
		
		return req;
	}
	
	public WriteRegisterRequest genWriteRequest(int slaveId, String name, int value)
			throws ModbusTransportException
	{
		WriteRegisterRequest req = null;
		InPowerModbusMetaItem item = (InPowerModbusMetaItem) modbusContext.getItem(name);
		if (checkAuthority(item.authority, "W"))
		{
			req = new WriteRegisterRequest(slaveId, item.addr, value);
		}
		
		return req;
	}
	
	public ReadHoldingRegistersRequest genReadRequest(int slaveId, String name)
			throws ModbusTransportException
	{
		ReadHoldingRegistersRequest req = null;
		InPowerModbusMetaItem item = (InPowerModbusMetaItem) modbusContext.getItem(name);
		if (checkAuthority(item.authority, "R"))
		{
			req = new ReadHoldingRegistersRequest(slaveId, item.addr/* + 0x1000*/, 1);
		}
		
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

}
