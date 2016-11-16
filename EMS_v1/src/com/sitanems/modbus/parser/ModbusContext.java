package com.sitanems.modbus.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitanems.modbus.inPower.InPowerModbusMetaItem;

public class ModbusContext {

	private Map<String, IModbusMetaItem> itemMap = null;
	private ModbusCfgFileParser parser = null;
	
	public ModbusContext(ModbusCfgFileParser parser)
	{
		itemMap = new HashMap<String, IModbusMetaItem>();
		load(parser);
	}
		
	private void load(ModbusCfgFileParser parser)
	{
		List<IModbusMetaItem> list = parser.parse();
		for (int i = 0; i < list.size(); i++)
		{
			IModbusMetaItem item = list.get(i);
			itemMap.put(item.getToken(), item);
		}
	}
	
	public IModbusMetaItem getItem(String token)
	{
		return itemMap.get(token);
	}
}
