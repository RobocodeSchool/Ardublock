package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;


public class FingerprintDelete extends TranslatorBlock
{
	public FingerprintDelete(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException
	{
		translator.registerBodyTranslateFinishCallback(this);

		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		String id = tb.toCode();

		translator.addDefinitionCommand("int deleteFingerprint(int id) {\n" +
				"  int p = -1;\n" +
				"  \n" +
				"  p = finger.deleteModel(id);\n" +
				"\n" +
				"  if (p == FINGERPRINT_OK) {\n" +
				"    Serial.println(\"Deleted!\");\n" +
				"  } else if (p == FINGERPRINT_PACKETRECIEVEERR) {\n" +
				"    Serial.println(\"Communication error\");\n" +
				"    return p;\n" +
				"  } else if (p == FINGERPRINT_BADLOCATION) {\n" +
				"    Serial.println(\"Could not delete in that location\");\n" +
				"    return p;\n" +
				"  } else if (p == FINGERPRINT_FLASHERR) {\n" +
				"    Serial.println(\"Error writing to flash\");\n" +
				"    return p;\n" +
				"  } else {\n" +
				"    Serial.print(\"Unknown error: 0x\"); Serial.println(p, HEX);\n" +
				"    return p;\n" +
				"  }   \n" +
				"}");

		return "deleteFingerprint(" + id + ");\n";
	}

}
