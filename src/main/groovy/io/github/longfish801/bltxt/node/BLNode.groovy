/*
 * BLNode.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */
package io.github.longfish801.bltxt.node

import groovy.util.logging.Slf4j
import groovy.xml.MarkupBuilder

/**
 * BLtxt文書のノードを表す抽象クラスです。
 * @author io.github.longfish801
 */
@Slf4j('LOG')
abstract class BLNode {
	/** タグ */
	String tag = null
	/** 属性リスト */
	BLAttrs<String> blAttrs = new BLAttrs<String>()
	/** 上位ノード */
	BLNode parent = null
	/** 下位ノードリスト */
	List<BLNode> blNodes = []
	/** 行番号 */
	int lineNo = 0
	/** 通番 */
	int serialNo = 0
	/** 下位要素として可能なクラスのリスト */
	List<Class> lowerClass = []
	
	/**
	 * 属性リストを返します。
	 * @return 属性リスト
	 */
	BLAttrs<String> getAttrs(){
		return blAttrs
	}
	
	/**
	 * 下位ノードリストを返します。
	 * @return 下位ノードリスト
	 */
	List<BLNode> getNodes(){
		return blNodes
	}
	
	/**
	 * 下位ノードを追加します。
	 * @param subNode 下位ノード
	 * @return 本インスタンス
	 */
	BLNode leftShift(BLNode subNode){
		if (validLowerClasses.every { !(it.isInstance(subNode)) }){
			throw new IllegalArgumentException("下位ノードが妥当なクラスではありません。下位ノードのクラス=${subNode.getClass()} 妥当なクラス候補=${validLowerClasses}");
		}
		subNode.parent = this
		nodes << subNode
		return this
	}
	
	/**
	 * 下位ノードとして可能なクラスの候補を返します。
	 * @return 下位ノードとして可能なクラスの候補
	 */
	static List<Class> getValidLowerClasses() {
		return []
	}
	
	/**
	 * 条件を満たすノードをひとつ返します。<br/>
	 * 引数に指定したクロージャで条件を満たすか判定します。<br/>
	 * このクロージャには引数として各ノードを渡します。<br/>
	 * 条件を満たすなら trueを、そうでなければ falseを返してください。<br/>
	 * まず自ノードが条件を満たすか試します。<br/>
	 * もし満たさなければ下位のノードを再帰的に試します。
	 * @param clos 条件を満たすか判定するためのクロージャ
	 * @return 条件を満たすノード（条件を満たすノードがなければnull）
	 */
	BLNode find(Closure clos){
		if (clos.call(this)) return this
		for (node in blNodes){
			def finded = node.find(clos)
			if (finded != null) return finded
		}
		return null
	}
	
	/**
	 * 条件を満たすノードをすべて返します。<br/>
	 * 引数に指定したクロージャで条件を満たすか判定します。<br/>
	 * このクロージャには引数として各ノードを渡します。<br/>
	 * 条件を満たすなら trueを、そうでなければ falseを返してください。<br/>
	 * まず自ノードが条件を満たすか試します。<br/>
	 * もし満たさなければ下位のノードを再帰的に試します。
	 * @param clos 条件を満たすか判定するためのクロージャ
	 * @return 条件を満たすノードのリスト（条件を満たすノードがなければ空リスト）
	 */
	List<BLNode> findAll(Closure clos){
		List nodes = []
		if (clos.call(this)) nodes << this
		blNodes.each { nodes.addAll(it.findAll(clos)) }
		return nodes
	}
	
	/**
	 * XML形式で表現した文字列を返します。
	 * @return XML形式で表現した文字列
	 */
	String toXml(){
		StringWriter writer = new StringWriter()
		MarkupBuilder builder = new MarkupBuilder(writer)
		builder.doubleQuotes = true
		this.writeXml(builder)
		return writer.toString()
	}
	
	/**
	 * このインスタンスをXML形式で表現した文字列をMarkupBuilderで生成します。
	 * @param builder MarkupBuilder
	 */
	void writeXml(MarkupBuilder builder){
		Map attributes = [:]
		if (tag != null && !tag.empty) attributes['tag'] = tag
		if (lineNo > 0) attributes['lnum'] = lineNo
		if (serialNo > 0) attributes['snum'] = serialNo
		builder."${xmlTag}"(attributes){
			if (attrs != null) attrs.writeXml(builder)
			if (nodes != null && nodes.size() > 0){
				nodes.each { BLNode node -> node.writeXml(builder) }
			}
		}
	}
	
	/**
	 * XMLとしてのタグ名を返します。
	 * @return XMLとしてのタグ名
	 */
	static String getXmlTag(){
		return ''
	}
}
