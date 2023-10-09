package com.ardublock.translator.block;


import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class BluetoothPrintln extends TranslatorBlock
{
	public BluetoothPrintln(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		TranslatorBlock translatorBlock = this.getTranslatorBlockAtSocket(0, "bt.print(", ");\n");
		
		String ret = "";
		if (translatorBlock != null) {
			ret = translatorBlock.toCode();
		}
		ret = ret + "bt.println();\n";
		
		return ret;
	}
}
