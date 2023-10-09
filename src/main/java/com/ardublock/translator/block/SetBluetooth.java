package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;


public class SetBluetooth extends TranslatorBlock
{
	public SetBluetooth(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException
	{
		translator.registerBodyTranslateFinishCallback(this);
		
		translator.addHeaderFile("SoftwareSerial.h");

		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		String rxPin = tb.toCode();
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		String txPin = tb.toCode();


		translator.addDefinitionCommand("SoftwareSerial " + "bt(" + rxPin + ", " + txPin + "); //rx, tx");
		translator.addSetupCommand("bt.begin(9600);");


		return "";
	}

}
