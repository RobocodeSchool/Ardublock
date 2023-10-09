package com.ardublock.translator.block.Duinoedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Temp extends TranslatorBlock {
	public Temp(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		String Pin;
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		Pin = translatorBlock.toCode();


		translator.addHeaderFile("DHT.h");

		translator.addDefinitionCommand("\nDHT dht_pin_"+Pin+" ("+Pin +", DHT11);"	);
		translator.addSetupCommand("dht_pin_"+Pin+".begin();");
		String ret = "dht_pin_"+Pin+".readTemperature()";


		return codePrefix + ret + codeSuffix;
	}









}
