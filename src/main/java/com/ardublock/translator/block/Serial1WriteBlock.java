package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Serial1WriteBlock extends TranslatorBlock
{
	public Serial1WriteBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		translator.addSetupCommand("Serial1.begin(9600);");
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		
		String ret = translatorBlock.toCode();
		
		
		
		    ret="Serial1.write("+ret+");\n";
		return ret;
	}
}
