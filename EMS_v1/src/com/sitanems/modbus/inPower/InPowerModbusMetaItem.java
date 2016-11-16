package com.sitanems.modbus.inPower;

import com.sitanems.modbus.parser.IModbusMetaItem;

public class InPowerModbusMetaItem implements IModbusMetaItem{

	/*���	Modbus��ַ	����	Ȩ��	��������	��λ	ȡֵ��Χ	Ĭ��ֵ*/
	public int sn;
	public int addr;
	public String name;
	public String authority;
	public String dataType;
	public String unit;
	public String valueScope;
	public int defaultValue;
	
	public InPowerModbusMetaItem(int sn, int addr, String name, String authority,
			String dataType, String unit, String valueScope, int defaultValue) {
		super();
		this.sn = sn;
		this.addr = addr;
		this.name = name;
		this.authority = authority;
		this.dataType = dataType;
		this.unit = unit;
		this.valueScope = valueScope;
		this.defaultValue = defaultValue;
	}

	public String getToken() {
		return name;
	}
}
