package com.sitanems.modbus.highVoltage;

import com.sitanems.modbus.parser.IModbusMetaItem;
import com.sitanems.modbus.parser.ModbusCfgFileParser;

public class HighVoltageCfgFileParser extends ModbusCfgFileParser {

	public HighVoltageCfgFileParser(String file) {
		super(file);
	}

	public IModbusMetaItem parseLine(String text)
	{
		//寄存器地址		名称		详细说明	 	字节数		 数据类型		 备注
		HighVoltageModbusMetaItem item = null;
		if (text != null && text.isEmpty()==false &&text.charAt(0)!='#')
		{
			String[] temp = text.split("\\s+");
			if (temp == null || (temp.length != 5 && temp.length != 6))
			{
				return item;
			}
			
			if (temp.length == 5)
			{
				int addr = parseInt(temp[0]);
				
				item = new HighVoltageModbusMetaItem(addr, temp[1], 
						temp[2], temp[3], temp[4]);
			}
			
			if (temp.length == 6)
			{
				int addr = parseInt(temp[0]);
				
				item = new HighVoltageModbusMetaItem(addr, temp[1], 
						temp[2], temp[3], temp[4], temp[5]);
			}
				
		}

		return item;
	}
	
	private static int parseInt(String str)
	{
		int value;
		if (str.startsWith("0x"))
		{
			str = str.substring(2);
			value = Integer.parseInt(str, 16);
		}
		else
		{
			value = Integer.parseInt(str);
		}
		return value;
	}
	
}
