/*
 * BLAttrs.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */
package io.github.longfish801.bltxt.node

import groovy.util.logging.Slf4j
import groovy.xml.MarkupBuilder

/**
 * BLtxt文書の属性リストです。
 * @author io.github.longfish801
 */
@Slf4j('LOG')
class BLAttrs<E> extends ArrayList {
	/** {@inheritDoc} */
	@Override
	String toString(){
		return (this.size() > 0)? this.collect { "：${escape(it.toString())}" }.join() : ''
	}
	
	/**
	 * bltxt記法上エスケープが必要な文字があればエスケープします。
	 * @param text エスケープ対象文字列
	 * @return エスケープ後の文字列
	 */
	static String escape(String text){
		if (text.indexOf('＿') >= 0) text = text.replaceAll('＿', '＿＿')
		if (text.indexOf('：') >= 0) text = text.replaceAll('：', '＿：')
		return BLText.escape(text)
	}
	
	/**
	 * このインスタンスをXML形式で表現した文字列をMarkupBuilderで生成します。
	 * @param builder MarkupBuilder
	 */
	void writeXml(MarkupBuilder builder){
		if (this.size() > 0) builder.'attrs'(){ this.each { builder.'attr'(it) } }
	}
}
