/*
 * BLMeta.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */
package io.github.longfish801.bltxt.node

import groovy.util.logging.Slf4j

/**
 * BLtxt文書のメタ要素です。
 * @author io.github.longfish801
 */
@Slf4j('LOG')
class BLMeta extends BLBlock {
	/** XMLとしてのタグ名 */
	static String xmlTag = 'meta'
	/** 下位要素として可能なクラスの候補 */
	static validLowerClasses = [ BLBlock.class, BLPara.class ]
	
	/**
	 * コンストラクタ。
	 * @param tag タグ
	 * @param lineNo 行番号
	 */
	BLMeta(String tag, int lineNo){
		super(tag, lineNo)
	}
	
	/** {@inheritDoc} */
	@Override
	String toString(){
		String result = null
		String cont = nodes.collect { it.toString() }.join("\n")
		if (isSingle()){
			result = "【＃${tag}${attrs.toString()}】${cont}"
		} else {
			result = "【＊${tag}${attrs.toString()}】\n${cont}\n【${tag}＊】"
		}
		return result
	}
}
