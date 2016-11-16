package com.sitanems.modbus.util;

import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersRequest;
import com.serotonin.modbus4j.msg.WriteRegisterRequest;

public interface IModbusRequestGen {
	WriteRegisterRequest genWriteRequest(int slaveId, String name) throws ModbusTransportException;
	WriteRegisterRequest genWriteRequest(int slaveId, String name, int value) throws ModbusTransportException;
	ReadHoldingRegistersRequest genReadRequest(int slaveId, String name) throws ModbusTransportException;
}
