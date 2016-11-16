package com.sitanems.modbus.inPower;

import com.sitanems.modbus.parser.IModbusMetaItem;
import com.sitanems.modbus.parser.ModbusCfgFileParser;

public class InPowerCfgFileParser extends ModbusCfgFileParser {

	public InPowerCfgFileParser(String file) {
		super(file);
	}

	public IModbusMetaItem parseLine(String text)
	{
		//���	    Modbus��ַ	����	    Ȩ��	��������	             ��λ	ȡֵ��Χ	Ĭ��ֵ
		InPowerModbusMetaItem item = null;
		if (text != null && text.isEmpty()==false &&text.charAt(0)!='#')
		{
			String[] temp = text.split("\\s+");
			if (temp == null || temp.length != 8)
			{
				return item;
			}
			int sn = parseInt(temp[0]);
			int addr = parseInt(temp[1]);
			String authority = null;
			if ("��".equals(temp[3]))
			{
				authority = "R";
			}
			else if ("д".equals(temp[3]))
			{
				authority = "W";
			}
			else if ("��д".equals(temp[3]))
			{
				authority = "RW";
			}
			int defaultValue = parseInt(temp[7]);
			
			item = new InPowerModbusMetaItem(sn, addr, temp[2], authority, 
					temp[4], temp[5], temp[6], defaultValue);	
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
