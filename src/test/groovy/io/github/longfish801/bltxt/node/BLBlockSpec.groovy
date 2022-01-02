/*
 * BLBlockSpec.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */
package io.github.longfish801.bltxt.node

import groovy.util.logging.Slf4j
import spock.lang.Specification

/**
 * BLBlockのテスト。
 * @version 0.3.00 2021/12/29
 * @author io.github.longfish801
 */
@Slf4j('LOG')
class BLBlockSpec extends Specification {
	def 'isSingle'(){
		given:
		BLBlock block
		
		when:
		block = new BLBlock('タグ', 1)
		then:
		block.single == true
		
		when:
		BLPara para = new BLPara(1)
		para << new BLLine(1)
		block = new BLBlock('タグ', 1)
		block << para
		then:
		block.single == true
		
		when:
		block = new BLBlock('タグ', 1)
		block << new BLBlock('タグ２', 1)
		then:
		block.single == false
	}
	
	def 'toString'(){
		given:
		BLBlock block
		BLPara para = new BLPara(1)
		BLLine line = new BLLine(1)
		BLText text = new BLText('テキスト', 1)
		line << text
		para << line
		
		when:
		block = new BLBlock('タグ', 1)
		block.attrs << '属性'
		then:
		block.toString() == '【＝タグ：属性】'
		
		when:
		block = new BLBlock('タグ', 1)
		block << para
		then:
		block.toString() == '【＝タグ】テキスト'
		
		when:
		block = new BLBlock('タグ', 1)
		block << para
		block.attrs << '属性'
		block << new BLBlock('タグ２', 1)
		then:
		block.toString() == '''\
			【－タグ：属性】
			テキスト
			【＝タグ２】
			【タグ－】'''.stripIndent()
	}
}
