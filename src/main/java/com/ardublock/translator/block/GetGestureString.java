package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class GetGestureString extends TranslatorBlock
{
	public GetGestureString(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
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
		translator.addDefinitionCommand("String getGestureString() {\n" +
				"    int gesture = APDS.readGesture();\n" +
				"    switch(gesture) {\n" +
				"      case 0:\n" +
				"        return \"UP\";\n" +
				"        break;\n" +
				"      case 1:\n" +
				"        return \"DOWN\";\n" +
				"        break;\n" +
				"      case 2:\n" +
				"        return \"LEFT\";\n" +
				"        break;\n" +
				"      case 3:\n" +
				"        return \"RIGHT\";\n" +
				"        break;\n" +
				"      default:\n" +
				"        return \"NONE\"; \n" +
				"        break;       \n" +
				"    }\n" +
				"}\n");

		String ret = "getGestureString()";


		return codePrefix + ret + codeSuffix;
	}
}
