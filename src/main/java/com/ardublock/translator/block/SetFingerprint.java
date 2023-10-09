package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;


public class SetFingerprint extends TranslatorBlock
{
	public SetFingerprint(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException
	{
		translator.registerBodyTranslateFinishCallback(this);

		translator.addHeaderFile("Adafruit_Fingerprint.h");
		translator.addHeaderFile("SoftwareSerial.h");

		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		String rxPin = tb.toCode();
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		String txPin = tb.toCode();


		translator.addDefinitionCommand("SoftwareSerial " + "mySerial(" + rxPin + ", " + txPin + "); //rx, tx");
		translator.addDefinitionCommand("Adafruit_Fingerprint finger = Adafruit_Fingerprint(&mySerial);");
		translator.addSetupCommand("Serial.begin(9600);");
		translator.addSetupCommand("delay(100);\n" +
				"  Serial.println(\"\\n\\Search for Fingerprint sensor\");\n" +
				"\n" +
				"  finger.begin(57600);\n" +
				"\n" +
				"  if (finger.verifyPassword()) {\n" +
				"    Serial.println(\"Found fingerprint sensor!\");\n" +
				"  } else {\n" +
				"    Serial.println(\"Did not find fingerprint sensor :( Try to reconnect sensor\");\n" +
				"    while (1) {\n" +
				"      delay(1);\n" +
				"    }\n" +
				"  }");


		return "";
	}

}
