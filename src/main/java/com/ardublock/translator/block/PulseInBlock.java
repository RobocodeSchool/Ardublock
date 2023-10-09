package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class PulseInBlock extends TranslatorBlock
{

    public PulseInBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
    {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException
    {
        TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);

        translator.addSetupCommand("pinMode(" + translatorBlock.toCode() + ", INPUT);");
        String ret = "pulseIn(";

        ret = ret + translatorBlock.toCode();
        ret = ret + ", ";
        translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
        ret = ret + translatorBlock.toCode();
        ret = ret + ", ";
        translatorBlock = this.getRequiredTranslatorBlockAtSocket(2);
        ret = ret + translatorBlock.toCode();
        ret = ret + ")";
        return ret;
//		}
    }
}
