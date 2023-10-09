package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;


public class Fingerprint extends TranslatorBlock
{
	public Fingerprint(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException
	{
		translator.registerBodyTranslateFinishCallback(this);


		translator.addDefinitionCommand("int getFingerprintID() {\n" +
				"  int p = finger.getImage();\n" +
				"  if (p != FINGERPRINT_OK)  return -1;\n" +
				"\n" +
				"  p = finger.image2Tz();\n" +
				"  if (p != FINGERPRINT_OK)  return -1;\n" +
				"\n" +
				"  p = finger.fingerFastSearch();\n" +
				"  if (p != FINGERPRINT_OK){\n" +
				"    //Serial.println(\"No such Fingerprint\");\n" +
				"    return 0;\n" +
				"  }\n" +
				"  return finger.fingerID; \n" +
				"}");

		return "getFingerprintID()";
	}

}
