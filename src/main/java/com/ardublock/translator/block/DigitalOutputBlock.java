package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class DigitalOutputBlock extends TranslatorBlock
{

    public DigitalOutputBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
    {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException
    {
        TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);

        translator.addSetupCommand("pinMode(" + translatorBlock.toCode() + ", OUTPUT);");
        String ret = "digitalWrite(";

        ret = ret + translatorBlock.toCode();
        ret = ret + ", ";
        translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
        ret = ret + translatorBlock.toCode();
        ret = ret + ");\n";
        return ret;
//		}

    }

}
