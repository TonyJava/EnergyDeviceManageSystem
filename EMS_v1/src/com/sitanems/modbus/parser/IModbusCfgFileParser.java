package com.sitanems.modbus.parser;

import java.util.List;

public interface IModbusCfgFileParser {
	List<IModbusMetaItem> parse();
	IModbusMetaItem parseLine(String text);
}
