package com.sitanems.modbus.highVoltage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitanems.modbus.parser.IModbusMetaItem;
import com.sitanems.modbus.parser.ModbusContext;
import com.sitanems.modbus.util.IVarInfoGen;

public class HighVoltageVarInfoGen implements IVarInfoGen {
	private ModbusContext modbusContext;
	public HighVoltageVarInfoGen(ModbusContext modbusContext)
	{
		this.modbusContext = modbusContext;
	}
	public Map<String, String> getVarInfo() {
		Map<String, String> result = new HashMap<String, String>();
		Map<String, IModbusMetaItem> itemMap = modbusContext.itemMap;
		List<IModbusMetaItem> tempList = new ArrayList<IModbusMetaItem>(itemMap.values());
		HighVoltageModbusMetaItem item = null;
		for (int i = 0; i < tempList.size(); i++)
		{
			item = (HighVoltageModbusMetaItem)tempList.get(i);
			result.put(item.name, item.dataType);
		}
		return result;
	}
}
