package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class DefineBlock extends TranslatorBlock
{

    public DefineBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
    {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException
    {
        TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);

        String def = "";
        def = "#define " + translatorBlock.toCode() + " ";
        translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
        def = def + translatorBlock.toCode();
        translator.addDefinitionCommand(def);
        return "";
//		}

    }

}
