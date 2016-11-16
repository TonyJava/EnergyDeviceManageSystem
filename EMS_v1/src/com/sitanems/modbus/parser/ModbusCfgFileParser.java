package com.sitanems.modbus.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sitanems.modbus.inPower.InPowerModbusMetaItem;

public abstract class ModbusCfgFileParser implements IModbusCfgFileParser{
	private String file;
	public ModbusCfgFileParser(String file)
	{
		this.file = file;
	}
	public List<IModbusMetaItem> parse()
	{
		List<IModbusMetaItem> result = new ArrayList<IModbusMetaItem>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String data = br.readLine();//һ�ζ���һ�У�ֱ������nullΪ�ļ�����  
			while( data!=null){ 
				  if (parseLine(data) != null)
				  {
					  result.add(parseLine(data));  
				  }
			      data = br.readLine(); //���Ŷ���һ��  
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return result;
	}

	public IModbusMetaItem parseLine(String text) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
