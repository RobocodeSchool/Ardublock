package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;


public class FingerprintEnroll extends TranslatorBlock
{
	public FingerprintEnroll(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException
	{
		translator.registerBodyTranslateFinishCallback(this);

		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		String id = tb.toCode();

		translator.addDefinitionCommand("int getFingerprintEnroll(int id) {\n" +
				"\n" +
				"  int p = -1;\n" +
				"  Serial.print(\"Waiting for valid finger to enroll as #\"); Serial.println(id);\n" +
				"  while (p != FINGERPRINT_OK) {\n" +
				"    p = finger.getImage();\n" +
				"    switch (p) {\n" +
				"      case FINGERPRINT_OK:\n" +
				"        Serial.println(\"Image taken\");\n" +
				"        break;\n" +
				"      case FINGERPRINT_NOFINGER:\n" +
				"        //Serial.println(\".\");\n" +
				"        break;\n" +
				"      case FINGERPRINT_PACKETRECIEVEERR:\n" +
				"        Serial.println(\"Communication error\");\n" +
				"        break;\n" +
				"      case FINGERPRINT_IMAGEFAIL:\n" +
				"        Serial.println(\"Imaging error\");\n" +
				"        break;\n" +
				"      default:\n" +
				"        Serial.println(\"Unknown error\");\n" +
				"        break;\n" +
				"    }\n" +
				"  }\n" +
				"\n" +
				"  // OK success!\n" +
				"\n" +
				"  p = finger.image2Tz(1);\n" +
				"  switch (p) {\n" +
				"    case FINGERPRINT_OK:\n" +
				"      Serial.println(\"Image converted\");\n" +
				"      break;\n" +
				"    case FINGERPRINT_IMAGEMESS:\n" +
				"      Serial.println(\"Image too messy\");\n" +
				"      return p;\n" +
				"    case FINGERPRINT_PACKETRECIEVEERR:\n" +
				"      Serial.println(\"Communication error\");\n" +
				"      return p;\n" +
				"    case FINGERPRINT_FEATUREFAIL:\n" +
				"      Serial.println(\"Could not find fingerprint features\");\n" +
				"      return p;\n" +
				"    case FINGERPRINT_INVALIDIMAGE:\n" +
				"      Serial.println(\"Could not find fingerprint features\");\n" +
				"      return p;\n" +
				"    default:\n" +
				"      Serial.println(\"Unknown error\");\n" +
				"      return p;\n" +
				"  }\n" +
				"\n" +
				"  Serial.println(\"Remove finger\");\n" +
				"  delay(2000);\n" +
				"  p = 0;\n" +
				"  while (p != FINGERPRINT_NOFINGER) {\n" +
				"    p = finger.getImage();\n" +
				"  }\n" +
				"  Serial.print(\"ID \"); Serial.println(id);\n" +
				"\n" +
				"\n" +
				"m1:\n" +
				"  p = -1;\n" +
				"  Serial.println(\"Place same finger again\");\n" +
				"  while (p != FINGERPRINT_OK) {\n" +
				"    p = finger.getImage();\n" +
				"    switch (p) {\n" +
				"      case FINGERPRINT_OK:\n" +
				"        Serial.println(\"Image taken\");\n" +
				"        break;\n" +
				"      case FINGERPRINT_NOFINGER:\n" +
				"        //Serial.print(\".\");\n" +
				"        break;\n" +
				"      case FINGERPRINT_PACKETRECIEVEERR:\n" +
				"        Serial.println(\"Communication error\");\n" +
				"        break;\n" +
				"      case FINGERPRINT_IMAGEFAIL:\n" +
				"        Serial.println(\"Imaging error\");\n" +
				"        break;\n" +
				"      default:\n" +
				"        Serial.println(\"Unknown error\");\n" +
				"        break;\n" +
				"    }\n" +
				"  }\n" +
				"\n" +
				"  // OK success!\n" +
				"\n" +
				"  p = finger.image2Tz(2);\n" +
				"  switch (p) {\n" +
				"    case FINGERPRINT_OK:\n" +
				"      Serial.println(\"Image converted\");\n" +
				"      break;\n" +
				"    case FINGERPRINT_IMAGEMESS:\n" +
				"      Serial.println(\"Image too messy\");\n" +
				"      return p;\n" +
				"    case FINGERPRINT_PACKETRECIEVEERR:\n" +
				"      Serial.println(\"Communication error\");\n" +
				"      return p;\n" +
				"    case FINGERPRINT_FEATUREFAIL:\n" +
				"      Serial.println(\"Could not find fingerprint features\");\n" +
				"      return p;\n" +
				"    case FINGERPRINT_INVALIDIMAGE:\n" +
				"      Serial.println(\"Could not find fingerprint features\");\n" +
				"      return p;\n" +
				"    default:\n" +
				"      Serial.println(\"Unknown error\");\n" +
				"      return p;\n" +
				"  }\n" +
				"  // OK converted!\n" +
				"\n" +
				"  Serial.print(\"Creating model for #\");  Serial.println(id);\n" +
				"\n" +
				"  p = finger.createModel();\n" +
				"  if (p == FINGERPRINT_OK) {\n" +
				"    Serial.println(\"Prints matched!\");\n" +
				"  } else if (p == FINGERPRINT_PACKETRECIEVEERR) {\n" +
				"    Serial.println(\"Communication error\");\n" +
				"    return p;\n" +
				"  } else if (p == FINGERPRINT_ENROLLMISMATCH) {\n" +
				"    Serial.println(\"Fingerprints did not match\");\n" +
				"    goto m1;\n" +
				"  } else {\n" +
				"    Serial.println(\"Unknown error\");\n" +
				"    return p;\n" +
				"  }\n" +
				"\n" +
				"  Serial.print(\"ID \"); Serial.println(id);\n" +
				"  p = finger.storeModel(id);\n" +
				"  if (p == FINGERPRINT_OK) {\n" +
				"    Serial.println(\"Stored!\");\n" +
				"    return 1;\n" +
				"  } else if (p == FINGERPRINT_PACKETRECIEVEERR) {\n" +
				"    Serial.println(\"Communication error\");\n" +
				"    return p;\n" +
				"  } else if (p == FINGERPRINT_BADLOCATION) {\n" +
				"    Serial.println(\"Could not store in that location\");\n" +
				"    return p;\n" +
				"  } else if (p == FINGERPRINT_FLASHERR) {\n" +
				"    Serial.println(\"Error writing to flash\");\n" +
				"    return p;\n" +
				"  } else {\n" +
				"    Serial.println(\"Unknown error\");\n" +
				"    return p;\n" +
				"  }\n" +
				"}");

		return "while (!getFingerprintEnroll(" + id + "));\n";
	}

}
