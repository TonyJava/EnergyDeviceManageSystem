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
			String data = br.readLine();//一次读入一行，直到读入null为文件结束  
			while( data!=null){ 
				  if (parseLine(data) != null)
				  {
					  result.add(parseLine(data));  
				  }
			      data = br.readLine(); //接着读下一行  
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
