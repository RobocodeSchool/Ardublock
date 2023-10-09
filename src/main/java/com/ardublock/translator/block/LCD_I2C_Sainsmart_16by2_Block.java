package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class LCD_I2C_Sainsmart_16by2_Block extends TranslatorBlock {
	
	public LCD_I2C_Sainsmart_16by2_Block(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	//@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(1);
		String lineNo = tb.toCode();
		tb = this.getRequiredTranslatorBlockAtSocket(2);
		String charNo = tb.toCode();
		tb = this.getRequiredTranslatorBlockAtSocket(3);
		String I2C_addr = tb.toCode();
		
		String ret = "";
		if ( !(charNo.equals("0") && lineNo.equals("0")) ){
			ret = "lcd_" + I2C_addr + ".setCursor(" + charNo + "-1, " + lineNo + "-1);";
		}
		
		tb = this.getRequiredTranslatorBlockAtSocket(0, "lcd_" + I2C_addr + ".print( ", " );\n");
		ret += tb.toCode();
		//Deal with line and character positioning
		translator.addDefinitionCommand("//Fixed by RoboCode");
		translator.addHeaderFile("Wire.h");
		translator.addHeaderFile("LiquidCrystal_I2C.h");

		//											These are *NOT* Arduino pins. They indicate how the I2C interface is connected to the LCD display
		//																								v  v  v  v  v  v  v  v									
		translator.addDefinitionCommand("LiquidCrystal_I2C lcd_" + I2C_addr + "(0x" + I2C_addr + ", 16, 2);");
		translator.addSetupCommand("lcd_" + I2C_addr + ".begin ();");
		translator.addSetupCommand("lcd_" + I2C_addr + ".backlight();");
		
		return ret;
	}
	
}