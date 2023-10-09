package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class GetGestureInt extends TranslatorBlock
{
	public GetGestureInt(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		translator.addHeaderFile("Arduino_APDS9960.h");
		translator.addSetupCommand("if (!APDS.begin()) {\n" +
				"    Serial.println(\"Error initializing APDS9960 sensor.\");\n" +
				"}");


		String ret = "APDS.readGesture()";


		return codePrefix + ret + codeSuffix;
	}
}
