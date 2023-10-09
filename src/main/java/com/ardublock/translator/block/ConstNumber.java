package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class ConstNumber extends TranslatorBlock
{

	public ConstNumber(Long blockId, Translator translator,
                       String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		String ret = "const int ";
		TranslatorBlock translatorBlock = getTranslatorBlockAtSocket(0);
		ret = ret + translatorBlock.label;
		translatorBlock = getTranslatorBlockAtSocket(1);
		ret = ret + " = " + translatorBlock.toCode() + ";";
		translator.addDefinitionCommand(ret);
		return "";
	}
}
