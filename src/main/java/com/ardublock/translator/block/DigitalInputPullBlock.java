package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class DigitalInputPullBlock extends TranslatorBlock
{
	
	public DigitalInputPullBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);

		translator.addSetupCommand("pinMode(" + translatorBlock.toCode() + ", INPUT_PULLUP);");
		String ret = "digitalRead(";
		ret = ret + translatorBlock.toCode();
		ret = ret + ")";
		return codePrefix + ret + codeSuffix;
	} 

}
