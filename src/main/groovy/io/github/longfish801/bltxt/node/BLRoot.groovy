/*
 * BLRoot.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */
package io.github.longfish801.bltxt.node

import groovy.util.logging.Slf4j

/**
 * BLtxt文書のルート要素です。
 * @version 0.3.00 2021/12/29
 * @author io.github.longfish801
 */
@Slf4j('LOG')
class BLRoot extends BLNode {
	/** XMLとしてのタグ名 */
	static String xmlTag = 'bltxt'
	/** 下位要素として可能なクラスの候補 */
	static validLowerClasses = [ BLBlock.class, BLPara.class, BLMeta.class ]
	/** 索引 */
	Map<String, Map<String, List<BLNode>>> index = null
	
	/**
	 * 本クラスは属性リストを利用しないため、nullを返します。
	 * @return null
	 */
	@Override
	BLAttrs<String> getAttrs(){
		return null
	}
	
	/** {@inheritDoc} */
	@Override
	String toString(){
		return nodes.collect { it.toString() }.join("\n\n")
	}
	
	/**
	 * 現在のルート要素に基づき索引を作成します。<br>
	 * 索引とは、タグ名別の各要素のリストを、各ノードの XMLタグ別に保持するものです。
	 * 索引の作成時に、タグが nullではないノードに通番をふります。
	 * @return XML形式で表現した文字列
	 */
	void refreshIndex(){
		LOG.trace('BGN create index')
		index = [:]
		Closure createIndex
		createIndex = { BLNode node ->
			if (node.tag != null){
				LOG.trace('index：{} {}', node.xmlTag, node.tag)
				Map<String, List<BLNode>> nodeMap = (index.containsKey(node.xmlTag))? index[node.xmlTag] : [:]
				List<BLNode> nodeList = (nodeMap.containsKey(node.tag))? nodeMap[node.tag] : []
				nodeList << node
				node.serialNo = nodeList.size()
				nodeMap[node.tag] = nodeList
				index[node.xmlTag] = nodeMap
			}
			node.nodes.each { createIndex(it) }
		}
		nodes.each { createIndex(it) }
		LOG.trace('EBD create index')
	}
}
