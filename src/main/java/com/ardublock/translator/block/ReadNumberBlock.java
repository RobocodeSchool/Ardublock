package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class ReadNumberBlock extends TranslatorBlock
{
	public ReadNumberBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{

		translator.addDefinitionCommand("int readNumber() {\n" +
				"  int num = 0;\n" +
				"  while (num == 0) {\n" +
				"    while (! Serial.available());\n" +
				"    num = Serial.parseInt();\n" +
				"  }\n" +
				"  return num;\n" +
				"}"	);
		
		String ret = "readNumber()";
		
		return codePrefix+ret+codeSuffix;
	}
}
