import io.github.longfish801.bltxt.BLtxt

try {
	BLtxt bltxt = new BLtxt(new File('src/test/resources/target.txt'))
	assert bltxt.toXml() == new File('src/test/resources/result.xml').getText('UTF-8')
} catch (exc){
	println "Failed to convert: ${exc.message}"
	throw exc
}
