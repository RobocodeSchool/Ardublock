package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class string_equals extends TranslatorBlock
{
	public string_equals(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode()  throws SocketNullException, SubroutineNotDeclaredException
	{
		String first;
		String second;
		
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		first = translatorBlock.toCode();
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		second = translatorBlock.toCode();
		String internalVariableName = translator.getNumberVariable(label);

		String ret= first + ".equals("+second+")";
		return codePrefix + ret + codeSuffix;
	}

}
