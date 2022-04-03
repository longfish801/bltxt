/*
 * BLPara.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */
package io.github.longfish801.bltxt.node

import groovy.util.logging.Slf4j

/**
 * BLtxt文書の段落要素です。
 * @author io.github.longfish801
 */
@Slf4j('LOG')
class BLPara extends BLBlock {
	/** XMLとしてのタグ名 */
	static String xmlTag = 'para'
	/** 下位要素として可能なクラスの候補 */
	static validLowerClasses = [ BLLine.class ];
	
	/**
	 * コンストラクタ。<br>
	 * タグは空文字を設定します。
	 * @param lineNo 行番号
	 */
	BLPara(int lineNo){
		this.tag = '';
		this.lineNo = lineNo;
	}
	
	/**
	 * 本クラスは属性リストを利用しないため、nullを返します。
	 * @return null
	 */
	@Override
	BLAttrs<String> getAttrs(){
		return null;
	}
	
	/** {@inheritDoc} */
	@Override
	String toString(){
		return nodes.collect { it.toString() }.join("\n");
	}
}
