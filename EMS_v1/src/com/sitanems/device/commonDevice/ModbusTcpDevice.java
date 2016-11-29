package com.sitanems.device.commonDevice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.ip.tcp.TcpMaster;
import com.serotonin.modbus4j.msg.ExceptionResponse;
import com.serotonin.modbus4j.msg.ModbusRequest;
import com.serotonin.modbus4j.msg.ModbusResponse;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersRequest;
import com.serotonin.modbus4j.msg.ReadResponse;
import com.serotonin.modbus4j.msg.WriteRegistersRequest;
import com.sitanems.modbus.inPower.InPowerRequestGen;
import com.sitanems.modbus.parser.ModbusContext;
import com.sitanems.modbus.util.IModbusRequestGen;
import com.sitanems.modbus.util.IVarInfoGen;

public class ModbusTcpDevice {
	public String ipAddress;
	public int port;
	public int slaveId;
	private TcpMaster tcpMaster;
	ModbusFactory modbusFactory = new ModbusFactory();
	IModbusRequestGen modbusRequestGen;
	IVarInfoGen varInfoGen;
	public Map<String, String> varInfoMap;
	private Map<String, Integer> varValueMap;

	public ModbusTcpDevice(String ipAddress, int port, int slaveId,
			IModbusRequestGen modbusRequestGen, IVarInfoGen varInfoGen) {
		super();
		this.ipAddress = ipAddress;
		this.port = port;
		this.slaveId = slaveId;
		this.modbusRequestGen = modbusRequestGen;
		this.varInfoGen = varInfoGen;
		varInfoMap = varInfoGen.getVarInfo();
		varValueMap = new HashMap<String, Integer>();
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
		initVarMap();
		return true;
	}
	
	public ModbusResponse send(ModbusRequest request)
			throws ModbusTransportException
	{
		ModbusResponse resp = null;
		//if(tcpMaster.isInitialized() == true)
		//{
			resp = tcpMaster.send(request);
		//}
		return resp;
	}
	
	public void write(String name, int value)
	{
		try {
			ModbusRequest request = 
					modbusRequestGen.genWriteRequest(slaveId, name, value);
			
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
		}
	}
	
	public byte[] read(String name) throws ModbusTransportException
	{
		byte[] temp = null;
		ModbusRequest request = null;
		ModbusResponse response =null;
		request = modbusRequestGen.genReadRequest(slaveId, name);
		response = send(request);
		if (response instanceof ReadResponse)
		{
			temp = ((ReadResponse)response).getData();
		}
		return temp;
	}
	
	public void initVarMap()
	{
		varInfoMap = varInfoGen.getVarInfo();
	}
	
	public void updateAllVar()
	{
		Iterator<Entry<String, String>> iter = varInfoMap.entrySet().iterator();
		while(iter.hasNext())
		{
			updateVar(iter.next().getKey());
		}
	}
	
	public void updateVar(String[] vars)
	{
		for (int i = 0; i < vars.length; i++)
		{
			updateVar(vars[i]);
		}
	}
	
	public void updateVar(String var)
	{
		byte[] result = null;
		try {
			result = read(var);
		} catch (ModbusTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result != null)
		{
			varValueMap.put(var, byte2int(result));
		}

	}
	
	public Map<String, Integer> getAllVarValue()
	{
		return varValueMap;
	}
	
	/*public static int byte2int(byte[] res) {   
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000   
		  
		int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // | 表示安位或   
		| ((res[2] << 24) >>> 8) | (res[3] << 24);   
		return targets;   
		return 5;
		}*/
	
	public static int byte2int(byte[] bRefArr) {
	    int iOutcome = 0;
	    byte bLoop;

	    for (int i = 0; i < bRefArr.length; i++) {
	        bLoop = bRefArr[i];
	        iOutcome += (bLoop & 0xFF) << (8 * i);
	    }
	    return iOutcome;
	}
}
