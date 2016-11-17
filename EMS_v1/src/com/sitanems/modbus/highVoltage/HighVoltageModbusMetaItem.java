package com.sitanems.modbus.highVoltage;

import com.sitanems.modbus.parser.IModbusMetaItem;

public class HighVoltageModbusMetaItem implements IModbusMetaItem{

	/*寄存器地址		名称		详细说明	 	字节数		 数据类型		 备注*/
	public int addr;
	public String name;
	public String detail;
	public String byteSize;
	public String dataType;
	public String note;

	public HighVoltageModbusMetaItem(int addr, String name, String detail,
			String byteSize, String dataType, String note) {
		super();
		this.addr = addr;
		this.name = name;
		this.detail = detail;
		this.byteSize = byteSize;
		this.dataType = dataType;
		this.note = note;
	}


	public HighVoltageModbusMetaItem(int addr, String name, String detail,
			String byteSize, String dataType) {
		super();
		this.addr = addr;
		this.name = name;
		this.detail = detail;
		this.byteSize = byteSize;
		this.dataType = dataType;
	}


	public String getToken() {
		return name;
	}
}
