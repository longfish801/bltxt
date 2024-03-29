/*
 * BLRootSpec.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */
package io.github.longfish801.bltxt.node

import groovy.util.logging.Slf4j
import spock.lang.Specification

/**
 * BLRootのテスト。
 * @author io.github.longfish801
 */
@Slf4j('LOG')
class BLRootSpec extends Specification {
	def 'getAttrs'(){
		given:
		BLRoot root
		
		when:
		root = new BLRoot()
		then:
		root.attrs == null
	}
	
	def 'toString'(){
		given:
		BLRoot root
		
		when:
		BLBlock block = new BLBlock('タグ', 1)
		block.attrs << '属性'
		BLPara para = new BLPara(1)
		BLLine line = new BLLine(1)
		BLText text = new BLText('テキスト', 1)
		para << line
		line << text
		BLMeta meta = new BLMeta('タグ', 1)
		meta << para
		root = new BLRoot()
		root << block
		root << meta
		then:
		root.toString() == "【＝タグ：属性】\n\n【＃タグ】テキスト"
	}
}
