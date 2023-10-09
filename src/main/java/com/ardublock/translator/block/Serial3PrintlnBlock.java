package com.ardublock.translator.block;


import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Serial3PrintlnBlock extends TranslatorBlock
{
	public Serial3PrintlnBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		translator.addSetupCommand("Serial3.begin(9600);");
		TranslatorBlock translatorBlock = this.getTranslatorBlockAtSocket(0, "Serial3.println(", ");\n");
		
		String ret = "";
		if (translatorBlock != null) {
			ret = translatorBlock.toCode();
		}
		
		return ret;
	}
}
