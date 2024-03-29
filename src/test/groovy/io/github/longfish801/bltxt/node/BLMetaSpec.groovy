/*
 * BLMetaSpec.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */
package io.github.longfish801.bltxt.node

import groovy.util.logging.Slf4j
import spock.lang.Specification

/**
 * BLMetaのテスト。
 * @author io.github.longfish801
 */
@Slf4j('LOG')
class BLMetaSpec extends Specification {
	def 'toString'(){
		given:
		BLMeta meta
		BLPara para = new BLPara(1)
		BLLine line = new BLLine(1)
		BLText text = new BLText('テキスト', 1)
		line << text
		para << line
		
		when:
		meta = new BLMeta('タグ', 1)
		meta.attrs << '属性'
		then:
		meta.toString() == '【＃タグ：属性】'
		
		when:
		meta = new BLMeta('タグ', 1)
		meta << para
		then:
		meta.toString() == '【＃タグ】テキスト'
		
		when:
		meta = new BLMeta('タグ', 1)
		meta << para
		meta.attrs << '属性'
		meta << new BLMeta('タグ２', 1)
		then:
		meta.toString() == '''\
			【＊タグ：属性】
			テキスト
			【＃タグ２】
			【タグ＊】'''.stripIndent()
	}
}
