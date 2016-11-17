package com.sitanems.device.commonDevice;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.ip.tcp.TcpMaster;
import com.serotonin.modbus4j.msg.ExceptionResponse;
import com.serotonin.modbus4j.msg.ModbusRequest;
import com.serotonin.modbus4j.msg.ModbusResponse;
import com.sitanems.modbus.inPower.InPowerRequestGen;
import com.sitanems.modbus.parser.ModbusContext;
import com.sitanems.modbus.util.IModbusRequestGen;

public class ModbusTcpDevice {
	public String ipAddress;
	public int port;
	public int slaveId;
	private TcpMaster tcpMaster;
	ModbusFactory modbusFactory = new ModbusFactory();
	IModbusRequestGen modbusRequestGen;
	
	public ModbusTcpDevice(String ipAddress, int port, int slaveId,
			IModbusRequestGen modbusRequestGen) {
		super();
		this.ipAddress = ipAddress;
		this.port = port;
		this.slaveId = slaveId;
		this.modbusRequestGen = modbusRequestGen;
	}
	
	public boolean init()
	{
		IpParameters ipPara = new IpParameters();
		ipPara.setHost(this.ipAddress);
		ipPara.setPort(this.port);
		tcpMaster = (TcpMaster) modbusFactory.createTcpMaster(ipPara, true);
		try {
			tcpMaster.init();
		} catch (ModbusInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public ModbusResponse send(ModbusRequest request)
			throws ModbusTransportException
	{
		ModbusResponse resp = null;
		if(tcpMaster.isInitialized() == true)
		{
			resp = tcpMaster.send(request);
		}
		return resp;
	}
	
	public boolean execute(String name, String operate, int value)
	{
		try {
			ModbusRequest request = null;
			if (operate.equalsIgnoreCase("W"))
			{
				request = modbusRequestGen.genWriteRequest(slaveId, name, value);
			}
			else if (operate.equalsIgnoreCase("R"))
			{
				request = modbusRequestGen.genReadRequest(slaveId, name);
			}
			
			
			ModbusResponse response = send(request);
			if (response instanceof ExceptionResponse == false)
			{
				System.out.println("Success!");
			}
			else
			{
				System.out.println("Failed!");
			}
		} catch (ModbusTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean execute(String name, String operate)
	{
		try {
			ModbusRequest request = null;
			if (operate.equalsIgnoreCase("W"))
			{
				request = modbusRequestGen.genWriteRequest(slaveId, name);
			}
			else if (operate.equalsIgnoreCase("R"))
			{
				request = modbusRequestGen.genReadRequest(slaveId, name);
			}
			
			
			ModbusResponse response = send(request);
			if (response instanceof ExceptionResponse == false)
			{
				System.out.println("Success!");
			}
			else
			{
				System.out.println("Failed!");
			}
		} catch (ModbusTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
