package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class ApdsColor extends TranslatorBlock
{
	public ApdsColor(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);

		translator.addHeaderFile("Arduino_APDS9960.h");
		translator.addSetupCommand("if (!APDS.begin()) {\n" +
				"    Serial.println(\"Error initializing APDS9960 sensor.\");\n" +
				"}");

		String ret = "APDS.readColor(";
		ret = ret + translatorBlock.toCode() + ", ";
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		ret = ret + translatorBlock.toCode() + ", ";
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(2);
		ret = ret + translatorBlock.toCode() + ");";
		return codePrefix + ret + codeSuffix;

	}
}
