/*
 * BLNodeSpec.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */
package io.github.longfish801.bltxt.node

import groovy.util.logging.Slf4j
import spock.lang.Shared
import spock.lang.Specification

/**
 * BLNodeのテスト。
 * @author io.github.longfish801
 */
@Slf4j('LOG')
class BLNodeSpec extends Specification {
	@Shared BLRoot rootNode
	
	def setup() {
		rootNode = new BLRoot()
		BLPara para = new BLPara(1)
		BLLine line1 = new BLLine(1)
		BLLine line2 = new BLLine(2)
		BLText text1 = new BLText('テキスト1', 1)
		BLText text2 = new BLText('テキスト2', 2)
		rootNode << para
		para << line1
		para << line2
		line1 << text1
		line2 << text2
	}
	
	def 'getRoot'(){
		given:
		BLNode node
		
		expect:
		rootNode.root == rootNode
		
		when:
		node = rootNode.find { it.xmlTag == 'text' && it.text == 'テキスト1' }
		then:
		node.root == rootNode
		
		when:
		node = rootNode.find { it.xmlTag == 'line' && it.lineNo == 1 }
		then:
		node.root == rootNode
	}
	
	def 'find'(){
		given:
		BLNode node
		
		when:
		node = rootNode.find { it.xmlTag == 'text' }
		then:
		node.text == 'テキスト1'
		
		when:
		node = rootNode.find { it.xmlTag == 'nosuch' }
		then:
		node == null
	}
	
	def 'findAll'(){
		given:
		List nodes
		
		when:
		nodes = rootNode.findAll { it.xmlTag == 'text' }
		then:
		nodes.collect { it.text } == ['テキスト1', 'テキスト2']
		
		when:
		nodes = rootNode.findAll { it.xmlTag == 'nosuch' }
		then:
		nodes == []
	}
}
